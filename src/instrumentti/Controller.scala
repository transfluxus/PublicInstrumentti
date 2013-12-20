package instrumentti

import processing.core.PConstants
import processing.event.MouseEvent

object Controller {

  val main =InstrumenttiMain
  
  def init = {
    main.registerMethod("mouseEvent", this)
    //InstrumenttiMain.registerMethod("keyEvent", this)
  }

  def mouseEvent(me: MouseEvent) = {
    val eventType = me.getAction()
    val button = me.getButton()
    button match {
      case PConstants.LEFT =>
        eventType match {
          case MouseEvent.PRESS => ElementCreator.start(main.mousePos)
          case MouseEvent.DRAG => ElementCreator.extend(main.mousePos)
          case MouseEvent.RELEASE => ElementCreator.end(main.mousePos)
          case _ =>
        }
      case PConstants.RIGHT =>
        eventType match {
          case MouseEvent.PRESS => ElementCollection.select(main.mousePos)
          case _ =>
        }
      case _ =>
    }
  }

}