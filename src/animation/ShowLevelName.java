/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;
import game.LevelInformation;
import sprite.Sprite;

import java.awt.Color;

/**
 * this sprite.Sprite responsible to display level name at the top of the screen.
 */
public class ShowLevelName implements Sprite {
    private LevelInformation currlevel;

    /**
     * animation.ShowLevelName constructor.
     * @param currlevel current game level.
     */
    public ShowLevelName(LevelInformation currlevel) {
        this.currlevel = currlevel;
    }

    /**
     * display level name.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black.darker());
        d.drawText(500, 20, "level name:" + this.currlevel.levelName(), 20);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}
