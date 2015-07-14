/*
 * Circle.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import io.breen.vectordraw.Ellipse;
import io.breen.vectordraw.Style;

/**
 * Represents a circle. Like an ellipse, but a circle's x- and y-radii
 * are the same.
 */
public class Circle extends Ellipse {
    public Circle(int x, int y, int radius) {
        this(x, y, radius, Style.DEFAULT_SHAPE_STYLE);
    }

    public Circle(int x, int y, int radius, Style style) {
        super(x, y, radius, radius, style);
    }
}
