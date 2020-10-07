package animation;

/**
 * Menu animation.
 * @param <T> Anonymous type
 */
public interface Menu<T> extends Animation {
    /**
     * add Option to the menu.
     * @param key String argument that determent the option.
     * @param message massage that describe the option.
     * @param returnVal  antoninus arg.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return get the status of the menu.
     */
    T getStatus();

    /**
     *  add sup menu to the main menu.
     * @param key key value of the action.
     * @param message  string massage.
     * @param subMenu menu that represent the difficulty options.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
