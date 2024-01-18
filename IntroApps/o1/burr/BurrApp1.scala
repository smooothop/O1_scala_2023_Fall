package o1.burr
import o1.*
import o1.gui.mutable.*

val burr = Burr()  // Here’s an instance of class Burr for you to use as the view’s model.

// Insert your code here and finish the main function below.


  val window = new View(burr) {
    def makePic = background.place(burrPic, burr.location)

    override def onMouseMove(mousePos: Pos) = {
      burr.location = mousePos
      println(mousePos)  // Optional: Print the mouse position
    }
  }


@main def launchGUI() =
  window.start()

