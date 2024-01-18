package o1.grouse
import o1.*

// This class is introduced in Chapter 3.4.

class Grouse:

  private var size = 400
  private val basePic = Pic("bird.png")

  def foretellsDoom = this.size <= 0

  def shrink() =
    if this.size > 0 then
      this.size = this.size - 1

  def toPic = this.basePic.scaleTo(this.size)

end Grouse
