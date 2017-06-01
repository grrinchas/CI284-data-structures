import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testHeadAndTail() {
		LinkedList<Integer> list = new Nil<Integer>().cons(1);
		assertEquals(list.head(), new Integer(1));
		assertEquals(list.tail(), new Nil<Integer>());
	}

	@Test
	public void testConsAndLength() {
		LinkedList<Integer> list = new Nil<Integer>();
		assertEquals(list.length(), 0);
		list = list.cons(1);
		assertEquals(list.length(), 1);
	}

	@Test
	public void testMember() {
		LinkedList<Integer> list = new Nil<Integer>();
		for (int i = 0; i < 10; i++) {
			list = list.cons(i);
		}
		assertTrue(list.member(1));
		assertFalse(list.member(10));
	}

	@Test
	public void testIsEmpty() {
		LinkedList<Integer> list = new Nil<Integer>();
		assertTrue(list.isEmpty());
		list = list.cons(99);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testIndexOf() {
		LinkedList<Integer> list = new Nil<Integer>();
		for (int i = 9; i >= 0; i--) {
			list = list.cons(i);
		}
		System.out.println(list);
		assertEquals(list.indexOf(5), 5);
		assertEquals(list.indexOf(99), -1);
	}

	@Test
	public void testDelete() {
		LinkedList<Integer> list = new Nil<Integer>();
		for (int i = 0; i < 10; i++) {
			list = list.cons(i);
		}

		list = list.delete(5);
		assertFalse(list.member(5));
		assertEquals(list.length(), 9);

		assertEquals(list.delete(99), list);

	}

}
