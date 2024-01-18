package o1.misc
import o1.*

// Various small assignments across several chapters will ask you to define functions in this file.
def isPortrait(picture: Pic) =
  (picture.width < picture.height)
def describe(pic: Pic) =
  if pic.height > pic.width then
    "portrait"
  else if pic.height == pic.width then
    "square"
  else
    "landscape"
def together(vector: Vector[String], speed: Int) = {
  vector.mkString("&") + "/" + speed.toString
}
def insert(str: String, target: String, index: Int): String = {
  if index <= 0  then str + target
  else if index >= target.length then target + str
  else target.substring(0, index) + str + target.substring(index)
}
def tempo(music: String): Int = {
  val parts = music.split("/") //정공법이야? ㅋㅋ 편법쓰는거 아냐?
  if parts.length > 1 then parts(1).toInt else 120
}

def myFunc = ???