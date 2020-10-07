/**
 * @author Dvir
 */
package collision;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * collision.GameEnvironment Class have collection of all collision objects.
 */
public class GameEnvironment {
    private List<Collidable> environmentObjects;

    /**
     * Create collision.GameEnvironment object, create a list and save this list in collision.GameEnvironment object.
     */
    public GameEnvironment() {
        this.environmentObjects = new ArrayList<>();
    }
    /**
     * add the given collision to the environment.
     * @param c collision object.
     */
    public void addCollidable(Collidable c) {
        this.environmentObjects.add(c);
    }
    /**
     * return the information about the closest collision that is going to occur. Else,If this object will not
     * collide with any of the collidables in this collection, return null.
     * @param trajectory specific line Object.
     * @return geometry.Point object that represent Closest interaction point with given line, if there isn't
     * interaction point,return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> collisionPoints = new ArrayList<>();
        List<Collidable> collisionObjects = new ArrayList<>();
        for (int i = 0; i < this.environmentObjects.size(); i++) {
            // Create a Rectangle Object for each object in collision.GameEnvironment list objects.
            Rectangle collidableShape = this.environmentObjects.get(i).getCollisionRectangle();
            // search for closest intersection point with rectangle, if there is intersection point
            // add to coalition points list.
            Point closestRectanglePoint = trajectory.closestIntersectionToStartOfLine(collidableShape);
            if (closestRectanglePoint != null) {
                collisionPoints.add(closestRectanglePoint);
                collisionObjects.add(environmentObjects.get(i));
            }
        }
        // if the coalition points list isn't empty, return the closest collation point to line's start point.
        if (!(collisionPoints.isEmpty())) {
            int minIndex = 0;
            for (int k = 1; k < collisionPoints.size(); k++) {
                if (trajectory.start().distance(collisionPoints.get(minIndex))
                        > trajectory.start().distance(collisionPoints.get(k))) {
                    minIndex = k;
                }
            }
            // Create a CollisionInfo object with collision geometry Point information and collision Rectangle shape.
            CollisionInfo closestCollision = new CollisionInfo(collisionPoints.get(minIndex),
                    collisionObjects.get(minIndex));
            return closestCollision;
        }
        return null;
    }

    /**
     * remove the given collision to the environment.
     * @param c collision object.
     */
    public void removeCollidable(Collidable c) {
        this.environmentObjects.remove(c);
    }
}