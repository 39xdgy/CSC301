import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class Autocomplete {
	private Term[] terms;
	private Term[] output;
	
    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms){
    	if(terms == null) throw new java.lang.NullPointerException("Input is null");
    	for(int i = 0; i < terms.length; i++){
        	if(terms[i] == null) throw new java.lang.NullPointerException("Input is null");
    	}
    	this.terms = terms;
    	MergeX.sort(this.terms);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix){
    	Term comp = new Term(prefix, 0);
    	int top = BinarySearchDeluxe.firstIndexOf(this.terms, comp, Term.byPrefixOrder(prefix.length()));
    	int bottom = BinarySearchDeluxe.lastIndexOf(this.terms, comp, Term.byPrefixOrder(prefix.length()));
    	//System.out.println(terms[top].toString());
    	//System.out.println(top);
    	//System.out.println(bottom);
    	if(top == -1 || bottom == -1) return new Term[0];
    	this.output = Arrays.copyOfRange(this.terms, top, bottom+1);
    	MergeX.sort(output, Term.byReverseWeightOrder());
		return output;
    	
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix){
    	Term comp = new Term(prefix, 0);
    	int top = BinarySearchDeluxe.firstIndexOf(this.terms, comp, Term.byPrefixOrder(prefix.length()));
    	int bottom = BinarySearchDeluxe.lastIndexOf(this.terms, comp, Term.byPrefixOrder(prefix.length()));
    	if (top == -1 || bottom == -1) return 0;
		return bottom - top + 1;
    	
    }
    
    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
    
}