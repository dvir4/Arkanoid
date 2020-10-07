/**
 * @author Dvir
 */
package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.util.List;

/**
 * game.GameFlow class in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter currentScore;
    private Counter currentLives;
    private GUI gui;
    private static final int START_LIFE = 7;
    private static final String STOP_KEY = "space";
    private static final int TABLE_SIZE = 5;

    /**
     * game.GameFlow constructor.
     * @param ar  given animation.AnimationRunner object.
     * @param ks  given keyboard.
     * @param gui given gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.currentScore = new Counter(0);
        this.currentLives = new Counter(START_LIFE);
    }

    /**
     * run each level in the game.
     * @param levels list that contains all of game's levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        File file = new File("highscore");
        HighScoresTable playerScores = new HighScoresTable(TABLE_SIZE);
        if (!file.exists()) {
            try {
                playerScores.save(file);
            } catch (Exception e) {
                System.out.println("unable to save file: " + file.getName());
            }
        } else {
            try {
                playerScores = HighScoresTable.loadFromFile(file);
            } catch (Exception e) {
                System.out.println("unable to load file: " + file.getName());
            }
        }

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.animationRunner, this.keyboardSensor, levelInfo,
                    this.currentLives, this.currentScore);
            level.initialize();
            // while level has more blocks and player has more lives run this game.
            while (level.getRemainBlocks() > 0 && level.getRemainLife() > 0) {
                level.playOneTurn();
            }
            // in case the player's counter lives reach to 0, run specific ending animation.
            if (level.getRemainLife() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, STOP_KEY,
                        new EndScreen(this.currentLives, this.currentScore, STOP_KEY)));
                break;
            }
        }
        // in case player pass all the levels, run specific ending animation.
        if (this.currentLives.getValue() > 0) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, STOP_KEY,
                    new EndScreen(this.currentLives, this.currentScore, STOP_KEY)));
        }
        // in case player break a record, add him to the high score table.
        if (playerScores.getRank(this.currentScore.getValue()) <= TABLE_SIZE) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo player = new ScoreInfo(name, this.currentScore.getValue());
            playerScores.add(player);

        }
        // run high score table screen.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, STOP_KEY,
                new HighScoresAnimation(playerScores, STOP_KEY)));
        // save changes in high score table.
        try {
            playerScores.save(file);
        } catch (Exception e) {
            System.out.println("unable to save file: " + file.getName());
        }
    }
}