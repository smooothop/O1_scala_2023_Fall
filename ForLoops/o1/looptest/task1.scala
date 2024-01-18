package o1.looptest

// This program is associated with Chapter 5.5.

@main def forTask1() =

  val numbers = Vector(3.4, 6.5, 2.3, 3, 1.2, 5.41, 5.1, 9.1, 3.4, 7.8, 10, 9.1, 5, 2.1, 1.2)  // Leave this line as it is.
  var num_count = 0

  println("The Beginning.")
  for currunt <- 1 to numbers.length  do
    println("-----")
    println(numbers(num_count) * 2)
    num_count += 1
  end for
  println("The End.")

end forTask1

