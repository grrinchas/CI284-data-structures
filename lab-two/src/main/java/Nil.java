public class Nil<T> extends LinkedList<T> {

	@Override
	public T head() {
		throw new RuntimeException("Called head on an empty list.");
	}

	@Override
	public LinkedList<T> tail() {
		throw new RuntimeException("Called tail on an empty list.");
	}
	@Override
	public boolean equals(Object o) {
		return (o instanceof Nil<?>);
	}
	
	@Override
	public String toString() {
		return "Nil";
	}
	/*
	 * The methods you need to implement start here.
	 */
	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public boolean member(T e) {
		return false;
	}

	@Override
	public LinkedList<T> delete(T e) {
		return this;
	}
	
	@Override
	public int indexOf(T e) {
		return -1;
	}
	@Override
	protected int indexOfInner(T e, int count) {
		return -1;
	}

}
