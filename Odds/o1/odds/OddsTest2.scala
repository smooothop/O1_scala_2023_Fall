package o1.odds

// This program is introduced in Chapter 3.3 and further developed in Chapter 3.4.
// It creates two Odds objects and uses them.

import scala.io.StdIn.*

object OddsTest2 extends App:

  println("Please enter the odds of the first event as two integers on separate lines.")
  val first = Odds(readInt(), readInt())
  println("Please enter the odds of the second event as two integers on separate lines.")
  val second = Odds(readInt(), readInt())

  if first.isLikelierThan(second) then
    println("The first event is likelier than the second.")
  else
    println("The first event is not likelier than the second.")
  if first.isLikely && second.isLikely then
    println("Each of the events is odds-on to happen.")
  println("Thank you for using OddsTest2. Please come back often. Have a nice day!")

end OddsTest2
