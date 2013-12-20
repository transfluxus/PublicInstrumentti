

package general

import java.io.File
import java.text.SimpleDateFormat
import java.util.Arrays
import java.util.Date
import scala.collection.JavaConversions._
import processing.core.PApplet
import processing.core.PVector 
import processing.data.JSONObject


object SBAppletRunner extends SBApplet(800,600){


 // def getBApplet(user: Class[_ <: SBApUser]): SBApplet = SBAppletHelper.getBApplet(user)

  
 def main(args: Array[String]) {
	val frame = new javax.swing.JFrame("SBPApplet")
	frame.getContentPane().add(this)
	init
	frame.setSize(w, h)
	frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE );
	frame.setVisible(true)  
  }

  
  override def setup() {
    super.setup();
	size(w, h)
//	stroke(0)
//	line(0,0,500,500)
	println(getDataPath)
  }
  
  override def draw() {
  //  line()
  }
}


@SerialVersionUID(1L)
class SBApplet(val w: Int,val h:Int) extends PApplet {

  var workingDirectory: String = _

  var config: JSONObject = _

  var runDir: String = _

  private var sbap: SBApplet = _
  
  override def setup() {
//    SBAppletHelper.initBApplet(this)
    sbap = this
    setDirectories()
  } 

  def vertex(p: PVector) {
    vertex(p.x, p.y)
  }

  def curveVertex(p: PVector) {
    curveVertex(p.x, p.y)
  }

  def ellipse(p: PVector, radius: Float) {
    ellipse(p.x, p.y, radius, radius)
  }

  def line(p1: PVector, p2: PVector) {
    line(p1.x, p1.y, p2.x, p2.y)
  }

  def setAndCreateAllDirectories() {
    setDirectories()
    createDirectories("data", "storage")
  }

  def loadConfig() {
    try {
      config = loadJSONObject(getDataPath + "config.json")
    } catch {
      case npe: NullPointerException => {
        System.err.println("No config file found: " + getDataPath + "config.json. Bye bye!")
        System.exit(-1)
      }
    }
  }

  def setDirectories() {
    if (workingDirectory == null) workingDirectory = getAppRefFolder
    sketchPath = workingDirectory
    runDir = workingDirectory + 
      new SimpleDateFormat("yyMMdd-kk-mm").format(new Date())
    var i = 1
    while (new File(runDir).exists()) {
      runDir += "-" + i
      i += 1
    }
  }

  def createDirectories(params: String*) {
    val folder = new File(workingDirectory)
    if (!folder.exists()) {
      folder.mkdirs()
      System.err.println(workingDirectory + " created")
    }
    if (Arrays.asList(params:_*).contains("data")) new File(workingDirectory + "data").mkdirs()
    if (Arrays.asList(params:_*).contains("storage")) new File(workingDirectory + "storage").mkdirs()
  }

  def getAppRefFolder(): String = {
    var path = new File("").getAbsolutePath
    if (path.endsWith("bin")) {
      path = path.substring(0, path.length - 3)
    }
//    val appName = 
    val thePath = path + File.separator + "apps" + File.separator + getClass.getSimpleName.replace("$","" ) + 
      File.separator
      
    thePath
  }

  def getDataPath(): String = sketchPath + "data" + File.separator
  

  def getStoragePath(): String = sketchPath + "storage" + File.separator

  def mousePos(): PVector = new PVector(mouseX, mouseY)

  def randomInt(min: Int, max: Int): Int = random(min, max).toInt
}