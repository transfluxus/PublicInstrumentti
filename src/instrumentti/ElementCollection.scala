package instrumentti

import scala.collection.immutable.TreeSet

import processing.core.PVector

object ElementCollection {

  var nodes = new TreeSet[Node]()(NodeOrdering)
  var links = new TreeSet[Link]()(LinkOrdering)

  var selected: Element = null

  private var nextNodeId = 0
  private var nextLinkId = 0

  def update() = {
    for (link <- links)
      link.display
    for (node <- nodes)
      node.display
    markSelected
  }

  def markSelected() = {
    if (selected != null)
      selected.displaySelected
    else if (ElementCreator.selected != null)
      ElementCreator.selected.displayTempConnection
  }

  // not used
  def add(el: Element) = {
    el match {
      case _: Node => addNode(el.asInstanceOf[Node])
      case _: Link => addLink(el.asInstanceOf[Link])
    }
  }

  def addNode(n: Node) = nodes = nodes + n
  def addLink(l: Link) = links = links + l

  def getNodeInRange(range: Float)(point: PVector): Option[Node] = {
    def dist(n: Node): Float = n.dist(point)
    val nodesInRange = nodes.filter(dist(_) < range)
    if (!nodesInRange.isEmpty) {
      val closest: Node = nodesInRange.minBy(dist(_))
      Some(closest)
    } else
      None
  }

  def getNodeInRange(pos: PVector): Option[Node] = getNodeInRange(NodeO.nodeDisplayRadius)(pos)

  def select(pos: PVector): Option[Node] = {
    val sel: Option[Node] = getNodeInRange(pos)
    if (sel.isDefined) {
      selected = sel.get
      Some(selected.asInstanceOf[Node])
    } else
      None
  }

  def getNextNodeId(): Int = {
    val ret = nextNodeId
    nextNodeId += 1
    ret
  }

    def getNextLinkId(): Int = {
    val ret = nextLinkId
    nextLinkId += 1
    ret
  }
  
}

//
//object ElementOrdering extends Ordering[Element] {
//  def compare(a:Element, b:Element) = a.getId  compare b.getId  

object NodeOrdering extends Ordering[Node] {
  def compare(a: Node, b: Node) = a.getId compare b.getId
}

object LinkOrdering extends Ordering[Link] {
  def compare(a: Link, b: Link) = a.getId compare b.getId
}