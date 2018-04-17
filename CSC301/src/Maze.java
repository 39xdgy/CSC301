import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

public class Maze {
    public static void main(String[] args) {
        Stopwatch clock = new Stopwatch();
        int N = Integer.parseInt(args[0]);
        WeightedQuickUnionUF UF1 = new WeightedQuickUnionUF(N);
        while (true) {
           int i = StdRandom.uniform(N);
           int j = StdRandom.uniform(N);
           if (!UF1.connected(i,j)){
              UF1.union(i,j);
           }
           if (UF1.connected(0,N-1)) {
             // some message
             break;
           }
        }
        System.out.println(N + "  " + clock.elapsedTime());
       
    }
}
