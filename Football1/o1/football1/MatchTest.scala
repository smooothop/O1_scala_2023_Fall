package o1.football1

/** A small program that uses the classes `Club` and `Match` to keep track
  * of some football matches. To be customized by the student. */
object MatchTest extends App {
  val club1 = Club("Manchester United", "Old Trafford")
  val club2 = Club("Liverpool", "Anfield")

  // Create a new match, add some goals and print out some stats.
  val match1 = Match(club2, club1)
  match1.addHomeGoal()
  match1.addAwayGoal()
  match1.addAwayGoal()
  match1.addAwayGoal()
  match1.addHomeGoal()
  println(match1.homeGoals)
  println(match1.awayGoals)
  println(match1.goalDifference)
  println("Is a home win:  " + match1.isHomeWin)
  println("Is an away win: " + match1.isAwayWin)

  // Add some more goals to the same match and print out more stats:
  match1.addHomeGoal()
  match1.addHomeGoal()
  println(match1.totalGoals)
  println(match1.goalDifference)

  // Create another match:
  val match2 = Match(club1, club2)
  // Print out the goals scored by each club in the second match:
  println(match2.homeGoals)
  println(match2.awayGoals)

  // Test which match has the larger total score:
  println(match2.isHigherScoringThan(match1))
  println(match1.isHigherScoringThan(match2))

  // Check goallessness:
  if match1.isGoalless then
    println("The first match is goalless.")

  if match2.isGoalless then
    println("The second match is goalless.")


  // Print out the locations:
  println(match1.location)
  println(match2.location)

  // Print out full descriptions of the matches:
  println(match1)
  println(match2)

  // You’ll need to expand this program in order to test all the features
  // of the classes properly.
}
end MatchTest