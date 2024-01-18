package o1.classes

// This class is introduced in Chapter 2.6.

class Treasure(val value: Double, val challenge: Int):

  def guardian = Monster("troll", this.challenge)

  def appeal = this.value / this.guardian.healthNow

  override def toString = "treasure (worth " + this.value + ") guarded by " + this.guardian

end Treasure
