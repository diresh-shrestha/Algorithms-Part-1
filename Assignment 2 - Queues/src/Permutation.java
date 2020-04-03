import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		if (k == 0) {
			return;
		}
		RandomizedQueue<String> rQ = new RandomizedQueue<>();
		
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			rQ.enqueue(s);
		}
		
		int i = 0;
		for(String s : rQ) {
			StdOut.println(s);
			i++;
			if (i == k) {
				break;
			}
		}
		
	}
}