package animation;
import game.Task;

/**
 * run high score table animation.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * constructor.
     * @param runner animation runner.
     * @param highScoresAnimation high score animation object.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * run and show this high score table.
     * @return nothing.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
