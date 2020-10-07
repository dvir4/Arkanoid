/**
 * @author Dvir
 */
package game;

import java.awt.Color;
import java.awt.Image;

/**
 * this class combine two object that can be set as object background - color or image.
 */
public class Fill {
    private java.awt.Color color;
    private Image image;

    /**
     * constructor - in case of color background.
     * @param color color object.
     */
    public Fill(java.awt.Color color) {
        this.color = color;
        this.image = null;
    }

    /**
     * constructor - in case of image background.
     * @param image  image object.
     */
    public Fill(Image image) {
        this.color = null;
        this.image = image;
    }

    /**
     * @return in case there is a color member, return color background.
     */
    public Color getFillColor() {
        if (this.color != null) {
            return this.color;
        }
        return null;
    }

    /**
     * @return in case there is a image member, return image background.
     */
    public Image getFillImage() {
        if (this.image != null) {
            return this.image;
        }
        return null;
    }
}
