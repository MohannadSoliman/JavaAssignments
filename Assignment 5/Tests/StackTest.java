package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {
	Stack obj = new Stack();

	@Test
	public void testPop() {
		obj.push(3);
		assertEquals(3, obj.pop());
	}

	@Test
	public void testPeek() {
		obj.push('a');
		assertEquals('a', obj.peek());
		
	}
	

	@Test
	public void testPush() {
		obj.push('x');
		obj.push('y');
		assertEquals('y', obj.peek());
		obj.pop();
		assertEquals('x', obj.pop());
	}

	@Test
	public void testIsEmpty() {
		obj.push(2);
		assertFalse(obj.isEmpty());
		obj.pop();
		assertTrue(obj.isEmpty());
	}

	@Test
	public void testSize() {
		obj.push(3);
		obj.push(6);
		obj.push(7);
		assertEquals(3, obj.size());
		obj.pop();
		assertEquals(2, obj.size());
	}
	@Test
	(expected = RuntimeException.class)
	public void testExceptionPop() {
		obj.push(2);
		obj.pop();
		obj.pop();
	}
	@Test
	(expected = RuntimeException.class)
	public void testExceptionPeek() {
		obj.push(2);
		obj.pop();
		obj.peek();
	}
}
