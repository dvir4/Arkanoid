/**
 * @author Dvir Aliyaho
 */
package game;

import animation.AnimationRunner;
import animation.MenuAnimation;
import biuoop.GUI;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.List;

/**
 * get level set file and convert it to sub menu with each difficulty level.
 */
public class LevelSet {
    /**
     * @param reader FileReader object that contain the level description.
     * @param gui    game gui object.
     * @param runner game Animation runner.
     * @return sub menu object.
     */
    public static MenuAnimation readLevelSet(java.io.Reader reader, GUI gui, AnimationRunner runner) {
        MenuAnimation menu = new MenuAnimation<Task<Void>>("Sub Menu", gui.getKeyboardSensor());
        LevelSpecificationReader levelMaker = new LevelSpecificationReader();
        String key = null;
        String name = null;
        List<LevelInformation> levels;
        // try to read level set file, in case of line odd number, get name and string massage.
        // in case of even line number,get level set path and create list of levels .
        try {
            LineNumberReader read = new LineNumberReader(reader);
            String line = read.readLine();
            while (line != null) {
                if (read.getLineNumber() % 2 != 0) {
                    String[] parts = line.split(":");
                    key = parts[0];
                    name = parts[1];
                } else {
                    if (key != null && name != null) {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                        Reader reader1 = new InputStreamReader(is);
                        levels = levelMaker.fromReader(reader1);
                        menu.addSelection(key, name, new RunGame(gui, runner, levels));
                        key = null;
                        name = null;
                    }
                }
                line = read.readLine();
            }
        } catch (Exception e) {
            System.out.println("cannot read file: LevelSet");
        }
        return menu;
    }
}