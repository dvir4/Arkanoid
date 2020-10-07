/**
 * @author Dvir
 */
package animation;

import biuoop.DrawSurface;


/**
 * this sprite.Sprite is responsible do display a pause screen.
 */
public class PauseScreen implements Animation {
    private boolean stop;
    private static final int TEXT_SIZE = 32;

    /**
     * animation.PauseScreen constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * display a screen with the message - game paused until a space key is pressed.
     * @param d selected gui DrawSurface.
     * @param dt change speed according to frame rate.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", TEXT_SIZE);
        //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
        //    this.stop = true;
        //}
    }

    /**
     * this function is charge of stopping condition.
     * @return return yes if there is a need to stopping,return no otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}