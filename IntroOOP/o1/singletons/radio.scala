package o1.singletons

// The following example is introduced in Chapter 2.1. It isn’t necessary to
// understand its internal workings. Some of the code that follows is not written
// in a beginner-friendly style.

import o1.play
import o1.util.mapFromID
import scala.util.Try

object radio:
  private val presets = Vector(87900, 94000, 94900, 98500)
  private val stations: Map[Int, Station] = Seq(
    Station(98500, "Radio Helsinki", // with apologies to Claire Boucher:
            "[39]<<" + "CC  CC  CC  CC  " * 2 + "&[33]<<<" + "CC  CC  CC  CC  " * 2 + "&P:<<                  D   D   D   D &[39]<" + "cc>c<gccc>c<|cc>c<gc>c<gg" * 2 + "/150"),
    Station(94000, "Radio Suomi", // with apologies to Fucked Up:
            "[41]>g-g-gg-g-g#-g---f-f--gfd#-d#-----c-c-cc<hb-----hb>ddd--dd#-d#---/180"),
    Station(87900, "YLE 1", // with apologies to Chairlift:
            "[17]edc<a>c-d<a-------hageg-ad-c<a----/150"),
    Station(94900, "Radio Rock", // with apologies to the old Ludwig Van:
            "[2](<G>G)(<G>G)(<G>G)(<Eb>Eb)---------- (<F>F)(<F>F)(<F>F)(<D>D)-------------- &[2]<<(<G>G)(<G>G)(<G>G)(<Eb>Eb)-----------(<F>F)(<F>F)(<F>F)(<D>D)---------------/180"),
    Station(106200, "Radio Nova", // with apologies to Fiona Apple:
            "[33]<<c# c#e# e#d de# e#c# c#e# e#c#&[46]<   g# g#   g# g#   g# g#>[113]  >E---"),
    Station(91900, "YleX", // with apologies to Heikki Kuula:
            "[110]>E-E-EDC-<A-G->E-E-EDC-<A->C-<A-G-    >E-E-EGE-D-C-E-E-EGE-D-C-[112]e-d-/150")
  ).mapFromID( _.kHz )

  val notchKHz = 100
  private var frequency = presets(1)

  def frequencyKHz_=(newFrequency: Int): Unit =
    this.frequency = newFrequency
    Try(this.nowPlaying.foreach(o1.play))

  def frequencyKHz = this.frequency

  def tune(steps: Int) =
    this.frequencyKHz += this.notchKHz * steps
    this.description

  def select(presetNumber: Int) =
    for chosen <- this.presets.lift(presetNumber - 1) do
      this.frequencyKHz = chosen
    this.description

  private def currentStation = this.stations.get(this.frequencyKHz)

  private def nowPlaying = this.currentStation.map( _.programme )

  private def description =
    val megaHz = this.frequencyKHz / 1000.0
    val station = this.currentStation.map( _.name ).getOrElse("just static")
    f"$megaHz%.1f MHz: $station"

  private class Station(val kHz: Int, val name: String, val programme: String)

end radio

