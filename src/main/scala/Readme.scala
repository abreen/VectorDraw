import VectorDraw._

object Readme {
  def main(args: Array[String]) {
    val lineExample = new VectorDraw(100, 100)
    lineExample.line(20, 20, 80, 80)
    lineExample.writeFile("examples/lineExample.svg")

    val rectangleExample = new VectorDraw(100, 100)
    rectangleExample.rectangle(20, 20, 20, 40)
    rectangleExample.writeFile("examples/rectangleExample.svg")

    val circleExample = new VectorDraw(100, 100)
    circleExample.circle(50, 50, 40)
    circleExample.writeFile("examples/circleExample.svg")

    val ellipseExample = new VectorDraw(100, 100)
    ellipseExample.ellipse(50, 50, 30, 45)
    ellipseExample.writeFile("examples/ellipseExample.svg")

    val textExample = new VectorDraw(100, 100)
    textExample.text(20, 20, 16, Font.SANS_SERIF, "Hello!")
    textExample.writeFile("examples/textExample.svg")
  }
}
