public class LeafNode<T extends Comparable<T>> extends BST<T> {

    public LeafNode(T label) {
        this.label = label;
    }

    // Exercises

    @Override
    public int countNodes() {
        return 1;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public BST<T> getLeft() {
        return null;
    }

    @Override
    public BST<T> getRight() {
        return null;
    }

    @Override
    public BST<T> insert(T e) {
        if (e.compareTo(this.label) > 0)
            return new BranchNode<>(e, this, null);
        else
            return new BranchNode<>(this.label, new LeafNode<>(e), null);
    }

    @Override
    public String toString() {
        return String.valueOf(this.label);
    }

    @Override
    public BST<T> remove(T e) {
        return e == super.label ? null : this;
    }

    @Override
    protected BST<T> merge(BST<T> that) {
        return that.insert(this.label);
    }

}
