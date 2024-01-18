package o1.time

/** A function that uses the classes `Moment` and `Interval`
  * in some way that the student decides. */
@main def timeTest() = {

  val moment1 = new Moment(2000)
  val moment2 = new Moment(2023)
  val interval1 = new Interval(moment1, moment2)

  println(interval1.length)  // Should print 23
  println(interval1)  // Should print "2000 to 2023"

  val moment3 = new Moment(2010)
  println(interval1.isLaterThan(moment3))  // Should print false

  val interval2 = new Interval(new Moment(2025), new Moment(2030))
  println(interval1.isLaterThan(interval2))  // Should print false

  println(interval1.contains(moment3)) // Should print true
  println(interval1.contains(interval2)) // Should print false

  println(interval1.overlaps(interval2)) // Should print false

  println(moment3.isIn(interval1)) // Should print true
} // Replace with some test code.

end timeTest