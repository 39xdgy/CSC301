import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] list;
	private int size;
	private int open_count;
	private WeightedQuickUnionUF union_list;
	private WeightedQuickUnionUF back_wash_solution;
	
	public Percolation(int N){
		// create N-by-N grid, with all sites initially blocked
		if(N < 0) throw new java.lang.IllegalArgumentException("The Index has to be positive number");
		list = new int[N][N];
		size = N;
		open_count = 0; 
		union_list = new WeightedQuickUnionUF(N*N + 2);
		back_wash_solution = new WeightedQuickUnionUF(N*N + 1);
	}

	public void open(int row, int col){
		// open the site (row, col) if it is not open already
		if(row < 0 || col < 0 || row >= size || col >= size ){
			throw new java.lang.IndexOutOfBoundsException("The row or col is out of index");
		}
		if(list[row][col] == 0) {
			list[row][col] = 1;
			open_count++;
			connect(row, col);
		}
	}
	
	private int get_index_union(int row, int col) {return size * row + col;}
	
	
	private void connect(int row, int col){
		int self_index = get_index_union(row, col);
		

		
		if(col != 0){
			int left = get_index_union(row, col-1);
			if(this.isOpen(row, col-1)) {
				//System.out.println("connect left");
				union_list.union(self_index, left);
				back_wash_solution.union(self_index, left);
			}
		}
		if(col != size-1){
			int right = get_index_union(row, col+1);
			if(this.isOpen(row, col+1)) {
				//System.out.println("connect right");
				union_list.union(self_index, right);
				back_wash_solution.union(self_index, right);
			}
		}
		if(row-1 >= 0){
			int up = get_index_union(row-1, col);
			if(this.isOpen(row-1, col)) {
				//System.out.println("connect up");
				union_list.union(self_index, up);
				back_wash_solution.union(self_index, up);
			}
		}
		if(row+1 < size){
			int down = get_index_union(row + 1, col);
			if(this.isOpen(row+1, col)) {
				//System.out.println("connect down");
				union_list.union(self_index, down);
				back_wash_solution.union(self_index, down);
			}
		}

		if(self_index < size) {
			//System.out.println("On the top");
			union_list.union(self_index, size*size);
			back_wash_solution.union(self_index, size*size);
		}
		if(self_index >= size*(size-1)) {
			//System.out.println("At the bottom");
			union_list.union(self_index, size*size + 1);
		}

	}
	
	
	
	
	
	
	
	public boolean isOpen(int row, int col){
		// is the site (row, col) open?
		if(row < 0 || col < 0 || row >= size || col >= size ){
			throw new java.lang.IndexOutOfBoundsException("The row or col is out of index");
		}
		return list[row][col] == 1;
	}
	
	public boolean isFull(int row, int col){
		// is the site (row, col) full?
		if(row < 0 || col < 0 || row >= size || col >= size ){
			throw new java.lang.IndexOutOfBoundsException("The row or col is out of index");
		}
		int index = size * row + col;
		return back_wash_solution.connected(index, size*size);
	}
	
	public int numberOfOpenSites(){
		// number of open sites
		return open_count;
	}
	
	public boolean percolates(){
		// does the system percolate?
		return union_list.connected(size*size, size*size+1);
	}
	
	
	
	
	
	public static void main(String[] args){
		// unit testing (required)
		Percolation test = new Percolation(8);
		test.open(0, 0);
		test.open(0, 1);
		test.open(1, 1);
		test.open(1, 2);
		test.open(2, 2);

		System.out.println(test.numberOfOpenSites());
		System.out.println(test.percolates());
		System.out.println(test.isFull(0, 0));
		
	}
	
}
