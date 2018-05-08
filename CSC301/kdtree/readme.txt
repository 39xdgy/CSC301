/******************************************************************************
 *  Name: Jiashu Wang
 *
 *  Operating system: Linux
 *  Compiler:
 *  Text editor / IDE: Elipse
 *  Hours to complete assignment (optional): 30
 ******************************************************************************/



/******************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 *****************************************************************************/
The Node has the point2D, the value, left child, right child and horizontal or vertical

/******************************************************************************
 *  Describe your method for range search in a kd-tree.
 *****************************************************************************/
It would check the node is null or not, and then check if the rectangle is intersected with each other or not.
And if both works, It would goes to left or right to do the function again until run
into a null point.

/******************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 *****************************************************************************/


/******************************************************************************
 *  Using the 64-bit memory cost model from the textbook and lecture,
 *  give the total memory usage in bytes of your 2d-tree data structure
 *  as a function of the number of points N. Use tilde notation to
 *  simplify your answer (i.e., keep the leading coefficient and discard
 *  lower-order terms).
 *
 *  Include the memory for all referenced objects (including
 *  Node, Point2D, and RectHV objects) except for Value objects
 *  (because the type is unknown). Also, include the memory for
 *  all referenced objects.
 *
 *  Justify your answer below.
 *
 *****************************************************************************/

bytes per Point2D:

bytes per RectHV:

bytes per KdTree of N points:   ~





/******************************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Explain how you determined the
 *  operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 *****************************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt

input1M.txt



/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

The Range and the nearest function for KDtree is not working.


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and exercises, but do
 *  include any help from people (including classmates and friends) and
 *  attribute them by name.
 *****************************************************************************/

Get a lot of help from the class, also ask Salem for debugging.

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/

The logic is really complicated and the English understanding become a big problem this time.
Once I saw a article talks about why there are a lot of Indians programmer works in the US compare to other Asian people,
and one of the reason is that India still use English as the basic language.
I did not understand it until this project that I start feeling like understanding by using English is harder then I thought.

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
The time is hard and also This week is not going well for me in general. It is my personal problem that if this project is on other time,
the output will be better. But this time, I really tried my best.
