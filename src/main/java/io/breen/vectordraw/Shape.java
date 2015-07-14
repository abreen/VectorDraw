/*
 * Shape.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import io.breen.vectordraw.Style;

public abstract class Shape {
    protected final int x;
    protected final int y;
    protected final Style style;

    public Shape(int x, int y, Style style) {
        this.x = x;
        this.y = y;
        this.style = style;
    }

    public abstract Element toElement(Document doc);
}
