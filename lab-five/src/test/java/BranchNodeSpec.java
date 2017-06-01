import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BranchNodeSpec {

    private BST<Integer> root;

    @Before
    public void setUp() throws Exception {
        LeafNode<Integer> l1 = new LeafNode<>(9);
        LeafNode<Integer> l2 = new LeafNode<>(14);
        LeafNode<Integer> l3 = new LeafNode<>(19);
        LeafNode<Integer> l4 = new LeafNode<>(67);
        LeafNode<Integer> l5 = new LeafNode<>(76);

        BranchNode<Integer> b11 = new BranchNode<>(12, l1, l2);
        BranchNode<Integer> b12 = new BranchNode<>(23, l3, null);
        BranchNode<Integer> b13 = new BranchNode<>(54, null, l4);
        BranchNode<Integer> b21 = new BranchNode<>(17, b11, b12);
        BranchNode<Integer> b22 = new BranchNode<>(72, b13, l5);
        this.root = new BranchNode<>(50, b21, b22);
    }

    @Test
    public void whenCountNodes_thenReturnOnePlusSumOfBranches() throws Exception {
        BranchNode<Integer> right = new BranchNode<>(15, new LeafNode<>(12), new LeafNode<>(20));
        BranchNode<Integer> left = new BranchNode<>(5, new LeafNode<>(3), new LeafNode<>(7));
        BranchNode<Integer> bst = new BranchNode<>(10, left, right);
        assertThat(bst.countNodes()).isEqualTo(7);
    }
    @Test
    public void whenHeight_thenReturnHeightOfOnePlusMaxBranches() throws Exception {
        BranchNode<Integer> bst1 = new BranchNode<>(15, new LeafNode<>(12), new LeafNode<>(20));
        BranchNode<Integer> bst2 = new BranchNode<>(15, bst1, null);
        BranchNode<Integer> bst = new BranchNode<>(15, bst2, bst1);
        assertThat(bst.height()).isEqualTo(1 + Math.max(bst2.height(), bst1.height()));
    }

    @Test
    public void givenLabel_whenInsert_thenInsertInCorrectPosition() throws Exception {
        assertThat(this.root.insert(49).toString()).isEqualTo("9 12 14 17 19 23 49 50 54 67 72 76 ");
        assertThat(this.root.insert(50).toString()).isEqualTo("9 12 14 17 19 23 50 50 54 67 72 76 ");
        assertThat(this.root.insert(52).toString()).isEqualTo("9 12 14 17 19 23 50 52 54 67 72 76 ");
        assertThat(this.root.insert(8).toString()).isEqualTo("8 9 12 14 17 19 23 50 54 67 72 76 ");
        assertThat(this.root.insert(88).toString()).isEqualTo("9 12 14 17 19 23 50 54 67 72 76 88 ");
    }

    @Test
    public void givenLabel_whenRemove_thenRemoveIt() throws Exception {
        assertThat(this.root.remove(20).toString()).isEqualTo("9 12 14 17 19 23 50 54 67 72 76 ");
        assertThat(this.root.remove(76).toString()).isEqualTo("9 12 14 17 19 23 50 54 67 72 ");
        assertThat(this.root.remove(9).toString()).isEqualTo("12 14 17 19 23 50 54 67 72 76 ");
        assertThat(this.root.remove(19).toString()).isEqualTo("9 12 14 17 23 50 54 67 72 76 ");
        assertThat(this.root.remove(12).toString()).isEqualTo("9 14 17 19 23 50 54 67 72 76 ");
        assertThat(this.root.remove(23).toString()).isEqualTo("9 12 14 17 19 50 54 67 72 76 ");
        assertThat(this.root.remove(17).toString()).isEqualTo("9 12 14 19 23 50 54 67 72 76 ");
        assertThat(this.root.remove(50).toString()).isEqualTo("9 12 14 17 19 23 54 67 72 76 ");
    }

    @Test
    public void whenToString_thenReturnOrdered() throws Exception {
        assertThat(this.root.toString()).isEqualTo("9 12 14 17 19 23 50 54 67 72 76 ");
    }
}