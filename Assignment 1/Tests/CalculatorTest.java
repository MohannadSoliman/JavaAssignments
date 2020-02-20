package idk;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {


	@Test
	public void testAdd() {
		assertEquals(7, new Calculator().add(2, 5));
		assertEquals(-16, new Calculator().add(-20, 4));
		assertEquals(100, new Calculator().add(2, 98));
		assertEquals(90, new Calculator().add(22, 68));
		assertEquals(50, new Calculator().add(45, 5));
	}


	@Test
	public void testDivide() {
		assertEquals(4, new Calculator().divide(4, 1), 0);
		assertEquals(2, new Calculator().divide(4, 2), 0);
		assertEquals(3.33333, new Calculator().divide(10, 3), 0.00001);
		assertEquals(0.6667, new Calculator().divide(2,3), 0.0001);
		assertEquals(0.0, new Calculator().divide(0, 5), 0);
		
	}
	@Test
	(expected = RuntimeException.class)
	public void testZero() {
		new Calculator().divide(2,0);
		new Calculator().divide(0,0);
	}

	

}
