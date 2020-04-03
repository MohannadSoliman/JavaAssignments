package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionEvaluatorTest {
	ExpressionEvaluator test = new ExpressionEvaluator();
	@Test
	public void testInfixToPostfix() {
		assertEquals("a b +", test.infixToPostfix("a 		+b  "));
		assertEquals("a", test.infixToPostfix("a"));
		assertEquals("0 2 - 3 +", test.infixToPostfix("-2 + 3"));
		assertEquals("a b c + * d *", test.infixToPostfix("a*(b+c)*d"));
		assertEquals("0 a - 3 +", test.infixToPostfix("-a + 3"));
		assertEquals("1 213 0 2 - * +", test.infixToPostfix("1		+213 * -	2"));
		assertEquals("a 0 2 0 3 - / - * b * 3 -", test.infixToPostfix(" a *  -(2   / 		-3) * b -3      "));
	}

	@Test
	public void testEvaluate() {
		assertEquals(4, test.evaluate("1 3 +"));
		assertEquals(8, test.evaluate("6 2 / 3 - 4 2 * +"));
		assertEquals(-9, test.evaluate("0 2 0 0 8 - 4 / - * - 0 5 - +"));
		assertEquals(-632, test.evaluate("0 2 - 0 5 - 0 0 9 - 0 7 - * - * 0 2 - * +"));
		assertEquals(23, test.evaluate("23"));
	}
	@Test
	(expected = RuntimeException.class)
	public void testInfixToPostfixException() {
		test.infixToPostfix("  2+    3 - 5 , 1");
		test.infixToPostfix("  34+    12 */ 2 + 6");
		test.infixToPostfix("  21+    13 - 55 , 17");
		test.infixToPostfix("2 2 * 5");
		test.infixToPostfix("");
		test.infixToPostfix(null);
	}
	@Test
	(expected = RuntimeException.class)
	public void testEvaluateException() {
		test.evaluate("2 3 + *");
		test.evaluate("2 3 ,");
		test.evaluate("3 0 /");
		test.evaluate("* 2 3");
		test.evaluate("a 2 +");
		test.evaluate("");
		test.evaluate(null);
	}
	

}
