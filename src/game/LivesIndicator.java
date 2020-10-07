/**
 * @author Dvir
 */
package game;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

/**
 * game.LivesIndicator sprite.Sprite will sit at the top of the screen and indicate the number of lives.
 */
public class LivesIndicator implements Sprite {
    private Counter remainLives;
    private static final int TEXT_SIZE = 20;

    /**
     * game.LivesIndicator constructor.
     * @param remainLives current value of player's life points.
     */
    public LivesIndicator(Counter remainLives) {
        this.remainLives = remainLives;
    }

    /**
     * draw current player remaining lives at the top of the screen.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black.darker());
        d.drawText(35, 20, "Lives: " + this.remainLives.getValue(), TEXT_SIZE);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}

