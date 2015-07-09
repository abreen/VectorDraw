import VectorDraw._

object VectorClient {
  def main(args: Array[String]) {
    val d = new VectorDraw(300, 600)
    d.rectangle(10, 10, 60, 60, new Color(176, 196, 222))
    d.circle(120, 40, 30)
    d.line(200, 20, 330, 60, 2)
    d.line(200, 60, 330, 100, 3, Color.RED)
    d.text(50, 100, 16, Font.MONOSPACE, "Test!")
    d.writeFile("test.svg")
  }
}
