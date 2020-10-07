/**
 * @author Dvir
 */
package game;

import animation.AnimationRunner;
import biuoop.GUI;
import java.util.List;

/**
 * class that responsible to run the game.
 */
public class RunGame implements Task<Void> {
    private GUI gui;
    private AnimationRunner runner;
    private List<LevelInformation> levels;

    /**
     * constructor.
     * @param gui game's gui object.
     * @param runner game's animation runner.
     * @param levels game's levels.
     */
    public RunGame(GUI gui, AnimationRunner runner, List<LevelInformation> levels) {
        this.gui = gui;
        this.runner = runner;
        this.levels = levels;
    }

    /**
     * create a new game object, initialize the game and run it.
     * @return nothing.
     */
    public Void run() {
        GameFlow runGame = new GameFlow(this.runner, this.gui.getKeyboardSensor(), this.gui);
        runGame.runLevels(this.levels);
        return null;
    }
}
