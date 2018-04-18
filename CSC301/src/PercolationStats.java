import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
	private double[] mean_array;
	private int time;

	public PercolationStats(int N, int T) {
		// perform T independent experiments on an N-by-N grid
		mean_array = new double[T];
		time = T;
		for(int i = 0; i < T; i++) {
			Percolation matrix = new Percolation(N);
			while(!matrix.percolates()){
				int row = StdRandom.uniform(N);
				int col = StdRandom.uniform(N);
				matrix.open(row, col);
			}
			int num_open = matrix.numberOfOpenSites();
			//System.out.println(num_open);
			double precent_open = (double)num_open / (double)(N*N);
			mean_array[i] = precent_open;
		}
	}
	
	
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(mean_array);
	}
	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(mean_array);
	}
	public double confidenceLow() {
		// low  endpoint of 95% confidence interval
		double mean = this.mean();
		double stddev = this.stddev();
		double fin = mean - (1.96*stddev)/Math.sqrt((double)time);
		return fin;
	}
	public double confidenceHigh() {
		// high endpoint of 95% confidence interval
		return (-1 * this.confidenceLow()) + (2 * this.mean());
	}

	//have to be delete before upload
	public static void main(String[] args) {
		Stopwatch timer = new Stopwatch();
		PercolationStats test = new PercolationStats(200, 100);
		System.out.println(timer.elapsedTime());
		System.out.println(test.mean());
		System.out.println(test.stddev());
		System.out.println(test.confidenceLow());
		System.out.println(test.confidenceHigh());
	}






}
