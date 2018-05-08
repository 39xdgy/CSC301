import edu.princeton.cs.algs4.StdIn;

public class Subset {
	public static void main(String[] args){ 
		RandomizedQueue<String> input = new RandomizedQueue<String>();
		
		for(String in : StdIn.readAllStrings()){
			System.out.println("ha");
			input.enqueue(in);
		}
		
		int pop_time = Integer.valueOf(args[0]);
		
		for(int i = 0; i < pop_time; i++){
			System.out.println(input.dequeue());
		}
	}
}
