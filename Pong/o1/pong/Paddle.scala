package o1.pong

import o1.*

class Paddle(val facing: Direction, var pos: Pos, var velocity: Velocity) extends MovingObjectInContainer:

  val height = Paddles.Size
  val width  = Paddles.Thickness
  val container = Court

  def advance() =
    this.moveFreely()
    this.bringYToContainer()

  def push(direction: Direction) =
    this.velocity = Velocity(direction, Paddles.Speed)

  def stop() =
    this.velocity = Velocity.Still

  def blocks(ball: Ball) = !ball.isBoundFor(this.facing) && this.touches(ball)

  def launch(ball: Ball) =
    import Paddles.*
    val hitDistance    = ball.center.y - this.center.y
    val maxHitDistance = this.height / 2 + ball.height / 2
    val hitSkew        = hitDistance / maxHitDistance
    val launchDir      = launchDirection(this.facing, hitSkew)
    val isSmash        = hitSkew.abs < SmashThreshold
    val launchSpeed    = if isSmash then SmashSpeed
                         else MinLaunchSpeed + hitSkew.abs * (MaxLaunchSpeed - MinLaunchSpeed)
    val newBallState   = if isSmash then Ball.BeingSmashed else Ball.BeingHit
    ball.hit(Velocity(launchDir, launchSpeed), newBallState)

  private def touches(ball: Ball) =
    val target = ball.center
    val myClosestDistanceToTarget = this.closestPosTo(target).distance(target)
    myClosestDistanceToTarget < ball.radius

end Paddle
