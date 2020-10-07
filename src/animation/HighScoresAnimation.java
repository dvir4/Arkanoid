package animation;

import biuoop.DrawSurface;
import game.HighScoresTable;
import game.ScoreInfo;

import java.awt.Color;
import java.util.List;

/**
 * create a High score table animation object.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private String endKey;
    private boolean stop;
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * Constructor of this class.
     * @param scores contains the scores table.
     * @param endKey specific key value that stop the animation runner.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey) {
        this.scores = scores;
        this.endKey = endKey;
        this.stop = false;
    }

    /**
     * draw specific sprite objects in each frame.
     * @param d selected gui DrawSurface.
     * @param dt change speed according to frame rate.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // draw background.
        d.setColor(Color.blue.brighter().brighter());
        d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
        List<ScoreInfo> playerScores = this.scores.getHighScores();
        // draw high score table.
        d.setColor(Color.yellow.brighter().brighter());
        d.drawText(d.getWidth() / 4, d.getHeight() / 10, "Top players scores", 32);
        d.drawLine(d.getWidth() / 12, d.getHeight() / 7, d.getWidth() - 200, d.getHeight() / 7);
        d.drawLine(d.getWidth() / 10, d.getHeight() / 14, d.getWidth() / 10, d.getHeight() - 10);
        for (int i = 0; i < playerScores.size(); i++) {
            d.drawText(d.getWidth() / 8, d.getHeight() / 5 + 50 * i, playerScores.get(i).getName(), 20);
            d.drawText(d.getWidth() / 2, d.getHeight() / 5 + 50 * i, "Score: " + playerScores.get(i).getScore(), 20);
        }
        // draw stop animation key text.
        d.setColor(Color.white);
        d.drawText(d.getWidth() - 270, d.getHeight() - 30, "press " + this.endKey + " key to continue", 18);
    }

    /**
     * this function is charge of stopping condition.
     * @return return yes if there is a need to stopping,return no otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
