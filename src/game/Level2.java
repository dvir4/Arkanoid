/**
 * @author Dvir
 */
package game;

import animation.LevelTwoBackground;
import collision.Block;
import collision.Velocity;
import geometry.Point;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this interface specifies the information required to fully describe level 4.
 */
public class Level2 implements LevelInformation {
    private int numOfBlocks;
    private static final int NUM_OF_BALLS = 10;
    private static final int PADDLE_SPEED = 4 * 60;
    private static final int PADDLE_WIDTH = 450;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BALL_SPEED = 4 * 60;
    private static final int MAX_ANGLE = 70;

    /**
     * game.Level2 constructor.
     */
    public Level2() {
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
        for (int i = 1; i <= NUM_OF_BALLS; i++) {
            // set velocity of each ball.
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
        return "Level 2";
    }

    /**
     * @return returns a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new LevelTwoBackground();

    }

    /**
     * @return return the Blocks that make up this level, each block contains, its size, color and location.
     */
    public List<Block> blocks() {
        List listOfBlocks = new ArrayList<>();
        // list of colors.
        java.awt.Color[] colors = {Color.blue, Color.yellow,
                Color.red, Color.pink, Color.cyan.brighter()};
        int maxLIfe = 1;
        // create new blocks in specific pattern.
        for (int k = 1; k <= 1; k++) {
            for (int i = 1; i <= 15; i++) {
                Block block = new Block(new Point(775 - (i * 50), 320 - k * 30), BLOCK_WIDTH,
                        BLOCK_HEIGHT, colors[i % 5]);
                block.setLife(maxLIfe);
                listOfBlocks.add(block);
                this.numOfBlocks++;
            }
        }
        return listOfBlocks;
    }

    /**
     * @return return Number of levels that should be removed before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}
