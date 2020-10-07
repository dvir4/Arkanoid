/**
 * @author Dvir Aliyaho
 */
package game;
import collision.Ball;
import collision.Block;
import collision.HitListener;


/**
 * this class responsible to count player's score during the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private static final int HITTING_BLOCK_POINTS = 5;
    private static final int DESTROYING_BLOCK_POINTS = 15;

    /**
     * game.ScoreTrackingListener constructor.
     * @param scoreCounter current player's score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitting a block is worth 5 points, and destroying a block is worth and additional 10 points.
     * @param beingHit collision.Block object that hit by a ball object.
     * @param hitter   collision.Ball object that collide with block object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() > 0) {
            this.currentScore.increase(HITTING_BLOCK_POINTS);
        } else {
            this.currentScore.increase(DESTROYING_BLOCK_POINTS);
        }
    }

    /**
     * @return return player's current score.
     */
    public Counter getCounter() {
        return this.currentScore;
    }

}