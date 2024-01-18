package o1.hofuncs

import o1.*

private def launchUnscramblerGUI(scrambled: Pic, solved: Pic) =

  object gui extends View(scrambled):
    this.tooltip = "Click to toggle scrambled/revealed pic."
    var showSolved = false

    def makePic = if this.showSolved then solved else scrambled

    override def onClick(clickPos: Pos) =
      this.showSolved = !this.showSolved

  end gui

  gui.start()

end launchUnscramblerGUI
