public class ListItem<T> extends LinkedList<T> {
    T head;
    LinkedList<T> tail;

    public ListItem(T head, LinkedList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public LinkedList<T> tail() {
        return tail;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ListItem<?>))
            return false;
        ListItem<T> that = (ListItem<T>) o;
        return head.equals(that.head) && tail.equals(that.tail);
    }

    @Override
    public String toString() {
        return head.toString() + " :: " + tail.toString();
    }

    /*
     * The methods you need to implement start here
     */
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
    public LinkedList<T> delete(T e) {
        return e.equals(this.head) ? this.tail : new ListItem<>(e, this.tail.delete(e));
    }
}
