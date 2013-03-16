import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TestDeque {

	Deque<Integer> d = new Deque<Integer>();

	@Test
	public void dequeSize() {
		d.addFirst(1);
		d.addFirst(2);
		d.addFirst(3);
		d.addFirst(4);
		d.addFirst(5);
		d.addLast(2);
		d.addLast(3);
		d.addLast(4);
		d.addLast(5);
		assertTrue(d.size() == 9);
	}

	@Test public void dequeSize2() {
		/*
		d.addFirst(1);
		d.addLast(3);
		d.addFirst(1);
		d.removeLast();
		d.removeFirst();
		d.removeLast();
*/
		d.addFirst(3);
		d.addFirst(5);
		d.removeFirst();
		d.removeLast();

		int count = 0;
		for (int i : d) {
			StdOut.print(i + " ");	
			++count;
		}
		StdOut.println("Count: " + count);
		assertTrue(count == 4);

		/*
		d.addLast(2);
		d.removeFirst();
		d.addFirst(1);
		d.addLast(2);
		d.removeLast();
		d.addLast(2);
		d.addLast(2);
		assertTrue(d.size() == 3);
		*/
}
	/*
	Iterator<int> iter = d.iterator();

	while(iter.hasNext()) {

		int i = iter.next();	
	}
*/

}
