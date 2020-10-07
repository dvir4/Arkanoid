/**
 * @author Dvir Aliyaho
 */
package game;

import collision.Ball;
import collision.Block;
import collision.HitListener;

/**
 * game.PrintingHitListener class check hitEvent method.
 */
public class PrintingHitListener implements HitListener {
    /**
     * print massage in case of hit between block and ball.
     * @param beingHit collision.Block object that hit by a ball object.
     * @param hitter   collision.Ball object that collide with block object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A collision.Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}