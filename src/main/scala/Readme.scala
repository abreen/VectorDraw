import VectorDraw._

object Readme {
  def main(args: Array[String]) {
    val lineExample = new VectorDraw(100, 100)
    lineExample.line(20, 20, 80, 80)
    lineExample.writeFile("examples/lineExample.svg")
  }
}
