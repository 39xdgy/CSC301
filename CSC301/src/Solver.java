import java.util.Scanner; //unit testing use
import java.util.Stack;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	private SearchNode fin;
	private int count;
	
	private class SearchNode implements Comparable<SearchNode> {
		private Board input;
		private int move;
		private SearchNode The_mom;
		
		public SearchNode(Board in, int num_move, SearchNode from){
			input = in;
			move = num_move;
			The_mom = from;
		}
		
		public int compareTo(SearchNode that){
			int this_num = this.input.hamming() + this.move;
			int that_num = that.input.hamming() + that.move;
			if(this_num > that_num) return 1;
			if(this_num < that_num) return -1;
			return 0;
		}
		
		
	}
	
	
	
	
    public Solver(Board initial){
    	// find a solution to the initial board (using the A* algorithm)
    	//initial.hamming();
    	if(!initial.isSolvable()) throw new java.lang.IllegalArgumentException("This is not solvable");
    	if(initial == null) throw new java.lang.NullPointerException("Input is null");
    	MinPQ<SearchNode> list = new MinPQ<SearchNode>();
    	count = 0;
    	fin = new SearchNode(initial, count, null);
    	//System.out.println(fin.input.isGoal());
    	while(!fin.input.isGoal()){
    		count++;
    		for(Board i : fin.input.neighbors()){
    			//System.out.println(i.toString());
    			i.hamming();
    			i.manhattan();
    			if(fin.The_mom == null) list.insert(new SearchNode(i, 1, fin));
    			else if((!i.equals(fin.The_mom.input))) list.insert(new SearchNode(i, fin.move+1, fin));
    		}
    		fin = list.delMin();
    		//System.out.println(fin.input.toString() + fin.input.isGoal());
    	}
    	//System.out.println(fin.input.toString());
    	
    }
    
    public int moves(){
    	// min number of moves to solve initial board
    	return fin.move;
    }
    
    public Iterable<Board> solution(){
    	// sequence of boards in a shortest solution
    	Stack sol = new Stack();
    	SearchNode out_follow = fin;
    	Board out = fin.input;
    	sol.push(out);
    	Stack fin = new Stack();
    	while(out_follow.The_mom != null){
    		out_follow = out_follow.The_mom;
    		out = out_follow.input;
    		sol.push(out);
    	}
    	while(!sol.isEmpty()){
    		
    		fin.push(sol.pop());
    	}
    	return fin;
    }
    
    public static void main(String[] args){
    	// solve a slider puzzle (given below) 
    	Scanner read = new Scanner(System.in);
    	int size = read.nextInt();
    	int[][] input = new int[size][size];
    	
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			input[i][j] = read.nextInt();
    		}
    	}
    	read.close();
    	Board test = new Board(input);
    	//System.out.println(test.toString());
    	//System.out.println(test.isGoal());
    	Solver solution = new Solver(test);
    	for(Board i : solution.solution()){
    		System.out.println(i.toString());
    	}
    	
    }
    
}

