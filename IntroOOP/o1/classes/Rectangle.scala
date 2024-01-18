package o1.classes
import o1.*
import o1.gui.Pic

// This class is introduced in Chapter 2.4.

class Rectangle(val side1: Double, val side2: Double):

  def area = this.side1 * this.side2
  def dimensions = this.side1 + " by " + this.side2

  def makePic(color: Color): Pic = {
    //rectangle이렇게 정의할 수 있는거였어?
    Pic.rectangle(this.side1.toInt, this.side2.toInt, color)
  }
end Rectangle

class ColoredRectangle(val side1: Double, val side2: Double, colour: Color):

  def area = this.side1 * this.side2
  def dimensions = this.side1 + " by " + this.side2

  val color = colour
  def makePic: Pic = {
    //왜 colour로 바로 들어가는건 안되고 color라는 변수를 따로 만들어야 돼?
    Pic.rectangle(this.side1.toInt, this.side2.toInt, color)
  }

end ColoredRectangle

