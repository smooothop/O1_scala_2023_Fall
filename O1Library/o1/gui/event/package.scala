/** This package exists to provide aliases to event classes in Swing and to add
  * some methods to Swing’s [[scala.swing.event.InputEvent InputEvent]] via
  * [[o1.gui.event.ConvenientInputEvent]].
  *
  * The contents of this package are aliased in the top-level package [[o1]],
  * so they are accessible to students simply via `import o1.*`. */
package o1.gui.event

import scala.swing.event as SwEv

private val _ = o1.util.smclInit()


/** An alias for convenient access to Scala Swing’s events`. */
type MouseMoved = SwEv.MouseMoved
/** An alias for convenient access to Scala Swing’s events`. */
val MouseMoved = SwEv.MouseMoved

/** An alias for convenient access to Scala Swing’s events`. */
type MouseDragged = SwEv.MouseDragged
/** An alias for convenient access to Scala Swing’s events`. */
val MouseDragged = SwEv.MouseDragged

/** An alias for convenient access to Scala Swing’s events`. */
type MouseExited = SwEv.MouseExited
/** An alias for convenient access to Scala Swing’s events`. */
val MouseExited = SwEv.MouseExited

/** An alias for convenient access to Scala Swing’s events`. */
type MouseEntered = SwEv.MouseEntered
/** An alias for convenient access to Scala Swing’s events`. */
val MouseEntered = SwEv.MouseEntered

/** An alias for convenient access to Scala Swing’s events`. */
type MouseWheelMoved = SwEv.MouseWheelMoved
/** An alias for convenient access to Scala Swing’s events`. */
val MouseWheelMoved = SwEv.MouseWheelMoved

/** An alias for convenient access to Scala Swing’s events`. */
type MouseReleased = SwEv.MouseReleased
/** An alias for convenient access to Scala Swing’s events`. */
val MouseReleased = SwEv.MouseReleased

/** An alias for convenient access to Scala Swing’s events`. */
type MousePressed = SwEv.MousePressed
/** An alias for convenient access to Scala Swing’s events`. */
val MousePressed = SwEv.MousePressed

/** An alias for convenient access to Scala Swing’s events`. */
type MouseClicked = SwEv.MouseClicked
/** An alias for convenient access to Scala Swing’s events`. */
val MouseClicked = SwEv.MouseClicked

/** An alias for convenient access to Scala Swing’s events`. */
type KeyPressed = SwEv.KeyPressed
/** An alias for convenient access to Scala Swing’s events`. */
val KeyPressed = SwEv.KeyPressed

/** An alias for convenient access to Scala Swing’s events`. */
type KeyReleased = SwEv.KeyReleased
/** An alias for convenient access to Scala Swing’s events`. */
val KeyReleased = SwEv.KeyReleased

/** An alias for convenient access to Scala Swing’s events`. */
type KeyTyped = SwEv.KeyTyped
/** An alias for convenient access to Scala Swing’s events`. */
val KeyTyped = SwEv.KeyTyped

/** An alias for convenient access to Scala Swing’s events`. */
type KeyEvent = SwEv.KeyEvent

/** An alias for convenient access to Scala Swing’s events`. */
type InputEvent = SwEv.InputEvent


/** This object extends the interface of [[scala.swing.event.InputEvent]] with
  * convenience methods. */
object ops:
  extension (self: scala.swing.event.InputEvent)
    def isAltDown: Boolean = self.peer.isAltDown
    def isAltGraphDown: Boolean = self.peer.isAltGraphDown
    def isControlDown: Boolean = self.peer.isControlDown
    def isMetaDown: Boolean = self.peer.isMetaDown
    def isShiftDown: Boolean = self.peer.isShiftDown

