/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Color;

/**
 * sprite.Sprite class that responsible for drawing level 1 background.
 */
public class LevelFourBackground implements Sprite {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * draw level 3 background.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        // setting background color.
        d.setColor(Color.cyan.darker());
        d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
        // draw green hill.
        d.setColor(Color.green.darker());
        d.fillOval(-250, 500, 1300, 800);
        // draw cloud number 1.
        d.setColor(Color.lightGray);
        d.fillOval(55, 80, 50, 60);
        d.fillOval(85, 75, 70, 70);
        d.fillOval(130, 100, 70, 70);
        d.fillOval(50, 100, 50, 50);
        d.fillOval(80, 110, 70, 70);
        // draw cloud number 2.
        d.fillOval(355, 65, 50, 60);
        d.fillOval(385, 60, 70, 70);
        d.fillOval(430, 85, 70, 70);
        d.fillOval(350, 85, 50, 50);
        d.fillOval(380, 95, 70, 70);
        // draw cloud number 3.
        d.fillOval(605, 115, 50, 60);
        d.fillOval(635, 100, 70, 70);
        d.fillOval(680, 125, 70, 70);
        d.fillOval(600, 125, 50, 50);
        d.fillOval(630, 135, 70, 70);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}
