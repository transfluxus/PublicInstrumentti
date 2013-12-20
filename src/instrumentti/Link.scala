package instrumentti

object LinkO {
  val UNIDIRECTION: Int = 0
  val BIDIRECTONAL: Int = 1
}

class Link(a: Node, b: Node, var dir: Int) extends Element(ElementCollection.getNextLinkId) {

  ElementCollection.addLink(this)

  def display() = {
    InstrumenttiMain.stroke(0);
    InstrumenttiMain.line(a.location, b.location);
  }
  
  def displaySelected() = {
    
  }
}