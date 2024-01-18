package o1.subprograms

import o1.*

import scala.swing.Image

// WRITE YOUR OWN FUNCTIONS BELOW. (You may remove the example function.)
def twoByTwo(pic: Pic) =
  val testPic = pic
  val sBs = testPic.leftOf(testPic)
  sBs.above(sBs)

def flagOfSomalia(width: Double) =
    star(width*4/13, White).onto(rectangle(width, width*2/3, RoyalBlue))
def flagOfFinland(width: Double) =
  val partA = rectangle(width*5/18, width*4/18, White).
    leftOf(rectangle(width*3/18, width*4/18, Blue)).
    leftOf(rectangle(width*10/18, width*4/18, White))
  val partB = rectangle(width, width*3/18, Blue)
  val wholeFlag = partA.above(partB).above(partA)
  wholeFlag
def clownify(pic: Pic, pos: Pos) =
  val redNose: Pic = circle(15, Red)
  pic.place(redNose, pos)
def leftSide(original: Pic, relativeSize: Double): Pic = {
    // Ensure that relativeSize is within the valid range [0, 100]
    val clampedSize = Math.min(100.0, Math.max(0.0, relativeSize))
    // Calculate the width of the left side as a percentage of the original's width
    val leftWidth = (original.width * clampedSize / 100).toInt
    // Crop the left side of the original Pic
    original.crop(0, 0, leftWidth, original.height)
}
def rightSide(original: Pic, relativeSize: Double): Pic = {
    // Ensure that relativeSize is within the valid range [0, 100]
    val clampedSize = Math.min(100.0, Math.max(0.0, relativeSize))
    // Calculate the width of the right side as a percentage of the original's width
    val rightWidth = (original.width * clampedSize / 100).toInt
    // Calculate the x-coordinate where the right side starts
    val rightX = original.width - rightWidth
    // Crop the right side of the original Pic
    original.crop(rightX, 0, rightWidth, original.height)
}
def foldIn(unfolded: Pic, percentageVisible: Double): Pic = {
    // Calculate the width of the visible portion on both sides
    val visibleWidth = unfolded.width * (percentageVisible / 100)
    // Extract the left and right sides of the unfolded picture
    val leftSidePic = leftSide(unfolded, percentageVisible)
    val rightSidePic = rightSide(unfolded, percentageVisible)
    // Place the left and right sides side by side
    leftSidePic.leftOf(rightSidePic)
}
def flagOfCzechia(width: Double): Pic = {
    val height = width*2/3
    val whitePart = rectangle(width, height/2, White)
    val crimsonPart = rectangle(width, height/2, Crimson)
    val bluePart = triangle(height, width/2, MidnightBlue)
    val halfFlag = whitePart.above(crimsonPart)
    val anotherBlue = bluePart.clockwise(90)
    val wholeFlag = anotherBlue.onto(halfFlag, TopLeft, TopLeft)
    wholeFlag
}

