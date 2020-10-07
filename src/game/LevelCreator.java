/**
 * @author Dvir
 */
package game;

import collision.Block;
import collision.Velocity;
import sprite.Sprite;

import java.util.List;

/**
 * Create a new level object.
 */
public class LevelCreator implements LevelInformation {
    private int numBalls;
    private List<Velocity> ballVelocities;
    private int pSpeed;
    private int pWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numBlocks;

    /**
     * constructor.
     * @param numberOfBalls  numbers of balls.
     * @param ballVelocities ball velocities.
     * @param paddleSpeed    paddle speed.
     * @param paddleWidth    paddle width.
     * @param background     level background.
     * @param blocks         list of blocks.
     * @param numberOfBlocks number of blocks.
     */
    public LevelCreator(int numberOfBalls, List<Velocity> ballVelocities, int paddleSpeed, int paddleWidth,
                         Sprite background, List<Block> blocks, int numberOfBlocks) {
        this.numBalls = numberOfBalls;
        this.ballVelocities = ballVelocities;
        this.pSpeed = paddleSpeed;
        this.pWidth = paddleWidth;
        this.background = background;
        this.blocks = blocks;
        this.numBlocks = numberOfBlocks;
    }


    /**
     * @return return number of balls.
     */
    public int numberOfBalls() {
        return this.numBalls;
    }

    /**
     * @return return ball velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * @return return paddle speed.
     */
    public int paddleSpeed() {
        return this.pSpeed;
    }

    /**
     * @return return paddle width.
     */
    public int paddleWidth() {
        return this.pWidth;
    }

    /**
     * @return return level name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return return level background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @return return level's block.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return return number of block in this level.
     */
    public int numberOfBlocksToRemove() {
        return this.numBlocks;
    }

    /**
     * set level name.
     * @param levelName1 level name.
     */
    public void setLevelName(String levelName1) {
        this.levelName = levelName1;
    }
}
