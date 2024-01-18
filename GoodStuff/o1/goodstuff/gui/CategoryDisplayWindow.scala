package o1.goodstuff.gui

import o1.goodstuff.*
import scala.swing.*
import scala.swing.event.*
import o1.gui.*
import o1.gui.swingops.*
import o1.gui.Dialog.*
import javax.swing.table.AbstractTableModel
import java.awt.Color
import scala.collection.mutable.Buffer
import scala.language.adhocExtensions // enable extension of Swing classes

////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it’s not necessary
// that you understand or even look at the code in this file.
// Well, apart from a little typo fix in an early exercise.
//////////////////////////////////////////////////////////////

/** Class `CategoryDisplayWindow` represents windows that display a single category
  * of experiences recorded by a user of the GoodStuff experience diary application.
  * Such a window enables the user to record new experiences in the category and
  * graphically highlights the user’s favorite among those experiences.
  *
  * (Please note that the given version of the application does not support multiple
  * categories. Persistent storage of experiences into files is also unsupported.)
  *
  * **Note to students: In this course, you don’t need to understand how this class works.**
  *
  * @param category  the category of experiences to be displayed in this window */
class CategoryDisplayWindow(val category: Category) extends Frame, TerminatesOnClose, O1WindowDefaults:
  window =>

  // Set the window’s title, background color, and initial location
  this.title = "GoodStuff Diary — Category: " + category.name + " Experiences"
  this.background = Color.white
  this.location = Point(100, 100)

  // The Add button and reactions to it:
  private val addExperienceButton = Button("Add a new " + category.name.toLowerCase)( onAddButtonClick() )
  def onAddButtonClick() =
    val newExperience = requestExperienceInfo()
    category.addExperience(newExperience)
    tableData.updated()

  // The table:
  private val tableData = ExperienceTableData()
  private val tableView = ExperienceTableView()

  // Layout: first the button (above) then the table (below)
  this.contents = new BoxPanel(Orientation.Vertical):
    contents += FlowPanel(addExperienceButton) // the flowpane centers the contents
    contents += ScrollPane(tableView)          // scrollpane needed for table headings
    preferredSize = Dimension(650, 400)

  // The menu bar
  this.menuBar = new MenuBar:
    contents += new Menu("Program"):
      contents += MenuItem(Action("Quit")( window.dispose() ))



  private def requestExperienceInfo() =
    val name   = requestNonEmptyLine("Enter " + this.category.name.toLowerCase + " name:",               "A name is required.",                 RelativeTo(this)).getOrElse("N/A")
    val price  = requestDouble(      "Enter the price per " + this.category.unit + " in euros:", _ >= 0, "A non-negative number is required.",  RelativeTo(this)).getOrElse(0.0)
    val descr  = requestAnyLine(     "Enter your description of " + name + ":",                                                                 RelativeTo(this)).getOrElse("")
    val rating = requestInt(         "Rate the " + this.category.name.toLowerCase + ":",         _ >= 0, "A non-negative integer is required.", RelativeTo(this)).getOrElse(0)
    Experience(name, descr, price, rating)


  private class ExperienceTableData extends AbstractTableModel:
    val columnNames = Seq("Fave?", window.category.name, "Price / " + window.category.unit, "Rating", "Value for money", "Description")
    val getters = Seq[Experience => Any](window.category.favorite.contains(_), _.name, _.price.toString + "€", _.rating.toString, _.valueForMoney.toString, _.description )

    def getRowCount = window.category.allExperiences.size
    def getColumnCount = this.columnNames.size
    override def getColumnName(col: Int) = this.columnNames(col)

    def getValueAt(row: Int, col: Int) = this.getters(col)(window.category.allExperiences(row)).asInstanceOf[AnyRef]
    def updated() = this.fireTableDataChanged()
  end ExperienceTableData


  private class ExperienceTableView extends Table:
    this.showGrid = false
    this.focusable = false
    this.model = window.tableData
    this.peer.getColumnModel.getColumn(0).setPreferredWidth(1)
    this.rowHeight = 30

    object faveRenderer extends Table.AbstractRenderer[Boolean, Label](Label()):
      this.component.preferredSize = Dimension(20, 20)
      def configure(table: Table, isSelected: Boolean, hasFocus: Boolean, isFave: Boolean, row: Int, column: Int) =
        this.component.iconOption = if isFave then CategoryDisplayWindow.FaceIcon else None

    override def rendererComponent(isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int) =
      this.model.getValueAt(row, this.viewToModelColumn(column)) match
        case booleanValue: java.lang.Boolean => this.faveRenderer.componentFor(this, isSelected, hasFocus, booleanValue, row, column)
        case otherwise                       => super.rendererComponent(isSelected, hasFocus, row, column)
  end ExperienceTableView

end CategoryDisplayWindow



/** The companion object of [[CategoryDisplayWindow class `CategoryDisplayWindow`]]. */
object CategoryDisplayWindow:
  /** The face that marks a favorite experience. */
  val FaceIcon = Pic("face.png").scaleTo(30).toIcon

