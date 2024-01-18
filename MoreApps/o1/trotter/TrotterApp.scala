package o1.trotter
import o1.*

// This program is introduced in Chapter 3.6.

/** A simple app that lets the user control a “trotter” onscreen. */
@main def runTrotterApp() =
  trotterView.start()

val game = TrotterGame(20, 40)

object trotterView extends View(game, 8):

  private val background =
    val gray = rectangle(game.horseStep, game.horseStep, Gray)
    val blue = rectangle(game.horseStep, game.horseStep, LightBlue)
    val row1 = gray.alternatingRow(blue, game.sizeInSteps)
    val row2 = blue.alternatingRow(gray, game.sizeInSteps)
    row1.alternatingColumn(row2, game.sizeInSteps)

  private val horsePic =
    Pic("horse.png").scaleTo(game.horseStep * 1.5) // a bit bigger than the square looks nice

  def makePic = background.place(horsePic, game.horsePos)

  override def onTick() =
    game.advance()

  override def onKeyDown(key: Key) =
  if key == Key.Up then
    game.horseHeading = Direction.Up
  else if key == Key.Right then
    game.horseHeading = Direction.Right
  else if key == Key.Down then
    game.horseHeading = Direction.Down
  else if key == Key.Left then
    game.horseHeading = Direction.Left



end trotterView

