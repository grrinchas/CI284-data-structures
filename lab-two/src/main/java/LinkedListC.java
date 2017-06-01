public abstract class LinkedListC<T extends Comparable<T>> {
	
	/**
	 * The insert method inserts a new element into the list and keeps the list
	 * in sorted order. Write your function recursively. Inserting an element into 
	 * an empty list just creates a new ListItem with one element. To insert an element 
	 * into a non-empty list, use compareTo to see whether the element is less than, equal 
	 * to or greater than the head of the list. If e is less than the head, return a new
	 * ListItem with the current head and e inserted into the tail. If e is greater than 
	 * head, return a new ListItem with e as the head and the old head inserted into the 
	 * tail. Decide what to do if they are equal.
	 * 
	 * @param e
	 * @return
	 */
	public abstract ListItemC<T> insert(T e);
	/*
	 * The following methods should work in the same way as for the LinkedList class.
	 */
	public abstract T head();
	public abstract LinkedListC<T> tail();
	public abstract int length();
	public abstract boolean member(T e);
	public abstract boolean isEmpty();
	public abstract int indexOf(T e);
	protected abstract int indexOfInner(T e, int count);
	public abstract LinkedListC<T> delete(T e);

}
