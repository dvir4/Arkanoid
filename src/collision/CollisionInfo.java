/**
 * @author Dvir
 */
package collision;

import geometry.Point;

/**
 * collision.CollisionInfo Class contain collusion point and collusion object type and shape.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidableShape;

    /**
     * Create a new collision.CollisionInfo object, determent a collision geometry.Point and collision object.
     * @param collisionPoint  geometry.Point of interaction.
     * @param collidableShape collision.Collidable object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidableShape) {
        this.collisionPoint = collisionPoint;
        this.collidableShape = collidableShape;
    }
    /**
     * @return return the collision point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * @return return the collision object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidableShape;
    }

}