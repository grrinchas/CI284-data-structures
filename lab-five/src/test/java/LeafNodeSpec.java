import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class LeafNodeSpec {

    private BST<Integer> leafNode;
    private int label;

    @Before
    public void setUp() throws Exception {
        this.label = 26;
        this.leafNode = new LeafNode<>(this.label);
    }

    @Test
    public void whenCountNodes_thenReturnOne() throws Exception {
        assertThat(this.leafNode.countNodes()).isEqualTo(1);
    }

    @Test
    public void whenHeight_thenReturnZero() throws Exception {
        assertThat(this.leafNode.height()).isEqualTo(0);
    }

    @Test
    public void whenToString_thenReturnLabel() throws Exception {
        assertThat(this.leafNode.toString()).isEqualTo(String.valueOf(this.label));
    }

    @Test
    public void whenGetLabel_thenReturnLabel() throws Exception {
        assertThat(this.leafNode.getLabel()).isEqualTo(this.label);
    }

    @Test
    public void givenLabel_whenInsert_thenReturnNewBranch() throws Exception {
        int newLabel = 13;
        assertThat(this.leafNode.insert(newLabel).getRight()).isNull();
        assertThat(this.leafNode.insert(newLabel).getLabel()).isEqualTo(this.label);
        assertThat(this.leafNode.insert(newLabel).getLeft().getLabel()).isEqualTo(newLabel);

        newLabel = 30;
        assertThat(this.leafNode.insert(newLabel).getRight()).isNull();
        assertThat(this.leafNode.insert(newLabel).getLabel()).isEqualTo(newLabel);
        assertThat(this.leafNode.insert(newLabel).getLeft().getLabel()).isEqualTo(this.label);
    }

    @Test
    public void givenLabel_whenRemove_ifLabelSameAsLeafLabel_thenReturnNull() {
        int label = 26;
        assertThat(this.label).isEqualTo(label);
        assertThat(this.leafNode.remove(label)).isNull();
    }
    @Test
    public void givenLabel_whenRemove_ifLabelNotSameAsLeafLabel_thenReturnLeaf() {
        int label = 20;
        assertThat(this.label).isNotEqualTo(label);
        assertThat(this.leafNode.remove(label)).isEqualTo(this.leafNode);
    }

    @Test
    public void whenGetRight_thenReturnNull() throws Exception {
        assertThat(this.leafNode.getRight()).isNull();
    }
    @Test
    public void whenGetLeft_thenReturnNull() throws Exception {
        assertThat(this.leafNode.getLeft()).isNull();
    }

    @Test
    public void givenABS_whenMerge_ifABSIsLeaf_thenReturnMergedABS() throws Exception {
        BST<Integer> newLeaf = new LeafNode<>(10);
        assertThat(this.leafNode.merge(newLeaf).getRight()).isNull();
        assertThat(this.leafNode.merge(newLeaf).getLeft().getLabel()).isEqualTo(newLeaf.getLabel());
        assertThat(this.leafNode.merge(newLeaf).getLabel()).isEqualTo(this.label);

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
        BranchNode<Integer> root = new BranchNode<>(50, b21, b22);

        assertThat(this.leafNode.merge(root).toString()).isEqualTo("9 12 14 17 19 23 26 50 54 67 72 76 ");
    }

}