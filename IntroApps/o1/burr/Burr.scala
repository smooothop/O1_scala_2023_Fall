package o1.burr
import o1.*

// This class is introduced in Chapter 3.1.

class Burr:
  var location = Pos(0, 0)  // most-recent holder

val width = 800
val height = 500
val background = rectangle(width, height, White)
val burrPic = circle(40, YellowGreen)
