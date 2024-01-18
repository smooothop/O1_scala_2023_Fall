////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it’s not necessary
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////

package o1.carsim.gui

import scala.swing.{Action, MenuItem, Point, PopupMenu, Swing}
import scala.swing.event.*
import scala.util.*
import scala.util.control.NonFatal
import scala.collection.mutable.Buffer
import o1.util.nice.number.*
import o1.gui.Dialog.*
import scala.language.adhocExtensions // enable extension of Swing classes

import CarMap.*
import MapPanel.*
import CarSim.Directions
import CarSim.Pos
import Directions.Segment

import scala.concurrent.*
import java.awt.Cursor
import java.awt.dnd.DragSource
import org.openstreetmap.gui.jmapviewer.*
import org.openstreetmap.gui.jmapviewer.interfaces.*

import Ordering.Double.TotalOrdering
import ExecutionContext.Implicits.global

import scala.language.implicitConversions
import CoordinateConversions.given



/** A “car map” is the graphical map shown in a CarSim application window. It may contain
  * any number of cars which are drawn onto the map. A car map listens for mouse commands
  * that signal the creation of cars or the execution of car methods.
  *
  * **NOTE TO STUDENTS: In this course, you don’t need to understand how this class works or can be used.** */
class CarMap(tileSource: TileSource, center: Pos, zoomLevel: Int) extends MapPanel(tileSource):
  thisMap =>

  this.tileLoader = Try(OsmFileCacheTileLoader(this)) getOrElse OsmTileLoader(this)
  this.recenter(center, zoomLevel)
  this.markersVisible = true

  private val carThreads = Buffer[CarThread]()   // Each car has its own thread, which moves a car marker

  // GUI state:

  private var clickedPoint: Option[Point] = None
  private var clickedCar: Option[CarThread] = None
  private var mostRecentCarThread: Option[CarThread] = None
  private var mouseDragStart: Option[Point] = None
  private var mostRecentRoute = Iterable[Marker.Route]()
  private var highlightRoutesEnabled = false

  // Convenience accessors:

  def mostRecentCar = this.mostRecentCarThread

  def mostRecentCar_=(car: Option[CarThread]): Unit =
    this.mostRecentCarThread = car
    this.driveHereItem.enabled = this.mostRecentCarThread.isDefined

  def highlightRoutes = this.highlightRoutesEnabled

  def highlightRoutes_=(enabled: Boolean) =
    this.highlightRoutesEnabled = enabled
    this.displayRouteHighlight(this.mostRecentRoute)


  // Reacting to the mouse:

  this.listenTo(this.mouse.clicks, this.mouse.moves)
  this.reactions += {

    case event: MousePressed =>
      this.stopDragging()
      this.clickedCar = this.carAt(event.point)
      if this.clickedCar.isDefined && event.isRightClick then
        this.startDragging(event.point)

    case event: MouseReleased =>
      val releasePoint = event.point
      def isLongDrag(start: Point, end: Point) = math.hypot(end.x - start.x, end.y - start.y) > 7
      if this.mouseDragStart.exists( isLongDrag(_, releasePoint) ) then
        this.clickedCar.foreach( this.attemptDrive(_, releasePoint) )
        this.stopDragging()
      else if event.isRightClick then
        this.mouseDragStart = None
        this.showPopupMenuAt(releasePoint)

    case MouseMoved(_, point, _) =>
      this.tooltip = this.carAt(point).map( _.marker.toString ).orNull

    case MouseDragged(_, _, _) =>
      if this.mouseDragStart.isDefined then
        thisMap.cursor = DragSource.DefaultLinkDrop

  }


  // The menu that pops up when an empty pixel is right-clicked:

  private val driveHereItem =
    def driveHere(): Unit =
      for point <- clickedPoint; car <- mostRecentCar do
        this.attemptDrive(car, point)
    MenuItem(Action("Drive the most recently used car here")( driveHere() ))
  this.driveHereItem.enabled = false

  private val emptyPixelMenu = new PopupMenu:
    contents += MenuItem(Action("Add a new car here...")( thisMap.clickedPoint.foreach(createNewCar) ))
    contents += driveHereItem

  // The menu that pops up when a car is right-clicked:

  private val carMenu =
    def drive() =
      for
        carDriven <- thisMap.clickedCar
        destination <- requestAnyLine("Destination, please: ", RelativeTo(thisMap)).filter( _.nonEmpty )
      do thisMap.attemptDrive(carDriven, destination)

    def fuel() =
      for
        carFueled <- thisMap.clickedCar
        amountWanted <- requestDouble("Add how many liters of fuel?", _ >= 0, "A non-negative number is required.", RelativeTo(this))
      do
        thisMap.mostRecentCar = Some(carFueled)
        val gainedFuel: Double = carFueled.fuel(amountWanted)
        if gainedFuel == 0.0 && amountWanted > 0.0 then
          display("Could not add any fuel.", RelativeTo(thisMap))
        else if gainedFuel < amountWanted then
          display(s"Could only add $gainedFuel liters.", RelativeTo(thisMap))

    def fillUp() =
      thisMap.clickedCar.foreach( _.fillUp() )
      thisMap.mostRecentCar = thisMap.clickedCar orElse thisMap.mostRecentCar

    import scala.language.adhocExtensions
    new PopupMenu:
      contents += MenuItem(Action("Drive...")( drive() ))
               += MenuItem(Action("Fuel...")( fuel() ))
               += MenuItem(Action("Fill up")( fillUp() ))
      listenTo(this)
      reactions += { case PopupMenuCanceled(_) => thisMap.clickedCar.foreach( _.unpause() ) }

  end carMenu

  // Creating and adding cars:

  private def createNewCar(pixel: Point) =
    val location = this.pixelToCoordinates(pixel)
    for
      consumption <- requestDouble("Enter a fuel consumption rate (liters / 100 km) for the new car:", _ >= 0, "Non-negative number required.", RelativeTo(this))
      tankSize    <- requestDouble("Enter the size of its fuel tank in liters:",                       _ > 0, "Positive number required.", RelativeTo(this))
      fuelInTank  <- requestDouble("Enter amount of fuel in tank, in liters:",                         n => (n >= 0 && n <= tankSize), "A number between zero and the tank size, please.", RelativeTo(this))
    do this.addCar(CarEnhancement(consumption, tankSize, fuelInTank, location))

  def addCar(car: CarEnhancement) =
    val carMarker = CarMarker(this, car)
    this += carMarker
    this += carMarker.destinationMarker
    val carThread = CarThread(carMarker)
    this.carThreads += carThread
    this.mostRecentCar = Some(carThread)
    carThread.start()

  // Various helper methods for using the cars:

  private def showPopupMenuAt(point: Point) =
    this.clickedPoint = Some(point)
    this.clickedCar = carAt(point)
    val menu = if this.clickedCar.isDefined then carMenu else emptyPixelMenu
    for carThread <- this.clickedCar do
      carThread.pause()
    menu.show(this, point.x, point.y)


  private def startDragging(start: Point) =
    this.mouseDragStart = Some(start)
    this.clickedCar.foreach( _.pause() )

  private def stopDragging() =
    this.cursor = Cursor(Cursor.DEFAULT_CURSOR)
    this.mouseDragStart = None
    this.clickedCar.foreach( _.unpause() )


  private def carAt(pixel: Point) =
    def distance(carThread: CarThread) =
      val marker = carThread.marker
      val location = marker.coordinates
      val carPixel = this.coordinatesToPixel(location.x, location.y, false)
      val closeEnough = (pixel.x - carPixel.x).abs <= marker.radius && (pixel.y - carPixel.y).abs <= marker.radius
      if closeEnough then pixel.distance(carPixel) else Double.PositiveInfinity

    val closest = this.carThreads.reverse.minByOption(distance)
    closest.filterNot( car => distance(car) == Double.PositiveInfinity )
  end carAt


  private def attemptDrive(car: CarThread, pixel: Point): Unit =
    val location: Pos = this.pixelToCoordinates(pixel)
    this.attemptDrive(car, toDirectionsFormat(location))


  private def attemptDrive(carThread: CarThread, destination: String) =

    def startDriving(route: IndexedSeq[Segment]) =
      this.mostRecentCar = Some(carThread)
      this.newRouteHighlight(route)
      carThread.startDriving(route)

    Future(carThread.car.findRoute(destination)).onComplete {
      case Success(result)                       => Swing.onEDT( startDriving(result) )
      case Failure(noRoute: DirectionsException) => display(noRoute.message, RelativeTo(this))
      case Failure(NonFatal(otherProblem))       => otherProblem.printStackTrace()
      case Failure(fatalError)                   => throw fatalError
    }

  end attemptDrive


  def newRouteHighlight(route: IndexedSeq[Segment]): Unit =

    val MaxNumberOfMarkers = 20000 // cap the number of segments _that are shown_ to avoid certain performance issues (all segments are nevertheless used in routing)
    lazy val numbersTilMax = (0 until MaxNumberOfMarkers)

    def dropEvenlyTillMax(elements: IndexedSeq[Segment]) =
      val dropRatio = (elements.size.toDouble / MaxNumberOfMarkers) atLeast 1.0
      val includedIndices = numbersTilMax.map( index => (index * dropRatio).toInt )
      val included = Buffer[Segment]()
      for index <- includedIndices do
        included += elements(index)
      included

    val filteredRoute = if route.size > MaxNumberOfMarkers then dropEvenlyTillMax(route) else route
    displayRouteHighlight(filteredRoute.map( segment => Marker.Route(segment.destination) ))

  end newRouteHighlight


  private def displayRouteHighlight(route: Iterable[Marker.Route]) =
    for marker <- this.mostRecentRoute do
      this -= marker
    this.mostRecentRoute = route
    if this.highlightRoutes then
      this ++= this.mostRecentRoute
    // An ugly hack to make car markers appear on top of all other markers:
    val carMarkers = this.carThreads.map( _.marker )
    this --= carMarkers
    this ++= carMarkers

end CarMap


private object CarMap:

  object Marker:
    import java.awt.Color
    class Destination(location: Coordinate) extends Marker(null, null, location, 8, MapMarker.STYLE.FIXED, Style(Color.black, Color(100, 100, 255), null, null))
    class Route(location: Coordinate) extends Marker(null, null, location, 1, MapMarker.STYLE.FIXED, Style(Color.blue, Color.blue, null, null))
  end Marker

  class DirectionsException(val message: String) extends Exception(message)

  extension (event: MouseEvent)
    def isRightClick = event.peer.getButton == 3

  private[gui] def toDirectionsFormat(pos: Pos) = s"${pos.x},${pos.y}"

end CarMap


private[gui] object CoordinateConversions:
  import scala.language.implicitConversions

  given directionsCoordsToMapViewerCoordinate: Conversion[Directions.Coords, Coordinate] =
    coords => Coordinate(coords.lat, coords.lng)

  given posToMapViewerCoordinate: Conversion[Pos, Coordinate] =
    pos => Coordinate(pos.x, pos.y)

  given mapViewerCoordinateToPos: Conversion[Coordinate, Pos] =
    coord => Pos(coord.getLat, coord.getLon)

end CoordinateConversions

