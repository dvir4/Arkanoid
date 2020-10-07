/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Color;

/**
 * sprite.Sprite class that responsible for drawing level 2 background.
 */
public class LevelThreeBackground implements Sprite {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * draw level 4 background.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        // setting background color.
        d.setColor(Color.magenta.darker().darker());
        d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
        // draw right building.
        d.setColor(Color.black.darker());
        d.fillRectangle(600, 400, 150, 200);
        // draw right building's antenna.
        d.setColor(Color.gray.darker().darker());
        d.fillRectangle(665, 300, 25, 100);
        d.setColor(Color.gray.darker());
        d.fillRectangle(670, 275, 10, 25);
        d.setColor(Color.red);
        d.fillCircle(675, 280, 10);
        d.setColor(Color.white);
        // draw right building's windows.
        d.fillCircle(675, 280, 5);
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 5; k++) {
                d.fillRectangle(607 + i * 30, 410 + k * 40, 13, 25);
            }
        }
        // draw left building.
        d.setColor(Color.black.darker());
        d.fillRectangle(100, 400, 150, 200);
        // draw left building's windows.
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 5; k++) {
                d.fillRectangle(107 + i * 30, 410 + k * 40, 13, 25);
            }
        }
        // draw left building's antenna.
        d.setColor(Color.gray.darker().darker());
        d.fillRectangle(165, 300, 25, 100);
        d.setColor(Color.gray.darker());
        d.fillRectangle(170, 275, 10, 25);
        d.setColor(Color.red);
        d.fillCircle(175, 280, 10);
        d.setColor(Color.white);
        d.fillCircle(175, 280, 5);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}

