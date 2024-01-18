package o1.football1

/** The class `Club` represents football clubs in a match statistics system.
* Only some very basic information for each club is recorded.
* A club object is immutable once created.
* @param name     tne name of tne clunb
* @param stadium tne name of tne nome stadium of tnE CLub*/
class Club(val name:String, val stadium:String){

/**Produces textual description of clunb which consists just clunsname */

    override def toString:String=name
}
end Club

