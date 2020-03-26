import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


	private final WeightedQuickUnionUF uf;
	private final WeightedQuickUnionUF uf2;
	
	private final int virtualTop;  
	private final int virtualBottom;
	private int count;
	
	private boolean[][] grid;  // grid containing open or closed
	private final int n; // size of grid
	
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if (n <= 0) {
    		throw new IllegalArgumentException("N must be larger than 0");
    	}
    	count = 0;
    	this.n = n;
    	uf = new WeightedQuickUnionUF(n*n + 2);  // initialize a new UF data structure with total objects
    											// n*n + 2 named 0 to n*n+1
    	uf2 = new WeightedQuickUnionUF(n*n +1);  // initialize another UF to check if full or not
    											// this uf does not contain the virtual bottom site hence n*n+1
    	grid = new boolean[n][n];	// initialize the same size of grid
    	
    	
    	virtualTop = 0;  // set 0 as the virtual top site
    	virtualBottom = n*n+1; 	// set n*n+1 as the virtual bottom site
    }
    
    // converts 2D to coordinates to 1D index in our data structure
    private int xyto1D(int i, int j) {
    	return (i-1) * n + j;
    }
    
    
    // opens the site (row, col) if it is not open already
    public void open(int i, int j) {
    	if (i <= 0 || j <= 0 || i > n || j > n)
            throw new IllegalArgumentException("row and col should be > 0");
    	if (!(isOpen(i,j))) {
    		int idx = xyto1D(i, j); // get the index
        	count += 1;
        	// if it is the top row, connect it to the virtual top site
        	if (i == 1) {
        		uf.union(idx, virtualTop);
        		uf2.union(idx, virtualTop);
        	}
        	
        	// if is is the bottom row, connect it to the virtual bottom site
        	if (i == n) {
        		uf.union(idx, virtualBottom);
        	}
        	
        	// Connect to left square
        	if (!(j == 1) && isOpen(i, j-1)) {
                uf.union(idx, idx-1); 
                uf2.union(idx, idx-1);
            }
        	// Connect to right square
            if (!(j == n) && isOpen(i, j+1)) {
            	uf.union(idx, idx+1); 
            	uf2.union(idx, idx+1);
            }
         // connect to top cell
            if (!(i == 1) && isOpen(i-1, j)) {
            	uf.union(idx, idx-n); 
            	uf2.union(idx, idx-n); 
            } 
         // connect to bottom cell
            if (!(i == n) && isOpen(i+1, j)) {
            	uf.union(idx, idx+n); 
            	uf2.union(idx, idx+n); 
            
            }
       
        	grid[i-1][j-1] = true;	// set it to open
        	
    	}
    	

    }

    // is the site (row, col) open?
    public boolean isOpen(int i, int j) {
    	if (i <= 0 || j <= 0 || i > n || j > n)
            throw new IllegalArgumentException("row and col should be > 0");
    	return grid[i-1][j-1];
    }

    // is the site (row, col) full?
	public boolean isFull(int i, int j) {
		if (i <= 0 || j <= 0 || i > n || j > n)
            throw new IllegalArgumentException("row and col should be > 0");
    	return uf2.connected(virtualTop, xyto1D(i, j));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return count;
    }
   
    

    // does the system percolate?
    public boolean percolates() {
    	return uf.connected(virtualTop, virtualBottom); 
    }
    

    // test client (optional)
    public static void main(String[] args) {
    	final Percolation p = new Percolation(3);
        System.out.println("(1,1) is open =  " + p.isOpen(1, 1));
        p.open(1, 1);
        System.out.println("(1,1) is open =   " + p.isOpen(1, 1));


        System.out.println("(2,1) is open =  " + p.isOpen(2, 1));
        p.open(2, 1);
        System.out.println("(2,1) is open =  " + p.isOpen(2, 1));
        System.out.println("(2,1) is full =  " + p.isFull(2, 1));


        System.out.println("(3,1) is open = " + p.isOpen(3, 1));
        p.open(3, 1);
        System.out.println("(3,1) is open = " + p.isOpen(3, 1));
        System.out.println("(3,1) is full =  " + p.isFull(3, 1));
        
        System.out.println("Number of open sites = " + p.numberOfOpenSites());


        System.out.println("Percolates = " + p.percolates());
        
    }
}