/**
 * @author Dvir
 */
package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * read level description text and return list of levels.
 */
public class LevelSpecificationReader {
    private List<LevelInformation> listOfLevels;
    private int numOfBalls;

    private static final String TEXT_SPLIT = "%";

    /**
     * constructor of this object.
     */
    public LevelSpecificationReader() {
        this.listOfLevels = new ArrayList<>();
        this.numOfBalls = 0;
    }

    /**
     * read level description and create list of levels objects.
     * @param reader level description file.
     * @return list of levels objects.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> gameLevels = new ArrayList<>();
        List<List<String>> levels = getLevels(reader);
        for (int i = 0; i < levels.size(); i++) {
            // create each level in the file description.
            LevelFactory levelMaker =  new LevelFactory(levels.get(i));
             LevelInformation  finalLevel = levelMaker.getLevel();
            gameLevels.add(finalLevel);
        }
        return gameLevels;
    }

    /**
     * read the level description, and separate each level to List of String arguments.
     * @param reader level description reader file.
     * @return list that contains list of levels arguments. each level got his string list.
     */
    public List<List<String>> getLevels(java.io.Reader reader) {
        StringBuilder contentBuffer = new StringBuilder();
        List<List<String>> levels = new ArrayList<>();
        try {
            BufferedReader read = new BufferedReader(reader);
            String line = read.readLine();
            // combine all File's lines to one line, use '#' sign as separator.
            while (line != null) {
                contentBuffer.append(line.trim());
                contentBuffer.append(TEXT_SPLIT);
                line = read.readLine();
            }
            // separate each level.
            String[] levelsDescription = contentBuffer.toString().split(TEXT_SPLIT);
            // for each level, create a list of string actions.
            for (int k = 0; k < levelsDescription.length; k++) {
                List<String> level = new ArrayList<>();
                while (!(levelsDescription[k].equals("START_LEVEL"))) {
                    k++;
                }
                while (!(levelsDescription[k].equals("END_LEVEL"))) {
                    k++;
                    level.add(levelsDescription[k]);
                }
                levels.add(level);
            }
        } catch (IOException e) {
            System.err.println("Failed reading file");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
        return levels;
    }
}
