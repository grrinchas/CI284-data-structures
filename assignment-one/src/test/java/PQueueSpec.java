import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("all")
public class PQueueSpec {

    private PQueue<String> queue;
    private PQueueItem<String> item;

    @Before
    public void setUp() throws Exception {
        this.item = new PQueueItem<>("item", 3);
        this.queue = new PQueue<>();
    }

    @Test
    public void whenNew_thenDefaultOrderDesc() throws Exception {
        assertThat(this.queue.getOrder()).isEqualTo(PQueue.ORDER.DESC);
    }

    @Test
    public void givenOrder_whenNew_thenSetOrder() throws Exception {
        PQueue<Integer> asc = new PQueue<>(PQueue.ORDER.ASC);
        assertThat(asc.getOrder()).isEqualTo(PQueue.ORDER.ASC);
    }

    @Test
    public void givenItem_whenInsert_thenContain() throws Exception {
        this.queue.insert(this.item);
        assertThat(this.queue.peekItem()).isEqualTo(this.item);
    }

    @Test
    public void givenItem_whenInsert_thenIncreaseLength() throws Exception {
        this.queue.insert(this.item);
        assertThat(this.queue.length()).isEqualTo(1);
    }

    @Test
    public void givenItem_whenInsert_ifOrderDescAndPriorityHigher_thenInsertAtBeginning() throws Exception {
        PQueueItem<String> first = new PQueueItem<>("first", 4);
        PQueueItem<String> second = new PQueueItem<>("second", 3);
        this.queue.insert(second);
        this.queue.insert(first);
        assertThat(this.queue.popItem()).isEqualTo(first);
        assertThat(this.queue.popItem()).isEqualTo(second);
    }

    @Test
    public void givenItem_whenInsert_ifOrderDescAndPriorityLower_thenInsertAtEnd() throws Exception {
        PQueueItem<String> first = new PQueueItem<>("first", 4);
        PQueueItem<String> second = new PQueueItem<>("second", 3);
        this.queue.insert(first);
        this.queue.insert(second);
        assertThat(this.queue.popItem()).isEqualTo(first);
        assertThat(this.queue.popItem()).isEqualTo(second);
    }

    @Test
    public void givenItem_whenInsert_ifOrderDesc_thenInsertByPriority() throws Exception {
        List<Integer> list = this.initList(this.queue);
        Collections.reverse(list);
        assertThat(list).isSorted();
    }

    private List<Integer> initList(PQueue<String> queue) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            queue.insert(new PQueueItem<>("item", random.nextInt(20)));
        }
        List<Integer> list = new ArrayList<>();
        while (queue.length() > 0) {
            list.add(queue.popItem().getPriority());
        }
        return list;
    }

    @Test
    public void givenItem_whenInsert_ifOrderAsc_thenInsertByPriority() throws Exception {
        assertThat(this.initList(new PQueue<>(PQueue.ORDER.ASC))).isSorted();
    }

    @Test
    public void whenPeekItem_thenReturnFirstItem() throws Exception {
        this.queue.insert(this.item);
        assertThat(this.queue.peekItem()).isEqualTo(this.item);
    }

    @Test
    public void whenPeek_thenReturnFirstItemData() throws Exception {
        this.queue.insert(this.item);
        assertThat(this.queue.peek()).isEqualTo(this.item.getData());
    }

    @Test
    public void whenPopItem_thenReturnItem() throws Exception {
        this.queue.insert(this.item);
        assertThat(this.queue.popItem()).isEqualTo(this.item);
    }

    @Test
    public void whenPopItem_thenNotContains() throws Exception {
        this.queue.insert(this.item);
        this.queue.popItem();
        assertThat(this.queue.peekItem()).isNotEqualTo(this.item);
    }

    @Test
    public void whenPopItem_thenDecreaseLength() throws Exception {
        this.queue.insert(this.item);
        this.queue.popItem();
        assertThat(this.queue.length()).isEqualTo(0);
    }

    @Test
    public void whenPop_thenReturnFirstItemData() throws Exception {
        this.queue.insert(this.item);
        assertThat(this.queue.pop()).isEqualTo(this.item.getData());
    }

    @Test
    public void whenPop_thenNotContains() throws Exception {
        this.queue.insert(this.item);
        this.queue.pop();
        assertThat(this.queue.length()).isEqualTo(0);
    }

    @Test
    public void whenPop_thenDecreaseLength() throws Exception {
        this.queue.insert(this.item);
        this.queue.pop();
        assertThat(this.queue.length()).isEqualTo(0);
    }
}