import io.breen.vectordraw._

object Demo {
  def main(args: Array[String]) {
    val style1 = new Style(Color.BLUE, Color.RED, 2)
    val style2 = new Style(Color.TRANSPARENT, Color.CYAN, 3)
    val style3 = new Style(Color.TRANSPARENT, new Color(160, 80, 40), 5)

    val img = new SVGImage(300, 600)

    img.draw(new Rectangle(10, 10, 60, 60, style1))

    img.draw(new Circle(120, 40, 30, style2))

    img.draw(new Line(180, 20, 330, 60))
    img.draw(new Line(180, 60, 330, 100, style3))

    img.draw(new Text(50, 100, 16, "Test!", Font.MONOSPACE))

    img.writeFile("demo.svg")
  }
}
