package o1.snake

import o1.*

val SizeInSquares = 40

val PixelsPerGridSquare = 20
val WorldSizeInPixels = PixelsPerGridSquare * SizeInSquares
val GameSpeed = 24

val Background = rectangle(WorldSizeInPixels, WorldSizeInPixels, White)
val SegmentPic = circle(PixelsPerGridSquare * 1.5, Purple) // looks nicer if the segments are bigger than the actual squares
val FoodPic    = rectangle(PixelsPerGridSquare * 2 / 3, PixelsPerGridSquare * 2 / 3, Green)

def toPixelPos(gridPos: GridPos) = Pos(gridPos.x * PixelsPerGridSquare, gridPos.y * PixelsPerGridSquare)

val initialGridPosOfSnake = GridPos(SizeInSquares / 5, SizeInSquares / 2)
val game = SnakeGame(initialGridPosOfSnake, East)

object snakeView extends View(game, GameSpeed, "Snake"):

  def makePic =
    val posOfEachSegment = game.snakeSegments.map(toPixelPos)
    val bgWithSnake = Background.placeCopies(SegmentPic, posOfEachSegment) 
    bgWithSnake.place(FoodPic, toPixelPos(game.nextFood))

  override def onTick() =
    game.advance()

  override def isDone = game.isOver

  override def onKeyDown(key: Key) =
    CompassDir.fromKey(key) match
      case Some(newDirection) =>
        game.snakeHeading = newDirection
      case None =>
end snakeView


@main def runSnakeApp() =
  snakeView.start()

