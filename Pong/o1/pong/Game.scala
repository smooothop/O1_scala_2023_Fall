package o1.pong

import o1.*
import Direction.{Left,Right}
import Velocity.Still

class Game:
  val leftPaddle  = Paddle(Right, InitialLeftPaddleCenter,  Still)
  val rightPaddle = Paddle(Left,  InitialRightPaddleCenter, Still)
  val ball        = Ball()

  this.ball.serve()

  def timePasses() =
    this.moveBall()
    if this.ball.isWayOut then
      this.ball.serve()
    this.leftPaddle.advance()
    this.rightPaddle.advance()

  private def moveBall() =
    this.ball.advance()
    if this.rightPaddle.blocks(this.ball) then
      this.rightPaddle.launch(this.ball)
    else if this.leftPaddle.blocks(this.ball) then
      this.leftPaddle.launch(this.ball)
    else if this.ball.bouncesOffWall then
      this.ball.bounceVertical()

end Game

