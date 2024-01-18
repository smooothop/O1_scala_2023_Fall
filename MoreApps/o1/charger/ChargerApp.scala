package o1.charger
import o1.*

// This program is introduced in Chapter 3.6.

/** A simple app that lets the user control a “charger” onscreen. */
@main def runChargerApp() =
  chargerView.start()

val charger = Charger(Pos(350, 350), 25)

object chargerView extends View(charger):

  private val background = square(700, LightGray)
  private val chargerPic = Pic("bull.png")

  def makePic = background.place(chargerPic, charger.pos)

  override def onTick() =
    charger.move()

  override def onKeyDown(key: Key) =
    if key == Key.Up then
      charger.accelerate(Direction.Up)
    // TODO: other directions missing

end chargerView

