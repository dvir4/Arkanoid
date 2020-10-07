/**
 * @author Dvir
 */
package collision;

/**
 * objects that implement this interface will get notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl collision.HitListener object.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl collision.HitListener object.
     */
    void removeHitListener(HitListener hl);
}