import VectorDraw._

object VectorClient {
  def main(args: Array[String]) {
    val style1 = new Style(Color.BLUE, Color.RED, 2)
    val style2 = new Style(Color.TRANSPARENT, Color.CYAN, 3)
    val style3 = new Style(Color.TRANSPARENT, new Color(160, 80, 40), 5)

    val d = new VectorDraw(300, 600)
    d.rectangle(10, 10, 60, 60, style1)

    d.circle(120, 40, 30, style2)

    d.line(180, 20, 330, 60, 2)
    d.line(180, 60, 330, 100, 3, style3)

    d.text(50, 100, 16, Font.MONOSPACE, "Test!")

    d.writeFile("test.svg")
  }
}
