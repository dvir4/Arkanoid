/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Color;

/**
 * sprite.Sprite class that responsible for drawing level 4 background.
 */
public class LevelOneBackground implements Sprite {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * draw level 1 background.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white.brighter());
        d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
        // draw blue target symbol.
        d.setColor(Color.blue);
        d.drawCircle(400, 220, 100);
        d.drawCircle(400, 220, 120);
        d.drawCircle(400, 220, 140);
        d.drawLine(400, 65, 400, 375);
        d.drawLine(220, 220, 570, 220);

    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}
