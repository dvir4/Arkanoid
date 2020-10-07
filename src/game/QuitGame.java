/**
 * @author Dvir
 */
package game;
import biuoop.GUI;

/**
 * quit game class.
 */
public class QuitGame implements Task<Void> {
    private GUI gui;

    /**
     * constructor.
     * @param gui game's gui object.
     */
    public QuitGame(GUI gui) {
        this.gui  = gui;
    }

    /**
     * @return close the gui and exit this code.
     */
    public Void run() {
        this.gui.close();
        System.exit(0);
        return null;
    }
}
