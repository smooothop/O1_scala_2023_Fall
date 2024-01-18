////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it’s not necessary
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////

package o1.carsim.gui

import scala.swing.*
import scala.swing.event.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.Color
import org.openstreetmap.gui.jmapviewer.interfaces.*
import org.openstreetmap.gui.jmapviewer.*
import MapPanel.*
import scala.annotation.targetName

/** The class `MapPanel` is a Scala wrapper around the `JMapViewer` class of the JMapViewer Java library.
  * It provides a zoomable world map.
  *
  * **NOTE TO STUDENTS: In this course, you don’t need to understand how this class works or can be used.** */
open class MapPanel(val tileSource: TileSource) extends Component, Publisher, TileLoaderListener:

  private val storedTileLoader: TileLoader = null

  override lazy val peer: JMapViewer =
    val javaPanel = new JMapViewer(MemoryTileCache(), 8):
      attribution = new AttributionSupport: // attribution overridden; handled differently
        override def paintAttribution(g: java.awt.Graphics, width: Int, height: Int, topLeft: Coordinate, bottomRight: Coordinate, zoom: Int, observer: java.awt.image.ImageObserver) = ()
    javaPanel.setTileSource(tileSource)
    val mapController = DefaultMapController(javaPanel)
    mapController.setDoubleClickZoomEnabled(false)
    mapController.setMovementMouseButton(MouseEvent.BUTTON1)
    javaPanel
  end peer

  @targetName("add")
  def +=(marker: Marker) =
    this.peer.addMapMarker(marker)

  @targetName("addAll")
  def ++=(markers: Iterable[Marker]) =
    markers.foreach(this.peer.addMapMarker)

  @targetName("remove")
  def -=(marker: Marker) =
    this.peer.removeMapMarker(marker)

  @targetName("removeAll")
  def --=(markers: Iterable[Marker]) =
    markers.foreach(this.peer.removeMapMarker)

  def coordinatesToPixel(latitude: Double, longitude: Double, checkOutside: Boolean) =
    this.peer.getMapPosition(latitude, longitude, checkOutside)

  def pixelToCoordinates(pixel: Point) = this.peer.getPosition(pixel)

  def tileLoader = this.storedTileLoader
  def tileLoader_=(loader: TileLoader) =
    this.peer.setTileLoader(loader)

  def recenter(center: Coordinate, zoomLevel: Int) =
    this.peer.setDisplayPosition(center, zoomLevel)

  def markersVisible = peer.getMapMarkersVisible

  def markersVisible_=(visible: Boolean) =
    this.peer.setMapMarkerVisible(visible)

  def tileLoadingFinished(tile: Tile, success: Boolean) =
    peer.tileLoadingFinished(tile, success)

  def getTileCache = peer.getTileCache

end MapPanel


/** The singleton object `MapPanel` provides a selection of utilities for use
  * with the class of the same name.
  *
  * **NOTE TO STUDENTS: In this course, you don’t need to understand how
  * this singleton object works or can be used.** */
object MapPanel:

  type Marker = MapMarkerCircle

  extension (self: Marker)

    def radius = self.getRadius

    def coordinates = Coordinate(self.getLat, self.getLon)
    def coordinates_=(coordinates: Coordinate) =
      self.setLat(coordinates.getLat)
      self.setLon(coordinates.getLon)

    def visible = self.isVisible
    def visible_=(visibility: Boolean) =
      self.setVisible(visibility)

  end extension

end MapPanel


