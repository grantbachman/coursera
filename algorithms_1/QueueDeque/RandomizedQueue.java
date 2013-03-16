import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // need to make private
    private Item[] array; 
    private int size;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
        size = 0;
    }

    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {

        private int current;
        private Item[] iter;    

        public ListIterator() {
            iter = (Item[]) new Object[size()];
            current = 0;
            for (int i = 0; i < size(); i++)
                iter[i] = array[i]; 
            StdRandom.shuffle(iter);
        }   

        public boolean hasNext() { return current != size; }

        public Item next() {
            if (current == size) throw new java.util.NoSuchElementException();
            return iter[current++];
        }
        public void remove() { throw new UnsupportedOperationException(); }
    }
    public static void main(String[] args) { }


    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++)
            copy[i] = array[i]; 
        array = copy;
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (array.length == size) resize(size * 2);
        array[size++] = item;
    }


    public Item dequeue() {
        if (size == 0) throw new java.util.NoSuchElementException();   
        if (size > 0 && size == array.length/4) resize(array.length/2);
        int index = StdRandom.uniform(size);
        Item item = array[index];
        array[index] = array[size-1];
        array[--size] = null;
        return item;
    }

    public Item sample() {
        if (size == 0) throw new java.util.NoSuchElementException();
        Item item = array[StdRandom.uniform(size)];
        return item;
    }



}