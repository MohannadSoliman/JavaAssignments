package eg.edu.alexu.csd.datastructure.linkedList;

import static org.junit.Assert.*;

import org.junit.Test;

public class DLListTest {
	DLList link = new DLList();

	@Test
	public void testAdd1() {
		link.add(5);
		link.add(4);
		link.add(2, 'x');
		link.add('a');
		assertEquals(5, link.head.data);
		assertEquals(4, link.head.next.data);
		assertEquals('x', link.head.next.next.data);
		assertEquals('a', link.head.next.next.next.data);
	}
	
	@Test
	public void testGet1() {
		link.clear();
		link.add(4);
		link.add('a');
		link.add(1, 'x');
		link.add(5);
		
		assertEquals(4, link.get(0));
		assertEquals('x', link.get(1));
		assertEquals('a', link.get(2));
		assertEquals(5, link.get(3));
	}
	@Test
	public void testGet2() {
		link.clear();
		link.add(1);
		link.add(2);
		link.add(1, 'x');
		assertEquals('x', link.get(1));
	}
	@Test
	public void testSet()
	{
		link.clear();
		link.add(0);
		link.add(1);
		link.add(2);
		link.add(3);
		link.set(2, 'x');
		assertEquals('x', link.get(2));
	}
	@Test
	public void testRemove()
	{
		link.clear();
		link.add(0);
		link.add(1);
		link.add(2);
		link.add(3);
		link.add(4);
		link.add(3, 'a');
		assertEquals(6, link.size());
		link.remove(1);
		assertEquals(5, link.size());
	}
	@Test
	public void testContains()
	{
		link.clear();
		link.add(0);
		link.add(1);
		link.add(2);
		link.add(3);
		link.add(4);
		assertTrue(link.contains(3));
		assertFalse(link.contains(7));
	}
	@Test
	public void testClear()
	{
		link.clear();
		assertEquals(null, link.head);
		assertEquals(null, link.tail);
	}
	@Test
	public void testSublist()
	{
		link.clear();
		link.add(0);
		link.add(1);
		link.add(2);
		link.add(3);
		link.add(4);
		link.add(5);
		link.add(6);
		DLList review = (DLList)link.sublist(2, 6);
		assertEquals(4, link.sublist(2, 6).get(2));
		assertEquals(2, review.get(0));
	}
	
	
	
	
	
	
	
	
	
}
