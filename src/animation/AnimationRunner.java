/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * animation.AnimationRunner takes an animation.Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private static final int FRAME_PER_SECOND = 60;

    /**
     * animation.AnimationRunner constructor.
     * @param gui - selected GUI object.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = FRAME_PER_SECOND;
    }

    /**
     * run certain objects which define as animation objects.
     * @param animation - selected animation.
     */
    public void run(Animation animation) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            double dt =  1.0 /  this.framesPerSecond;

            animation.doOneFrame(d, dt);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}