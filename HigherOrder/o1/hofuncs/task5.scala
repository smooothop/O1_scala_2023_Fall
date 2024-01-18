package o1.hofuncs
import scala.io.StdIn.*

// This program is introduced in Chapter 6.1.

@main def task5() =

  val howMany = readLine("How many sheep should I count? ").toInt

  // Your task:
  //  0. Stay awake.
  //  1. Examine the function countSheep (below).
  //  2. Use the tabulate method to generate a new vector
  //     whose size equals howMany (above) and whose contents
  //     are determined by calling countSheep on each index.
  //     Store the vector in the variables named "output".
  //  3. Modify the println command so that it prints each
  //     element in the vector on a separate line.

  def countSheep(index: Int) =
    if index % 10 < 9 then
      "sheep #" + (index + 1)
    else
      "Z" * ((index / 10) + 1)

  val output = Vector.tabulate(howMany)(countSheep).mkString("\n")
  println(output) // should print, on separate lines: sheep #1, sheep #2, etc.

end task5

