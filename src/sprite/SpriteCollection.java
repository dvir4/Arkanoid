/**
 * @author Dvir
 */
package sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * sprite.SpriteCollection Class have collection of all sprite.Sprite objects.
 */
public class SpriteCollection {
    private List<Sprite> spriteObjects;

    /**
     * Create a sprite.SpriteCollection object.
     */
    public SpriteCollection() {
        this.spriteObjects = new ArrayList<>();
    }

    /**
     * add a sprite.Sprite Object to sprite.SpriteCollection's list.
     * @param s sprite.Sprite Object.
     */
    public void addSprite(Sprite s) {
        this.spriteObjects.add(s);
    }

    /**
     * call timePassed function for each sprite objects in this class.
     * @param dt change speed according to frame rate.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < spriteObjects.size(); i++) {
            spriteObjects.get(i).timePassed(dt);
        }
    }
    /**
     * call drawOn function on all sprites objects.
     * @param d DrawSurface object.
     */
    public void drawAllOn(DrawSurface d) {
        for (int k = 0; k < spriteObjects.size(); k++) {
            spriteObjects.get(k).drawOn(d);
        }
    }

    /**
     * remove a sprite.Sprite Object to sprite.SpriteCollection's list.
     * @param s sprite.Sprite Object.
     */
    public void removeSprite(Sprite s) {
        this.spriteObjects.remove(s);
    }
}