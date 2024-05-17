package TADs_Tests;

import Exceptions.ItemNotFoundException;
import TADs.List.IllegalIndexException;
import TADs.List.List;
import TADs.List.ListImpl;
import TADs.Node.Node;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void testAdd() {
        List<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Node<Integer> node = list.getFirst();
        assertEquals(node.getValue(), (Integer) 1);
        assertEquals(node.getNext().getValue(), (Integer) 2);
        assertEquals(node.getNext().getNext().getValue(), (Integer) 3);
    }

    @Test
    public void testAddIndex() throws IllegalIndexException {
        List<Integer> list = new ListImpl<>();
        list.add(1, 0);
        list.add(3, 1);
        list.add(4, 2);
        list.add(8, 3);
        Node<Integer> node = list.getFirst();
        assertEquals(node.getValue(), (Integer) 1);
        assertEquals(node.getNext().getValue(), (Integer) 3);
        assertEquals(node.getNext().getNext().getValue(), (Integer) 4);
        assertEquals(node.getNext().getNext().getNext().getValue(), (Integer) 8);
    }


    @Test
    public void AddIndexIlegalIndexException() {
        List<Integer> list = new ListImpl<>();
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
        List<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(2);
        Node<Integer> node = list.getFirst();
        assertEquals(node.getValue(), (Integer) 1);
        assertEquals(node.getNext().getValue(), (Integer) 2);
        assertEquals(node.getNext().getNext().getValue(), (Integer) 4);
    }

    @Test
    public void testRemoveIndexIlegalIndexException() {
        List<Integer> list = new ListImpl<>();
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
    public void testRemove() throws ItemNotFoundException {
        List<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(4);
        list.remove((Integer) 1);
        Node<Integer> node = list.getFirst();
        assertEquals(node.getValue(), (Integer) 2);
        assertEquals(node.getNext().getValue(), (Integer) 3);
        assertEquals(node.getNext().getNext().getValue(), (Integer) 4);
    }

    @Test
    public void testRemoveItemNotFoundException() {
        List<Integer> list = new ListImpl<>();
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
    public void testAddAll() {
        List<Integer> list = new ListImpl<>();
        List<Integer> list1 = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list1.add(4);
        list1.add(5);
        list.addAll(list1);
        Node<Integer> node = list.getFirst();
        assertEquals(node.getValue(), (Integer) 1);
        assertEquals(node.getNext().getValue(), (Integer) 2);
        assertEquals(node.getNext().getNext().getValue(), (Integer) 3);
        assertEquals(node.getNext().getNext().getNext().getValue(), (Integer) 4);
        assertEquals(node.getNext().getNext().getNext().getNext().getValue(), (Integer) 5);
    }

    @Test
    public void contains() {
        List<Integer> list = new ListImpl<>();
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
        List<Integer> list = new ListImpl<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }
}
