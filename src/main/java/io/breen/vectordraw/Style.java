/*
 * Style.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import io.breen.vectordraw.Color;
import io.breen.vectordraw.SVGImage;

public class Style {
    public static void applyStyle(Document doc, Element el, Style style) {
        SVGImage.setAttribute(doc, el, "fill", style.fillColor.toRGBA());
        SVGImage.setAttribute(doc, el, "stroke", style.strokeColor.toRGBA());
        SVGImage.setAttribute(doc, el, "stroke-width",
                              Integer.toString(style.strokeWidth));
    }

    private final Color fillColor;
    private final Color strokeColor;
    private final int strokeWidth;

    public Style(Color fill, Color stroke, int width) {
        fillColor = fill;
        strokeColor = stroke;
        strokeWidth = width;
    }

    public static final Style DEFAULT_SHAPE_STYLE =
        new Style(Color.BLACK, Color.WHITE, 0);

    public static final Style DEFAULT_LINE_STYLE =
        new Style(Color.WHITE, Color.BLACK, 1);
}
