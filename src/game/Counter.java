/**
 * @author Dvir
 */
package game;

/**
 * class that use for counting .
 */
public class Counter {
    private int counterValue;

    /**
     * counter constructor.
     * @param number represent value that the counter will be starting counting from.
     */
    public Counter(int number) {
        this.counterValue = number;
    }

    /**
     * add number to current count.
     * @param number nominal number.
     */
    public void increase(int number) {
        this.counterValue = this.counterValue + number;

    }

    /**
     * subtract number from current count.
     * @param number nominal number.
     */
    public void decrease(int number) {
        this.counterValue = this.counterValue - number;
    }

    /**
     * @return get current count value.
     */
    public int getValue() {
        return this.counterValue;
    }
}