public abstract class Set<T> {

    public abstract T head();

    public abstract Set<T> tail();

    public abstract int length();

    public abstract boolean member(T e);

    public abstract boolean isEmpty();

    public abstract Set<T> delete(T e);

    public abstract Set<T> insert(T e);

    public abstract Set<T> union(Set<T> s);

    public abstract Set<T> intersect(Set<T> s);

    public abstract Set<T> difference(Set<T> s);

}
