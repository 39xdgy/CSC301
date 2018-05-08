/******************************************************************************
 *  Name:Jiashu Wang
 *
 *  Operating system: Linux
 *  Compiler:
 *  Text editor / IDE: Eclipse
 *  Hours to complete assignment (optional): 5
 ******************************************************************************/


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 *****************************************************************************/

So The Idea that I have is having a matrix to simulate the actually flow. 
And I have another weighted union list to make the tree to link all the node. 
When I create the weighted union list, I create two vertial nodes with repersent the top and the bottom. 
if these two nodes are connected, it means the whole system is percolated. 



/******************************************************************************
 *  Using Percolation with QuickFindUF.java,  fill in the table below such that
 *  the N values are multiples of each other.

 *  Give a formula (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as a function of both N and T. Be sure to give both
 *  the coefficient and exponent of the leading term. Your coefficients should
 *  be based on empirical data and rounded to two significant digits, such as
 *  5.3*10^-8 * N^5.0 T^1.5.
 *****************************************************************************/

(keep T constant)

 N          time (seconds)
------------------------------
50          0.10
100         1.09
200         15.24
400         215.28
800         3582.2        



(keep N constant)

 T          time (seconds)
------------------------------
25          1.11
50          2.23
100         4.31
200         8.51
400         16.78


running time as a function of N and T:  8.41*10^-9 N^4.0 T


/******************************************************************************
 *  Repeat the previous question, but use WeightedQuickUnionUF.java.
 *****************************************************************************/

(keep T constant)

 N          time (seconds)
------------------------------
50          0.03
100         0.04
200         0.14
400         0.43
800         3.42

n ^ 2 2.68 * 10 ^ -6
(keep N constant)

 T          time (seconds)
------------------------------
25          0.15
50          0.21
100         0.34
200         0.64
400         1.19


running time as a function of N and T:  2.68*10^-6 N^2 T


/**********************************************************************
 *  How much memory (in bytes) does a Percolation object use to store
 *  an N-by-N grid? Use the 64-bit memory cost model from Section 1.4
 *  of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers.
 *
 *  Include the memory for all referenced objects (deep memory).
 **********************************************************************/


4N^2 for the matrix
4N^2 * 2 for the both weight union 






/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

Nothing, everything works


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and exercises, but do
 *  include any help from people (including classmates and friends) and
 *  attribute them by name.
 *****************************************************************************/
 
A lot of people helped me. Meanly a lot of people in class helped me.
Also, 

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/

Not a lot of problems encountered. A lot of problems were about index overflow


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
 
 If we have more help on the readme file, it would be great
 
