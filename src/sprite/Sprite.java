/**
 * @author Dvir
 */
package sprite;

import biuoop.DrawSurface;

/**
 * sprite.Sprite interface relevant for visibility of each object.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d current DrawSurface object.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     * @param dt  change speed according to frame rate.
     */
    void timePassed(double dt);
}