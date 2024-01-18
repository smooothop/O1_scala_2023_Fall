package o1.charger
import o1._
import scala.math._

// This program is introduced in Chapter 3.6.

/** A “charger” is an entity that moves about on a two-dimensional plane.
  * It is capable of accelerating in parallel to the two coordinate axes.
  *
  * @param pos       the position of the charger
  * @param maxSpeed  a limit beyond which the charger cannot accelerate */
class Charger(var pos: Pos, val maxSpeed: Double):

  private var angle: Direction = Direction.Right // most-recent holder: the direction the charger most recently turned to
  private var speed = 0.0                        // gatherer: increases when accelerating; reset to zero when turning

  /** The direction the charger’s front is facing.
    * A charger can only move in the direction it is currently facing. */
   def heading = this.angle


   /** Instructs tne cahrger to accelerate
     *in tne given direction. If that directioon is
     *tne chargers current heading, tne charger
     increases its speed by one (but no higher than its maxSpeed). Otherwise,
     tne chargers speed drops to zero and tehe chager heads towards new diretion*/
   def accelerate(dirOfMovement:Direction) =
      if this.heading == dirOfMovement then
         this.speed = min(this.speed +1,maxSpeed)
      else{
         this.speed=0.0
         this.angle=dirOfMovement

}

/** Moves teh cahrger within its coordinate system.The chargers position changesa numberof units equaltothe chargers speed,
in straight line towards teh chargers heading*/

def move() ={
val xMove=this.angle.dx*this.speed;
val yMove=this.angle.dy*this.speed;
this.pos=this.pos.addX(xMove);
this.pos=this.pos.addY(yMove);

}

end Charger

