package eg.edu.alexu.csd.datastructure.linkedList;

import org.junit.Test;

import static org.junit.Assert.*;

public class PolyOperationsTest {
    PolyOperations test = new PolyOperations();
    @Test
    public void setAndPrintTest1(){
        int[][] testPoly = {{2,3}, {1,3}, {1,1}, {1,2}, {0,1}};
        test.setPolynomial('A', testPoly);
        assertEquals("3x^3+x^2+x", test.print('a'));
    }
    @Test
    public void setAndPrintTest2(){
        int[][] testPoly = {{1,3}, {1,0}, {1,1}, {1,2}, {0,1}};
        test.setPolynomial('A', testPoly);
        assertEquals("x^3+x^2+x+1", test.print('a'));
    }
    @Test
    public void setAndPrintTest3(){
        int[][] testPoly = {{-1,3}, {1,0}, {1,1}, {-1,-2}, {0,1}};
        test.setPolynomial('A', testPoly);
        assertEquals("-x^3+x+1-x^-2", test.print('a'));
    }
    @Test
    public void clearTest(){
        int[][] testPoly = {{1,3}, {1,0}, {1,1}, {1,2}, {0,1}};
        test.setPolynomial('A', testPoly);
        test.clearPolynomial('A');
        assertTrue(test.A.isEmpty());
    }
    @Test
    public void evaluationTest1(){
        int[][] testPoly = {{-1,3}, {1,0}, {1,1}, {-1,-2}, {0,1}};
        test.setPolynomial('A', testPoly);
        assertEquals("-x^3+x+1-x^-2", test.print('a'));
        assertEquals(0.0,test.evaluatePolynomial('a',1),0.0);
        assertEquals(0.0,test.evaluatePolynomial('a',-1),0.0);
        assertEquals(-5.25,test.evaluatePolynomial('a',2),0.0);
    }
    @Test
    public void addTest1(){
        int[][] testPoly1 = {{1,3}, {1,1}, {1,2}, {1,0}};
        int[][] testPoly2 = {{1,2}, {1,1}, {1,0}};
        test.setPolynomial('A', testPoly1);
        test.setPolynomial('b', testPoly2);
        test.add('a', 'B');
        assertEquals("x^3+2x^2+2x+2",test.print('r'));
    }
    @Test
    public void subtractTest1(){
        int[][] testPoly1 = {{1,3}, {1,1}, {1,2}, {1,0}};
        int[][] testPoly2 = {{1,2}, {1,1}, {1,0}};
        test.setPolynomial('A', testPoly1);
        test.setPolynomial('b', testPoly2);
        test.subtract('a', 'B');
        assertEquals("x^3",test.print('r'));
    }
    @Test
    public void multiplyTest1(){
        int[][] testPoly1 = {{1,3}, {1,1}, {1,2}, {1,0}};
        int[][] testPoly2 = {{1,2}, {1,1}, {1,0}};
        test.setPolynomial('A', testPoly1);
        test.setPolynomial('b', testPoly2);
        test.multiply('a', 'B');
        assertEquals("x^5+2x^4+3x^3+3x^2+2x+1",test.print('r'));
    }
    @Test
    public void sequenceOfOperations(){
        int[][] testPoly1 = {{1,3}, {1,1}, {1,2}, {1,0}};
        int[][] testPoly2 = {{1,2}, {1,1}, {1,0}};
        int[][] testPoly3 = {{-1,1}};
        test.setPolynomial('A', testPoly1);
        test.setPolynomial('b', testPoly2);
        test.setPolynomial('c', testPoly3);
        test.multiply('a', 'B');
        assertEquals("x^5+2x^4+3x^3+3x^2+2x+1",test.print('r'));
        test.subtract('r','b');
        assertEquals("x^5+2x^4+3x^3+2x^2+x",test.print('r'));
        test.add('r', 'r');
        assertEquals("2x^5+4x^4+6x^3+4x^2+2x",test.print('r'));
        test.multiply('r', 'c');
        assertEquals("-2x^6-4x^5-6x^4-4x^3-2x^2",test.print('r'));
        test.subtract('r', 'r');
        assertEquals("0",test.print('r'));
    }
    @Test
    public void minusExpTest(){
        int[][] testPoly1 = {{-1,-3}, {1,1}, {1,2}, {1,0}};
        int[][] testPoly2 = {{1,2}, {1,1}, {1,-0}};
        test.setPolynomial('A', testPoly1);
        test.setPolynomial('b', testPoly2);
        assertEquals("x^2+x+1-x^-3",test.print('a'));
        assertEquals("x^2+x+1",test.print('b'));
        test.subtract('b', 'a');
        assertEquals("x^-3",test.print('r'));
        test.add('r', 'b');
        assertEquals("x^2+x+1+x^-3",test.print('r'));
    }
}