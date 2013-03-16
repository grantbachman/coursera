import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestPoint {

	public static final double DELTA = 1e-10;

    @Test public void negHorizontalLine() {
        Point x = new Point(3,3);
        Point y = new Point(2,3);   
        assertEquals(0.0, x.slopeTo(y), DELTA);
    }
    @Test public void horizontalLine() {
    	Point x = new Point(2,3);
    	Point y = new Point(3,3);	
    	assertEquals(0.0, x.slopeTo(y), DELTA);
    }

    @Test public void verticalLine() {
    	Point x = new Point(1,0);	
    	Point y = new Point(1,2);	
    	assertEquals(Double.POSITIVE_INFINITY, x.slopeTo(y), DELTA);
    }

    @Test public void degenerateLine() {
    	Point x = new Point(1,0);	
    	Point y = new Point(1,0);	
    	assertEquals(Double.NEGATIVE_INFINITY, x.slopeTo(y), DELTA);
    }

    // Test SLOPE_ORDER comparator
    @Test public void comparator() {
        int arr_size = 3;
        Point[] arr = new Point[arr_size];
        Point[] correct = new Point[arr_size];
        Point p = new Point(1,2);

        correct[2] = arr[0] = new Point(3,3); // slope to p = 1/2
        correct[0] = arr[1] = new Point(2,1); // slope to p = -1
        correct[1] = arr[2] = new Point(7,2); // slope to p = 0
        Arrays.sort(arr, p.SLOPE_ORDER);

        assertArrayEquals(correct, arr);
    }


}
