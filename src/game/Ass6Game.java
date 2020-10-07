/**
 * @author Dvir
 */
package game;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import animation.ShowHiScoresTask;
import biuoop.GUI;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * class that responsible to run the game.
 */
public class Ass6Game {
    public static final int GUI_HEIGHT = 600;
    public static final int GUI_WIDTH = 800;
    public static final int TABLE_SIZE = 5;

    /**
     * create a new game object, initialize the menu and run it.
     * @param args the arguments determent as path to level set file.
     */
    public static void main(String[] args) {
        String path;
        if (args.length != 0) {
            path = args[0];
        } else {
            path = "level_sets.txt";
        }
        final String levelSetPath = path;
        // load high score table.
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
        GUI gui = new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", gui.getKeyboardSensor());

        Task<Void> runGame = new Task<Void>() {
            public Void run() {
                try {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetPath);
                    Reader reader = new InputStreamReader(is);
                    Menu subMenu = LevelSet.readLevelSet(reader, gui, runner);
                    runner.run(subMenu);
                    Task<Void> task = (Task<Void>) subMenu.getStatus();
                    task.run();
                } catch (Exception e) {
                    System.out.println("error, cannot read level sets file: " + levelSetPath);
                }

                return null;
            }
        };
        menu.addSelection("s", "Press \"s\" to start a new game.", runGame);
        HighScoresAnimation scoreAnimation = new HighScoresAnimation(playerScores, "space");
        menu.addSelection("h", "Press \"h\" to see the high scores.",
                new ShowHiScoresTask(runner, new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "space",
                        scoreAnimation)));
        menu.addSelection("q", "Press \"q\" to quit.", new QuitGame(gui));

        while (true) {

            runner.run(menu);

            Task<Void> task = menu.getStatus();
            task.run();
            playerScores.clear();
            try {
                playerScores.load(file);
            } catch (Exception e) {
                System.out.println("unable to load file: " + file.getName());
            }
        }
    }
}
