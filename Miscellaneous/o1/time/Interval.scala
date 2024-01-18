package o1.time

class Interval(private val start: Moment, private val end: Moment) {
  
  def length = start.distance(end)

  override def toString = start + " to " + end

  def isLaterThan(aTime: Moment) = start.isLaterThan(aTime)

  def isLaterThan(anInterval: Interval) = start.isLaterThan(anInterval.start)

  def contains(aTime: Moment) = !start.isLaterThan(aTime) && !aTime.isLaterThan(end)

  def contains(anInterval: Interval) = this.contains(anInterval.start) && this.contains(anInterval.end)

  def overlaps(anInterval: Interval) = this.contains(anInterval.start) || this.contains(anInterval.end)
}