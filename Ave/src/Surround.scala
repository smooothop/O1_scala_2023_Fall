import scala.io.StdIn.*

@main def surround() =
  val sur = readLine("Please enter a string: ")
  val around = readLine("And a surrounding string: ")
  println(around + sur + around)