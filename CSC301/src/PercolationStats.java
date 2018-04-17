import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	private double mean = 0.0;


	public PercolationStats(int N, int T) {
		// perform T independent experiments on an N-by-N grid
		double precent_sum = 0.0;
		for(int i = 0; i < T; i++) {
			Percolation matrix = new Percolation(N);
			while(!matrix.percolates()){
				int row = (int)Math.round(Math.random() * (N-1));
				int col = (int)Math.round(Math.random() * (N-1));
				matrix.open(row, col);
			}
			int num_open = matrix.numberOfOpenSites();
			//System.out.println(num_open);
			double precent_open = (double)num_open / (double)(N*N);

			precent_sum += precent_open;
		}
		
		
		mean = precent_sum / (double) T;


	}
	public double mean() {
		// sample mean of percolation threshold
		return mean;
	}
	public double stddev() {
		// sample standard deviation of percolation threshold
		return 0.0;
	}
	public double confidenceLow() {
		// low  endpoint of 95% confidence interval
		return 0.0;
	}
	public double confidenceHigh() {
		// high endpoint of 95% confidence interval
		return 0.0;
	}

	//have to be delete before upload
	public static void main(String[] args) {
		PercolationStats test = new PercolationStats(200, 100);
		System.out.println(test.mean());
	}






}
