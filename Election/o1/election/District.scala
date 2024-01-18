package o1.election

import scala.None
import scala.math.Ordering.Double.TotalOrdering

/** The class `District` represents electoral districts (Finnish: *vaalipiiri*).
  * Each district has a certain number of seats for which a (larger) number of
  * candidates compete in an election. Each district has its own candidates.
  *
  * A district object is immutable.
  *
  * @param name        the name of the electoral district
  * @param seats       the number of seats (elected positions) available
  * @param candidates  the candidates vying for the seats in the district;
  *                    there is always at least one candidate per district */
class District(val name: String, val seats: Int, val candidates: Vector[Candidate]):

  /** Returns a textual description of the district. The description is of the
    * form `Name: X candidates, Y seats`, where `Name` is the name of the district,
    * and `X` and `Y` the numbers of candidates and seats, respectively.
    * For instance, might return the string `Helsinki: 1064 candidates, 85 seats` */
  override def toString = this.name + ": " + this.candidates.size + " candidates, " + this.seats + " seats"


  /** Prints (to the console) a description of each candidate in this district, each on
    * a line of its own. A candidate’s description is obtained by calling its `toString` method.
    *
    * NOTE TO STUDENTS: It’s not usually a good idea to create “print some of your data”
    * methods (“return some information” methods are better), but we’re still making one
    * as an exercise here. More about this in later chapters. */
  def printCandidates() =
    this.candidates.foreach(println)


  /** Returns all the candidates in this district that belong to the party named by the parameter. */
  def candidatesFrom(party: String) = this.candidates.filter( _.party == party )


  /** Returns the top candidate (Finnish: *ääniharava*) of the district. That is, returns the candidate
    * with the highest number of votes. If multiple candidates have the same number of votes, this
    * method chooses one of them arbitrarily. (The choice is arbitrary in the sense that the method
    * is free to choose whichever of the tied candidates; this does not imply randomization.)
    * @return  the top candidate, wrapped in an `Option`; `None` if there are no candidates at all */
  def topCandidate =
    if this.candidates.isEmpty then
      None
    else
      var topSoFar = this.candidates.head
      for candidate <- this.candidates.tail do
        if candidate > topSoFar then
          topSoFar = candidate
      Some(topSoFar)


  /** Returns the total number of votes received by all the candidates,
    * that is, the total number of votes cast in this district. */
  def totalVotes = this.countVotes(this.candidates)


  /** Returns the total number of votes received by members of the party named by the parameter. */
  def totalVotes(party: String) = this.countVotes(this.candidatesFrom(party))


  /** Returns the total number of votes received by the given candidates. */
  private def countVotes(candidates: Vector[Candidate]) =
    var voteCount = 0
    for candidate <- candidates do
      voteCount += candidate.votes
    voteCount


  /** **NOTE TO STUDENTS: THIS METHOD IS INTENDED FOR IMPLEMENTATION ONLY IN CHAPTER 10.1!**
    *
    * Returns a mapping from parties to their candidates. That is, returns a `Map` whose keys
    * are the names of all the parties that have candidates in this district. For each key,
    * the value is a vector containing the candidates from that party in arbitrary order.
    * (The order is arbitrary in the sense that the method is free to choose whichever order;
    * this does not imply randomization.)
    *
    * @see [[rankingsWithinParties]] */
  def candidatesByParty = Map[String, Vector[Candidate]]() // this is a ineffectual dummy implementation; the method will be implemented later


  /** **NOTE TO STUDENTS: THIS METHOD IS INTENDED FOR IMPLEMENTATION ONLY IN CHAPTER 10.1!**
    *
    * Returns a mapping from parties to their top candidates. That is, returns a `Map` whose
    * keys are the names of all the parties that have candidates in this district. For each
    * key, the value is the candidate from that party with the most votes.
    *
    * If multiple candidates from one party received the same number of votes, this method
    * chooses one of them arbitrarily. (The choice is arbitrary in the sense that the method is
    * free to choose whichever of the tied candidates; this does not imply randomization.) The
    * method assumes that every party has at least one candidate. */
  def topCandidatesByParty = Map[String, Candidate]() // this is a ineffectual dummy implementation; the method will be implemented later


  /** **NOTE TO STUDENTS: THIS METHOD IS INTENDED FOR IMPLEMENTATION ONLY IN CHAPTER 10.1!**
    *
    * Returns a mapping from parties to their vote totals. That is, returns a `Map` whose keys
    * are the names of all the parties that have candidates in this district. For each key, the
    * value is the number of votes received in total by all the members of that party in this district. */
  def votesByParty = Map[String, Int]() // this is a ineffectual dummy implementation; the method will be implemented later


  /** **NOTE TO STUDENTS: THIS METHOD IS INTENDED FOR IMPLEMENTATION ONLY IN CHAPTER 10.1!**
    *
    * Returns a mapping from parties to ranking lists of those parties’ candidates. That is,
    * returns a `Map` whose keys are the names of all the parties that have candidates in this
    * district. For each key, the value is a vector containing the candidates from that party
    * *in order by the number of votes they received, starting with the highest*.
    *
    * If multiple candidates from a single party received the same number of votes, this method
    * returns those candidates in an arbitrary order. (Arbitrary in the sense that those candidates
    * may be in whichever order, not in the sense of randomizing the order.) */
  def rankingsWithinParties = Map[String, Vector[Candidate]]() // this is a ineffectual dummy implementation; the method will be implemented later


  /** **NOTE TO STUDENTS: THIS METHOD IS INTENDED FOR IMPLEMENTATION ONLY IN CHAPTER 10.1!**
    *
    * Returns a vector containing the names of all the parties that have candidates in this
    * district, ordered by the total number of votes received by each party. Descending order
    * is used, so the party with the most votes comes first.
    *
    * If multiple parties received the same number of votes, this method orders them in an
    * arbitrary way. (The order is arbitrary in the sense that the method is free to choose
    * whichever order; this does not imply randomization.) */
  def rankingOfParties = Vector[String]() // this is a ineffectual dummy implementation; the method will be implemented later


  /** **NOTE TO STUDENTS: THIS METHOD IS INTENDED FOR IMPLEMENTATION ONLY IN CHAPTER 10.1!**
    *
    * Returns a mapping of candidates to their distribution figures (Finnish: *vertailuluku*).
    * That is, returns a `Map` whose keys are all the candidates in this district and whose
    * values are the distribution figures of those candidates.
    *
    * The distribution figure of a candidate is obtained as follows. Take the position (rank) of
    * the candidate in the ranking list within his or her own party (as defined by
    * `rankingsWithinParties`). For instance, the most-voted-for candidate within a party has a
    * rank of 1, the second-most-voted-for has a rank of two, and so on. Divide the total number
    * of votes received by the candidate’s party by the candidate’s rank, and you have the
    * candidate’s distribution figure.
    *
    * If multiple candidates from a single party received the same number of votes, the arbitrary
    * order chosen by `rankingsWithinParties` is used here, even though that would be an undesirable
    * feature in a real-world system.
    *
    * @see [[votesByParty]]
    * @see [[rankingsWithinParties]]
    * @see [[electedCandidates]] */
  def distributionFigures = Map[Candidate, Double]() // this is a ineffectual dummy implementation; the method will be implemented later


  /** **NOTE TO STUDENTS: THIS METHOD IS INTENDED FOR IMPLEMENTATION ONLY IN CHAPTER 10.1!**
    *
    * Returns all the candidates who will be elected. The candidates with the highest distribution
    * figures get elected; the total number of elected candidates equals [[seats]].
    *
    * If multiple candidates happen to have the same distribution figure, an arbitrary order is
    * used, even though this would be an undesirable feature in a real-world system. (The order
    * is arbitrary in the sense that the method is free to choose whichever order; this does not
    * imply randomization.)
    *
    * @return the elected candidates in descending order by distribution figure
    * @see [[distributionFigures]] */
  def electedCandidates = Vector[Candidate]() // this is a ineffectual dummy implementation; the method will be implemented later

end District

