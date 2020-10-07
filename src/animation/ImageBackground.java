package animation;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Image;

/**
 * in case of Image's type background, create this object.
 */
public class ImageBackground implements Sprite {
    private Image image;

    /**
     * Constructor.
     * @param image image object.
     */
    public ImageBackground(Image image) {
        this.image = image;
    }

    /**
     * draw the sprite to the screen.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}
