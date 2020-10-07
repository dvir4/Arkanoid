/**
 * @author Dvir
 */
package game;

import collision.Block;
import geometry.Point;

import java.awt.Color;
import java.util.Map;

/**
 * create new block.
 */
public class CreateBlock implements BlockCreator {
    private double width;
    private double height;
    private Map<Integer, Fill> background;
    private int currentLife;
    private java.awt.Color stroke;

    /**
     * constructor.
     * @param width      block width.
     * @param height     block height.
     * @param background block background.
     * @param lifePoints block life points.
     * @param stroke     block stroke.
     */
    public CreateBlock(double width, double height, Map<Integer, Fill> background, int lifePoints, Color stroke) {
        this.width = width;
        this.height = height;
        this.stroke = stroke;
        this.currentLife = lifePoints;
        this.background = background;
    }

    /**
     * create a new block.
     * @param xpos x position of the block.
     * @param ypos y position of the block.
     * @return Block object.
     */
    public Block create(int xpos, int ypos) {
        // change block method.
        Point upperLeft = new Point(xpos, ypos);
        Block block = new Block(upperLeft, this.width, this.height, this.background, this.currentLife, this.stroke);
        return block;
    }

    /**
     * @return return the width of the block.
     */
    public double getWidth() {
        return this.width;
    }
}
