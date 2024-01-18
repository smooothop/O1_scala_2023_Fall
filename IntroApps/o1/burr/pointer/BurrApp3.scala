package o1.burr.pointer
import o1.*
import o1.burr.*

val burr = Burr()

object window extends View(burr) {
    def makePic = {
      val centerX = width / 2
      val centerY = height / 2
      val mPositon = burr.location
      val halfPoint = Pos(centerX, centerY).add(mPositon).divide(2)
      val blackLine = line(halfPoint, halfPoint, Black)
      background.place(blackLine, Pos(centerX, centerY))
    }

    override def onMouseMove(mousePos: Pos) = {
      burr.location = mousePos
      println(mousePos)  // Optional: Print the mouse position
    }
  }

  @main def launchGUI() =
    window.start()





