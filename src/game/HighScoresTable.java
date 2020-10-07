/**
 * @author Dvir
 */
package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * high score table responsible to show top's players high score.
 */
public class HighScoresTable {
    private List<ScoreInfo> scoreList;
    private static final String SEPARATOR = ":";
    private static final int MAX_SIZE = 5;

    /**
     * Create an empty high-scores table with the specified size.
     * @param size table size.
     */
    public HighScoresTable(int size) {
        this.scoreList = new ArrayList<>(size);
    }


    /**
     * add a player to high score table.
     * @param score player score.
     */
    public void add(ScoreInfo score) {
        this.scoreList.add(score);
        // in case high score table reach to max size, remove player with the lowest score.
        if (this.scoreList.size() > MAX_SIZE) {
            this.scoreList.remove(findLowestScoreIndex());
        }
    }

    /**
     * @return Return table size.
     */
    public int size() {
        return this.scoreList.size();
    }

    /**
     * @return Return the current high scores object, sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        List<ScoreInfo> highScoresList = new ArrayList<>(this.scoreList);
        bubbleSort(highScoresList);
        return highScoresList;
    }

    /**
     * return the rank of the current score. Rank 1 means the score will be highest on the list.
     * @param score player score.
     * @return rank value of the player high score.
     */
    public int getRank(int score) {
        List<ScoreInfo> sortedScoreList = getHighScores();
        for (int i = 0; i < sortedScoreList.size(); i++) {
            if (sortedScoreList.get(i).getScore() < score) {
                return (i + 1);
            }
        }
        return sortedScoreList.size() + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreList.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     * @param filename high score table file.
     * @throws IOException in case of loading error, throw exception.
     */
    public void load(File filename) throws IOException {
        BufferedReader reader = null;
        StringBuilder contentBuffer = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuffer.append(line.trim());
                String[] parts = contentBuffer.toString().split(SEPARATOR);
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);

                this.scoreList.add(new ScoreInfo(name, score));
                contentBuffer.delete(0, contentBuffer.length());
            }
        } catch (IOException e) {
            System.err.println("Failed reading file: " + filename.getName()
                    + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("failed closing the file!" + filename.getName());
                }
            }
        }
    }

    /**
     * Save table data to the specified file.
     * @param filename high score table file.
     * @throws IOException in case of saving error, throw exception.
     */
    public void save(File filename) throws IOException {
        PrintWriter writer = null;
        StringBuilder sb = new StringBuilder();

        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            List<ScoreInfo> sortList = getHighScores();
            for (int i = 0; i < sortList.size(); i++) {
                sb.append(this.scoreList.get(i).getName());
                sb.append(SEPARATOR);
                sb.append(this.scoreList.get(i).getScore());

                writer.println(sb.toString());
                sb.delete(0, sb.length());
                //writer.println(this.scoreList.get(i).getName() + ":" + this.scoreList.get(i).getScore());
                //writer.println(this.scoreList.get(i).getScore());
            }
        } catch (IOException e) {
            System.out.println("invalid writing! " + filename.getName());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Read a table from file and return it. return empty table in case of error.
     * @param filename high score table file.
     * @return high score table object.
     */
    public static HighScoresTable loadFromFile(File filename) {
        BufferedReader reader = null;
        HighScoresTable highScoresTable = new HighScoresTable(10);
        StringBuilder contentBuffer = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            // print each read line
            String line = reader.readLine();
            while (line != null) {
                contentBuffer.append(line.trim());
                String[] parts = contentBuffer.toString().split(SEPARATOR);
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);

                highScoresTable.scoreList.add(new ScoreInfo(name, score));
                contentBuffer.delete(0, contentBuffer.length());
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
        } catch (IOException e) {
            System.err.println("Failed reading file: " + filename.getName()
                    + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
        return highScoresTable;
    }

    /**
     * sort high score list base on bubbleSort algorithm.
     * @param scoreLists high score table.
     */
    public void bubbleSort(List<ScoreInfo> scoreLists) {
        ScoreInfo temp;
        for (int i = 0; i < scoreLists.size(); i++) {
            for (int j = 0; j < scoreLists.size() - 1 - i; j++) {
                if (scoreLists.get(j).getScore() < scoreLists.get(j + 1).getScore()) {
                    temp = scoreLists.get(j);
                    scoreLists.set(j, scoreLists.get(j + 1));
                    scoreLists.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * @return return the index value of the player with the lowest score.
     */
    public int findLowestScoreIndex() {
        int minIndex = 0;
        for (int i = 1; i < this.scoreList.size(); i++) {
            if (this.scoreList.get(i).getScore() <= this.scoreList.get(minIndex).getScore()) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
