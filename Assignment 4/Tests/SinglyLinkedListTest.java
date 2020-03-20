package eg.edu.alexu.csd.datastructure.linkedList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SinglyLinkedListTest {
    SinglyLinkedList test = new SinglyLinkedList();
    @Before
    public void setup(){
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
    }

    @Test
    public void initializationTest(){
        assertNull(test.get(-1));
        assertEquals(1, test.get(0));
        assertEquals(2, test.get(1));
        assertEquals(3, test.get(2));
        assertEquals(4, test.get(3));
        assertNull(test.get(4));
    }

    @Test
    public void addExtraNodes(){
        test.add(1,5);
        assertEquals(5,test.get(1));
        test.add(4,6);
        assertEquals(6,test.get(4));
        assertEquals(4,test.get(5));
        int size = test.size();
        test.remove(3);
        assertEquals(5, test.size());
        test.remove(0);
        assertEquals(size-2, test.size());
        assertEquals(5,test.get(0));
    }

    @Test
    public void replaceTest(){
        test.set(2,10);
        assertEquals(10,test.get(2));
    }

    @Test
    public void containsTest(){
        assertTrue(test.contains(3));
        assertFalse(test.contains(6));
    }

    @Test
    public void cleatTest(){
        test.clear();
        assertTrue(test.isEmpty());
    }
    @Test
    public void sublistTest(){
        SinglyLinkedList sublist = (SinglyLinkedList)test.sublist(0,2);
        assertEquals(1,sublist.get(0));
        assertEquals(2,sublist.get(1));
        assertEquals(3,sublist.get(2));
    }
}