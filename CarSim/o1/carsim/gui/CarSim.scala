
////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it’s not necessary
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////
package o1.carsim.gui

import scala.swing.*
import scala.swing.event.*
import java.awt.{Font, Point, Desktop, Cursor, Dimension}
import java.awt.font.TextAttribute
import java.net.URI
import BorderPanel.Position.*
import o1.gui.{O1AppDefaults, Tooltips}

/** The singleton object `CarSim` represents the CarSim application, which allows its user
  * to simulate simple car behavior (movement, fueling). The object serves as an entry
  * point for the application, and can be run  to start up the user interface.
  *
  * **NOTE TO STUDENTS: In this course, you don’t need to understand how this object works
  * on the inside. It’s enough to know that you can use this file to start the program.** */
object CarSim extends SimpleSwingApplication, O1AppDefaults, Tooltips.Everpresent:
  import scala.language.adhocExtensions // enable extension of Swing classes

  def top = new MainFrame:

    val map = CarMap(Directions.TileSource(), Pos(60.186727, 24.822589), 5)
    map.addCar(CarEnhancement(4.0, 70.0, 15.0, Pos(60.186727, 24.822589)))

    private object content extends BorderPanel:
      val bottomRow = new BorderPanel:
        val attributions = FlowPanel(FlowPanel.Alignment.Right)(Attribution("Maps and routes by HERE.com.", "https://www.here.com/en"),
                                                                Attribution("Uses JMapViewer.", "http://svn.openstreetmap.org/applications/viewer/jmapviewer/"))
        val instructions = Label("   Drag to scroll, wheel to zoom. Right-click to add cars. Right-click or right-drag a car to use it.")
        layout(instructions) = West
        layout(attributions) = East
      end bottomRow

      layout(map) = Center
      layout(bottomRow) = South
      preferredSize = Dimension(1200, 600)
    end content

    private object menus extends MenuBar:
      contents += new Menu("Program") { contents += MenuItem(Action("Quit")( dispose() )) }
      val highlightItem = new CheckMenuItem("Highlight latest route"):
        this.listenTo(this)
        reactions += { case ButtonClicked(_) => map.highlightRoutes = this.peer.getState }
      contents += new Menu("Settings") { contents += highlightItem }
    end menus

    this.title = "CarSim"
    this.location = Point(100, 100)
    this.contents = content
    this.menuBar = menus

  end top


  private class Attribution(text: String, linkText: String) extends Label(text):
    this.font = AttributionFont
    val link = URI(linkText)

    this.listenTo(this.mouse.moves, this.mouse.clicks)

    for
      desktop <- if Desktop.isDesktopSupported then Some(Desktop.getDesktop) else None
    do
      this.reactions += {
        case MouseEntered(_, _, _) =>
          this.font = AttributionLinkFont
          this.cursor = Cursor(Cursor.HAND_CURSOR)
        case MouseExited(_, _, _) =>
          this.font = AttributionFont
          this.cursor = Cursor(Cursor.DEFAULT_CURSOR)
        case MouseClicked(_, _, _, _, _) =>
          desktop.browse(this.link)
      }

  end Attribution

  private val AttributionFont = Font("Arial", Font.PLAIN, 10)
  private val AttributionLinkFont =
    val props = java.util.HashMap[TextAttribute, Integer]()
    props.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)
    AttributionFont.deriveFont(props)

  private[gui] val Directions = HereMaps
  private[gui] type Pos = o1.world.Pos
  private[gui] val  Pos = o1.world.Pos

end CarSim

