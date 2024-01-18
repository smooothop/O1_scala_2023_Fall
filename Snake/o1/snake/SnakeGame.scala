package o1.snake

import o1.*

// Represents games of Snake. A SnakeGame object is mutable: it tracks the
// position and heading of a snake as well as the position of a food item that
// is available for the snake to eat next.
class SnakeGame(initialPos: GridPos, initialHeading: CompassDir):

  private var segments = Vector(initialPos)
  var snakeHeading = initialHeading
  var nextFood = randomEmptyLocation()

  def snakeSegments = this.segments

  def isOver =
    val head = this.segments.head
    val validCoords = 1 until SizeInSquares
    val collidedWithWall = !validCoords.contains(head.x) || !validCoords.contains(head.y)
    val collidedWithSelf = this.segments.tail.contains(head)
    collidedWithWall || collidedWithSelf

  def advance() =
    val nextHead = this.segments.head.neighbor(this.snakeHeading)
    if nextHead == this.nextFood then
      this.segments = nextHead +: this.segments
      this.nextFood = randomEmptyLocation()
    else
      this.segments = nextHead +: this.segments.init 


  private def randomEmptyLocation(): GridPos =
    val screenFull = this.snakeSegments.size >= (SizeInSquares - 1) * (SizeInSquares - 1)
    if !screenFull then
      def randomLocs = LazyList.continually( GridPos.random(1, SizeInSquares) )
      randomLocs.dropWhile(this.snakeSegments.contains).head
    else
      GridPos(0, 0)

end SnakeGame

