package o1.hofuncs
import o1.*

// This example is introduced in Chapter 6.1.

def artwork(x: Int, y: Int) =
  if x * x > y * 100  then Red
  else if x + y < 200 then Black
  else if y % 10 < 5  then Blue
  else                     White


@main def example4() =
  val size = Color.Max + 1
  def blueGradient(x: Int, y: Int) = Color(0, 0, x.toDouble / (size - 1) * Color.Max)
  val pic1 = Pic.generate(size, size, blueGradient)
  val pic2 = Pic.generate(size, size * 2, artwork)
  (pic1 above pic2).show()

