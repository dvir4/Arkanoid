/**
 * @author Dvir
 */
package game;
import collision.Block;

/**
 * create a new block according to x and y position.
 */
public interface BlockCreator {
    // Create a block at the specified location.

    /**
     *  creating a new block.
     * @param xpos x position of the block.
     * @param ypos y position of the block.
     * @return new Block.
     */
    Block create(int xpos, int ypos);
}
