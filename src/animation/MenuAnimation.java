
package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.MenuSelection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * menuAnimation object, run the menu.
 * @param <T> anonymous arg.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String menuTitle;
    private KeyboardSensor keyboard;
    private List<MenuSelection<T>> menuOptions;
    private T chosenValue;
    private Boolean stop;
    private Boolean isAlreadyPressed;
    private Menu<T> subMenu;

    /**
     * constructor.
     * @param menuTitle title of this menu.
     * @param keyboard keyboard.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keyboard) {
        this.menuTitle = menuTitle;
        this.keyboard = keyboard;
        this.menuOptions = new ArrayList<>();
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * add option to the menu.
     * @param key String argument that determent the option.
     * @param message massage that describe the option.
     * @param returnVal  antoninus arg.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.menuOptions.add(new MenuSelection<>(key, message, returnVal));
    }

    /**
     * @return get menu status.
     */
    public T getStatus() {
        return this.chosenValue;
    }

    /**
     * rub one single frame of this animation.
     * @param d selected gui DrawSurface.
     * @param dt change speed according to frame rate.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText(d.getWidth() / 4, 100, this.menuTitle, 50);
        for (int i = 0; i < this.menuOptions.size(); i++) {
            d.setColor(Color.cyan.darker());
            d.fillRectangle(150, d.getHeight() / 2 - 35 - 55 * i, 450, 50);
            d.setColor(Color.white);
            d.drawText(d.getWidth() / 5, d.getHeight() / 2 - 55 * i,
                    "<" + this.menuOptions.get(this.menuOptions.size() - 1 - i).getKey() + "> "
                            + this.menuOptions.get(this.menuOptions.size() - 1 - i).getMassage(), 25);
        }
    }

    /**
     * check if the animation should stop or not.
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        for (int i = 0; i < this.menuOptions.size(); i++) {
            if (this.keyboard.isPressed(this.menuOptions.get(i).getKey()) && !(this.isAlreadyPressed)) {
                this.chosenValue = this.menuOptions.get(i).getValue();
                return true;
            }
            if (!(this.keyboard.isPressed(this.menuOptions.get(i).getKey()))) {
                isAlreadyPressed = false;
            }
        }
        return  false;
    }

    /**
     * @param key      key value of the action.
     * @param message  string massage.
     * @param menu     sub menu - split the difficulty levels.
     */
    public void addSubMenu(String key, String message, Menu<T> menu) {
        addSelection(key, message, (T) menu);
        //this.menuOptions.add(key, message,  menu);
        return;
    }
}
