public class BranchNode<T extends Comparable<T>> extends BST<T> {

    BST<T> left;
    BST<T> right;

    public BranchNode(T label, BST<T> left, BST<T> right) {
        if (left == null && right == null)
            throw new RuntimeException("Branch node has to have at least one branch");
        this.label = label;
        this.left = left;
        this.right = right;
    }

    @Override
    public BST<T> getLeft() {
        return left;
    }

    @Override
    public BST<T> getRight() {
        return right;
    }

    // Exercises
    @Override
    public int countNodes() {
        int result = 1;
        if (this.left != null)
            result += this.left.countNodes();
        if (this.right != null)
            result += this.right.countNodes();
        return result;
    }

    @Override
    public int height() {
        int leftHeight = (this.left == null) ? 0 : this.left.height();
        int rightHeight = (this.right == null) ? 0 : this.right.height();
        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    public BST<T> insert(T e) {
        if (e.compareTo(this.label) < 0) {
            if (this.left != null)
                return new BranchNode<>(this.label, this.left.insert(e), this.right);
            else
                return new BranchNode<>(this.label, new LeafNode<>(e), this.right);
        } else {
            if (this.right != null)
                return new BranchNode<>(this.label, this.left, this.right.insert(e));
            else
                return new BranchNode<>(this.label, this.left, new LeafNode<>(e));
        }
    }

    @Override
    public String toString() {
        this.traverse(this);
        return this.result.toString();
    }

    private StringBuilder result = new StringBuilder(300);

    private void traverse(BST<T> node) {
        if (node.getLeft() != null) {
            traverse(node.getLeft());
        }
        this.result.append(node.label).append(" ");
        if (node.getRight() != null) {
            traverse(node.getRight());
        }
    }

    @Override
    public BST<T> remove(T e) {
        if (e != this.label) {
            BST<T> left = this.left == null ? null : this.left.remove(e);
            BST<T> right = this.right == null ? null : this.right.remove(e);

            if (left == null && right == null)
                return new LeafNode<>(this.label);
            else
                return new BranchNode<>(this.label, left, right);
        } else {
            if (this.left == null)
                return this.right;
            else if (this.right == null)
                return this.left;
            else
                return this.left.merge(this.right);
        }
    }

    @Override
    protected BST<T> merge(BST<T> that) {
        if (that != null)
            return this.insert(that.label).merge(that.getLeft()).merge(that.getRight());
        else
            return this;
    }

}
