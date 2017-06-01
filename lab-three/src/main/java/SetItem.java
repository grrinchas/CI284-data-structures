public final class SetItem<T> extends Set<T> {
    private final T head;
    private final Set<T> tail;

    public SetItem(T head, Set<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return this.head;
    }

    @Override
    public Set<T> tail() {
        return this.tail;
    }

    @Override
    public int length() {
        return 1 + this.tail.length();
    }

    @Override
    public boolean member(T e) {
        return this.head().equals(e) || this.tail.member(e);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set<T> delete(T e) {
      //  return this.head.equals(e) ? this.tail : new SetItem<>(e, this.tail.delete(e));
        return this.head.equals(e)? this.tail : new SetItem<>(this.head, this.tail.delete(e));
    }

    @Override
    public Set<T> insert(T e) {
        return this.member(e) ? this : new SetItem<>(e, this);
    }

    @Override
    public Set<T> union(Set<T> s) {
        return s.isEmpty() ? this : this.insert(s.head()).union(s.tail());
    }

    @Override
    public Set<T> intersect(Set<T> s) {
        return s.isEmpty() ? s : this.member(s.head()) ? new SetItem<>(s.head(), this.intersect(s.tail())) : this.intersect(
                s.tail());
    }

    @Override
    public Set<T> difference(Set<T> s) {
        return s.isEmpty()? this: this.delete(s.head()).difference(s.tail());
    }

    @Override
    public int hashCode() {
        int result = this.head.hashCode();
        result = 31 * result + this.tail.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {

        boolean result = false;
        if (o == this) {
            result = true;
        } else if (o.getClass() == this.getClass()) {
            SetItem<T> that = (SetItem<T>) o;
            result = this.head.equals(that.head()) && this.tail.equals(that.tail());
        }
        return result;
    }

    @Override
    public String toString() {
        return this.head + " " + this.tail();
    }

}
