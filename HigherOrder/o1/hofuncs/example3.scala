package o1.hofuncs
import o1.*

// These example functions are introduced in Chapter 6.1.

def swapGreenAndBlue(original: Color) = Color(original.red, original.blue, original.green)

@main def example3() =
  val originalPic = Pic("defense.png")
  val manipulatedPic = originalPic.transformColors(swapGreenAndBlue)
  originalPic.leftOf(manipulatedPic).show()

