package o1.subprograms

import o1.*
import scala.math.*
// WRITE YOUR OWN FUNCTIONS HERE, BELOW THE IMPORT COMMAND:
def toMeters(feet: Double, inch: Double) = feet*0.3048 + inch*0.0254
def volumeOfCube(edgeLength: Double) = pow(edgeLength, 3)
def areaOfCube(edgeLength: Double) = pow(edgeLength, 2)*6
def row(square: Int) = (square/8)
def column(square: Int) = (square%8)
def accompany(text: String, song: String) =
 println(text)
 play(song)
end accompany
//def verticalBar(width: Int) = rectangle(width, 10*width, Blue)
def verticalBar(width: Int, colour: Color) = rectangle(width, 10*width, colour)
def overallGrade(projectGrade: Int, bonus: Int, participation: Int) =
  val sum = (projectGrade + bonus + participation)
  val grade = min(sum, 5)
  grade
def leaguePoints(wins: Int, draws: Int) =
  wins*3 + draws
def teamStats(teamName: String, wins: Int, draws: Int, Losses: Int) =
  val totalGame = wins+draws+Losses
  teamName + ": "
    + wins + "/" + totalGame + " wins, "
    + draws + "/" + totalGame + " draws, "
    + Losses + "/" + totalGame + " Losses, "
    + leaguePoints(wins, draws) + " points"
def toInches(meter: Double) = meter*39.37007874015748
def wholeFeet(meters: Double): Double = {
    val inches = toInches(meters)
    val feet = inches / 12
    math.floor(feet)
  }
 def remainingInches(meters: Double): Double = {
    val inches = toInches(meters)
    val feet = wholeFeet(meters)
    inches - (feet * 12)
  }
def toFeetAndInches(meters: Double): (Double, Double) = {
   val inches = toInches(meters)
   val feet = math.floor(inches/12)
   val remainingInches = inches%12
   (feet, remainingInches)
}
def verbalEvaluation(projectGrade: Int, examBonus: Int, participationBonus: Int) =
  val descriptions = Buffer("failed", "acceptable", "satisfactory", "good", "very good", "excellent")
  val grade = overallGrade(projectGrade, examBonus, participationBonus)
  descriptions(grade)
def doubleScore(score: Buffer[Int], doublePlayer: Int) =
  score(doublePlayer-1) = score(doublePlayer-1)*2
def penalize(score: Buffer[Int], playerNumber: Int, sizePenalty: Int) =
  val actualNumber = playerNumber-1
  val removedScore = score(actualNumber) - 1
  score(actualNumber) = max((score(actualNumber) - sizePenalty),1)
  min(sizePenalty, removedScore)
// Here is a buggy piece of code for you to fix in one of Chapter 1.7’s assignments.
def onTwoInstruments(melody: String, first: Int, second: Int, lengthOfPause: Int) =
  val melodyUsingFirst  = "[" + first  + "]" + melody
  val melodyUsingSecond = "[" + second + "]" + melody
  val pause = " " * lengthOfPause
  val playedTwice = melodyUsingFirst + pause + melodyUsingSecond
  playedTwice


