/**
 * @author Dvir
 */
package geometry;

import java.util.List;

/**
 * Class line is combination of two Points, that represents the start and end values of the line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Create a new line and with 2 Points objects that Represents start and end points of the line.
     * @param start start geometry.Point  of the line.
     * @param end   end geometry.Point  of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Create a new line and with 4 coordinate that Represents start and end points of the line.
     * @param x1 start geometry.Point x coordinate.
     * @param y1 start geometry.Point y coordinate.
     * @param x2 end geometry.Point y coordinate.
     * @param y2 end geometry.Point y coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the length of the selected line.
     * @return return the length of the line,nominal number.
     */
    public double length() {
        double result = this.start.distance(this.end);
        return result;
    }


    /**
     * calculate the Middle geometry.Point of the selected line.
     * @return Returns the middle point of the line.
     */
    public Point middle() {

        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();

        double midX = (x1 + x2) / 2;
        double midY = (y1 + y2) / 2;

        Point middlePoint = new Point(midX, midY);
        return middlePoint;

    }


    /**
     * Get the start geometry.Point.
     * @return Returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Get the end geometry.Point.
     * @return Returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * check if two line are Intersecting with each other.
     * @param other - compare other line with this line.
     * @return Returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // difference between the x coordinate in line1.
        double dx1 = this.end().getX() - this.start().getX();
        // difference between the y coordinate in line1.
        double dy1 = this.end().getY() - this.start().getY();
        // difference between the x coordinate in line2.
        double dx2 = other.end().getX() - other.start().getX();
        // difference between the y coordinate in line2.
        double dy2 = other.end().getY() - other.start().getY();
        // checkParallel
        double checkParallel = (dx1 * dy2) - (dy1 * dx2);
        // if the lines have the same slope,return false.
        if (checkParallel == 0) {
            return false;
        }
        // calculate the distance between lines start x coordinate.
        double cx = other.start().getX() - this.start().getX();
        // calculate the distance between lines start y coordinate.
        double cy = other.start().getY() - this.start().getY();
        double checkLine1 = (cx * dy1 - cy * dx1) / checkParallel;
        if (checkLine1 < 0 || checkLine1 > 1) {
            return false;
        }
        double checkLine2 = (cx * dy2 - cy * dx2) / checkParallel;
        if (checkLine2 < 0 || checkLine2 > 1) {
            return false;
        }
        return true;
    }

    /**
     * calculate intersection geometry.Point with other line.
     * @param other - compare other line with this line.
     * @return Returns the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // if 'isIntersecting' function return false, return null.
        if (!(this.isIntersecting(other))) {
            return null;
        }
        if (this.start.getX() == this.end.getX()) {
            double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            // define second line equation.
            double b2 = -(m2 * other.start.getX()) + other.start.getY();
            double y = m2 * this.start.getX() + b2;
            if (y >= Math.min(this.start.getY(), this.end.getY()) && y <= Math.max(this.start.getY(),
                    this.end.getY())) {
                Point intersectionPoint = new Point(this.start.getX(), y);
                return intersectionPoint;
            }
        }
        if (other.start.getX() == other.end.getX()) {
            double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            // define second line equation.
            double b1 = -(m1 * this.start.getX()) + this.start.getY();
            double y = m1 * other.start.getX() + b1;
            if (y >= Math.min(other.start.getY(), other.end.getY()) && y <= Math.max(other.start.getY(),
                    other.end.getY())) {
                Point intersectionPoint = new Point(other.start.getX(), y);
                return intersectionPoint;
            }
        }
        // calculate the slope of the first line.
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        // define first line equation.
        double b1 = -(m1 * this.start.getX()) + this.start.getY();
        // calculate the slope of the second line.
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        // define second line equation.
        double b2 = -(m2 * other.start.getX()) + other.start.getY();

        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;

        Point intersectionPoint = new Point(x, y);
        return intersectionPoint;
    }

    /**
     * check if two line are equals.
     * @param other -compare other line with this line.
     * @return return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && ((this.end.equals(other.end)))) {
            return true;
        }
        return false;
    }

    /**
     * Find the closest point to this line's start between all intersectionPoints with rectangle Object.
     * @param rect Rectangle Object.
     * @return return geometry.Point object that have the closest distance to this line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // get all the intersectionPoints with rectangle Object.
        List<Point> listOfPoints = rect.intersectionPoints(this);
        // if the list isn't empty, check for minimum distance between this start point to intersectionPoints.
        if (!(listOfPoints.isEmpty())) {
            int minIndex = 0;
            for (int j = 1; j < listOfPoints.size(); j++) {
                if ((this.start.distance(listOfPoints.get(minIndex)) > (this.start.distance(listOfPoints.get(j))))) {
                    minIndex = j;
                }
            }
            return listOfPoints.get(minIndex);
        }
        return null;
    }
}
