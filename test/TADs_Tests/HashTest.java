package TADs_Tests;

import TADs.HashTable.HashTableImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTest {
    @Test
    public void testPut() {
        HashTableImpl<Integer,String> hash=new HashTableImpl<>(7);
        hash.put(0,"A");
        hash.put(1,"B");
        hash.put(2,"C");
        hash.put(3,"D");
        assertEquals(hash.get(0),"A");
        assertEquals(hash.get(1),"B");
        assertEquals(hash.get(2),"C");
        assertEquals(hash.get(3),"D");
    }
    @Test
    public void testDelete() {
        HashTableImpl<Integer, String> hash = new HashTableImpl<>(10);
        hash.put(0, "A");
        hash.put(1, "B");
        hash.put(2, "C");
        hash.put(3, "D");
        hash.put(4, "E");
        hash.put(5, "F");
        hash.remove(3);
        assertEquals(hash.get(0), "A");
        assertEquals(hash.get(1), "B");
        assertEquals(hash.get(2), "C");
        assertFalse(hash.contains(3));
        assertNull(hash.get(3));
        assertEquals(hash.get(4), "E");
        assertEquals(hash.get(5), "F");
    }
    @Test public void testContainsKey() {
        HashTableImpl<Integer, String> hash = new HashTableImpl<>(10);
        hash.put(0, "A");
        hash.put(1, "B");
        hash.put(2, "C");
        hash.put(3, "D");
        hash.put(4, "E");
        hash.put(5, "F");
        assertTrue(hash.contains(0));
        assertTrue(hash.contains(1));
        assertTrue(hash.contains(2));
        assertTrue(hash.contains(3));
        assertTrue(hash.contains(4));
        assertTrue(hash.contains(5));
        assertFalse(hash.contains(6));
        hash.remove(2);
        assertFalse(hash.contains(2));
    }
    @Test
    public void testRefactor() {
        HashTableImpl<Integer, String> hash = new HashTableImpl<>(10);
        hash.put(0, "A");
        hash.put(1, "B");
        hash.put(2, "C");
        hash.put(3, "D");
        hash.put(4, "E");
        hash.put(5, "F");
        hash.put(6, "G");
        hash.put(7, "H");
        hash.put(8, "I");
        hash.put(9, "J");
        hash.put(10, "K");
        hash.put(11, "L");
        hash.put(12, "M");
        assertEquals(hash.get(0), "A");
        assertEquals(hash.get(1), "B");
        assertEquals(hash.get(2), "C");
        assertEquals(hash.get(3), "D");
        assertEquals(hash.get(4), "E");
        assertEquals(hash.get(5), "F");
        assertEquals(hash.get(6), "G");
        assertEquals(hash.get(7), "H");
        assertEquals(hash.get(8), "I");
        assertEquals(hash.get(9), "J");
        assertEquals(hash.get(10), "K");
        assertEquals(hash.get(11), "L");
        assertEquals(hash.get(12), "M");

    }
}
