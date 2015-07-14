/*
 * Text.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import io.breen.vectordraw.Font;
import io.breen.vectordraw.Shape;
import io.breen.vectordraw.Style;
import io.breen.vectordraw.SVGImage;

public class Text extends Shape {
    protected final int size;
    protected final String content;
    protected final Font font;

    public Text(int x, int y, int size, String content) {
        this(x, y, size, content, Font.DEFAULT_FONT, Style.DEFAULT_SHAPE_STYLE);
    }

    public Text(int x, int y, int size, String content, Font font) {
        this(x, y, size, content, font, Style.DEFAULT_SHAPE_STYLE);
    }

    public Text(int x, int y, int size, String content, Style style) {
        this(x, y, size, content, Font.DEFAULT_FONT, style);
    }

    public Text(int x, int y, int size, String content,
        Font font, Style style)
    {
        super(x, y, style);
        this.size = size;
        this.content = content;
        this.font = font;
    }

    public Element toElement(Document doc) {
        Element el = doc.createElement("text");

        SVGImage.setAttribute(doc, el, "x", Integer.toString(x));
        SVGImage.setAttribute(doc, el, "y", Integer.toString(y));

        SVGImage.setAttribute(doc, el, "font-family", font.getFamily());
        SVGImage.setAttribute(doc, el, "font-size", Integer.toString(size));

        Style.applyStyle(doc, el, style);

        el.appendChild(doc.createTextNode(content));

        return el;
    }
}
