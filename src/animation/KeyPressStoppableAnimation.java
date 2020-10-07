package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class responsible to stop animation, in case of key was press by the user.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor keyboard sensor.
     * @param key specific key String.
     * @param animation Animation runner object.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * @param d selected gui DrawSurface.
     * @param dt change speed according to frame rate.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
    }

    /**
     * @return determent if the runner animation should stop or not.
     */
    public boolean shouldStop() {
        if (this.sensor.isPressed(key) && !(this.isAlreadyPressed)) {
            return true;
        }
        if (!(this.sensor.isPressed(key))) {
            isAlreadyPressed = false;
        }
        return false;
    }
}