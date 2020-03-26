import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final int T;
	private final double[] frac;
	private final static double con= 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	this.T = trials;
    	if (n <= 0 || T <= 0)
            throw new IllegalArgumentException("N and T should be > 0");
    	
    	frac = new double[T];
    	
    	for (int k = 0; k < T; k++) {
    		final Percolation p = new Percolation(n);
    		while (!(p.percolates())) {
    			int i = StdRandom.uniform(1, n+1);
    			int j = StdRandom.uniform(1,n+1);
    			p.open(i, j);
    		}
    		int count = p.numberOfOpenSites();
    		double tot = n*n;
    		frac[k] = count/tot;
    	}
    	
    	
    	
    }

    // sample mean of percolation threshold
    public double mean() {
    	return StdStats.mean(frac);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return StdStats.stddev(frac);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return mean()-((con*stddev())/Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return mean()+((con*stddev())/Math.sqrt(T));
    }

   // test client (see below)
   public static void main(String[] args) {
	   int N = Integer.parseInt(args[0]);
       int T = Integer.parseInt(args[1]);

       PercolationStats ps = new PercolationStats(N, T);
       System.out.println("mean                    = " + ps.mean());
       System.out.println("stddev                  = " + ps.stddev());
       System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
   }

}