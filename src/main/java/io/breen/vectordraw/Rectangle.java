/*
 * Rectangle.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import io.breen.vectordraw.Shape;
import io.breen.vectordraw.Style;
import io.breen.vectordraw.SVGImage;

public class Rectangle extends Shape {
    protected final int width;
    protected final int height;

    public Rectangle(int x, int y, int width, int height) {
        this(x, y, width, height, Style.DEFAULT_SHAPE_STYLE);
    }

    public Rectangle(int x, int y, int width, int height, Style style) {
        super(x, y, style);
        this.width = width;
        this.height = height;
    }

    public Element toElement(Document doc) {
        Element el = doc.createElement("rect");

        SVGImage.setAttribute(doc, el, "x", Integer.toString(x));
        SVGImage.setAttribute(doc, el, "y", Integer.toString(y));

        SVGImage.setAttribute(doc, el, "width", Integer.toString(width));
        SVGImage.setAttribute(doc, el, "height", Integer.toString(height));

        Style.applyStyle(doc, el, style);

        return el;
    }
}
