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
public class LevelTwoBackground implements Sprite {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * draw level 2 background.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        // set background color of this level.
        d.setColor(Color.cyan.darker());
        d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
        //setting the color of the sun, and define it.
        d.setColor(Color.yellow.darker());
        d.fillCircle(0, 0, 220);
        d.setColor(Color.yellow);
        d.fillCircle(0, 0, 200);
        // creating yellow lines.
        for (int i = 0; i < 50; i++) {
            d.drawLine(100, 100, 100 + i * 15, 290);
        }
        d.setColor(Color.WHITE);
        d.fillCircle(650, 450, 100);
        d.setColor(Color.black);
        d.fillRectangle(580, 400, 40, 40);
        d.fillRectangle(660, 400, 40, 40);
        d.fillRectangle(560, 420, 170, 5);
        d.drawLine(730, 420, 750, 390);
        d.drawLine(560, 420, 570, 390);
        d.fillRectangle(570, 470, 130, 10);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}


