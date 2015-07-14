/*
 * Color.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

import java.util.Arrays;

/**
 * Represents a standard RGBA color for the World Wide Web. Each color
 * component (red, green, and blue) is represented by an unsigned, 8-bit
 * integer (i.e., a minimum value of 0 and a maximum value of 255). The
 * alpha value is represented here by a floating point number between 0.0
 * and 1.0, inclusive.
 */
public class Color {
    private final int red;
    private final int green;
    private final int blue;
    private final double alpha;

    public Color(int red, int green, int blue) {
        this(red, green, blue, 1.0);
    }

    public Color(int red, int green, int blue, double alpha) {
        for (int color : Arrays.asList(red, green, blue))
            if (color > 255 || color < 0)
                throw new IllegalArgumentException("invalid color value");

        if (alpha > 1 || alpha < 0)
            throw new IllegalArgumentException("invalid alpha value");

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public String toRGBA() {
        return "rgba(" + red + ", " + green + ", " + blue + ", " + alpha + ")";
    }

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0.0);
    public static final Color WHITE = new Color(255, 255, 255, 1.0);
    public static final Color BLACK = new Color(0, 0, 0, 1.0);
    public static final Color RED = new Color(255, 0, 0, 1.0);
    public static final Color GREEN = new Color(0, 255, 0, 1.0);
    public static final Color BLUE = new Color(0, 0, 255, 1.0);
    public static final Color YELLOW = new Color(255, 255, 0, 1.0);
    public static final Color CYAN = new Color(0, 255, 255, 1.0);
    public static final Color MAGENTA = new Color(255, 0, 255, 1.0);
}
