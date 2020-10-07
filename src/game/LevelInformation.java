/**
 * @author Dvir
 */
package game;

import collision.Block;
import collision.Velocity;
import sprite.Sprite;

import java.util.List;

/**
 * this interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {

    /**
     * @return number of balls in this level.
     */
    int numberOfBalls();

    /**
     * @return return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return return paddle width.
     */
    int paddleWidth();

    /**
     * @return return the level name that will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return return the Blocks that make up this level, each block contains, its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return reutrn Number of levels that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}