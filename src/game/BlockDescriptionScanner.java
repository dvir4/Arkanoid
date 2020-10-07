/**
 * @author Dvir
 */
package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * read block definitions file, and create the necessary maps.
 */
public class BlockDescriptionScanner {
    private Map<String, Integer> spacerWidths = new HashMap<String, Integer>();
    private Map<String, BlockCreator> blockCreators = new HashMap<String, BlockCreator>();
    private Map<Integer, Fill> symboColor;
    private int height = 0;
    private int width = 0;
    private int hitPoints = 0;
    private java.awt.Color strokeColor = null;

    /**
     * read block definition file and convert it to map.
     * @param reader block definition file.
     */
    public void blockDescription(java.io.Reader reader) {
        List<String> blockInfo = new ArrayList<>();
        try {
            BufferedReader read = new BufferedReader(reader);
            String line = read.readLine();
            while (line != null) {
                if (line.startsWith("#") || line.isEmpty()) {
                    line = read.readLine();
                } else {
                    blockInfo.add(line.trim());
                    line = read.readLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Failed reading file");
        }
        for (int i = 0; i < blockInfo.size(); i++) {
            createBlock(blockInfo.get(i));
        }
    }

    /**
     *
     * @param line a String line from block definition txt file.
     */
    public void createBlock(String line) {
        String[] parts = line.split("\\s+");
        for (int i = 1; i < parts.length; i++) {
            if (parts[0].contains("default")) {
                String[] blockParts = parts[i].split(":");
                if (blockParts[0].contains("height")) {
                    this.height = Integer.parseInt(blockParts[1]);
                } else if (blockParts[0].equals("width")) {
                    this.width = Integer.parseInt(blockParts[1]);
                } else if (blockParts[0].equals("hit_points")) {
                    this.hitPoints = Integer.parseInt(blockParts[1]);
                } else if (blockParts[0].equals("stroke")) {
                    strokeColor = new ColorsAndImageParser().colorFromString(blockParts[1]);
                }

            } else if (parts[0].contains("sdef")) {
                addSpacersToMap(line);
                break;
            } else if (parts[0].contains("bdef")) {
                addBlockCreator(line);
                break;
            }
        }
    }

    /**
     * create a space block map.
     * @param line block space description.
     */
    public void addSpacersToMap(String line) {
        String spaceSign = null;
        int spaceWidth = -1;
        String[] parts = line.split("\\s+");
        for (int i = 1; i < parts.length; i++) {
            String[] spaceParts = parts[i].split(":");
            if (spaceParts[0].equals("symbol")) {
                spaceSign = spaceParts[1];
            } else if (spaceParts[0].equals("width")) {
                spaceWidth = Integer.parseInt(spaceParts[1]);
            }
        }
        if (spaceSign != null && spaceWidth != -1) {
            this.spacerWidths.put(spaceSign, spaceWidth);
        }
    }

    /**
     * add block creator object to the map.
     * @param line special block definition.
     */
    public void addBlockCreator(String line) {
        String symbol = null;
        int lifePoints = this.hitPoints;
        int blockHeight = this.height;
        int blockWidth = this.width;
        Map<Integer, Fill> symboColors = new HashMap<>();
        String[] parts = line.split("\\s+");
        for (int i = 1; i < parts.length; i++) {
            String[] blockParts = parts[i].split(":");
            if (blockParts[0].equals("symbol")) {
                symbol = blockParts[1];
            } else if (blockParts[0].equals("hit_points")) {
                lifePoints = Integer.parseInt(blockParts[1]);
            } else if (blockParts[0].equals("fill")) {
                Fill background = new ColorsAndImageParser().getBackground(blockParts[1]);
                symboColors.put(1, background);
            } else if (blockParts[0].contains("fill-")) {
                Fill background = new ColorsAndImageParser().getBackground(blockParts[1]);
                String[] fillParts = blockParts[0].split("-");
                int lifePoint = Integer.parseInt(fillParts[1]);
                symboColors.put(lifePoint, background);
            } else if (blockParts[0].contains("width")) {
                blockWidth = Integer.parseInt(blockParts[1]);
            } else if (blockParts[0].contains("height")) {
                blockHeight = Integer.parseInt(blockParts[1]);
            }
        }
        if (blockWidth != 0 && blockHeight != 0 && !symboColors.isEmpty() && lifePoints != 0) {
            BlockCreator block = new CreateBlock(blockWidth,  blockHeight,
                    symboColors, lifePoints, this.strokeColor);
            blockCreators.put(symbol, block);
        }
    }

    /**
     * @return return block space map.
     */
    public Map<String, Integer> getSpacerWidths() {
        return this.spacerWidths;
    }

    /**
     * @return return block creators map.
     */
    public Map<String, BlockCreator> getBlockCreators() {
        return this.blockCreators;
    }

}
