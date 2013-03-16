import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestRandomizedQueue {

	private RandomizedQueue<String> q;

	@Before public void setUp() {
   		q = new RandomizedQueue<String>();
	}
   	@Test public void arrayEmpty() {
   		assertEquals(0, q.size());
   	}

   	@Test public void addItem() {
   		q.enqueue("Hello");
   		assertEquals(1, q.size());
   		assertEquals(1, q.arrayLength());
   	}

   	@Test public void arrayDoubles() {
   		for (int i = 0; i < 2; i++) q.enqueue(Integer.toString(i));
   		assertEquals(2, q.arrayLength());
   		assertEquals(2, q.size());
   	}

   	@Test public void doublesAgain() {
   		for (int i = 0; i < 3; i++) q.enqueue(Integer.toString(i));
   		assertEquals(4, q.arrayLength());
   		assertEquals(3, q.size());
   	}

   	@Test public void doublesBig() {
   		for (int i = 0; i < 257; i++) q.enqueue(Integer.toString(i));
   		assertEquals(512, q.arrayLength());
   		assertEquals(257, q.size());
   	}

   	@Test public void arrayHalves() {
   		// make array of length 8 with 5 items
   		for (int i = 0; i < 5; i++) q.enqueue(Integer.toString(i));
   		assertEquals(8, q.arrayLength());
   		assertEquals(5, q.size());
   		for (int i = 0; i < 3; i++) q.dequeue();
   		assertEquals(8, q.arrayLength());		
   		assertEquals(2, q.size());		
   		q.dequeue();	
   		assertEquals(4, q.arrayLength());		
   		assertEquals(1, q.size());		
   		q.dequeue();
   		assertEquals(0, q.size());		
   		assertEquals(2, q.arrayLength());		
   	}
   	@Test(expected = NoSuchElementException.class) public void errorDequeueWhenEmpty() {
   		q.enqueue("0");	
   		q.dequeue();	
   		q.dequeue();	
   	}

   	@Test(expected = NoSuchElementException.class) public void errorSampleWhenEmpty() {
   		q.enqueue("0");
   		q.dequeue();	
   		q.sample();
	}
	@Test(expected = NullPointerException.class) public void addNullItem() {
		q.enqueue(null);
	}
	
   @Test public void sample() {
      for (int i = 0; i < 10; i++) q.enqueue(Integer.toString(i)); 
      int arraySize = q.size();
      for (int j = 0; j < 1000; j++) {
         String x = q.sample();
         int y = Integer.parseInt(x); 
         assertTrue(y >= 0);
         assertTrue(y < 10);
         assertTrue(q.size() == arraySize);
      }
   }

   @Test public void dequeue() {
      for(int i = 0; i < 10; i++) q.enqueue(Integer.toString(i)); 
      for(int i = 0; i < 10; i++) q.dequeue();
   }

   @Test public void iterate() {
      for(int i = 0; i < 10; i++) q.enqueue(Integer.toString(i)); 
      // Throw iteration results into an array   
      // Loop through array to make sure no values are duplicates
      String[] testArray = new String[q.size()];
      int currentSize = 0;
      for (String x : q) {
         testArray[currentSize++] = x;
      }
      for(int i = 0; i < testArray.length - 1; i++)
         for(int j = i+1; j < testArray.length; j++)
            assertTrue(i != j);
   }

   @Test(expected = UnsupportedOperationException.class) public void iterateRemove() {
      for(int i = 0; i < 10; i++) q.enqueue(Integer.toString(i)); 
      Iterator<String> iter = q.iterator();
      iter.remove();
   }

   @Test(expected = NoSuchElementException.class) public void noNext() {
      q.enqueue("you");
      Iterator<String> iter = q.iterator();   
      iter.next(); 
      iter.next(); 
   }










}