/*
 * Font.java
 *
 * Author: Alexander Breen (abreen@bu.edu)
 */

package io.breen.vectordraw;

public class Font {
    private final String fontFamily;

    public Font(String family) {
        fontFamily = family;
    }

    public String getFamily() {
        return fontFamily;
    }

    public static final Font SANS_SERIF = new Font("sans-serif");
    public static final Font SERIF = new Font("serif");
    public static final Font MONOSPACE = new Font("monospace");

    public static final Font DEFAULT_FONT = Font.SERIF;
}
