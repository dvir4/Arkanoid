/**
 * @author Dvir
 */
package game;

import animation.LevelOneBackground;
import collision.Block;
import collision.Velocity;
import geometry.Point;
import sprite.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this interface specifies the information required to fully describe level 1.
 */
public class Level1 implements LevelInformation {
    private int numOfBlocks;
    private static final int NUM_OF_BALLS = 1;
    private static final int PADDLE_SPEED = 5 * 60;
    private static final int PADDLE_WIDTH = 120;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BALL_SPEED = 4 * 60;
    private static final int MAX_ANGLE = 70;

    /**
     * game.Level1 constructor.
     */
    public Level1() {
        this.numOfBlocks = 0;
    }

    /**
     * @return return number of balls in this level.
     */
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * @return return The initial velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List velocityList = new ArrayList<>(NUM_OF_BALLS);
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed((Math.min(i * 10, MAX_ANGLE) * Math.pow(-1, i)), -BALL_SPEED));
        }
        return velocityList;
    }

    /**
     * @return return paddle speed.
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * @return return paddle width.
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * @return return the level name that will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Level 1";
    }

    /**
     * @return returns a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new LevelOneBackground();

    }

    /**
     * @return return the Blocks that make up this level, each block contains, its size, color and location.
     */
    public List<Block> blocks() {
        List listOfBlocks = new ArrayList<>();
        int maxLIfe = 1;
        // create new blocks in specific pattern.
        Block block = new Block(new Point(375, 200), BLOCK_WIDTH, BLOCK_HEIGHT, Color.BLUE.brighter());
        block.setLife(maxLIfe);
        listOfBlocks.add(block);
        this.numOfBlocks++;
        return listOfBlocks;
    }

    /**
     * @return return Number of levels that should be removed before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}

