/**
 * @author Dvir
 */
package game;

import animation.ColorBackground;
import animation.ImageBackground;
import collision.Block;
import collision.Velocity;
import sprite.Sprite;

//import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * get a level reader file, create all the members of the level, and create a level.
 */
public class LevelFactory {
    private String levelName;
    private int numOfBlocks;
    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int blockWidth;
    private int blockHeight;
    private String blockPath;
    private Sprite levelBackground;
    private List<Velocity> ballsVelocity;
    private List<String> levelDescription;
    private int xPos;
    private int yPos;
    private BlocksFromSymbolsFactory factory;
    private List<Block> blocksList;
    private int rowHeight;
    private static final String SPLIT_SIGN = ":";
    private static final String VELOCITY_SEPARATOR = "\\,|\\s+";

    /**
     * constructor.
     * @param levelDescription String list that contains level description.
     */
    public LevelFactory(List<String> levelDescription) {
        this.numOfBalls = 0;
        this.blocksList = new ArrayList<>();
        this.levelDescription = levelDescription;
    }

    /**
     * create each member of the selected level, and return level object.
     * @return level object.
     */
    public LevelInformation getLevel() {
        for (int i = 0; i < this.levelDescription.size(); i++) {
            String[] parts = this.levelDescription.get(i).split(SPLIT_SIGN);
            if (parts[0].equals("ball_velocities")) {
                setBallVelocities(parts[1]);
            } else if (parts[0].equals("level_name")) {
                setLevelName(parts[1]);
            } else if (parts[0].equals("paddle_speed")) {
                setPaddleSpeed(parts[1]);
            } else if (parts[0].equals("paddle_width")) {
                setPaddleWidth(parts[1]);
            } else if (parts[0].equals("background")) {
                Fill background = new ColorsAndImageParser().getBackground(parts[1]);
                if (background.getFillColor() != null) {
                    this.levelBackground = new ColorBackground(background.getFillColor());
                } else if (background.getFillImage() != null) {
                    this.levelBackground = new ImageBackground(background.getFillImage());
                }
            } else if (parts[0].equals("block_definitions")) {
                this.blockPath = parts[1];
                BlocksDefinitionReader reader = new BlocksDefinitionReader();
                try {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(parts[1]);
                    Reader reader1 = new InputStreamReader(is);
                    //this.factory = reader.fromReader(new FileReader(parts[1]));
                    this.factory = reader.fromReader(reader1);
                } catch (Exception e) {
                    System.out.println("error! cannot find block description: " + this.blockPath);
                }
            } else if (parts[0].equals("blocks_start_x")) {
                this.xPos = Integer.parseInt(parts[1]);
            } else if (parts[0].equals("blocks_start_y")) {
                this.yPos = Integer.parseInt(parts[1]);
            } else if (parts[0].equals("row_height")) {
                this.rowHeight = Integer.parseInt(parts[1]);
            } else if (parts[0].equals("num_blocks")) {
                this.numOfBlocks = Integer.parseInt(parts[1]);
            }
        }
        // create each line of block according to level's block pattern.
        List<String> blockDescription = getBlockDescription();
        for (int i = 0; i < blockDescription.size(); i++) {
            getBlocksLine(blockDescription.get(i));
        }
        // create a new level.
        LevelInformation level = new LevelCreator(this.numOfBalls, this.ballsVelocity, this.paddleSpeed,
                this.paddleWidth, this.levelBackground, this.blocksList, this.numOfBlocks);
        ((LevelCreator) level).setLevelName(this.levelName);
        return level;
    }

    /**
     * get ball's velocities according to level.
     * @param ballVelocities  String line that represent blocks velocities.
     */
    public void setBallVelocities(String ballVelocities) {
        List<Velocity> velocityList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(ballVelocities.trim());
        String[] parts = sb.toString().split(VELOCITY_SEPARATOR);
        for (int i = 0; i < parts.length - 1; i = i + 2) {
            int angle = Integer.parseInt(parts[i]);
            int speed = Integer.parseInt(parts[i + 1]);
            Velocity ballVelocity = Velocity.fromAngleAndSpeed(angle, speed);
            velocityList.add(ballVelocity);
            this.numOfBalls++;
        }
        this.ballsVelocity = velocityList;
    }

    /**
     * @param lvlName String line that represent level name.
     */
    public void setLevelName(String lvlName) {
        this.levelName = lvlName;
    }

    /**
     * @param pSpeed String line that represent paddle speed.
     */
    public void setPaddleSpeed(String pSpeed) {
        this.paddleSpeed = Integer.parseInt(pSpeed);
    }

    /**
     * @param pWidth String line that represent paddle width.
     */
    public void setPaddleWidth(String pWidth) {
        this.paddleWidth = Integer.parseInt(pWidth);
    }

    /**
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * @return convert block description file to list of String arguments.
     */
    public List<String> getBlockDescription() {
        List<String> blockDescription = new ArrayList<>();
        int currentIndex = 0;
        for (int k = 0; k < this.levelDescription.size(); k++) {
            if ((this.levelDescription.get(k).contains("START_BLOCKS"))) {
                break;
            }
            currentIndex++;
        }
        for (int k = currentIndex + 1; k < this.levelDescription.size(); ++k) {
            if (!(this.levelDescription.get(k).equals("END_BLOCKS") || this.levelDescription.get(k).isEmpty()
                    || this.levelDescription.get(k).equals("END_LEVEL"))) {
                blockDescription.add(this.levelDescription.get(k));
            }
        }
        return blockDescription;
    }

    /**
     * // create each line of block according to level's block pattern.
     * @param line String line,line of blocks in symbols pattern.
     */
    public void getBlocksLine(String line) {
        int x = this.xPos;
        int namBlockCreate = 0;
        for (int i = 0; i < line.length(); ++i) {
            String symbol = line.substring(i, i + 1);
            if (this.factory.isSpaceSymbol(symbol)) {
                x += this.factory.getSpaceWidth(symbol);
            }
            if (this.factory.isBlockSymbol(symbol)) {
                CreateBlock block = (CreateBlock) factory.getBlockCreator(symbol);
                int theBlockWidth = (int) block.getWidth();
                this.blocksList.add(this.factory.getBlock(symbol, x + theBlockWidth * namBlockCreate, this.yPos));
                namBlockCreate++;
            }
        }
        this.yPos += this.rowHeight;
    }
}
