`VectorDraw`
------------

A tiny Java library for programmaticaly creating Scalable Vector
Graphics (SVG) images.

This repository contains the Java source for `VectorDraw` and several
other clients of the code, written in Scala. The repository uses
[SBT](http://www.scala-sbt.org), the build tool for Scala. However, you
don't need Scala or SBT installed to use `VectorDraw`; just use the Java
classes in `main/src/java`.


## Features and usage

`VectorDraw` is designed to be as simple as possible. Create a `SVGImage`
instance with your desired image size, create your desired shapes or text
using subclasses of the `Shape` class, and call the `SVGImage`'s `draw()`
method to add the shapes or text to the image. Lastly, call the `writeFile()`
method with a file name to save the SVG image. Then use your browser (or
vector graphics editing software) to see the results!


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
*   `alpha` (a `double`, which should be between 0.0 and 1.0, inclusive)

The constructor takes these parameters in the order given above.
You may omit the `alpha` parameter in the constructor, and it will be
given the value 1.0 (completely opaque).


### The `Style` class

A `Style` object contains three public fields:

*   `fillColor` (a `Color`)
*   `strokeColor` (a `Color`)
*   `strokeWidth` (an `int`)

The constructor takes these parameters in the order given
above. `Style` objects are used when constructing shape or text objects
to specify the visual style of the object.


### Subclasses of `Shape`

See each of the Scala code fragments below that show you how to create
and draw shapes and text.

#### The `Line` class

The following code fragment creates and adds a line to
the `SVGImage` object `img`.

    val line = new Line(
        20,         // starting x-coordinate
        20,         // starting y-coordinate
        80,         // ending x-coordinate
        80,         // ending y-coordinate
        new Style(Color.TRANSPARENT, Color.RED, 3)
    )
    img draw line

If the style is omitted, `Style.DEFAULT_LINE_STYLE` is used.

See the SVG created [on GitHub][lineExample] or in this repository,
under `examples`.

#### The `Rectangle` class

The following code fragment creates and adds a rectangle to
the `SVGImage` object `img`.

    val rect = new Rectangle(
        20,         // x-coordinate of top-left corner
        20,         // y-coordinate of top-left corner
        30,         // width
        40,         // height
        new Style(Color.YELLOW, Color.MAGENTA, 5)
    )
    img draw rect

If the style is omitted, `Style.DEFAULT_SHAPE_STYLE` is used.

See the SVG created [on GitHub][rectangleExample] or in this repository,
under `examples`.

#### The `Ellipse` class

The following code fragment creates and adds an ellipse to
the `SVGImage` object `img`.

    val ellipse = new Ellipse(
        50,         // x-coordinate of center
        50,         // y-coordinate of center
        30,         // radius along x-axis
        45,         // radius along y-axis
        new Style(Color.TRANSPARENT, Color.GREEN, 3)
    )
    img draw ellipse

If the style is omitted, `Style.DEFAULT_SHAPE_STYLE` is used.

See the SVG created [on GitHub][ellipseExample] or in this repository,
under `examples`.

#### The `Text` class

While not really a shape, the `Text` class can be used to put text in your
image, using whatever font families are available on the viewer's machine
(just like with HTML/CSS). The following code fragment creates and adds
text to the `SVGImage` object `img`.

    val text = new Text(
        20,             // x-coordinate of top-left corner
        40,             // y-coordinate of top-left corner
        24,             // point size of font
        "Test!",        // actual text content
        Font.MONOSPACE,
        new Style(Color.GREEN, Color.BLUE, 1)
    )
    img draw text

If the style is omitted, `Style.DEFAULT_SHAPE_STYLE` is used, but the
font is required. If the font is omitted, `Font.DEFAULT_FONT` is used, but
the style is required. If both the font and style are omitted, the defaults
for both are used.

See the SVG created [on GitHub][textExample] or in this repository,
under `examples`.

### More subclasses!

See `Circle` and `Square` for examples of straightforward subclasses.
They simply delegate work to the `Ellipse` and `Rectangle` classes,
respectively.


[lineExample]: https://github.com/abreen/VectorDraw/blob/master/examples/lineExample.svg
[rectangleExample]: https://github.com/abreen/VectorDraw/blob/master/examples/rectangleExample.svg
[ellipseExample]: https://github.com/abreen/VectorDraw/blob/master/examples/ellipseExample.svg
[textExample]: https://github.com/abreen/VectorDraw/blob/master/examples/textExample.svg
