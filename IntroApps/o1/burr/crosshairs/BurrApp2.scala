package o1.burr.crosshairs
import o1.*
import o1.burr.*

val burr = Burr()
val horizontalLine = line(Pos(-800,0), Pos(800,0), Black)
val verticalLine = line(Pos(0,800), Pos(0,-800), Black)
val window = new View(burr) {
    def makePic = background.place(horizontalLine,Pos(burr.location.x-800, burr.location.y)).place(verticalLine, Pos(burr.location.x, burr.location.y+800))

    override def onMouseMove(mousePos: Pos) = {
      burr.location = mousePos
      println(mousePos)  // Optional: Print the mouse position
    }
  }

@main def launchGUI() =
  window.start()
