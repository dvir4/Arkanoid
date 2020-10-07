/**
 * @author Dvir
 */
package collision;

/**
 * This method is called whenever the beingHit object is hit, and notify to relevant objects.
 */
public interface HitListener {

    /**
     * notify to Objects that want to be notified of hit events.
     * @param beingHit collision.Block object that hit by a ball object.
     * @param hitter   collision.Ball object that collide with block object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}