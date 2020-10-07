/**
 * @author Dvir
 */
package game;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * get color or image object, base on level or block description.
 */
public class ColorsAndImageParser {
    private static final String RGB_SEPARATOR = "\\,";
    private static final String BACKGROUND_SEPARATOR = "\\(";

    /**
     * check if the background is color type or Image type, and return the background object.
     * @param s String argument - background String line.
     * @return Image or Color object.
     */
    public Fill getBackground(String s) {
        String[] parts = s.split(BACKGROUND_SEPARATOR);
        if (parts[0].equals("color")) {
            Color color = colorFromString(s);
            return new Fill(color);
        } else {
            Image image = imageFromString(s);
            return new Fill(image);
        }

    }

    /**
     * read background text file, and create a color base on background description.
     * @param s String argument - background line.
     * @return Color object.
     */
    public java.awt.Color colorFromString(String s) {
        String[] parts = s.split("\\(");
        Color color;
        if (parts[1].equals("RGB")) {
            parts[2] = parts[2].replace(")", "");
            String[] rgbParts = parts[2].split(RGB_SEPARATOR);
            return new Color(Integer.parseInt(rgbParts[0]), Integer.parseInt(rgbParts[1]),
                    Integer.parseInt(rgbParts[2]));
        } else {
            parts[1] = parts[1].replace(")", "");
            try {
                Field field = Class.forName("java.awt.Color").getField(parts[1]);
                color = (Color) field.get(null);
                return color;
            } catch (Exception e) {
                System.out.println("cannot find this color" + s);
            }
            return null;
        }
    }

    /**
     * read background text file, and create a image object base on background description.
     * @param background String argument - background line.
     * @return Image object.
     */
    public Image imageFromString(String background) {
        String[] parts = background.split(BACKGROUND_SEPARATOR);
        parts[1] = parts[1].replace(")", "");
        Image backgroundImage = null;
        //File f;
        try {
            //f = new File(parts[1]);
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(parts[1]);
            backgroundImage = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("error, cannot load Image:  " + parts[1]);
        }
        return backgroundImage;
    }
}

