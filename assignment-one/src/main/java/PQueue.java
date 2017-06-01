/**
 * Complexity of insert method is O(n) where n is number of items in the queue.
 * The reason for this is that PQueue is implemented using nodes and it has order (desc or asc).
 * Thus to find the right place for new item we have to compare it's priority with other node's priority.
 * So if the queue contains n elements then in the worst case scenario we have to do n comparisons.
 * <p>
 * In the best case scenario we need to do only one comparison. For example if queue has one element.
 * In the case when queue is empty then we don't even need to do comparison we just need to insert the item.
 * In both of these cases complexity would be O(1).
 * <p>
 * If in the worst case we need to do n comparisons and in the best case just 1 or 0 then on average we would
 * have (n + 1)/2 or n/2 comparisons. But in complexity terms this is the same as worst case O(n).
 * <p>
 * The difference between space and time complexity. Is that time complexity describes how much time an
 * algorithm will consume based on given input, while space complexity describes how much memory it will
 * consume. My implementation of insert method uses recursion which takes more memory in JVM due to
 * the fact that at each recursive method call is put on JVM stack consuming memory, thus making space complexity
 * larger than iterative approach.
 * <p>
 * If PQueue would be implemented using an array then time complexity of insert method would be the same O(n). For example
 * we could use binary search with O(logN) complexity to find a place for a new item. But to do insertion we will need
 * to shift the items to the right. In the worst case we will have to shift n items, if we insert in front
 * of the queue, thus making item complexity O(n).
 * <p>
 * The most efficient way to implement PQueue would be using heaps, where insert method would have O(longN) complexity.
 *
 * @param <T>
 * @author Dainius Grinciukas
 */
@SuppressWarnings("all")
public class PQueue<T> {

    private PQueueItem<T> head;
    private int size = 0;

    public enum ORDER {
        ASC, DESC
    }

    public static ORDER DEFAULT_ORDER = ORDER.DESC;
    private ORDER order;

    /**
     * The default constructor for a PQueue, with the default order for priorities
     */
    public PQueue() {
        this.order = DEFAULT_ORDER;
    }

    /**
     * The constructor to make a new PQueue with a given order
     *
     * @param order
     */
    public PQueue(ORDER order) {
        this.order = order;
    }

    /**
     * Remove and return data from the item at the front of the queue
     *
     * @return
     */
    public T pop() {
        return this.popItem().getData();
    }

    /**
     * Return the data from the item at the front of the queue, without removing the item itself
     *
     * @return
     */
    public T peek() {
        return this.peekItem().getData();
    }

    /**
     * Remove and return the item at the front of the queue
     *
     * @return
     */
    public PQueueItem<T> popItem() {
        PQueueItem<T> returnable = this.head;
        this.head = this.head.getNext();
        this.size--;
        return returnable;
    }

    /**
     * Return the item at the front of the queue without removing it
     *
     * @return
     */
    public PQueueItem<T> peekItem() {
        return this.head;
    }

    /**
     * Insert a new item into the queue, which should be put in the right place according to its priority.
     * That is, is order == ASC, then the new item should appear in the queue before all items with a
     * HIGHER priority. If order == DESC, then the new item should appear in the queue before all items
     * with a LOWER priority.
     *
     * @param data
     * @param priority
     */
    public void insert(T data, int priority) {
        this.insert(new PQueueItem<>(data, priority));
    }

    /**
     * Return the length of the queue
     *
     * @return
     */
    public int length() {
        return this.size;
    }

    /**
     * Return order type of the queue.
     *
     * @return
     */
    public ORDER getOrder() {
        return this.order;
    }

    public String toString() {
        int i = length();
        PQueueItem<T> current = head;
        StringBuffer sb = new StringBuffer();
        while (i > 0) {
            sb.append(current.toString());
            if (i > 1)
                sb.append(": ");
            current = current.getNext();
            i--;
        }
        return sb.toString();
    }


    /*
     * Inserts item into queue. First by checking the head,
     * then front of the head, and lastly the tail.
     */
    public void insert(PQueueItem<T> item) {
        if (this.head == null) {
            this.head = item;
        } else if (this.assertFrontInsertion(this.head, item)) {
            item.setNext(this.head);
            this.head = item;
        } else {
            this.insertInTail(this.head, item);
        }
        this.size++;
    }

    /*
     * Tries to insert item into queue's tail recursively. First by
     * checking tail itself, then front of the tail.
     */
    private void insertInTail(PQueueItem<T> node, PQueueItem<T> item) {
        if (node.getNext() == null) {
            node.setNext(item);
        }
        else if (this.assertFrontInsertion(node.getNext(), item)) {
            item.setNext(node.getNext());
            node.setNext(item);
        }
        else {
            this.insertInTail(node.getNext(), item);
        }
    }

    /*
     * Checks if order is Desc.
     */
    private boolean isDesc() {
        return this.order == ORDER.DESC;
    }

    /*
     * Checks if item's priority is higher than node's.
     */
    private boolean assertItemHigherPriority(PQueueItem<T> node, PQueueItem<T> item) {
        return item.getPriority() > node.getPriority();
    }

    /*
     * Checks if item can be inserted in front of the node, depending on queue's order
     * and both item's and node's priorities.
     */
    private boolean assertFrontInsertion(PQueueItem<T> node, PQueueItem<T> item) {
        //noinspection OverlyComplexBooleanExpression
        return this.isDesc() && this.assertItemHigherPriority(node,
                item) || !(this.isDesc() || this.assertItemHigherPriority(node, item));
    }

}

