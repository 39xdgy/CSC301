import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;

public class PointST<Value> {
	
	private RedBlackBST<Point2D, Value> table;


	public PointST(){
		// construct an empty symbol table of points 
		table = new RedBlackBST<Point2D, Value>();
	}
	public boolean isEmpty(){
		// is the symbol table empty? 
		return table.size() == 0;
	}
	public int size(){
		// number of points
		return table.size();
	}
	public void put(Point2D p, Value val){
		// associate the value val with point p
		table.put(p, val);
	}
	public Value get(Point2D p){
		return table.get(p);
		// value associated with point p 
	}
	public boolean contains(Point2D p){
		// does the symbol table contain point p? 
		return table.contains(p);
	}
	public Iterable<Point2D> points(){
		// all points in the symbol table 
		return table.keys();
	}
	
	public Iterable<Point2D> range(RectHV rect){
		// all points that are inside the rectangle 
		Stack<Point2D> fin = new Stack<Point2D>();
		for(Point2D i : this.points()){
			if(rect.contains(i)) fin.push(i);
		}
		return fin;
	}
	public Point2D nearest(Point2D p){
		// a nearest neighbor to point p; null if the symbol table is empty 
		Point2D fin = null;
		double dis = Double.MAX_VALUE;
		RedBlackBST<Point2D, Value> take_out = table;
		while(!take_out.isEmpty()){
			Point2D min = take_out.min();
			double new_D = min.distanceTo(p);
			if(new_D == 0) return min;
			if(new_D < dis) {
				fin = min;
				dis = new_D;
			}
			take_out.deleteMin();
		}
		return fin;
	}
	public static void main(String[] args){
		// unit testing (required) 
		PointST<Integer> test = new PointST<Integer>();
		Point2D input = new Point2D(1.0, 0.0);
		test.put(input, 3);
		System.out.println(test.isEmpty());
		System.out.println(test.size());
		for(Point2D i : test.range(new RectHV(0.1,0.1,1.1,1.1))){
			System.out.println(i.toString());
		}
	}
}