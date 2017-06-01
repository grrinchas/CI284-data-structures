import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class LinkedListCTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsert() {
		LinkedListC<Integer> list = new NilC<Integer>();
		assertEquals(list.length(), 0);
		list = list.insert(1);
		list = list.insert(99);
		assertEquals(list.head(), new Integer(1));
		
		//create some random numbers and make sure they are inserted in the right order.
		Random gen = new Random( 19580427 );
		
		for(int i=0;i<10;i++) {
			list = list.insert(gen.nextInt());
		}
		System.out.println(list);
		int prev = list.head();
		while(!list.isEmpty()) {
			assert(prev <= list.head());
			list = list.tail();
		}
	}

}
