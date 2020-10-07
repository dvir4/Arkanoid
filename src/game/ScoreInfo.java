/**
 * @author Dvir
 */
package game;

/**
 * object that contain name and score of each player.
 */
public class ScoreInfo {
    private String name;
    private int score;

    /**
     * constructor.
     * @param name player name.
     * @param score player score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return return player name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return return player score.
     */
    public int getScore() {
        return this.score;
    }
}
