import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SetItemSpec {

    private Set<Integer> set;
    private int head;
    private Set<Integer> tail;
    private int length;

    @Before
    public void setUp() throws Exception {
        this.head = 10;
        this.length = 2;
        this.tail = NilS.INSTANCE.insert(50);
        this.set = new SetItem<>(this.head, this.tail);
    }

    @Test
    public void whenHead_thenReturnHead() throws Exception {
        assertThat(this.set.head()).isEqualTo(this.head);
    }

    @Test
    public void whenTail_thenReturnTail() throws Exception {
        assertThat(this.set.tail()).isEqualTo(this.tail);
    }

    @Test
    public void whenLength_thenReturnMemberCount() throws Exception {
        assertThat(this.set.length()).isEqualTo(this.length);
    }

    @Test
    public void whenMember_ifInSet_thenTrue() throws Exception {
        assertThat(this.set.member(this.head)).isTrue();
    }

    @Test
    public void whenMember_ifNotInSet_thenFalse() throws Exception {
        assertThat(this.set.member(12)).isFalse();
    }

    @Test
    public void whenIsEmpty_thenFalse() throws Exception {
        assertThat(this.set.isEmpty()).isFalse();
    }

    @Test
    public void givenMember_whenDelete_thenNewSetWithoutMember() throws Exception {
        assertThat(this.set.delete(this.head).member(this.head)).isFalse();
    }
    @Test
    public void givenMember_whenDelete_ifNotMember_thenReturnSameSet() throws Exception {
        assertThat(this.set.delete(4)).isEqualTo(this.set);
    }

    @Test
    public void givenMember_whenInsert_thenNewSetWithMember() {
        assertThat(this.set.insert(2).member(2)).isTrue();
        assertThat(this.set.insert(2).length()).isEqualTo(this.length + 1);
    }

    @Test
    public void givenMember_whenInsert_ifDuplicate_ThenOldSet() {
        assertThat(this.set.insert(this.head).length()).isEqualTo(this.length);
    }

    @Test
    public void givenSet_whenUnion_thenNewSetWithMembersOfBothSets() throws Exception {
        Set<Integer> newSet = NilS.INSTANCE.insert(12).insert(13);
        assertThat(this.set.union(newSet).length()).isEqualTo(this.length + 2);
        assertThat(this.set.union(newSet).member(this.head)).isTrue();
        assertThat(this.set.union(newSet).member(50)).isTrue();
        assertThat(this.set.union(newSet).member(13)).isTrue();
        assertThat(this.set.union(newSet).member(12)).isTrue();
    }
    @Test
    public void givenSet_whenUnion_thenNotDuplicatesInNewSet() throws Exception {
        Set<Integer> newSet = NilS.INSTANCE.insert(this.head).insert(13);
        assertThat(this.set.union(newSet).length()).isEqualTo(this.length + 1);
        assertThat(this.set.union(newSet).member(this.head)).isTrue();
        assertThat(this.set.union(newSet).member(50)).isTrue();
        assertThat(this.set.union(newSet).member(13)).isTrue();
    }

    @Test
    public void givenSet_whenIntersect_thenNewSetWithSameElementsInBothSets() throws Exception {
        Set<Integer> set1 = NilS.INSTANCE.insert(10).insert(20).insert(30).insert(40);
        Set<Integer> set2 = NilS.INSTANCE.insert(1).insert(20).insert(30).insert(4);
        assertThat(set1.intersect(set2).length()).isEqualTo(2);
        assertThat(set1.intersect(set2).member(20)).isTrue();
        assertThat(set1.intersect(set2).member(30)).isTrue();
    }

    @Test
    public void givenSet_whenDifference_thenNewSetWithRemovedIntersection() throws Exception {
        Set<Integer> set1 = NilS.INSTANCE.insert(10).insert(20).insert(30).insert(40).insert(50);
        Set<Integer> set2 = NilS.INSTANCE.insert(1).insert(20).insert(30).insert(4);
        System.out.println(set1.difference(set2));
        assertThat(set1.difference(set2).length()).isEqualTo(3);
        assertThat(set1.difference(set2).member(10)).isTrue();
        assertThat(set1.difference(set2).member(40)).isTrue();
        assertThat(set1.difference(set2).member(50)).isTrue();

    }
}