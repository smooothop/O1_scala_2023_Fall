package o1.flappy

import o1.*

class Game():

   val bug = new Bug(new Pos(100,40))

   // Only pass in the radius to create an Obstacle instance.
   // It will use its own method to generate a random starting position.
   val obstacles = Vector(Obstacle(70), Obstacle(30), Obstacle(20))

   def timePasses() = {
     bug.fall()
     for (obstacle <- obstacles) {
       obstacle.approach()
     }
   }

   def activateBug() =
     bug.flap(FlapStrength)

   def isLost: Boolean = {
     var touched = false
     for (obstacle <- obstacles) {
       if obstacle.touches(bug) then {
         touched = true
       }
     }
     touched || !bug.isInBounds
   }
     //!this.bug.isInBound
     //obstacle.touches(bug)
     //this.obstacle.touches(this.bug)

end Game