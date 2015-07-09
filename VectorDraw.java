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
    private static final Color DEFAULT_COLOR = Color.BLACK;

    public static class Color {
        private final int red;
        private final int green;
        private final int blue;

        public Color(int red, int green, int blue) {
            for (int color : Arrays.asList(red, green, blue))
                if (color > 255 || color < 0)
                    throw new IllegalArgumentException("invalid color value");

            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public String toRGB() {
            return "rgb(" + red + ", " + green + ", " + blue + ")";
        }

        public static final Color WHITE = new Color(255, 255, 255);
        public static final Color BLACK = new Color(0, 0, 0);
        public static final Color RED = new Color(255, 0, 0);
        public static final Color GREEN = new Color(0, 255, 0);
        public static final Color BLUE = new Color(0, 0, 255);
        public static final Color YELLOW = new Color(255, 255, 0);
        public static final Color CYAN = new Color(0, 255, 255);
        public static final Color MAGENTA = new Color(255, 0, 255);
    }

    public static class Font {
        public static final String SANS_SERIF = "sans-serif";
        public static final String SERIF = "serif";
    }

    private String fileName;
    private Document document;
    private Element root;

    public VectorDraw() {
        this(DEFAULT_FILE_NAME);
    }

    public VectorDraw(String fileName) {
        this.fileName = fileName;

        this.document = VectorDraw.createEmptyDocument();

        Element root = VectorDraw.createSVGRoot(this.document);
        this.root = root;
        this.document.appendChild(root);
    }

    public void circle(int x, int y, int radius) {
        ellipse(x, y, radius, radius, DEFAULT_COLOR);
    }

    public void circle(int x, int y, int radius, Color c) {
        ellipse(x, y, radius, radius, c);
    }

    public void ellipse(int x, int y, int radiusX, int radiusY) {
        ellipse(x, y, radiusX, radiusY, DEFAULT_COLOR);
    }

    public void ellipse(int x, int y, int radiusX, int radiusY, Color c) {
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

        setAttribute(document, el, "fill", c.toRGB());

        root.appendChild(el);
    }

    public void line(int x1, int y1, int x2, int y2, int width) {
        line(x1, y1, x2, y2, width, DEFAULT_COLOR);
    }

    public void line(int x1, int y1, int x2, int y2, int width, Color c) {
        Element el = document.createElement("line");

        setAttribute(document, el, "x1", Integer.toString(x1));
        setAttribute(document, el, "y1", Integer.toString(y1));

        setAttribute(document, el, "x2", Integer.toString(x2));
        setAttribute(document, el, "y2", Integer.toString(y2));

        setAttribute(document, el, "stroke-width", Integer.toString(width));

        setAttribute(document, el, "stroke", c.toRGB());

        root.appendChild(el);
    }

    public void rectangle(int x, int y, int width, int height) {
        rectangle(x, y, width, height, DEFAULT_COLOR);
    }

    public void rectangle(int x, int y, int width, int height, Color c) {
        Element el = document.createElement("rect");

        setAttribute(document, el, "x", Integer.toString(x));
        setAttribute(document, el, "y", Integer.toString(y));

        setAttribute(document, el, "width", Integer.toString(width));
        setAttribute(document, el, "height", Integer.toString(height));

        setAttribute(document, el, "fill", c.toRGB());

        root.appendChild(el);
    }

    public void text(int x, int y, int size, String font, String content) {
        text(x, y, size, font, content, DEFAULT_COLOR);
    }

    public void text(int x, int y, int size, String font,
        String content, Color c)
    {
        Element el = document.createElement("text");

        setAttribute(document, el, "x", Integer.toString(x));
        setAttribute(document, el, "y", Integer.toString(y));

        setAttribute(document, el, "font-family", font);
        setAttribute(document, el, "font-size", Integer.toString(size));

        setAttribute(document, el, "fill", c.toRGB());

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

    private void writeFile() {
        TransformerFactory factory = TransformerFactory.newInstance();

        Transformer transformer = null;
        try {
            transformer = factory.newTransformer();
        } catch (TransformerConfigurationException e) {
            System.err.println(e);
            System.exit(1);
        }

        DOMSource src = new DOMSource(this.document);

        StreamResult result = new StreamResult(new File(this.fileName));

        try {
            transformer.transform(src, result);
        } catch (TransformerException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private static Element createSVGRoot(Document doc) {
        Element el = doc.createElement("svg");

        Attr attr = doc.createAttribute("xmlns");
        attr.setValue(XMLNS);
        el.setAttributeNode(attr);

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

    public static void main(String[] args) {
        VectorDraw d = new VectorDraw("test.svg");
        d.rectangle(10, 10, 60, 60, new Color(176, 196, 222));
        d.circle(120, 40, 30);
        d.line(200, 20, 330, 60, 2);
        d.line(200, 60, 330, 100, 3, Color.RED);
        d.text(50, 100, 16, Font.SANS_SERIF, "Test!");
        d.writeFile();
    }
}
