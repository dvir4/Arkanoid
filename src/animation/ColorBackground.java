package animation;

import biuoop.DrawSurface;
import sprite.Sprite;

/**
 * in case of Color's type background, create this object.
 */
public class ColorBackground implements Sprite {
    private java.awt.Color color;
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;

    /**
     * Constructor.
     * @param color color object.
     */
    public ColorBackground(java.awt.Color color) {
        this.color = color;
    }

    /**
     * draw the sprite to the screen.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}
