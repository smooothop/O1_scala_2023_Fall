package o1.hofuncs
import o1.*

// This program is introduced in Chapter 6.1.

def colorfulGradient(x: Int, y: Int) = Color(x, y, (x + y) % (Color.Max + 1))

// Your task: Examine the function colorfulGradient (above). Generate
// a new 350-by-350 image so that each pixel’s value is determined by
// colorfulGradient. Store the result in generatedPic.
@main def task4() =
  val generatedPic: Pic = Pic.generate(350, 350, colorfulGradient)
  generatedPic.show()

