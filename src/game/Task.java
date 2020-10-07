/**
 * @author Dvir Aliyaho
 */
package game;

/**
 * Task object, contain run method that can return different value's types.
 * @param <T>
 */
public interface Task<T> {
    /**
     * @return run the method.
     */
    T run();
}
