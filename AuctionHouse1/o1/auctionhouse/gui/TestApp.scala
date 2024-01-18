////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it’s not necessary
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////

package o1.auctionhouse.gui

import scala.swing.*
import scala.swing.event.*
import FlowPanel.Alignment
import BorderPanel.Position
import scala.language.adhocExtensions
import o1.gui.swingops.*
import o1.gui.layout.*
import o1.gui.Dialog.*
import o1.gui.O1AppDefaults
import o1.util.assignments.*
import o1.util.nice.pairs.*

import java.util.Locale.US
import scala.util.Try
import scala.language.dynamics

/** The singleton object `TestApp` represents a simple application that a programmer can use
  * to experiment with the main functionality of the classes from package `o1.auctionhouse`.
  *
  * **NOTE TO STUDENTS: In this course, you don’t need to understand how this object works
  * on the inside. It’s enough to know that you can use this file to start the program.** */
object TestApp extends SimpleSwingApplication, O1AppDefaults:

  // This class is a bit convoluted as a result of jumping through some hoops in order to allow
  // it to work unmodified whether or not all auction types have been implemented.

  def top = new MainFrame:

    title = """A Simple UI for Playing Around with an "Auction House""""
    location = Point(100, 100)

    val itemTabs = TabbedPane()
    val buttonRow = FlowPanel(Alignment.Center)()
    private val buttonPairings = Seq("Add a fixed-price item"       -> FPS,
                                     "Add a Dutch-style auction"    -> DA,
                                     "Add an English-style auction" -> EA)

    private def makeAddButton(label: String, itemType: ItemType) =
      def makeNewItemTab() = itemType.requestInstanceInPanel().foreach(this.addTab)
      def onClick() = withStudentSolution(owner=buttonRow)( makeNewItemTab() )
      Button(label)( onClick() )
    buttonPairings.map(makeAddButton).foreach( buttonRow.contents += _ )

    listenTo(itemTabs.selection)
    reactions += { case SelectionChanged(_) => changeDefaultItem() }
    private def changeDefaultItem() =
      defaultButton = itemTabs.selection.page.content.asInstanceOf[ItemPanel].defaultButton

    Try(this.addTab(FPS.panelForParams("Example item", 10000, 3)))
    Try(this.addTab(DA.panelForParams("Dutch auction example", 100000, 1000, 70000)))
    Try(this.addTab(EA.panelForParams("English auction example", 50000, 10)))

    contents = new BorderPanel:
      layout(buttonRow) = Position.North
      layout(itemTabs) = Position.Center

    this.resizable = false
    this.pack()


    def addTab(panel: ItemPanel) =
      val itemCount = this.itemTabs.pages.size
      this.itemTabs.pages += TabbedPane.Page("Item #" + (itemCount + 1), panel, panel.tooltipText)
      this.itemTabs.selection.index = itemCount
      panel.requestFocus()
      this.pack()


    class ItemWrapper(actual: AnyRef) extends DynamicObject(actual):
      def saleType = this.actual.getClass.getSimpleName

    abstract class ItemPanel(actualItem: AnyRef) extends EasyPanel:
      val item = ItemWrapper(actualItem)
      val tooltipText = item.description[String]

      def defaultButton: Button

      val open, expired, buyer = Label()
      val title = Label(s"<html><b>${item.description[String]}</b> (${item.saleType})</html>")
      val advanceButton = new Button("Advance one day"):
        def advance() =
          item.applyDynamic[Unit]("advanceOneDay")() // temporarily(?) rephrased because IJ complains unnecessarily about item.advanceOneDay[Unit]()
          refresh()
        listenTo(this)
        reactions += { case ButtonClicked(_) => withStudentSolution(owner = buttonRow)( advance() ) }
      val customerSelector = ComboBox(Seq("Melchior", "Caspar", "Balthazar"))
      // customerSelector.makeEditable()
      val buyAction = FlowPanel(Alignment.Left)(Label("Select customer: "), customerSelector)
      val advanceAction = FlowPanel(Alignment.Left)(advanceButton)

      placeNW(title,         (0, 0), ThreeWide, Slight, (20, 20, 8, 8))
      placeNW(open,          (0, 1), OneSlot,   Slight, (0,  20, 0, 0))
      placeNW(expired,       (1, 1), OneSlot,   Slight, (0,  0,  0, 0))
      placeNW(buyer,         (0, 4), ThreeWide, Slight, (0,  20, 0, 0))
      placeNW(buyAction,     (0, 5), ThreeWide, Slight, (5,  20, 0, 0))
      placeNW(advanceAction, (0, 6), ThreeWide, Slight, (5,  15, 0, 0))

      def refresh() =
        this.open.text    = s"<html>Open: ${if this.item.isOpen[Boolean] then "yes" else "<font color=\"red\">no</font>"}</html>"
        this.buyer.text   = s"<html>Buyer: ${this.item.buyer[Option[String]].map( name => s"""<font color="green">$name</font>""" ) getOrElse "(none)"}</html>"
        this.expired.text = s"<html>Expired: ${if this.item.isExpired[Boolean] then "<font color=\"red\">yes</font>" else "no"}</html>"

    end ItemPanel


    private abstract class IPPanel(actualItem: AnyRef) extends ItemPanel(actualItem):

      val buyButton = new Button("Buy"):
        listenTo(this)
        def buyIt() =
          val result = item.applyDynamic[Boolean]("buy")(classOf[String] -> arg(customerSelector.selection.item)) // temporarily(?) rephrased because IJ complains unnecessarily about val result = item.buy[Boolean](classOf[String] -> arg(customerSelector.selection.item))
          if !result then display("Failed to buy the item.", RelativeTo(this))
          refresh()
        reactions += { case ButtonClicked(_) => withStudentSolution(buttonRow)( buyIt() ) }

      buyAction.contents += buyButton
      def defaultButton = this.buyButton

    end IPPanel


    private sealed abstract class ItemType(className: String, constructorParameters: Class[?]*) extends DynamicClass[AnyRef](className, constructorParameters), RequestArguments[AnyRef]:

      def requestInstanceInPanel(): Option[ItemPanel] =
        this.requestInstance().map(this.panelForInstance)

      protected def descriptionParam() =
        requestNonEmptyLine("Item description: ", "Please enter a reasonable integer value.", RelativeTo(buttonRow))

      protected def intParam(prompt: String) =
        requestInt(prompt + ": ", _ >= 0, "Please enter a non-negative number.", RelativeTo(buttonRow)).map(Integer.valueOf)

      def panelForInstance(actualItem: AnyRef): ItemPanel

      def panelForParams(itemParameters: Matchable*) =
        this.panelForInstance(this.instantiate(itemParameters.map(arg)*))

    end ItemType


    private object FPS extends ItemType("o1.auctionhouse.FixedPriceSale", classOf[String], classOf[Int], classOf[Int]):
      val argumentRequesters = Seq( () => descriptionParam(), () => intParam("Price (in cents)"), () => intParam("Duration (in days)"))

      def panelForInstance(actualItem: AnyRef) = new IPPanel(actualItem):
        val daysRemaining = Label()
        val price = Label("Price: %.2f €".formatLocal(US, this.item.price[Int] / 100.0))
        refresh()
        placeNW(price,         (0, 2), OneSlot, NoFill(1, 0), (0,  20, 0, 0))
        placeNW(daysRemaining, (1, 2), OneSlot, NoFill(1, 0), (0,  0,  0, 0))

        override def refresh() =
          super.refresh()
          this.daysRemaining.text = s"Days remaining: ${this.item.daysLeft[Int]}"
      end panelForInstance

    end FPS

    private object DA extends ItemType("o1.auctionhouse.DutchAuction", classOf[String], classOf[Int], classOf[Int], classOf[Int]):
      val argumentRequesters = Seq( () => descriptionParam(), () => intParam("Starting price (in cents)"), () => intParam("Decrement (in cents)"), () => intParam("Minimum price (in cents)") )

      def panelForInstance(actualItem: AnyRef) = new IPPanel(actualItem):
        val priceRatio, currentPrice = Label()
        val startingPrice = Label("Starting price: %.2f €".formatLocal(US, this.item.startingPrice[Int] / 100.0))
        val minimumPrice  = Label( "Minimum price: %.2f €".formatLocal(US, this.item.minimumPrice[Int]  / 100.0))
        refresh()
        placeNW(startingPrice, (0, 2), OneSlot, NoFill(1, 0), (0,  20, 0, 0))
        placeNW(minimumPrice,  (1, 2), OneSlot, NoFill(1, 0), (0,  0,  0, 0))
        placeNW(currentPrice,  (0, 3), OneSlot, NoFill(1, 0), (0,  20, 0, 0))
        placeNW(priceRatio,    (1, 3), OneSlot, NoFill(1, 0), (0,  0,  0, 0))

        override def refresh() =
          super.refresh()
          this.currentPrice.text = "Price now: %.2f €".formatLocal(US, this.item.price[Int] / 100.0)
          this.priceRatio.text   = "Price/starting: %.2f".formatLocal(US, this.item.priceRatio[Double])
      end panelForInstance
    end DA

    private object EA extends ItemType("o1.auctionhouse.EnglishAuction", classOf[String], classOf[Int], classOf[Int]):
      val argumentRequesters = Seq( () => descriptionParam(), () => intParam("Starting price (in cents)"), () => intParam("Duration (in days)") )

      def panelForInstance(actualItem: AnyRef) = new ItemPanel(actualItem):
        val requiredBid, currentPrice, daysRemaining = Label()
        val startingPrice = Label("Starting price: %.2f €".formatLocal(US, this.item.startingPrice[Int] / 100.0))
        val amountField = TextField((this.item.price[Int] * 1.1).toInt.toString)
        amountField.columns = 8

        val bidButton = new Button("Place bid"):
          listenTo(this)
          def bidOnIt() = // temporarily(?) rephrased because IJ complains unnecessarily about val result = item.bid[Boolean](classOf[String] -> arg(customerSelector.selection.item), classOf[Int] -> arg(amountField.text.toIntOption.getOrElse(0)))
            val result = item.applyDynamic[Boolean]("bid")(classOf[String] -> arg(customerSelector.selection.item),
                                                           classOf[Int]    -> arg(amountField.text.toIntOption getOrElse 0))
            if !result then display("Didn't become the highest bidder.", RelativeTo(this))
            refresh()
          end bidOnIt
          reactions += { case ButtonClicked(_) => withStudentSolution(buttonRow)( bidOnIt() ) }
        end bidButton

        buyAction.contents += Label("Amount (in cents): ") += amountField += bidButton
        refresh()
        placeNW(startingPrice, (0, 2), OneSlot, NoFill(1, 0), (0,  20, 0, 0))
        placeNW(daysRemaining, (1, 2), OneSlot, NoFill(1, 0), (0,  0,  0, 0))
        placeNW(currentPrice,  (0, 3), OneSlot, NoFill(1, 0), (0,  20, 0, 0))
        placeNW(requiredBid,   (1, 3), OneSlot, NoFill(1, 0), (0,  0,  0, 0))

        override def refresh() =
          super.refresh()
          this.currentPrice.text =   "Price now: %.2f €".formatLocal(US, this.item.price[Int] / 100.0)
          this.requiredBid.text =    "Bid at least: %.2f €".formatLocal(US, this.item.requiredBid[Int] / 100.0)
          this.daysRemaining.text = s"Days remaining: ${this.item.daysLeft[Int]}"

        def defaultButton = this.bidButton

        override def requestFocus() =
          this.amountField.requestFocus()

      end panelForInstance
    end EA

  end top

end TestApp

