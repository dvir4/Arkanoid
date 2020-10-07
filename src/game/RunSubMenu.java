/**
 * @author Dvir Aliyaho
 */
package game;

import animation.AnimationRunner;
import animation.Menu;

/**
 * run sub menu object.
 */
public class RunSubMenu implements Task<Void> {
    private Menu<Task<Void>> subMenu;
    private AnimationRunner runner;

    /**
     * constructor.
     * @param subMenu sub menu object.
     * @param runner game's animation runner.
     */
    public RunSubMenu(Menu<Task<Void>> subMenu, AnimationRunner runner) {
        this.subMenu = subMenu;
        this.runner = runner;
    }

    /**
     * @return run this sub menu.
     */
    public Void run() {
        runner.run(subMenu);

        Task<Void> task = subMenu.getStatus();
        task.run();
        return null;
    }
}
