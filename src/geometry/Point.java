/**
 * @author Dvir
 */
package geometry;

/**
 * A geometry.Point with x and y coordinate.
 */
public class Point {
    private double x;
    private double y;

    // constructor

    /**
     * create a new geometry.Point with x and y coordinate values.
     * @param x nominal value of the x coordinate.
     * @param y nominal value of the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point

    /**
     * function 'distance' - calculate the distance between two nominal numbers.
     * @param other another geometry.Point object, in order to calculate the distance with this point.
     * @return nominal value.
     */
    public double distance(Point other) {
        double x1 = this.x;
        double y1 = this.y;
        double x2 = other.x;
        double y2 = other.y;
        double distancePoints = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        distancePoints = Math.sqrt(distancePoints);
        return distancePoints;
    }

    /**
     * function 'equals' - check if two points are equal.
     * @param other another point object.
     * @return return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (this.x == other.x && this.y == other.y) {
            return true;
        }
        return false;
    }
    // Return the x and y values of this point

    /**
     * function 'getX' - get the x value of the selected point.
     * @return return the x nominal value of the selected point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * function 'getY' - get the y value of the selected point.
     * @return return the x nominal value of the selected point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * check if this point is on a specific line segment.
     * @param line line segment.
     * @return return true if the point is on specific line segment, false otherwise.
     */
    public boolean isPointOnLine(Line line) {
        double x1 = line.start().getX();
        double y1 = line.start().getY();
        double x2 = line.end().getX();
        double y2 = line.end().getY();
        // in case of a straight line, check with next method.
        if (x1 == x2) {
            if (this.getX() == x1 && this.getY() >= Math.min(y1, y2) && this.getY() <= Math.max(y1, y2)) {
                return true;
            }
        }
        double slope = (y1 - y2) / (x1 - x2);
        double yIntersect = -(slope * x1) + y1;
        if (this.getY() == (slope * this.getX() + yIntersect)) {
            return true;
        }
        return false;
    }
}
