package instrumentti

import processing.core.PVector

object ElementCreator {

  var selected: Node = null

  val defaultLinkLength: Int = 75

  def start(point: PVector) = {
    if (!selectInRange(point))
      selected = new Node(point)
  }

  def selectInRange(point: PVector): Boolean = {
    val pos: Option[Node] = ElementCollection.select(point)
    if (pos.isDefined) {
      selected = pos.get
      true
    } else
      false
  }

  def extend(point: PVector) = {
    val act = selected
    if (selectInRange(point) && act != selected) 
    	new Link(act,selected, LinkO.BIDIRECTONAL)
    else {
      val dist = selected.dist(point)
      if (dist > defaultLinkLength) {
        val newNode: Node = new Node(point)
        new Link(selected, newNode, LinkO.BIDIRECTONAL)
        selected = newNode
      }
    }
  }

  def end(point: PVector) = selected = null

  def update = {
    if(selected!= null) {
      InstrumenttiMain.noFill
      InstrumenttiMain.stroke(255,50,50)
      InstrumenttiMain.ellipse(selected.location,defaultLinkLength)
      InstrumenttiMain.line(selected.location, InstrumenttiMain.mousePos)
    }
  }
  
}