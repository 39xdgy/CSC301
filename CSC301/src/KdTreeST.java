import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;

public class KdTreeST<Value> {
	
	private Node The_mom;
	private boolean cross = false;
	
	private class Node<Value>{
		
		private Point2D in;
		private boolean is_horozontal;
		private Value value;
		private Node left;
		private Node right;
		
		public Node(Point2D input, boolean cross, Value input_value){
			in = input;
			is_horozontal = cross;
			value = input_value;
			left = null;
			right = null;
		}
	}
	
	public KdTreeST(){
		// construct an empty symbol table of points 
		The_mom = null;
	}
	public boolean isEmpty(){
		// is the symbol table empty? 
		return The_mom == null;
	}
	public int size(){
		// number of points 
		return 0;
	}
	public void put(Point2D p, Value val){      
		// associate the value val with point p
		boolean left = false;
		boolean right = false;
		if(The_mom == null) The_mom = new Node(p, cross, val);
		else{
			int level = 0;
			Node temp = The_mom;
			while(true){
				if(level % 2 == 0){
					double x_mom = temp.in.x();
					double x_new = p.x();
					if(x_mom < x_new){
						if(temp.right == null){
							right = true;
							break;
						}
						temp = temp.right;
						level++;
					}
					else{
						if(temp.left == null){
							left = true;
							break;
						}
						temp = temp.left;
						level++;
					}
				}
				else if(level % 2 == 1){
					double y_mom = temp.in.y();
					double y_new = p.y();
					if(y_mom < y_new){
						if(temp.right == null){
							right = true;
							break;
						}
						temp = temp.right;
						level++;
					}
					else{
						if(temp.left == null){
							left = true;
							break;
						}
						temp = temp.left;
						level++;
					}
				}
			}
			if(left) {
				if(level % 2 == 0) temp.left = new Node(p, cross, val);
				else if(level % 2 == 1) temp.left = new Node(p, !cross, val);
			}
			else if(right) {
				if(level % 2 == 0) temp.right = new Node(p, cross, val);
				else if(level % 2 == 1) temp.right = new Node(p, !cross, val);
			}
		}
	}
	public Value get(Point2D p){
		// value associated with point p 
		Node temp = The_mom;
		while(temp != null){
			if(temp.in.equals(p)) {
				System.out.println("Success");
				return (Value) temp.value;
			}
			if(!temp.is_horozontal){
				double x_mom = temp.in.x();
				double x_new = p.x();
				if(x_mom < x_new){
					temp = temp.left;
				}
				else{
					temp = temp.right;
				}
			}
			else if(temp.is_horozontal){
				double y_mom = temp.in.y();
				double y_new = p.y();
				if(y_mom < y_new){
					temp = temp.left;
				}
				else{
					temp = temp.right;
				}
			}
		}
		return null;
	}
	public boolean contains(Point2D p){
		// does the symbol table contain point p? 
		return this.get(p) != null;
	}
	
	private void runthough(Queue list,Queue order){
		Node input = (Node) order.dequeue();
		if(input == null) return;
		list.enqueue(input.in);
		order.enqueue(input.left);
		order.enqueue(input.right);
		if(!order.isEmpty()) runthough(list, order);
	}
	
	public Iterable<Point2D> points(){
		// all points in the symbol table 
		Queue<Point2D> list = new Queue<Point2D>();
		Queue<Node> order = new Queue<Node>();
		order.enqueue(The_mom);
		runthough(list, order);
		return list;
	}
	
	private void range_help(Node in, RectHV rect, Queue fin){
		double x_min_rect = rect.xmin();
		double x_max_rect = rect.xmax();
		double y_min_rect = rect.ymin();
		double y_max_rect = rect.ymax();
		
		if(in == null) return;
		if(rect.contains(in.in)) fin.enqueue(in.in);
		range_help(in.left, rect, fin);
		range_help(in.right, rect, fin);
	}
	
	
	public Iterable<Point2D> range(RectHV rect){
		// all points that are inside the rectangle 
		Node temp = The_mom;
		Queue fin = new Queue();
		range_help(temp, rect, fin);
		return fin;
	}
	
	
	public Point2D nearest(Point2D p){
		// a nearest neighbor to point p; null if the symbol table is empty 
		Node temp = The_mom;
		Point2D fin = null;
		double min = Double.MAX_VALUE;
		while(true){
			
			double dis = p.distanceTo(temp.in);
			if(min > dis) {
				fin = temp.in;
				min = dis;
			}
			
			if(temp.is_horozontal){
				double x_mom = temp.in.x();
				double x_input = p.x();
				if(x_mom > x_input){
					temp = temp.left;
				}
				else {
					temp = temp.right;
				}
			}
			else if(!temp.is_horozontal){
				double y_mom = temp.in.y();
				double y_input = p.y();
				if(y_mom > y_input){
					temp = temp.left;
				}
				else {
					temp = temp.right;
				}
			}
			break;
		}
		return fin;
	}
	public static void main(String[] args){
		// unit testing (required) 
		//System.out.println("哈哈哈哈");
		KdTreeST<Integer> test = new KdTreeST<Integer>();
		test.put(new Point2D(.7, .2),  1);
		test.put(new Point2D(.5, .4),  2);
		test.put(new Point2D(.2, .3),  3);
		test.put(new Point2D(.4, .7),  4);
		test.put(new Point2D(.9, .6),  5);
		RectHV rect = new RectHV(0.2, 0.2, 0.8, 0.8);
		//System.out.println(test.The_mom.in.toString());
		for(Point2D i : test.range(rect)){
			System.out.println(i.toString());
		}
	}
}