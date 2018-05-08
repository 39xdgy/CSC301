import java.util.Collections;
import java.util.Comparator;

import edu.princeton.cs.algs4.MergeX;

public class Term implements Comparable<Term> {
	private String query;
	private long weight;
	
	
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight){
    	if(query == null || weight < 0) throw new java.lang.NullPointerException("Please import a useful input");
    	this.query = query;
    	this.weight = weight;
    }
    

    private static class byWeight implements Comparator<Term>{
    	public int compare(Term a, Term that){
    		long value = a.weight - that.weight;
    		//System.out.println(value);
    		if(value < 0) return 1;
    		else if(value == 0) return 0;
    		else return -1;
    	}
    }
    
    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
		return new byWeight();
    	
    }

    private static class byPrefix implements Comparator<Term> {
    	int input;
    	public byPrefix(int input){
    		this.input = input;
    	}
    	
    	public int compare(Term a, Term that){
    		int a_len = a.query.length();
    		String a_q = a.query;
    		int that_len = that.query.length();
    		String that_q = that.query;
    		if(a_len < input) a_q = a_q.substring(0, a_len);
    		else a_q = a_q.substring(0,input);
    		if(that_len < input) that_q = that_q.substring(0, that_len);
    		else that_q = that_q.substring(0, input);
    		//System.out.println(a_q + " " + that_q);
    		//System.out.println(a_q.compareTo(that_q));
    		return a_q.compareTo(that_q);
    	}
    	
    }
    
    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
    	if(r < 0) throw new java.lang.IllegalArgumentException("Please input a positive number"); 
    	return new byPrefix(r);
    	
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that){
		return this.query.compareTo(that.query);
    	
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString(){
		return (this.weight + "\t" + query);
    	
    }

    // unit testing (required)
    public static void main(String[] args){
    	Term[] test = new Term[6];
    	test[0] = new Term("hello", 1);
    	test[1] = new Term("darkness", 2);
    	test[2] = new Term("my", 3);
    	test[3] = new Term("old", 4);
    	test[4] = new Term("friend", 5);
    	test[5] = new Term("darkneaa", 8);
    	for(int i = 0; i < 5;i++){
    		System.out.println(test[i].toString());
    	}
    	System.out.println("");
    	MergeX.sort(test, byReverseWeightOrder());
    	for(int i = 0; i < 5; i++){
    		System.out.println(test[i].toString());
    	}
    	System.out.println("");
    	MergeX.sort(test, byPrefixOrder(1));
    	for(int i = 0; i < 5; i++){
    		System.out.println(test[i].toString());
    	}
    	
    }
}