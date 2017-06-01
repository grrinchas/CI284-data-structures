public final class NilS<T> extends Set<T> {

    public static final NilS INSTANCE = new NilS();

    private NilS() {

    }

    @Override
    public T head() {
        throw new RuntimeException("Called head on empty set");
    }

    @Override
    public Set<T> tail() {
        throw new RuntimeException("Called tail on empty set");
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
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Set<T> delete(T e) {
        return INSTANCE;
    }

    @Override
    public Set<T> insert(T e) {
        return new SetItem<>(e, this);
    }

    @Override
    public Set<T> union(Set<T> s) {
        return s;
    }

    @Override
    public Set<T> intersect(Set<T> s) {
        return INSTANCE;
    }

    @Override
    public Set<T> difference(Set<T> s) {
        return INSTANCE;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == INSTANCE;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
