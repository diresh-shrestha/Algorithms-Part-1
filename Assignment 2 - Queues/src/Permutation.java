public class Permutation {
	public static void main(String[] args) {
		String[] stringArr = StdIn.readAllStrings();
		
		RandomizedQueue<String> rQ = new RandomizedQueue<>();
		
		int k = Integer.parseInt(args[0]);
		
		for (String s : stringArr) {
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