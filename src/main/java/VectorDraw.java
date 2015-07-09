/*
 * VectorDraw.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.*;
import java.io.*;

public class VectorDraw {
    private static final String XMLNS = "http://www.w3.org/2000/svg";
    private static final String DEFAULT_FILE_NAME = "default.svg";
    private static final int DEFAULT_HEIGHT = 300;
    private static final int DEFAULT_WIDTH = 300;

    private static final Style DEFAULT_STYLE =
        new Style(Color.BLACK, Color.WHITE, 0);
    private static final Style DEFAULT_LINE_STYLE =
        new Style(Color.WHITE, Color.BLACK, 1);

    public static class Color {
        private final int red;
        private final int green;
        private final int blue;
        private final int alpha;

        public Color(int red, int green, int blue) {
            this(red, green, blue, 100);
        }

        public Color(int red, int green, int blue, int alpha) {
            for (int color : Arrays.asList(red, green, blue))
                if (color > 255 || color < 0)
                    throw new IllegalArgumentException("invalid color value");

            if (alpha > 100 || alpha < 0)
                throw new IllegalArgumentException("invalid alpha value");

            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }

        public String toRGBA() {
            return "rgba(" + red + ", " + green + ", " + blue + ", " +
                   alpha / 100.0 + ")";
        }

        public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
        public static final Color WHITE = new Color(255, 255, 255, 100);
        public static final Color BLACK = new Color(0, 0, 0, 100);
        public static final Color RED = new Color(255, 0, 0, 100);
        public static final Color GREEN = new Color(0, 255, 0, 100);
        public static final Color BLUE = new Color(0, 0, 255, 100);
        public static final Color YELLOW = new Color(255, 255, 0, 100);
        public static final Color CYAN = new Color(0, 255, 255, 100);
        public static final Color MAGENTA = new Color(255, 0, 255, 100);
    }

    public static class Font {
        public static final String SANS_SERIF = "sans-serif";
        public static final String SERIF = "serif";
        public static final String MONOSPACE = "monospace";
    }

    public static class Style {
        public Color fillColor;
        public Color strokeColor;
        public int strokeWidth;

        public Style(Color fill, Color stroke, int width) {
            fillColor = fill;
            strokeColor = stroke;
            strokeWidth = width;
        }
    }

    private final int height;
    private final int width;
    private final Document document;
    private final Element root;

    public VectorDraw() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    public VectorDraw(int height, int width) {
        this.height = height;
        this.width = width;

        this.document = createEmptyDocument();

        Element root = createSVGRoot(this.document, height, width);
        this.root = root;
        this.document.appendChild(root);
    }

    public void circle(int x, int y, int radius) {
        circle(x, y, radius, DEFAULT_STYLE);
    }

    public void circle(int x, int y, int radius, Style style) {
        ellipse(x, y, radius, radius, style);
    }

    public void ellipse(int x, int y, int radiusX, int radiusY) {
        ellipse(x, y, radiusX, radiusY, DEFAULT_STYLE);
    }

    public void ellipse(int x, int y, int radiusX, int radiusY, Style style) {
        String name;

        if (radiusX == radiusY)
            name = "circle";
        else
            name = "ellipse";

        Element el = document.createElement(name);

        setAttribute(document, el, "cx", Integer.toString(x));
        setAttribute(document, el, "cy", Integer.toString(y));

        if (radiusX == radiusY) {
            setAttribute(document, el, "r", Integer.toString(radiusX));
        } else {
            setAttribute(document, el, "rx", Integer.toString(radiusX));
            setAttribute(document, el, "ry", Integer.toString(radiusY));
        }

        applyStyle(document, el, style);

        root.appendChild(el);
    }

    public void line(int x1, int y1, int x2, int y2) {
        line(x1, y1, x2, y2, DEFAULT_LINE_STYLE);
    }

    public void line(int x1, int y1, int x2, int y2, Style style) {
        Element el = document.createElement("line");

        setAttribute(document, el, "x1", Integer.toString(x1));
        setAttribute(document, el, "y1", Integer.toString(y1));

        setAttribute(document, el, "x2", Integer.toString(x2));
        setAttribute(document, el, "y2", Integer.toString(y2));

        applyStyle(document, el, style);

        root.appendChild(el);
    }

    public void rectangle(int x, int y, int width, int height) {
        rectangle(x, y, width, height, DEFAULT_STYLE);
    }

    public void rectangle(int x, int y, int width, int height, Style style) {
        Element el = document.createElement("rect");

        setAttribute(document, el, "x", Integer.toString(x));
        setAttribute(document, el, "y", Integer.toString(y));

        setAttribute(document, el, "width", Integer.toString(width));
        setAttribute(document, el, "height", Integer.toString(height));

        applyStyle(document, el, style);

        root.appendChild(el);
    }

    public void text(int x, int y, int size, String font, String content) {
        text(x, y, size, font, content, DEFAULT_STYLE);
    }

    public void text(int x, int y, int size, String font,
        String content, Style style)
    {
        Element el = document.createElement("text");

        setAttribute(document, el, "x", Integer.toString(x));
        setAttribute(document, el, "y", Integer.toString(y));

        setAttribute(document, el, "font-family", font);
        setAttribute(document, el, "font-size", Integer.toString(size));

        applyStyle(document, el, style);

        el.appendChild(document.createTextNode(content));

        root.appendChild(el);
    }

    private static void setAttribute(Document doc, Element el,
        String attrName, String val)
    {
        Attr attr = doc.createAttribute(attrName);
        attr.setValue(val);
        el.setAttributeNode(attr);
    }

    private static void applyStyle(Document doc, Element el, Style style) {
        setAttribute(doc, el, "fill", style.fillColor.toRGBA());
        setAttribute(doc, el, "stroke", style.strokeColor.toRGBA());
        setAttribute(doc, el, "stroke-width",
            Integer.toString(style.strokeWidth));
    }

    public void writeFile(String fileName) {
        TransformerFactory factory = TransformerFactory.newInstance();

        Transformer transformer = null;
        try {
            transformer = factory.newTransformer();
        } catch (TransformerConfigurationException e) {
            System.err.println(e);
            System.exit(1);
        }

        DOMSource src = new DOMSource(this.document);

        StreamResult result = new StreamResult(new File(fileName));

        try {
            transformer.transform(src, result);
        } catch (TransformerException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private static Element createSVGRoot(Document doc, int height, int width) {
        Element el = doc.createElement("svg");

        setAttribute(doc, el, "xmlns", XMLNS);
        setAttribute(doc, el, "height", Integer.toString(height));
        setAttribute(doc, el, "width", Integer.toString(width));

        return el;
    }

    private static Document createEmptyDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println(e);
            System.exit(1);
        }

        return builder.newDocument();
    }
}
