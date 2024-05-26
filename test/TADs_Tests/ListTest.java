package TADs_Tests;

import Exceptions.ItemNotFoundException;
import uy.edu.um.prog2.adt.List.IllegalIndexException;
import uy.edu.um.prog2.adt.List.ListImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void testAdd() throws IllegalIndexException {
        ListImpl<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list.get(0), (Integer) 1);
        assertEquals(list.get(1), (Integer) 2);
        assertEquals(list.get(2), (Integer) 3);
    }

    @Test
    public void testAddIndex() throws IllegalIndexException {
        ListImpl<Integer> list = new ListImpl<>();
        list.add(1, 0);
        list.add(3, 1);
        list.add(4, 2);
        list.add(8, 3);
        assertEquals(list.get(0), (Integer) 1);
        assertEquals(list.get(1), (Integer) 3);
        assertEquals(list.get(2), (Integer) 4);
        assertEquals(list.get(3), (Integer) 8);
    }


    @Test
    public void AddIndexIllegalIndexException() {
        ListImpl<Integer> list = new ListImpl<>();
        try {
            list.add(1, 1);
            fail("Should have thrown an exception");
        } catch (IllegalIndexException _) {}

        try {
            list.add(2, 0);
        } catch (IllegalIndexException _) {
            fail();
        }

        try {
            list.add(2, 2);
            fail("Should have thrown an exception");
        } catch (IllegalIndexException _) {}
    }

    @Test
    public void testRemoveIndex() throws IllegalIndexException {
        ListImpl<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(2);
        assertEquals(list.get(0), (Integer) 1);
        assertEquals(list.get(1), (Integer) 2);
        assertEquals(list.get(2), (Integer) 4);
    }

    @Test
    public void testRemoveIndexIllegalIndexException() {
        ListImpl<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        try {
            list.remove(5);
            fail("Should have thrown an exception");
        } catch (IllegalIndexException _) {}

        try {
            list.remove(1);
        } catch (IllegalIndexException _) {
            fail();
        }

        try {
            list.remove(0);
        } catch (IllegalIndexException _) {}
    }

    @Test
    public void testRemove() throws ItemNotFoundException, IllegalIndexException {
        ListImpl<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(4);
        list.remove((Integer) 1);
        assertEquals(list.get(0), (Integer) 2);
        assertEquals(list.get(1), (Integer) 3);
        assertEquals(list.get(2), (Integer) 4);
    }

    @Test
    public void testRemoveItemNotFoundException() {
        ListImpl<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        try {
            list.remove((Integer) 5);
            fail("Should have thrown an exception");
        } catch (ItemNotFoundException _) {}

        try {
            list.remove((Integer) 1);
        } catch (ItemNotFoundException e) {
            fail();
        }

        try {
            list.remove((Integer) 12);
            fail("Should have thrown an exception");
        } catch (ItemNotFoundException _) {}
    }

    @Test
    public void testAddAll() throws IllegalIndexException {
        ListImpl<Integer> list = new ListImpl<>();
        ListImpl<Integer> list1 = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list1.add(4);
        list1.add(5);
        list.addAll(list1);
        assertEquals(list.get(0), (Integer) 1);
        assertEquals(list.get(1), (Integer) 2);
        assertEquals(list.get(2), (Integer) 3);
        assertEquals(list.get(3), (Integer) 4);
        assertEquals(list.get(4), (Integer) 5);
    }

    @Test
    public void contains() {
        ListImpl<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertTrue(list.contains(4));
        assertFalse(list.contains(5));
        assertTrue(list.contains(3));
        assertTrue(list.contains(2));
        assertTrue(list.contains(1));
    }

    @Test
    public void isEmpty() {
        ListImpl<Integer> list = new ListImpl<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }
}
