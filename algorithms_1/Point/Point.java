/*************************************************************************
 * Name: Grant David Bachman
 * Username: grantbachman
 * Email: grantbachman@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        // Simpler way to write this? Looks ugly
        if (this.y < that.y) return -1;
        else if (this.y > that.y) return 1;
        else { // y-coordinates are equal, compare x-coordinates
            if (this.x < that.x) return -1;
            else if (this.x > that.x) return 1;
            else return 0; // points are the same
        }
    }

    private class SlopeOrder implements Comparator<Point> {

        public int compare(Point a, Point b) {
            double slopeA = Point.this.slopeTo(a);
            double slopeB = Point.this.slopeTo(b);
            if (slopeA < slopeB) return -1;
            else if (slopeA > slopeB) return 1;
            else return 0;
        }
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY; 
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        double slope = (((double)that.y - this.y)/(that.x - this.x)); 
        if(slope == 0) slope = +0.0;
        return slope;
    }


    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point x = new Point(1,1);
        Point y = new Point(1,1);
        StdOut.println("slope: " + x.slopeTo(y));
    }
}
