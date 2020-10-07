/**
 * @author Dvir
 */
package collision;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;
import java.awt.Color;

/**
 * paddle of the game. Rectangle shape object that can move left and right.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Point upperLeft;
    private double width;
    private double height;
    private double leftLimit;
    private double rightLimit;
    private java.awt.Color color;
    private int paddleSpeed;

    /**
     * Create a new paddle,set keyboard and determent paddle's shape and color.
     * @param keyboard  current keyboard.
     * @param upperLeft upper - left geometry.Point of the paddle.
     * @param width     width size of the paddle.
     * @param height    height size of the paddle.
     * @param color     color of the paddle.
     * @param paddleSpeed     color of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Point upperLeft, double width, double height,
                  java.awt.Color color, int paddleSpeed) {
        this.keyboard = keyboard;
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * check if left key is pressed in the keyboard, if it is, change the paddle location according to paddle speed.
     * @param dt - determent speed according to the frame rate.
     */
    public void moveLeft(double dt) {
        this.upperLeft = new Point(this.upperLeft.getX() - this.paddleSpeed * dt, this.upperLeft.getY());
    }

    /**
     * check if right key is pressed in the keyboard, if it is, change the paddle location according to paddle speed.
     * @param dt - determent speed according to the frame rate.
     */
    public void moveRight(double dt) {
        this.upperLeft = new Point(this.upperLeft.getX() + this.paddleSpeed * dt, this.upperLeft.getY());
    }

    /**
     * call for right and left moving methods.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // if the current location of the paddle is higher then the left limit, change his upperLeft location.
            if (this.upperLeft.getX() > this.leftLimit) {
                this.moveLeft(dt);
            }
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // if the current location of the paddle is higher then the right limit, change his upperLeft location.
            if ((this.upperLeft.getX() + this.width) < this.rightLimit) {
                this.moveRight(dt);
            }
        }
    }

    /**
     * draw collision.Paddle's shape with his selected color.
     * @param d current DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
        d.setColor(Color.black);
        d.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width, (int) this.height);

    }

    /**
     * create a new Rectangle object with the same size as the paddle, and return the Rectangle object.
     * @return Rectangle object with paddle shape.
     */
    public Rectangle getCollisionRectangle() {
        Rectangle paddleShape = new Rectangle(this.upperLeft, this.width, this.height);
        return paddleShape;
    }

    /**
     * determent where the collisionPoint hits the paddle, change the ball direction according to hit's geometry.Point.
     * @param hitter          ball object that hit the paddle.
     * @param collisionPoint  geometry.Point object that represent the location of collation.
     * @param currentVelocity current ball velocity.
     * @return new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point upLeft = this.upperLeft;
        Point upRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line leftLine = new Line(upLeft, lowerLeft);
        Line topLine = new Line(upLeft, upRight);

        double length = this.width;
        // davide the paddle's top line to 5 equals parts.
        double paddlePartsLength = length / 5;
        // if there is collision with top line,determent ball's angle according to where the collision occur.
        if (collisionPoint.isPointOnLine(topLine)) {
            double hitLocationOnPaddle = collisionPoint.getX() - this.upperLeft.getX();
            // find in which part of the paddle the collision occur.
            int indexPaddlePart = (int) (hitLocationOnPaddle / paddlePartsLength);
            // set the new velocity according to collision place.
            if (indexPaddlePart == 2) {
                Velocity finalv = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                return finalv;
            } else {
                Velocity v = Velocity.fromAngleAndSpeed(300 + (indexPaddlePart) * 30, currentVelocity.getSpeed());
                Velocity finalv = new Velocity(v.getDx(), -1 * v.getDy());
                return finalv;
            }
        } else if (collisionPoint.isPointOnLine(leftLine)) {
            Velocity v = currentVelocity;
            Velocity finalv = new Velocity(-1 * v.getDx(), v.getDy());
            return finalv;
        } else {
            Velocity v = currentVelocity;
            Velocity finalv = new Velocity(-1 * v.getDx(), v.getDy());
            return finalv;
        }
    }

    /**
     * Add this paddle to the game by adding this object to sprite and collision lists.
     * @param g game.GameLevel object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * determent paddle left and right limit.
     * @param leftLim  Left limit of the paddle.
     * @param rightLim Right limit of the paddle.
     */
    public void setPaddleLimit(double leftLim, double rightLim) {
        this.leftLimit = leftLim;
        this.rightLimit = rightLim;
    }

    /**
     * @return get UpperLeft geometry.Point of the paddle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return get width size of the paddle.
     */
    public double getwidth() {
        return this.width;
    }

    /**
     * @return get height size of the paddle.
     */
    public double getheight() {
        return this.height;
    }

    /**
     * set upper-left location of this paddle.
     * @param upperLeft1 new upper-left geometry.Point.
     */
    public void setUpperLeft(Point upperLeft1) {
        this.upperLeft = upperLeft1;
    }
}
