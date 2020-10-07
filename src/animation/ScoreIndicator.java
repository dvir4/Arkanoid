/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;
import game.Counter;
import sprite.Sprite;

import java.awt.Color;

/**
 * animation.ScoreIndicator sprite.Sprite will sit at the top of the screen and indicate the player score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreTracking;

    /**
     * animation.ScoreIndicator constructor.
     * @param scoreTracking current value of player's score.
     */
    public ScoreIndicator(Counter scoreTracking) {
        this.scoreTracking = scoreTracking;
    }

    /**
     * draw current player's score at the top of the screen.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        int score = this.scoreTracking.getValue();
        d.setColor(Color.black.darker());
        d.drawText(380, 20, "Score: " + score, 20);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {

    }
}
