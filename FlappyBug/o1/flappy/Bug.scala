package o1.flappy

import o1.*

// Define class Bug here.
class Bug(private var thePos: Pos):
  def pos = this.thePos
  private var yVelocity = 0.0
  val radius = BugRadius
  def flap(dy: Double) = {
    this.yVelocity = -dy
  }
  def fall() = {
    if this.pos.y < ViewHeight - GroundDepth then
      this.yVelocity = this.yVelocity + 2

    this.move(yVelocity)
  }
  def move (dy: Double) = {
    val newPos = this.pos.addY(yVelocity)  // Calculate the new position
    this.thePos = newPos.clampY(0, ViewHeight - GroundDepth)
  }
  def isInBounds: Boolean =
    0 < this.pos.y && this.pos.y < GroundY

  override def toString = "center at " + this.pos + ", radius " + this.radius
