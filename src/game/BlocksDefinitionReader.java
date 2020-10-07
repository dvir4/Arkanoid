/**
 * @author Dvir
 */
package game;

import java.util.Map;

/**
 * read block definition file, and return block Factory object.
 */
public class BlocksDefinitionReader {

    /**
     * read the block definition file,create block creator and space block maps, and create block Factory.
     * @param reader block definition file.
     * @return block factory object.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlockDescriptionScanner bc = new BlockDescriptionScanner();
        bc.blockDescription(reader);
        Map<String, Integer> spaceMap = bc.getSpacerWidths();
        if (spaceMap == null) {
            return null;
        }
        Map<String, BlockCreator> blockCreatorsMap = bc.getBlockCreators();
        if (blockCreatorsMap == null) {
            return null;
        }
        BlocksFromSymbolsFactory blockFactory = new BlocksFromSymbolsFactory(spaceMap, blockCreatorsMap);
        return blockFactory;
    }
}