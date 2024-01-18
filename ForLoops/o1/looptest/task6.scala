package o1.looptest

// This program is associated with Chapter 5.6.

@main def forTask6() =

  // Try running the program and observe its output. Slightly edit the line
  // that starts with “for”. The program should instead print out all the
  // round numbers between 10 and 1000:
  // 10
  // 20
  // 30
  // ...
  // 990
  // 1000

  val first = 10
  val last = 1000

  for number <- first to last by 10 do
    println(number)

end forTask6

