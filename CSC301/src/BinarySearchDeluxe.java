import java.util.Comparator;

import edu.princeton.cs.algs4.MergeX;

public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){
    	if(a == null || key == null || comparator == null) throw new java.lang.NullPointerException("Please input something that would work. ");
        int lo = 0;
        int hi = a.length - 1;
        if(comparator.compare(a[0], key) == 0) return 0;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            //System.out.println(mid);
            Key mid_key = a[mid];
            int compare_num = comparator.compare(key, mid_key);
            //System.out.println(compare_num);
            if      (compare_num < 0) hi = mid - 1;
            else if (compare_num > 0) lo = mid + 1;
            else if (comparator.compare(a[mid - 1], mid_key) == 0) hi = mid - 1;
            else return mid;
            //System.out.printf("hi = %d, lo = %d. \n", hi, lo);
        }
        return -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
    	if(a == null || key == null || comparator == null) throw new java.lang.NullPointerException("Please input something that would work. ");
        int lo = 0;
        int hi = a.length - 1;
        if(comparator.compare(a[0], key) == 0) return 0;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            Key mid_key = a[mid];
            if      (comparator.compare(key, mid_key) < 0) hi = mid - 1;
            else if (comparator.compare(key, mid_key) > 0) lo = mid + 1;
            else if (comparator.compare(mid_key, a[mid+1]) == 0) lo = mid + 1;
            else return mid;
            //System.out.printf("hi = %d, lo = %d. \n", hi, lo);
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args){
    	Term[] test = new Term[7];
    	test[0] = new Term("hello", 1);
    	test[1] = new Term("darkness", 2);
    	test[2] = new Term("my", 3);
    	test[3] = new Term("my", 3);
    	test[4] = new Term("my", 3);
    	test[5] = new Term("old", 4);
    	test[6] = new Term("friend", 5);
    	MergeX.sort(test, Term.byPrefixOrder(1));
    	for(int i = 0; i < 7; i++){
    		System.out.println(test[i].toString());
    	}
    	System.out.println(BinarySearchDeluxe.firstIndexOf(test,new Term("my", 3), Term.byPrefixOrder(1)));
    	System.out.println(BinarySearchDeluxe.lastIndexOf(test,new Term("my", 3), Term.byPrefixOrder(1)));
    }
}
