/**
 * @author Dvir
 */
package collision;

import geometry.Point;

/**
 * collision.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * define collision.Velocity 'dx' and 'dy' parameters.
     * @param dx specifies the change in position on the `x`.
     * @param dy specifies the change in position on the `y`.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * get the 'dx' value of this collision.Velocity object.
     * @return dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get the 'dy' value of this collision.Velocity object.
     * @return dy value.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Take a point with position (x,y),add the change in position on the `x` and the `y` axes. return a new point.*
     * @param p selected geometry.Point.
     * @param dt change speed according to frame rate.
     * @return return new geometry.Point with the update position.
     */
    public Point applyToPoint(Point p, double dt) {
        if (p == null) {
            return null;
        }
        Point newPoint = new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt);
        return newPoint;
    }
    /**
     * change the position of  `x` and `y` axes base on angle and speed parameters.
     * @param angle specifies the direction of the ball.
     * @param speed specifies collision.Ball's speed.
     * @return new collision.Velocity object with update 'dx' and 'dy' parameters.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @return return the ball speed.
     */
    public double getSpeed() {
        return Math.sqrt((Math.pow(this.dx, 2) + (Math.pow(this.dy, 2))));
    }
}