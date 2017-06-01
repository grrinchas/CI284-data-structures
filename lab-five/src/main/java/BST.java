/*
 * This is an ADT for a Binary Search Tree which can contain Comparable data. Implement concrete versions of the methods
 * below in the two subclasses of BST, which are BranchNode and LeafNode.
 */
public abstract class BST<T extends Comparable<T>> {
	T label;
	
	/*
	 * Return the number of nodes in the BST.
	 */
	abstract public int countNodes();
	/*
	 * Return the height of the BST.
	 */
	abstract public int height();
	abstract public BST<T> getLeft();
	abstract public BST<T> getRight();
	/*
	 * Return a String representation of the BST. This should a String containing the labels
	 * in order. Use an inorder traversal to generate the string.
	 */
	abstract public String toString();
	/*
	 * Return a new BST which is the result of inserting the element e to the current BST.
	 * You need to maintain the BST conditions by inserting the element at the right location.
	 */
	abstract public BST<T> insert(T e);
	/*
	 * Return a BST which is the result of removing the element e from the current BST.
	 */
	abstract public BST<T> remove(T e);
	
	abstract protected BST<T> merge(BST<T> that);
	
	public T getLabel() {
		return label;
	}
}


