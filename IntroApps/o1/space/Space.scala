package o1.space
import o1.*

// This code is introduced in Chapters 2.6 and 2.7.

class CelestialBody(val name: String, val radius: Double, var location: Pos):

  def diameter = this.radius * 2

  override def toString = this.name

end CelestialBody



class AreaInSpace(size: Int):

  val width = size * 2
  val height = size

  val earth = CelestialBody("The Earth", 15.9, Pos(10,  this.height / 2))
  val moon  = CelestialBody("The Moon",   4.3, Pos(971, this.height / 2))

  override def toString = s"${this.width}-by-${this.height} area in space"

end AreaInSpace
