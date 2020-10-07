/**
 * @author Dvir
 */
package collision;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;
import java.awt.Color;

/**
 * collision.Ball object containing one geometry.Point as a center,size value,
 * specific color ,and Frame boundary's.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;

    /**
     * Create a new ball.
     * @param center geometry.Point that contain the x and y coordinate of the ball.
     * @param r      represents the size of each ball.
     * @param color  color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * get nominal number that represent the x coordinate of ball.
     * @return return nominal number.
     */
    public int getX() {
        return (int) (this.center.getX());
    }

    /**
     * get nominal number that represent the y coordinate of ball.
     * @return nominal number.
     */
    public int getY() {
        return (int) (this.center.getY());
    }

    /**
     * get nominal number that represent the radius of ball.
     * @return nominal number.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * get ball's color.
     * @return ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball in given DrawSurface.
     * @param surface DrawSurface object.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * set collision.Velocity to the collision.Ball.
     * @param v collision.Velocity object.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set collision.Velocity of the collision.Ball withing 'dx' and 'dy' coordinates.
     * @param dx specifies the change in position on the `x` coordinate.
     * @param dy specifies the change in position on the `y` coordinate.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * find collision.Velocity object of  this collision.Ball.
     * @return collision.Ball's collision.Velocity object.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set game environment of the ball.
     * @param environment game environment object.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;

    }

    /**
     * @return return ball's gameEnvironment object.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * determent ball's activity doing each timePass round.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * add the ball to the given gameLevel object.
     * @param gameLevel gameLevel Object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * specifies the change in collision.Ball's position on the `x` and the `y` axes.
     * check if collision.Ball's position cross one of the defined boundarys ,if it his,
     * change the ball's Location or his direction according to the specific situation.
     * @param dt change speed according to frame rate.
     */
    public void moveOneStep(double dt) {
        Point endPoint = this.getVelocity().applyToPoint(this.center, dt);
        // create a line from ball center to ball next location.
        Line trajectory = new Line(this.center, endPoint);
        GameEnvironment gameObject = this.gameEnvironment;
        //find next Collision Points, if there isn't any Collision, move the ball to his next location.
        CollisionInfo hitInfo = gameObject.getClosestCollision(trajectory);
        if (hitInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        } else {
            // get the object that closest Collision will be occur.
            Rectangle collationObject = hitInfo.collisionObject().getCollisionRectangle();
            Point upLeft = collationObject.getUpperLeft();
            Point upRight = new Point(upLeft.getX() + collationObject.getWidth(), upLeft.getY());
            Point bottomLeft = new Point(upLeft.getX(), upLeft.getY() + collationObject.getHeight());
            Point bottomRight = new Point(upLeft.getX() + collationObject.getWidth(),
                    upLeft.getY() + collationObject.getHeight());
            Line leftLine = new Line(upLeft, bottomLeft);
            Line topLine = new Line(upLeft, upRight);
            Line bottomLine = new Line(bottomLeft, bottomRight);
            Line rightLine = new Line(upRight, bottomRight);
            Point nextPosition = null;
            // if there is collision with one of rectangle lines, set ball location next to the collation point.
            if (hitInfo.collisionPoint().isPointOnLine(leftLine)) {
                nextPosition = new Point(hitInfo.collisionPoint().getX() - this.r, hitInfo.collisionPoint().getY());
            }
            if (hitInfo.collisionPoint().isPointOnLine(topLine)) {
                nextPosition = new Point(hitInfo.collisionPoint().getX(), hitInfo.collisionPoint().getY() - this.r);
            }
            if (hitInfo.collisionPoint().isPointOnLine(bottomLine)) {
                nextPosition = new Point(hitInfo.collisionPoint().getX(), hitInfo.collisionPoint().getY() + this.r);
            }
            if (hitInfo.collisionPoint().isPointOnLine(rightLine)) {
                nextPosition = new Point(hitInfo.collisionPoint().getX() + this.r, hitInfo.collisionPoint().getY());
            }
            this.center = nextPosition;
            // call hit function to determent new ball velocity.
            Velocity newVelocity = hitInfo.collisionObject().hit(this, hitInfo.collisionPoint(), this.velocity);
            setVelocity(newVelocity);
        }
    }

    /**
     * check if the ball is in the paddle, if it is,change ball's location.
     * @return collision.Ball new Center location.
     */
    public Point isBallInPaddle() {
        double leftLim = this.paddle.getUpperLeft().getX();
        double rightLim = this.paddle.getUpperLeft().getX() + this.paddle.getwidth();
        double paddleHeight = this.paddle.getUpperLeft().getY();
        Point p = null;
        // check if the ball is in the paddle.
        if (this.center.getX() > leftLim && this.center.getX() < rightLim && this.center.getY() > paddleHeight) {
            // check if ball location is close to the left line of the paddle. and set his new location.
            if (Math.abs(this.center.getX() - leftLim) < Math.abs(this.center.getX() - rightLim)) {
                p = new Point(rightLim + this.r, paddleHeight + this.paddle.getheight() / 2);
            } else {
                p = new Point(leftLim - this.r, paddleHeight + this.paddle.getheight() / 2);
            }
            // set the new ball velocity.
            Velocity v = this.getVelocity();
            this.setVelocity(-1 * v.getDx(), v.getDy());
        }
        return p;
    }

    /**
     * remove ball object from specific gameLevel object.
     * @param gameLevel current game level.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

}
