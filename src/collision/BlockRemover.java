/**
 * @author Dvir
 */
package collision;


import game.Counter;
import game.GameLevel;

/**
 * collision.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * collision.BlockRemover constructor.
     * @param gameLevel     current game level.
     * @param removedBlocks represent number of remainingBlocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points removed from gameLevel.
     * @param beingHit collision.Block object that was hit by a ball.
     * @param hitter   ball object that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
        }
    }
}