import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] a;
 	private int N;
	
    // construct an empty randomized queue
	public RandomizedQueue() {
		a = (Item[]) new Object[1];
    	N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return N;
    }
    

	private void resize(int cap) {
    	Item[] temp = (Item[]) new Object[cap];
    	for (int i = 0; i < N; i++) {
    		temp[i] = a[i];
    	}
    	a = temp;
    }

    // add the item
    public void enqueue(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("Null item.");
    	}
    	if (N == a.length)  resize(2*a.length);
    	a[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
    	if (N == 0) {
    		throw new NoSuchElementException("Queue is Empty.");
    	}
    	int rand = StdRandom.uniform(0, N);
    	Item item = a[rand];
    	a[rand] = null;
    	N--;
    	a[rand] = a[N];
    	a[N] = null;
    	if (N > 0 && N == a.length/4) resize(a.length/2);
    	return item;  	
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if (N == 0) {
    		throw new NoSuchElementException("Queue is Empty.");
    	}
    	int rand = StdRandom.uniform(0,N);
      	return a[rand];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new randIterator(); }
    
    private class randIterator implements Iterator<Item> {
    	int j = N;
		private Item[] b = (Item[]) new Object[N];
    	
    	public randIterator() {
    		b = ret();
    		StdRandom.shuffle(b);
    	}
    	
    	private Item[] ret() {
    		for(int i = 0; i < N; i++) {
    			b[i] = a[i];
    		}
    		return b;
    	}
    	
    	
    
  
    	public boolean hasNext() { return j > 0; }
    	
    	public Item next() {
    		if (!hasNext()) {
        		throw new NoSuchElementException("No more items");
        	}
    		
    		return b[--j];
    		
    	}
    	public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
    	RandomizedQueue<Integer> a = new RandomizedQueue<>();
    	StdOut.println("Is the queue empty? " + a.isEmpty());
    	a.enqueue(1);
    	a.enqueue(2);
    	a.enqueue(3);
    	a.enqueue(4);
    	a.enqueue(5);
    	a.enqueue(6);
    	a.enqueue(7);
    	StdOut.println("Returning random element " + a.sample());
    	StdOut.println("Removing " + a.dequeue());
    	StdOut.println("Is the queue empty? " + a.isEmpty());
    	StdOut.println("Size: " + a.size());
    	for (Integer i: a) {
    		StdOut.println(i);
    	}
    }
    

}