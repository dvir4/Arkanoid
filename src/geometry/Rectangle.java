/**
 * @author Dvir
 */
package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * this class determent rectangle methods and shape.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper-left geometry.Point of the geometry.Rectangle.
     * @param width     width size of the geometry.Rectangle.
     * @param height    height size of the geometry.Rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * find intersection points with the specified line, and return them.
     * @param line Specific line object.
     * @return Return a list (possibly empty) of geometry.Point Objects.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point upLeft = this.upperLeft;
        Point upRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + this.height);

        Line upperLine = new Line(upLeft, upRight);
        Line lowerLine = new Line(lowerLeft, lowerRight);
        Line leftLine = new Line(upLeft, lowerLeft);
        Line rightLine = new Line(upRight, lowerRight);
        // create a list of geometry.Rectangle's geometry.Line.
        Line[] rectangleLines = {upperLine, lowerLine, leftLine, rightLine};
        List<Point> intersectionPoints = new ArrayList<>(4);

        for (int i = 0; i < rectangleLines.length; i++) {
            // check for intersection points with each line, if there is a intersection point, add her to the list.
            Point intersectionPoint = rectangleLines[i].intersectionWith(line);
            if (intersectionPoint != null) {
                intersectionPoints.add(intersectionPoint);
            }
        }
        return intersectionPoints;
    }

    /**
     * @return Return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return Return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return Return the upper-left geometry.Point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}