/**
* Name: Grant David Bachman
* Login: grantbachman
* Date: February 11, 2013
* Purpose: Defines a Percolation data type 
* Instructions:
*/  


public class Percolation {

    private WeightedQuickUnionUF uf; // UF instance
    private WeightedQuickUnionUF uf2; // UF instance
    private int n; // size of an edge of the "grid" 
    private boolean[] openSites; // logs the open sites; true = open

    /**
    * Initializes a boolean N-by-N grid of sites where
    * false represents a blocked site, open represents an 
    * open site. Each site is initially false
    */
    public Percolation(int width) {
        uf = new WeightedQuickUnionUF(width*width+2);
        uf2 = new WeightedQuickUnionUF(width*width+1);
        n = width;
        openSites = new boolean[n*n];
    }
    /*public static void main(String[] args) {
        Percolation me = new Percolation(10);
        me.open(1,1);
        me.open(10,10);
        me.open(1,10);
        me.open(10,1);
        me.open(10,5);
        me.open(5,10);
        me.open(5,1);
        me.open(1,5);
        me.open(0,1);
        //StdOut.println(me.isOpen(1,2)); 
        //StdOut.println(me.uf.connected(0,1));
    }*/
 
    // Note: this method fails if given input out of bounds 
    private int xyTo1D(int i, int j) {     
       return (i - 1) * n + (j - 1);
    }

    private boolean isValid(int i, int j) {
        if (i > 0 && i <= n && j > 0 && j <= n) { return true; }
        return false;
    }
    /**
    * Opens a site (row i, column j) if it isn't already
    */
    public void open(int i, int j) {
        if (!isValid(i, j)) { throw new IndexOutOfBoundsException(); }
        int index = xyTo1D(i, j);
        openSites[index] = true;
        int up = xyTo1D(i+1, j), down = xyTo1D(i-1, j);
        int left = xyTo1D(i, j-1), right = xyTo1D(i, j+1);

        // Connect to virtual top or bottom site if in first/last row
        if (i == 1) {
            uf.union(index, n*n);
            uf2.union(index, n*n);
        }
        if (i == n) { uf.union(index, n*n+1); }
        if (isValid(i+1, j) && openSites[up]) {
            uf.union(index, up);
            uf2.union(index, up);
        }
        if (isValid(i-1, j) && openSites[down]) {
            uf.union(index, down);
            uf2.union(index, down);
        }
        if (isValid(i, j- 1) && openSites[left]) {
            uf.union(index, left);
            uf2.union(index, left);
        }
        if (isValid(i, j+ 1) && openSites[right]) {
            uf.union(index, right);
            uf2.union(index, right);
        }

    }
    /**
    * Returns true if a site is open, false if blocked
    */
    public boolean isOpen(int i, int j) {
        if (!isValid(i, j)) { throw new IndexOutOfBoundsException(); }
        return openSites[xyTo1D(i, j)];
    }

    /**
    * Returns true is a site is full, false otherwise
    */
    public boolean isFull(int i, int j) {
        if (!isValid(i, j)) { throw new IndexOutOfBoundsException(); }
        int index = xyTo1D(i, j);
        if (uf.connected(index, n*n) && uf2.connected(index, n*n)) {
            return true; 
        }
        return false;
    }
    /**
    * Returns true if the system percolates, false otherwise
    */
    public boolean percolates() {
        return uf.connected(n*n, n*n+1);
    }
}

