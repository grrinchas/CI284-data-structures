public class NilC<T extends Comparable<T>> extends LinkedListC<T> {

    @Override
    public T head() {
        throw new RuntimeException("Called head on an empty list.");
    }

    @Override
    public LinkedListC<T> tail() {
        throw new RuntimeException("Called tail on an empty list.");
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof NilC<?>);
    }

    @Override
    public String toString() {
        return "Nil";
    }

    /*
     * The methods you need to implement start here.
     */
    @Override
    public ListItemC<T> insert(T e) {
        return new ListItemC<>(e, new NilC<>());
    }

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
    public LinkedListC<T> delete(T e) {
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
