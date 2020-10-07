/**
 * @author Dvir
 */
package collision;

import game.Counter;
import game.GameLevel;

/**
 * collision.BallRemover will remove the ball from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * collision.BallRemover constructor.
     * @param gameLevel    current game level.
     * @param removedBalls number of the remaining balls in this current game level.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * removing ball object from the game.
     * @param beingHit specific block that hit by the ball and function as a "death region".
     * @param hitter   specific ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
