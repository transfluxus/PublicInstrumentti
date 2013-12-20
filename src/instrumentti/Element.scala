package instrumentti

abstract class Element(id :Int) {

  def getId:Int = id
  
  def display
  
  def displaySelected
}