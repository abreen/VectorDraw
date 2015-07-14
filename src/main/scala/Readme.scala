import io.breen.vectordraw._

object Readme {
  def main(args: Array[String]) {
    val lineExample = new SVGImage(100, 100)
    val line = new Line(20, 20, 80, 80,
      new Style(Color.TRANSPARENT, Color.RED, 3))

    lineExample draw line
    lineExample writeFile "examples/lineExample.svg"

    val rectangleExample = new SVGImage(100, 100)
    val rect = new Rectangle(20, 20, 30, 40,
      new Style(Color.YELLOW, Color.MAGENTA, 5))

    rectangleExample draw rect
    rectangleExample writeFile "examples/rectangleExample.svg"

    val ellipseExample = new SVGImage(100, 100)
    val ellipse = new Ellipse(50, 50, 30, 45,
      new Style(Color.TRANSPARENT, Color.GREEN, 3))

    ellipseExample draw ellipse
    ellipseExample writeFile "examples/ellipseExample.svg"

    val textExample = new SVGImage(100, 100)
    val text = new Text(20, 40, 24, "Test!", Font.MONOSPACE,
      new Style(Color.GREEN, Color.BLUE, 1))

    textExample draw text
    textExample writeFile "examples/textExample.svg"
  }
}
