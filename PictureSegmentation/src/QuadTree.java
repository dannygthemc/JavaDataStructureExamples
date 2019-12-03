/*
 * Coded by Daniel Gilbert
 * A Quad Tree data structure for compressing photos.
 * Completed for CS1027b Assignment Four
 * Completed on 4/11/2013
 */

public class QuadTree<T> {

	public int count;//keeps track of number of nodes
	public QuadNode<T> root;//root node
	//constants for homogeneity calculation
	double maxLevel;
	double RedT;//
	double GreenT;
	double BlueT;
	//holds parameters to be passed to node creator
	double[] parameters = new double[6];
	MyPicture pic;//holds picture
	
	/*
	 * constructor
	 * @param  _pic picture file that holds the original picture
	 * @param _root Quadnode root to build tree from
	 * @param _maxLevel double constant for homogeneity calculation
	 * @param _RedT double constant for homogeneity calculation
	 * @param _BlueT double constant for homogeneity calculation
	 * @param _GreenT double constant for homogeneity calculation
	 * 
	 */
	public QuadTree (MyPicture _pic, QuadNode<T> _root, double _maxLevel, double _RedT, double _BlueT, double _GreenT){
		count = 1;//sets count to 1
		root = _root;//adds root
		//set constants
		pic = _pic;
		maxLevel = _maxLevel;
		RedT = _RedT;
		BlueT = _BlueT;
		GreenT = _GreenT;
		//check homogeneity and split root
		if (!homogeanity(root)){
			split(root);
		}
	}
	/*
	 * check if node meets homogeneity criteria
	 * @param node Quadnode to be checked
	 * @return check boolean that returns true if the node meets the criteria
	 */
	public boolean homogeanity(QuadNode<T> node){
		boolean check = false;
		if ((node.sigmaRed <= RedT && node.sigmaGreen <= GreenT && 
				node.sigmaBlue <= BlueT) || node.level==maxLevel){
			check = true;
		}
		return check;	
	}
	
	/*
	 * creates leafs to current node. "splits" the node
	 * recursive function, continues until all leaf nodes meet the homogeneity criteria
	 * @param _node the node to be split
	 */
	public void split(QuadNode<T> _node){
		int length = (_node.getSideLength() / 2);//halves length of current node
		if(_node != null){
			//creates a new node for each NE, NW, SE, SW. Each node has half the length of the original node.
			//x and y coordinates are calculated accordingly 
		_node.setNorthWest(new QuadNode<T>(pic, _node.getX(), _node.getY(), length, _node.getLevel() +1,
				pic.simpleStatistics(_node.getX(), _node.getY(), length) ));//x and y remain the same
		
		_node.setNorthEast(new QuadNode<T>(pic, _node.getX() + length, _node.getY(), length, _node.getLevel() +1, 
				pic.simpleStatistics(_node.getX() + length, _node.getY(), length)));//(x +length, y)
		
		_node.setSouthWest(new QuadNode<T>(pic, _node.getX(), _node.getY() + length, length, _node.getLevel() +1,
				pic.simpleStatistics(_node.getX(), _node.getY() + length, length)));//(x, y+length)
		
		_node.setSouthEast(new QuadNode<T>(pic, _node.getX() + length, _node.getY() + length, length, _node.getLevel() +1, 
				pic.simpleStatistics(_node.getX() + length, _node.getY()+length, length)));//(x+length, y+length)
		}
		//checks each new node for homogeneity and recursively calls function until
		//all if statements fail
		if(!homogeanity(_node.getNorthWest())){
			split(_node.getNorthWest());
		}
		if(!homogeanity(_node.getNorthEast())){
			split(_node.getNorthEast());
		}
		if(!homogeanity(_node.getSouthWest())){
			split(_node.getSouthWest());
		}
		if(!homogeanity(_node.getSouthEast())){
			split(_node.getSouthEast());
		}
	}
	//checks if the tree has more than a root
	//@return check returns true if empty
	 public boolean isEmpty() 
	   {
	      boolean check = false;
	      if(count == 1){
	    	  check = true;
	      }
	      return check;
	   }
	 //@return count, size of tree
	 public int size() 
	   {
	      return count;
	   }
	 /*
	  * creates a queue using a preorder recursion of the tree
	  * enqueues the leaf nodes as these are the ones that will meet the homogeneity criteria
	  * @param node, node to start recursion from (root)
	  * @param temp, queue to hold lead nodes
	  * @return temp, queue full of leaf nodes
	  */
	 
	 protected LinkedQueue<QuadNode<T>> preorder (QuadNode<T> node, 
             LinkedQueue<QuadNode<T>> temp) 
	 {
		 	if (node != null)//checks for null nodes
		 	{
		 		if(isLeaf(node)){//if node is a leaf
		 			temp.enqueue(node);//enqueue
		 		}
		 		else{//if not a leaf, recursively call function on each child
		 		preorder (node.getNorthEast(), temp); 
		 		preorder (node.getNorthWest(), temp);
		 		preorder (node.getSouthEast(), temp);
		 		preorder (node.getSouthWest(), temp);
		 		}
		 	}
		 	return temp;//returns full queue
	 }
	 /*
	  * checks if current node is a leaf node (no children)
	  * @param node, node to be checked
	  * @return check, boolean returns true if it is a leaf
	  */
	 public boolean isLeaf(QuadNode<T> node){
		 boolean check = true;
		 //checks if any of the children pointers are non-null
		 if(node.northEast != null || node.northWest != null || node.southEast != null || node.southWest!= null){
			 check = false;
		 }
		 return check;
	 }
	 /*
	  * draws white lines to show areas of homogeneity
	  * @param pic, picture file
	  * @param queue, queue full of leaf nodes
	  */
	 public void drawSegmentation(MyPicture pic, LinkedQueue<QuadNode<T>> queue ){
		 	QuadNode<T> hold;//quadnode as a holder
		 	LinkedQueue<QuadNode<T>> newQueue = new LinkedQueue<QuadNode<T>>();//new queue to enqueue as the other empties
		 	while(!queue.isEmpty()){
		 		hold = queue.dequeue();//dequeues
		 		pic.drawWhiteSquare(hold.getX(), hold.getY(), hold.getSideLength());//draws square representing that node
		 		newQueue.enqueue(hold);//enqueues to the new queue
		 	}
		 	queue = newQueue;//gives nodes back to original queue in case we need to return it for another program
	 }
	 /*
	  * paints squares based on color and size of nodes
	  * @param pic, the picture
	  * @param queue, leaf nodes
	  * 
	  */
	 public void paintSquares(MyPicture pic, LinkedQueue<QuadNode<T>> queue){
		 	QuadNode<T> hold;//quadnode as a holder
		 	LinkedQueue<QuadNode<T>> newQueue = new LinkedQueue<QuadNode<T>>();//new queue to enqueue as the other empties
		 	while(!queue.isEmpty()){
		 		hold = queue.dequeue();
		 		//paints squares based on node values
		 		pic.paintSegment(hold.getX(), hold.getY(),hold.getSideLength(), hold.getMeanRed(), hold.getMeanGreen(), hold.getMeanBlue());
		 		newQueue.enqueue(hold);
		 	}
		 	queue = newQueue;//maintain queue in case it's necessary for use in a later program
	 }
	
}
