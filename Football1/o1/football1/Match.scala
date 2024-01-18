package o1.football1

class Match(val home: Club, val away: Club):

  private var homeCount = 0    // stepper: starts at zero and increases as goals are scored
  private var awayCount = 0    // stepper: starts at zero and increases as goals are scored

  def homeGoals = this.homeCount

  def awayGoals = this.awayCount

  def totalGoals = this.homeGoals + this.awayGoals

  def addHomeGoal() =
    this.homeCount = this.homeCount + 1

  def addAwayGoal() =
    this.awayCount = this.awayCount + 1

  def isHomeWin = this.homeGoals > this.awayGoals

  def isAwayWin = this.homeGoals < this.awayGoals

  def isTied = this.homeGoals == this.awayGoals

  def isGoalless = this.totalGoals == 0

  def goalDifference = this.homeGoals - this.awayGoals

  def isHigherScoringThan(anotherMatch: Match) = this.totalGoals > anotherMatch.totalGoals

  def location = this.home.stadium


  override def toString =
    val heading = s"$home vs. $away at $location: "
    val gameState =
      if this.isGoalless then
        "tied at nil-nil"
      else if this.isTied then
        s"tied at $homeGoals-all"
      else if this.isHomeWin then
        s"$homeGoals-$awayGoals to $home"
      else
        s"$awayGoals-$homeGoals to $away"
    heading + gameState

end Match
