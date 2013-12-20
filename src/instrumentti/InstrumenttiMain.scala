package instrumentti

import processing.core.PConstants
import general.SBApplet

object InstrumenttiMain extends SBApplet(800, 600) {


  def main(args: Array[String]) {
    val frame = new javax.swing.JFrame("Instrumentti")
    frame.getContentPane().add(this)
    init
    frame.setSize(w, h)
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true)
  }

  override def setup() = {
    super.setup
    setAndCreateAllDirectories();

    background(255);
    ellipseMode(PConstants.RADIUS);
    textAlign(PConstants.CENTER, PConstants.CENTER);
    
    Controller.init
  }

  override def draw() = {
    background(255)
    ElementCollection.update
    ElementCreator.update
  }

}