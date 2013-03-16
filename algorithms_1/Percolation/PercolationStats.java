public class PercolationStats {

    private double[] thresholds;
    private int n;
    private int t;
    private double mean;
    private double stddev;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) { throw new IllegalArgumentException(); }
        n = N;
        t = T;  
        thresholds = new double[t];

        for (int i = 0; i < t; i++) {
            Percolation grid = new Percolation(n);
            int count = 0;
            while (!grid.percolates()) {
                int x = (int) (n * StdRandom.uniform())+1;
                int y = (int) (n * StdRandom.uniform())+1;
                if (!grid.isOpen(y, x)) {
                    grid.open(y, x);
                    count++;
                }
            }
            thresholds[i] = ((double) count)/(n*n);
        }
    }

    public double mean() {
        mean = StdStats.mean(thresholds);
        return mean;
    }

    public double stddev() {
        stddev = StdStats.stddev(thresholds);
        return stddev;

    }

    public double confidenceLo() {
        if (mean == 0) { mean(); }
        if (stddev == 0) { stddev(); }
        return (mean - ((1.96*stddev)/Math.sqrt(t)));
    }

    public double confidenceHi() {
        if (mean == 0) { mean = mean(); }
        if (stddev == 0) { stddev = stddev(); }
        return (mean + ((1.96*stddev)/Math.sqrt(t)));
    }
    public static void main(String[] args) {
        //Stopwatch watch = new Stopwatch();  
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        StdOut.println("mean:\t\t\t " + ps.mean());
        StdOut.println("stddev:\t\t\t" + ps.stddev());
        StdOut.println("95% confidence interval: "
                        + ps.confidenceLo() + ", "
                        + ps.confidenceHi());
        //StdOut.println("Time elapsed: " + watch.elapsedTime());
    }   
}
