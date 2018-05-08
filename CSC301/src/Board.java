import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner; //united testing use
import java.util.Stack;

public class Board {
	private int[][] board;
	private int size;
	private int row_empty;
	private int col_empty;
	private int ham;
	private int man;
	private int inversions;
	
    public Board(int[][] tiles){
    	// construct a board from an N-by-N array of tiles
    	size = tiles[0].length;
    	board = new int[size][size];
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			board[i][j] = tiles[i][j];
    			if(board[i][j] == 0) {
    				row_empty = i;
    				col_empty = j;
    			}
    		}
    	}
    	ham = hamming();
    	man = manhattan();
    }
    
     // (where tiles[i][j] = tile at row i, column j)
    public int tileAt(int i, int j){
    	if(i < 0 || i >= size || j < 0 || j >= size) throw new java.lang.IndexOutOfBoundsException("Please type something in range");
    	// return tile at row i, column j (or 0 if blank)
    	return board[i][j];
    }
    
    public int size(){
    	// board size N
    	return size;
    }
    
    public int hamming(){
    	// number of tiles out of place
    	ham = 0;
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			int D1 = i * size + j; 
    			if(board[i][j] == 0) continue;
    			else if(board[i][j] != D1+1){
    				ham++;
    				//System.out.println(ham);
    			}
    		}
    	}
    	return ham;
    }
    
    public int manhattan(){
    	// sum of Manhattan distances between tiles and goal
    	man = 0;
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			int D1 = i * size + j; 
    			if(board[i][j] == 0) continue;
    			else if(board[i][j] != D1+1){
    				int wrong_index = board[i][j] - 1;
    				int wrong_i = wrong_index / size;
    				int wrong_j = wrong_index % size;
    				int diff_i = wrong_i - i;
    				int diff_j = wrong_j - j;
    				if(diff_i < 0) diff_i = -diff_i;
    				if(diff_j < 0) diff_j = -diff_j;
    				man += diff_i + diff_j;
    			}
    		}
    	}
    	return man;
    }
    
    public boolean isGoal(){
    	// is this board the goal board?
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			if((board[i][j] != i * size + j + 1) && board[i][j] != 0) return false;
    		}
    	}
    	return true;
    }
    
    public boolean isSolvable(){
    	// is this board solvable?
    	inversions = 0;
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			int D1 = i * size + j; 
    			if(board[i][j] != 0 ){
    				for(int k = D1; k < size*size; k++){
    					int new_i = k / size;
    					int new_j = k % size;
        				if (board[i][j] > board[new_i][new_j] && board[i][j] != 0 && board[new_i][new_j] != 0) {
        					//System.out.printf("%d, %d, %d, %d\n", i, j, new_i, new_j);
        					inversions++;
        				}
    				}
    			}
    		}
    	}
    	//System.out.println("inv" + inversions);
    	//System.out.println("zero row: " + row_empty);
    	//System.out.println("size: " + size);
    	//System.out.println("***********************************");
    	if(size % 2 == 1){
    		if(inversions % 2 == 1) return false;
    	}
    	else if(size % 2 == 0){
    		int sum = inversions + row_empty;
    		if(sum % 2 == 0) return false;
    	}
    	return true;
    }
    public boolean equals(Object y){
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if(this.size != that.size) return false;
        for(int i = 0; i < size; i++){
        	for(int j = 0; j < size; j++){
        		if(this.board[i][j] != that.board[i][j]) return false;
        	}
        }
    	return true;
    }
    
    private Board switch_num(int row0, int col0, int row1, int col1){
    		Board fin = new Board(board);
    		int temp = fin.board[row0][col0];
    		fin.board[row0][col0] = fin.board[row1][col1];
    		fin.board[row1][col1] = temp;
    		if(temp == 0){
    			fin.row_empty = row1;
    			fin.col_empty = col1;
    		}
    		else{
    			fin.row_empty = row0;
    			fin.col_empty = col0;
    		}
    	return fin;
    }
    
    public Iterable<Board> neighbors(){
    	// all neighboring boards
    	Stack nei = new Stack();
    	if(col_empty != size-1) nei.push(switch_num(row_empty, col_empty, row_empty, col_empty+1));
    	if(col_empty != 0) nei.push(switch_num(row_empty, col_empty, row_empty, col_empty-1));
    	if(row_empty != size-1) nei.push(switch_num(row_empty, col_empty, row_empty+1, col_empty));
    	if(row_empty != 0) nei.push(switch_num(row_empty, col_empty, row_empty-1, col_empty));
    	return nei;
    }
	
    
    public String toString(){
    	// string representation of this board (in the output format specified below)
    	String fin = size + "\n";
    	for(int i = 0; i < size; i++){
    		for(int j = 0; j < size; j++){
    			fin += String.format("%2d", board[i][j]);
    		}
    		fin += "\n";
    	}
    	return fin;
    }

    public static void main(String[] args){
    	// unit testing (required)
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
    	System.out.printf(
    			"0,0 has the number %d. \n"
    			+ "size is %d. \n"
    			+ "hamming is %d. \n"
    			+ "manhattan is %d. \n"
    			+ "isgoal is %b. \n"
    			+ "issolvable is %b", 
    			test.tileAt(0,0),
    			test.size(), 
    			test.hamming(), 
    			test.manhattan(), 
    			test.isGoal(), 
    			test.isSolvable());
    	for(Board i : test.neighbors()){
    		System.out.println(i.toString());
    	}
    }
    
    
    
    
}