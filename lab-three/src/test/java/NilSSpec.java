import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NilSSpec {

    private NilS<Integer> nils;

    @Before
    public void setUp() throws Exception {
        this.nils = NilS.INSTANCE;
    }

    @Test
    public void whenHead_thenException() throws Exception {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> this.nils.head());
    }

    @Test
    public void whenTail_thenException() throws Exception {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> this.nils.tail());
    }

    @Test
    public void whenLength_thenReturnZero() throws Exception {
        assertThat(this.nils.length()).isEqualTo(0);
    }

    @Test
    public void whenIsEmpty_thenReturnTrue() throws Exception {
        assertThat(this.nils.isEmpty()).isTrue();
    }

    @Test
    public void givenMember_whenMember_thenReturnFalse() throws Exception {
        assertThat(this.nils.member(1)).isFalse();
    }

    @Test
    public void givenMember_whenDelete_thenReturnNilS() throws Exception {
        assertThat(this.nils.delete(4)).isEqualTo(this.nils);
    }

    @Test
    public void givenObject_whenEquals_ifSameClass_thenReturnTrue() throws Exception {
        assertThat(this.nils.equals(NilS.INSTANCE)).isTrue();
    }

    @Test
    public void whenHashCode_thenReturnZero() throws Exception {
        assertThat(this.nils.hashCode()).isEqualTo(0);
        assertThat(NilS.INSTANCE.hashCode()).isEqualTo(0);
    }

    @Test
    public void givenSet_whenUnion_thenReturnGivenSet() throws Exception {
        Set<Integer> set = new SetItem<>(4, NilS.INSTANCE);
        assertThat(this.nils.union(set)).isEqualTo(set);
    }

    @Test
    public void givenSet_whenIntersect_thenReturnNilS() throws Exception {
        assertThat(this.nils.intersect(new SetItem<>(5, NilS.INSTANCE))).isEqualTo(this.nils);
    }

    @Test
    public void givenSet_whenDifference_thenReturnNilS() throws Exception {
        assertThat(this.nils.difference(new SetItem<>(5, NilS.INSTANCE))).isEqualTo(this.nils);
    }
    @Test
    public void givenMember_whenInsert_thenReturnSetItem() throws Exception {
        assertThat(this.nils.insert(4)).isExactlyInstanceOf(SetItem.class);
    }
    @Test
    public void givenMember_whenInsert_thenSetItemHeadIsMember() throws Exception {
        assertThat(this.nils.insert(4).head()).isEqualTo(4);
    }
    @Test
    public void givenMember_whenInsert_thenSetItemTailIsNilS() throws Exception {
        assertThat(this.nils.insert(4).tail()).isEqualTo(this.nils);
    }
}