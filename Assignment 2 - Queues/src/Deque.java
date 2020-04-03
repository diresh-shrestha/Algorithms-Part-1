import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int N;
	
	private class Node{
		Item item;
		Node next;
		Node prev;
	}
    // construct an empty deque
    public Deque() {
    	first = null;
    	last = null;
    	N = 0;
  
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return N == 0;
    }

    // return the number of items on the deque
    public int size() {
    	return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("Null item.");
    	}
    	if (isEmpty()) {
    		first = new Node();
    		first.item = item;
    		last = first;
    	}
    	else {
    		Node oldFirst = first;
    		first = new Node();
    		first.item = item;
    		first.next = oldFirst;
    		oldFirst.prev = first;
    		
    	}
    	N++;
    }

    // add the item to the back
    public void addLast(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("Null item.");
    	}
    	if (isEmpty() || last == null) {
        	last = new Node();
        	last.item = item;
        	first = last;
        }
    	else {
    		Node oldLast = last;
        	last = new Node();
        	last.item = item;
        	last.prev = oldLast;
        	oldLast.next = last;
        	last.next = null;
        	
    	}
    	N++;
    	
    	
    }

    // remove and return the item from the front
    public Item removeFirst() {
    	if (N == 0) {
    		throw new NoSuchElementException("Queue is Empty.");
    	}
    	Item item = first.item;
    	first = first.next;
    	first.prev = null;
    	if (isEmpty()) last = null;
    	N--;
    	return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
    	if (N == 0) {
    		throw new NoSuchElementException("Queue is Empty.");
    	}
    	
    	Node oldLast = last;
    	last = last.prev;
    	if (last == null) {
    		first = null;
    	}
    	else {
    		last.next = null;
    	}
    	
    	N--;
    	return oldLast.item;
    	
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new listIterator(); }
    
    private class listIterator implements Iterator<Item> {
    	private Node current = first; 
    	
    	public boolean hasNext() { return current !=null; }
    	
    	public Item next() {
    		if (!hasNext()) {
        		throw new NoSuchElementException("No more items");
        	}
    		Item item = current.item;
    		current = current.next;
    		return item;
    	}
    	
    	 public void remove() {
             throw new UnsupportedOperationException("Remove is not supported");
         }
  
    }

    // unit testing (required)
    public static void main(String[] args) {
    	Deque<Integer> d = new Deque<>();
    	StdOut.println("Is Queue empty? " + d.isEmpty());
    	StdOut.println("Adding to front");
    	d.addFirst(5);
    	StdOut.println("Is Queue empty? " + d.isEmpty());
    	StdOut.println("Adding to back");
    	d.addLast(6);
    	StdOut.println("Adding to front");
    	d.addFirst(4);
    	d.addFirst(3);
    	d.addFirst(2);
    	d.addFirst(1);
    	for (Integer i : d) {
    		StdOut.println(i);
    	}
    	StdOut.println("Remving from the front");
    	d.removeFirst();
    	StdOut.println("Remving from the back");
    	d.removeLast();
    	StdOut.println("Size: " + d.size());
    	
    	for (Integer i : d) {
    		StdOut.println(i);
    	}
    }
}