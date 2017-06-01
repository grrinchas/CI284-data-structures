public class ListItemC<T extends Comparable<T>> extends LinkedListC<T> {

    T head;
    LinkedListC<T> tail;

    public ListItemC(T head, LinkedListC<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public LinkedListC<T> tail() {
        return tail;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ListItemC<?>))
            return false;
        ListItemC<T> that = (ListItemC<T>) o;
        return head.equals(that.head) && tail.equals(that.tail);
    }

    @Override
    public String toString() {
        return head.toString() + " :: " + tail.toString();
    }

    /*
     * The methods you need to implement start here.
     */
    @Override
    public ListItemC<T> insert(T e) {
        int comparison = e.compareTo(this.head);
        if (comparison > 0) {
            return new ListItemC<>(this.head, this.tail.insert(e));
        } else if (comparison < 0) {
            return new ListItemC<>(e, this.tail.insert(this.head));
        } else {
            return new ListItemC<>(this.head, this.tail.insert(e));
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int length() {
        return 1 + this.tail.length();
    }

    @Override
    public boolean member(T e) {
        return e.equals(this.head) || this.tail.member(e);
    }

    @Override
    public int indexOf(T e) {
        return this.indexOfInner(e, 0);
    }

    @Override
    protected int indexOfInner(T e, int count) {
        return this.head.equals(e) ? count : this.tail.indexOfInner(e, count + 1);
    }

    @Override
    public LinkedListC<T> delete(T e) {
        return this.head.equals(e) ? this.tail : new ListItemC<>(e, this.tail.delete(e));
    }
}
