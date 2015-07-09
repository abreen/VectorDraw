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

Here's the SVG that is created:

![](https://raw.githubusercontent.com/abreen/VectorDraw/master/examples/lineExample.svg)
