/**
 * @author Dvir
 */
package game;

import collision.Block;

import java.util.Map;

/**
 * block factory, create.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     * @param spacerWidths block's space map.
     * @param blockCreators block creator map.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * @param s String object.
     * @return returns true if String argument is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        if (this.spacerWidths.get(s) != null) {
            return true;
        }
        return false;
    }

    /**
     * @param s String object
     * @return returns true if String argument is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        if (this.blockCreators.get(s) != null) {
            return true;
        }
        return false;
    }

    /**
     * @param s String argument - block Symbol.
     * @param xpos block x position
     * @param ypos block y position
     * @return Return a block according to the definitions associated with the String argument 's',
     * The block will be located at position (xpos, ypos).
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }


    /**
     * @param s String argument - space Symbol.
     * @return Returns the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * @param s String argument - block symbol.
     * @return Returns BlockCreator object associated with the given Block-symbol.
     */
    public BlockCreator getBlockCreator(String s) {
        return this.blockCreators.get(s);
    }
}