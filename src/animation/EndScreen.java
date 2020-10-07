/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;
import game.Counter;

import java.awt.Color;

/**
 * in case the game is over, display a animation screen.
 */
public class EndScreen implements Animation {
    //private KeyboardSensor keyboard;
    private boolean stop;
    private Counter currentLives;
    private Counter currentScore;
    private String key;
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int TEXT_SIZE = 32;


    /**
     * animation.EndScreen constructor.
     *
     * @param currentLives current player lives points.
     * @param currentScore current player score.
     * @param key          stop animation key.
     */
    public EndScreen(Counter currentLives, Counter currentScore, String key) {
        this.currentLives = currentLives;
        this.currentScore = currentScore;
        this.key = key;
        this.stop = false;

    }

    /**
     * show a ending screen.
     * @param d selected gui DrawSurface.
     * @param dt change speed according to frame rate.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
        d.setColor(Color.cyan.darker());
        d.fillRectangle(70, d.getHeight() / 2 - 35, 650, 50);
        d.setColor(Color.white);
        // in case of player life reach to 0, show specific animation.
        if (this.currentLives.getValue() > 0) {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2,
                    "You Win! Your score is " + this.currentScore.getValue(), TEXT_SIZE);

            d.drawText(d.getWidth() / 10, d.getHeight() - 50, "Press " + this.key + " key to continue", 20);
            //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            //   this.stop = true;
            //}
        } else {
            // in case the player pass all game's levels, show specific animation.
            d.drawText(d.getWidth() / 4, d.getHeight() / 2,
                    "Game Over. Your score is " + this.currentScore.getValue(), TEXT_SIZE);
            d.drawText(d.getWidth() / 10, d.getHeight() - 50, "Press " + this.key + " key to continue", 20);
            //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            //    this.stop = true;
            //}
        }
    }

    /**
     * this function is charge of stopping condition.
     *
     * @return return yes if there is a need to stopping,return no otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
