/**
 * @author Dvir
 */
package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import animation.ScoreIndicator;
import animation.ShowLevelName;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.BallRemover;
import collision.Block;
import collision.GameEnvironment;
import collision.Paddle;
import collision.BlockRemover;
import collision.Collidable;
import collision.Ball;
import collision.Velocity;
import geometry.Point;
import sprite.Sprite;
import sprite.SpriteCollection;

import java.awt.Color;
import java.util.List;

/**
 * game.GameLevel class responsible for initialise and running the game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter currentBlocks;
    private Counter currentBalls;
    private Counter currentScore;
    private Counter currentLives;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private Paddle paddle;
    private LevelInformation currLevel;

    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int BOUNDARY_WIDTH = 25;
    private static final int BALL_SIZE = 5;
    private static final int PADDLE_HEIGHT = 15;
    private static final int NUM_OF_SECONDS = 2;
    private static final int COUNT_FROM = 3;
    private static final int BONUS_POINTS = 100;

    /**
     * Create a new game object. create new lists of collision.Collidable and sprites objects, set gui object.
     * @param ar - animation.Animation runner.
     * @param ks - computer keyboard.
     * @param selectedLevel - chosen level.
     * @param currentLives - current number of lives.
     * @param currentScore - current Score.
     */
    public GameLevel(AnimationRunner ar, KeyboardSensor ks, LevelInformation selectedLevel,
                     Counter currentLives, Counter currentScore) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.keyboard = ks;
        this.currentBlocks = new Counter(0);
        this.currentBalls = new Counter(0);
        this.currentScore = currentScore;
        this.currentLives = currentLives;
        this.runner = ar;
        this.running = true;
        this.currLevel = selectedLevel;
    }

    /**
     * add collision.Collidable object to the game.GameLevel's collision.Collidable list.
     * @param c collision.Collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite.Sprite object to the game.GameLevel's Sprites list.
     * @param s sprite.Sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove collision.Collidable object to the game.GameLevel's collision.Collidable list.
     * @param c collision.Collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove sprite.Sprite object to the game.GameLevel's Sprites list.
     * @param s sprite.Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game, create all the relevant game objects, and add them to the game.
     */
    public void initialize() {
        // add current level background to the game.
        this.addSprite(this.currLevel.getBackground());
        // create a new paddle, set his limit and add him to the game.
        this.paddle = new Paddle(this.keyboard, new Point(GUI_WIDTH / 2, GUI_HEIGHT - BOUNDARY_WIDTH - 15),
                this.currLevel.paddleWidth(), PADDLE_HEIGHT, Color.gray.darker(), this.currLevel.paddleSpeed());

        this.paddle.setPaddleLimit(BOUNDARY_WIDTH, GUI_WIDTH - BOUNDARY_WIDTH);
        this.paddle.addToGame(this);


        BallRemover ballRemover = new BallRemover(this, this.currentBalls);
        // create gui bounderys by setting 4 blocks as a frame, and add them to the game.
        Block leftLimitBlock = new Block(new Point(0, 0), BOUNDARY_WIDTH, GUI_HEIGHT, Color.gray);
        Block rightLimitBlock = new Block(new Point(775, 0), BOUNDARY_WIDTH, GUI_HEIGHT, Color.gray);
        Block topLimitBlock = new Block(new Point(0, BOUNDARY_WIDTH),
                GUI_WIDTH, BOUNDARY_WIDTH, Color.gray);
        Block botLimitBlock = new Block(new Point(0, 575), GUI_WIDTH, BOUNDARY_WIDTH, Color.gray);
        leftLimitBlock.addToGame(this);
        rightLimitBlock.addToGame(this);
        topLimitBlock.addToGame(this);
        botLimitBlock.addHitListener(ballRemover);
        botLimitBlock.addToGame(this);
        this.removeSprite(botLimitBlock);
        // create scoreBoard block.
        Block scoreBoard = new Block(new Point(0, 0), GUI_WIDTH, BOUNDARY_WIDTH, Color.white);
        scoreBoard.addToGame(this);
        // create a blockRemover object.
        BlockRemover blockRemover = new BlockRemover(this, this.currentBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.currentScore);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.currentScore);
        this.addSprite(scoreIndicator);
        LivesIndicator livesIndicator = new LivesIndicator(this.currentLives);
        this.addSprite(livesIndicator);

        // add blocks of current level to the game.
        List<Block> blockList = this.currLevel.blocks();
        for (int i = 0; i < blockList.size(); i++) {
            blockList.get(i).addHitListener(blockRemover);
            blockList.get(i).addHitListener(scoreTrackingListener);
            blockList.get(i).addToGame(this);
            this.currentBlocks.increase(1);
        }
        // add level name sprite.Sprite object to the game.
        this.addSprite(new ShowLevelName(this.currLevel));
    }
    /**
     * start and set the animation loop.
     */
    public void playOneTurn() {
        // set the turn with determent number of balls, and place the paddle in the middle of the screen.
        restartBall(this.currLevel.numberOfBalls());
        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, this.sprites));
        this.running = true;
        this.runner.run(this);

    }

    /**
     * run game level until player reach to 0 lives or clear all the blocks in this level.
     */
    public void run() {
        while (this.currentLives.getValue() > 0 && this.currentBlocks.getValue() > 0) {
            this.playOneTurn();
            this.currentLives.decrease(1);
        }
    }

    /**
     * create certain amount of ball objects according to level demands, locate paddle in the middle of the screen.
     * @param number number of ball objects we want to create in this level.
     */
    public void restartBall(int number) {
        List<Velocity> velocityList = this.currLevel.initialBallVelocities();
        if (this.paddle != null) {
            // set paddle in the middle of the screen.
            this.paddle.setUpperLeft(new Point(GUI_WIDTH / 2 - this.currLevel.paddleWidth() / 2,
                    GUI_HEIGHT - BOUNDARY_WIDTH - PADDLE_HEIGHT));
            // create ball objects.
            for (int i = 0; i < number; i++) {
                Ball ball = new Ball(new Point(GUI_WIDTH / 2  , GUI_HEIGHT - BOUNDARY_WIDTH - 22),
                        BALL_SIZE, Color.white);
                ball.setVelocity(velocityList.get(i));
                ball.setGameEnvironment(this.environment);
                ball.addToGame(this);
                this.currentBalls.increase(1);
            }
        }
    }

    /**
     * draw specific sprite objects in each frame, and check for stopping condition.
     * @param d selected gui DrawSurface.
     * @param dt change speed according to frame rate.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

        // stopping condition, in case of 0 balls object, stop running animation and decrease 1 life.
        if (this.currentBalls.getValue() == 0) {
            this.currentLives.decrease(1);
            this.running = false;
        }
        // stopping condition, in case of 0 block objects, stop running animation and add 100 points.
        if (this.currentBlocks.getValue() == 0) {
            this.currentScore.increase(BONUS_POINTS);
            this.running = false;
        }
        // in case of pressing "p", run a pause animation.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
            //this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    /**
     * this function is charge of stopping condition.
     * @return return yes if there is a need to stopping,return no otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return return current blocks of this level.
     */
    public int getRemainBlocks() {
        return this.currentBlocks.getValue();
    }

    /**
     * @return return player current life points.
     */
    public int getRemainLife() {
        return this.currentLives.getValue();
    }
}