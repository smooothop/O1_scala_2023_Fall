package o1.grouse
import o1.*

// This program is introduced in Chapter 3.4.

val grouse = Grouse()

object view extends View(grouse, 50):

  val whiteBackground = rectangle(600, 600, White)
  val endOfWorldPic = rectangle(600, 600, Black)

  def makePic =
    if grouse.foretellsDoom then
       endOfWorldPic
    else
       grouse.toPic.onto(whiteBackground)
    end if

  override def onTick() =
    grouse.shrink()

  override def isDone = grouse.foretellsDoom

end view


@main def launchGrouseApp() =
  view.start()

