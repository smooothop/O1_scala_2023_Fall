package o1.counter
import o1.*

// This program is introduced in Chapter 3.1.

val tickCounter = Counter(0)
val blackBackground = rectangle(500, 500, Black)

object tickView extends View(tickCounter, 50):

  def makePic =
   val rotatingSquare = rectangle(tickCounter.value, tickCounter.value, White).clockwise(tickCounter.value)
    blackBackground.place(rotatingSquare, Pos(250, 250))

  override def onTick() = {
    tickCounter.advance()
  }
end tickView


@main def runTickProgram() =
  tickView.start()

