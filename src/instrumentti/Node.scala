package instrumentti

import processing.core.PVector

object NodeO  {
   val nodeDisplayRadius = 10
}

class Node(var location: PVector) extends Element(ElementCollection.getNextNodeId) {

  ElementCollection.addNode(this)

  val main = InstrumenttiMain

  def display = {
    main.stroke(0)
    main.noFill()
    main.ellipse(location, NodeO.nodeDisplayRadius)
  }

  def displaySelected = {
    main.fill(255, 0, 0, 75);
    main.noStroke();
    main.ellipse(location, NodeO.nodeDisplayRadius + 5);
  }

  def displayTempConnection = {
    main.stroke(255, 0, 0);
    main.line(location, InstrumenttiMain.mousePos());
    main.noFill();
    main.ellipse(location, ElementCreator.defaultLinkLength);
  }

  def dist(other: Node): Float = dist(other.location)

  def dist(other: PVector): Float = location.dist(other)

}