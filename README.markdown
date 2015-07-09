`VectorDraw`
------------

A tiny, one-file Java library for programmaticaly creating Scalable
Vector Graphics (SVG) images.

This repository contains the Java source for `VectorDraw` and several
other clients of the code, written in Scala. The repository uses
[SBT](http://www.scala-sbt.org), the build tool for Scala. However, you
don't need Scala or SBT installed to use `VectorDraw`; just copy the
`VectorDraw.java` file into your Java source code to use it.

## Features

`VectorDraw` is designed to be as simple as possible. Create a `VectorDraw`
instance with your desired image size, call methods on the object to draw
shapes or text, and finally call its `writeFile()` method with a file name
to save the SVG image. Then use your browser (or vector graphics editing
software) to see the results!

## API and examples

**Note**  
See the `Readme.scala` file for the code that generated these examples.
Although the exampe code is written in Scala, it can be easily translated
to Java.

### The `Color` class

A `Color` object contains four final, private fields:

*   `red` (an `int`, which should be between 0 and 255, inclusive)
*   `green` (an `int`, which should be between 0 and 255, inclusive)
*   `blue` (an `int`, which should be between 0 and 255, inclusive)
*   `alpha` (an `int`, which should be between 0 and 100, inclusive)

The constructor takes these parameters in the order given above.
You may omit the `alpha` parameter in the constructor, and it will be
given the value 100 (completely opaque).

### The `Style` class

A `Style` object contains three public fields:

*   `fillColor` (a `Color`)
*   `strokeColor` (a `Color`)
*   `strokeWidth` (an `int`)

The constructor takes these parameters in the order given
above. `Style` objects can be used with a drawing method
(e.g., `rectangle()`) to specify the visual style of a shape or text.

### `VectorDraw` instance methods

For each of the following instance methods, example code written in
Scala that produces the output is shown.

#### Lines

The following instance method adds a line to the image.

    VectorDraw.line(int x1,         // starting X coordinate
                    int y1,         // starting Y coordinate
                    int x2,         // ending X coordinate
                    int y2,         // ending Y coordinate
                    Style style)

If `style` is omitted, `DEFAULT_LINE_STYLE` is used. See the following
example code and SVG output.

    val lineExample = new VectorDraw(100, 100)
    lineExample.line(20, 20, 80, 80)
    lineExample.writeFile("examples/lineExample.svg")

See the SVG created [on GitHub][lineExample] or in this repository,
under `examples`.

#### Rectangles

The following instance method adds a rectangle to the image.

    VectorDraw.rectangle(int x,         // upper-left X coordinate
                         int y,         // upper-left Y coordinate
                         int width,
                         int height,
                         Style style)

If `style` is omitted, `DEFAULT_STYLE` is used. See the following
example code and SVG output.

    val rectangleExample = new VectorDraw(100, 100)
    rectangleExample.rectangle(20, 20, 20, 40)
    rectangleExample.writeFile("examples/rectangleExample.svg")

See the SVG created [on GitHub][rectangleExample] or in this repository,
under `examples`.

#### Circles

The following instance method adds a circle to the image.

    VectorDraw.circle(int x,         // X coordinate of circle's center
                      int y,         // Y coordinate of circle's center
                      int radius,
                      Style style)

If `style` is omitted, `DEFAULT_STYLE` is used. See the following
example code and SVG output.

    val circleExample = new VectorDraw(100, 100)
    circleExample.circle(50, 50, 40)
    circleExample.writeFile("examples/circleExample.svg")

See the SVG created [on GitHub][circleExample] or in this repository,
under `examples`.

#### Ellipses

The following instance method adds an ellipse to the image.

    VectorDraw.ellipse(int x,         // X coordinate of circle's center
                       int y,         // Y coordinate of circle's center
                       int radiusX,   // horizonal radius
                       int radiusY,   // vertical radius
                       Style style)

If `style` is omitted, `DEFAULT_STYLE` is used. See the following
example code and SVG output.

    val ellipseExample = new VectorDraw(100, 100)
    ellipseExample.ellipse(50, 50, 30, 45)
    ellipseExample.writeFile("examples/ellipseExample.svg")

See the SVG created [on GitHub][ellipseExample] or in this repository,
under `examples`.

#### Text

The following instance method adds text to the image.

    VectorDraw.text(int x,
                    int y,
                    int size,       // size in points
                    String font,    // (use CSS font names or constants)
                    String content,
                    Style style)

If `style` is omitted, `DEFAULT_STYLE` is used. For `font`, use a valid
CSS font name, or one of the following: `Font.SERIF`, `Font.SANS_SERIF`,
or `Font.MONOSPACE`.

See the following example code and SVG output.

    val textExample = new VectorDraw(100, 100)
    textExample.text(20, 20, 16, Font.SANS_SERIF, "Hello!")
    textExample.writeFile("examples/textExample.svg")

See the SVG created [on GitHub][textExample] or in this repository,
under `examples`.


[lineExample]: https://github.com/abreen/VectorDraw/blob/master/examples/lineExample.svg
[rectangleExample]: https://github.com/abreen/VectorDraw/blob/master/examples/rectangleExample.svg
[circleExample]: https://github.com/abreen/VectorDraw/blob/master/examples/rectangleExample.svg
[ellipseExample]: https://github.com/abreen/VectorDraw/blob/master/examples/ellipseExample.svg
[textExample]: https://github.com/abreen/VectorDraw/blob/master/examples/textExample.svg
