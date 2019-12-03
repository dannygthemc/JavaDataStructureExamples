/**
 * BinaryTreeNode represents a node in a binary tree with a left and 
 * right child.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

public class BinaryTreeNode<T>
{
   T element;
   BinaryTreeNode<T> left;
BinaryTreeNode<T> right;

   /**
    * Creates a new tree node with the specified data.
    *
    * @param obj  the element that will become a part of the new tree node
   */
   BinaryTreeNode (T obj) 
   {
      element = obj;
      left = null;
      right = null;
   }

   /**
    * Returns the number of non-null children of this node.
    * This method may be able to be written more efficiently.
    *
    * @return  the integer number of non-null children of this node 
    */
   public int numChildren() 
   {
      int children = 0;

      if (left != null)
         children = 1 + left.numChildren();

      if (right != null)
         children = children + 1 + right.numChildren();

      return children;
   }
   public T getElement(){
	   return element;
   }
   public BinaryTreeNode<T> getLeft(){
	   return left;
   }
   public BinaryTreeNode<T> getRight(){
	   return right;
   }
   public void setElement(T obj){
	   element = obj;
   }
   public void setLeft(BinaryTreeNode<T> L){
	   left =L;
   }
   public void setRight(BinaryTreeNode<T> R){
	   right = R;
   }
   
}