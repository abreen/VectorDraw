/*
 * Ellipse.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import io.breen.vectordraw.Shape;
import io.breen.vectordraw.Style;
import io.breen.vectordraw.SVGImage;

public class Ellipse extends Shape {
    protected final int radiusX;
    protected final int radiusY;

    public Ellipse(int x, int y, int radiusX, int radiusY) {
        this(x, y, radiusX, radiusY, Style.DEFAULT_SHAPE_STYLE);
    }

    public Ellipse(int x, int y, int radiusX, int radiusY, Style style) {
        super(x, y, style);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public Element toElement(Document doc) {
        Element el = doc.createElement("ellipse");

        SVGImage.setAttribute(doc, el, "cx", Integer.toString(x));
        SVGImage.setAttribute(doc, el, "cy", Integer.toString(y));

        SVGImage.setAttribute(doc, el, "rx", Integer.toString(radiusX));
        SVGImage.setAttribute(doc, el, "ry", Integer.toString(radiusY));

        Style.applyStyle(doc, el, style);

        return el;
    }
}
