/**
 * @author Dvir
 */
package collision;

import geometry.Point;
import geometry.Rectangle;

/**
 * collision.Collidable interface determent three method that relevant to collision objects.
 */
public interface Collidable {
    /**
     * @return Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter - ball object that hit the collision object.
     * @param collisionPoint geometry.Point object that represent the location of collation.
     * @param currentVelocity current ball velocity.
     * @return return is the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}