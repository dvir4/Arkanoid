/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;
import game.Counter;
import sprite.SpriteCollection;
import java.awt.Color;

/**
 * The animation.CountdownAnimation will display the given gameScreen, and show countdown from given number back to 1.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;
    private Counter roundCounter;

    /**
     * animation.CountdownAnimation constructor.
     *
     * @param numOfSeconds number of seconds determent for much time countdown display in given gameScreen.
     * @param countFrom    countdown  from this number to 1.
     * @param gameScreen   our game current game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
        this.roundCounter = new Counter(countFrom);
    }

    /**
     * draw specific sprite objects in each frame, also show a countdown back to 1.
     * @param d selected gui DrawSurface.
     * @param dt change speed according to frame rate.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(400, 300, Integer.toString(this.roundCounter.getValue()), 40);

        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 * (int) this.numOfSeconds / this.countFrom;
        long startTime = System.currentTimeMillis();
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0 && this.roundCounter.getValue() < this.countFrom) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
        // in case the countdown is over, stop running this animation.
        if (this.roundCounter.getValue() == 0) {
            this.running = false;
        }
        this.roundCounter.decrease(1);
    }

    /**
     * this function is charge of stopping condition.
     * @return return yes if there is a need to stopping,return no otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}