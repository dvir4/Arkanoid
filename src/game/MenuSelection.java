/**
 * @author Dvir
 */
package game;

/**
 * create a menu selection object that contain key, String massage, and value according to key value.
 * @param <T> anonymous type.
 */
public class MenuSelection<T> {
    private String key;
    private String massage;
    private T returnValue;

    /**
     * constructor.
     * @param key         String of a key.
     * @param massage     String massage.
     * @param returnValue value that will return according to key value.
     */
    public MenuSelection(String key, String massage, T returnValue) {
        this.key = key;
        this.massage = massage;
        this.returnValue = returnValue;
    }

    /**
     * @return return key value.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return return String massage.
     */
    public String getMassage() {
        return this.massage;
    }

    /**
     * @return return value according to which key was pressing.
     */
    public T getValue() {
        return this.returnValue;
    }
}
