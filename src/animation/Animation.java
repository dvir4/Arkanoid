/**
 * @author Dvir
 */
package animation;
import biuoop.DrawSurface;

/**
 * animation.Animation interface.
 */
public interface Animation {

    /**
     * draw specific sprite objects in each frame.
     * @param d selected gui DrawSurface.
     * @param dt change speed according to frame rate.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * this function is charge of stopping condition.
     * @return return yes if there is a need to stopping,return no otherwise.
     */
    boolean shouldStop();
}