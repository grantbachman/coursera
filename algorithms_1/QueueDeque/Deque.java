import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    // construct an empty deque     
    public Deque() {
        size = 0;
    }
    public Iterator<Item> iterator() { return new ListIterator(); }


    public static void main(String[] args) {
        /*
        Deque<String> d = new Deque<String>();
        d.addFirst("1");
        d.addFirst("2");
        d.addFirst("4");
        d.addFirst("8");
        d.addFirst("16");
        Iterator<String> i = d.iterator();
        while(i.hasNext()){
            String s = i.next();
            StdOut.println(s);
        }
        */
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); } 

        public Item next() {
            if (current == null) { throw new java.util.NoSuchElementException(); }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }



    // is the deque empty?
    public boolean isEmpty() {
        if (size == 0) { return true; }
        return false;
    }

    // return the number of items on the deque
    public int size() { return size; }

    private void createFirstNode(Item item) {
        first = new Node();
        first.item = item;
        first.next = null;
        first.prev = null;
        last = first;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        if (size == 0) createFirstNode(item);
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        if (size == 0) createFirstNode(item);
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            oldLast.next = last;
        }
        size++;
    }

    // delete and return the item at the front
    // NOTE: Should have listened to the professor and used a sentinel node
    public Item removeFirst() {
        if (size == 0) throw new java.util.NoSuchElementException(); 
        Item item = first.item;
        if (size != 1) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (size == 0) throw new java.util.NoSuchElementException(); 
        Item item = last.item;
        if (size != 1) {
        last = last.prev;
        last.next = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return item;
    }
}