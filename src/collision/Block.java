/**
 * @author Dvir Aliyaho
 */
package collision;

import biuoop.DrawSurface;
import game.Fill;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import sprite.Sprite;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * this class define all blocks object in this game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;
    private int currentLife;
    private List<HitListener> hitListeners;
    private Map<Integer, Fill> background;
    private java.awt.Color stroke;

    /**
     * collision.Block constructor,Create a new rectangle with location and width/height, and color.
     * @param upperLeft the upper-left geometry.Point of the block Rectangle shape.
     * @param width     block width.
     * @param height    block height.
     * @param color     block color.
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * another block constructor.
     * @param upperLeft  the upper-left geometry Point of the block Rectangle shape.
     * @param width      block width.
     * @param height     block height.
     * @param background block background.
     * @param lifePoints block hit points.
     * @param stroke     block's stroke color.
     */
    public Block(Point upperLeft, double width, double height, Map<Integer, Fill> background,
                 int lifePoints, Color stroke) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.stroke = stroke;
        this.currentLife = lifePoints;
        this.background = background;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Return the width of the rectangle.
     * @return collision.Block's width value.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     * @return collision.Block's height value.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return the upper-left geometry.Point of the rectangle.
     * @return upper-left geometry.Point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return Return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        Rectangle blockShape = new Rectangle(this.upperLeft, this.width, this.height);
        return blockShape;
    }

    /**
     * determent where the collisionPoint hits the rectangle,
     * change the ball direction according to hit's geometry Point.
     * @param hitter          ball object that hit the collision object.
     * @param collisionPoint  geometry.Point that represent the hit location.
     * @param currentVelocity current velocity of the selected ball.
     * @return new velocity;
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.decreaseLife();
        this.notifyHit(hitter);
        Point upLeft = this.upperLeft;
        Point upRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + this.height);

        Line leftLine = new Line(upLeft, lowerLeft);
        Line rightLine = new Line(upRight, lowerRight);
        // check if there is collision between the collision point to left/right line.
        if (collisionPoint.isPointOnLine(leftLine) || collisionPoint.isPointOnLine(rightLine)) {
            Velocity v = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            return v;
        } else {
            Velocity v = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            return v;
        }
    }

    /**
     * draw each block, and find the center of each block and print his lifePoints.
     * @param d gui draw-surface.
     */
    public void drawOn(DrawSurface d) {
        if (!(this.background == null)) {
            if (this.background.containsKey(this.currentLife)) {
                if (background.get(this.currentLife).getFillColor() != null) {
                    d.setColor(background.get(this.currentLife).getFillColor());
                    d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                            (int) this.width, (int) this.height);
                } else if (background.get(this.currentLife).getFillImage() != null) {
                    d.drawImage((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                            background.get(this.currentLife).getFillImage());
                }
            }
            if (this.stroke != null) {
                d.setColor(this.stroke);
                d.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                        (int) this.width, (int) this.height);
            }
        } else {
            d.setColor(this.getColor());
            d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                    (int) this.width, (int) this.height);
            d.setColor(Color.black);
            d.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                    (int) this.width, (int) this.height);
        }
    }

    /**
     * do nothing.
     * @param dt change speed according to frame rate.
     */
    public void timePassed(double dt) {
    }

    /**
     * add this block to relevant lists in gameLevel Object.
     * @param gameLevel gameLevel object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * decrease Life points of the ball by 1.
     */
    public void decreaseLife() {
        if (this.currentLife > 0) {
            currentLife -= 1;
        } else {
            this.currentLife = 0;
        }
    }

    /**
     * set collision.Block life points.
     * @param life current life points of the ball.
     */
    public void setLife(int life) {
        this.currentLife = life;
    }

    /**
     * get collision.Block color.
     * @return Color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * remove this block from relevant lists in gameLevel Object.
     * @param gameLevel gameLevel object.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * add object that will be notify in case of a hit.
     * @param hl collision.HitListener object.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * remove object that define as collision.HitListener object.
     * @param hl collision.HitListener object.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit event.
     * @param hitter - collision.Ball object that hit specific block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        if (!(listeners.isEmpty())) {
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }

    /**
     * @return return collision.Block current life point.
     */
    public int getHitPoints() {
        return this.currentLife;
    }
}
