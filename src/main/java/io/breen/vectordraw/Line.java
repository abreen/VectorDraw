/*
 * Line.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import io.breen.vectordraw.Shape;
import io.breen.vectordraw.Style;
import io.breen.vectordraw.SVGImage;

public class Line extends Shape {
    protected final int endX;
    protected final int endY;

    public Line(int startX, int startY, int endX, int endY) {
        this(startX, startY, endX, endY, Style.DEFAULT_LINE_STYLE);
    }

    public Line(int startX, int startY, int endX, int endY, Style style) {
        super(startX, startY, style);
        this.endX = endX;
        this.endY = endY;
    }

    public Element toElement(Document doc) {
        Element el = doc.createElement("line");

        SVGImage.setAttribute(doc, el, "x1", Integer.toString(x));
        SVGImage.setAttribute(doc, el, "y1", Integer.toString(y));

        SVGImage.setAttribute(doc, el, "x2", Integer.toString(endX));
        SVGImage.setAttribute(doc, el, "y2", Integer.toString(endY));

        Style.applyStyle(doc, el, style);

        return el;
    }
}
