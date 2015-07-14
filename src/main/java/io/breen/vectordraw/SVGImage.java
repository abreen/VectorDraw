/*
 * SVGImage.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.*;
import java.io.*;

public class SVGImage {
    private static final String XMLNS = "http://www.w3.org/2000/svg";
    private static final String PUBLIC_ID = "-//W3C//DTD SVG 1.1//EN";
    private static final String SYSTEM_ID = "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd";

    private static final int DEFAULT_HEIGHT = 300;
    private static final int DEFAULT_WIDTH = 300;

    public static void setAttribute(Document doc, Element el,
        String attrName, String val)
    {
        Attr attr = doc.createAttribute(attrName);
        attr.setValue(val);
        el.setAttributeNode(attr);
    }

    private final int height;
    private final int width;
    private final Document document;
    private final Element root;

    public SVGImage() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    public SVGImage(int height, int width) {
        this.height = height;
        this.width = width;

        this.document = createEmptyDocument();

        Element root = createSVGRoot(this.document, height, width);
        this.root = root;
        this.document.appendChild(root);
    }

    public void draw(Shape s) {
        Element el = s.toElement(document);
        root.appendChild(el);
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

        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, PUBLIC_ID);
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, SYSTEM_ID);

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
