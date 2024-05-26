package TADs_Tests;

import uy.edu.um.prog2.adt.List.List;
import uy.edu.um.prog2.adt.Node.Node;
import uy.edu.um.prog2.adt.Stack.EmptyStackException;
import uy.edu.um.prog2.adt.Stack.FullStackException;
import uy.edu.um.prog2.adt.Stack.StackImpl;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void testPush() throws FullStackException {
        StackImpl stack = new StackImpl(20);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        List<Integer> list = stack.getList();
        Node<Integer> node = list.getFirst();
        assertEquals(node.getValue(), (Integer) 1);
        assertEquals(node.getNext().getValue(), (Integer) 2);
        assertEquals(node.getNext().getNext().getValue(), (Integer) 3);
        assertEquals(node.getNext().getNext().getNext().getValue(), (Integer) 4);
        assertEquals(node.getNext().getNext().getNext().getNext().getValue(), (Integer) 5);
    }

    @Test
    public void testPushFullStackException() {
        StackImpl stack = new StackImpl(4);
        try {
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
        } catch (FullStackException _) {
            fail();
        }

        try {
            stack.push(5);
            fail("Should have thrown an exception");
        } catch (FullStackException _) {}
    }


    @Test
    public void testPop() throws EmptyStackException, FullStackException {
        StackImpl stack = new StackImpl(20);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.pop();
        List<Integer> list = stack.getList();
        Node<Integer> node = list.getLast();
        assertEquals(node.getValue(), (Integer) 4);
    }

    @Test
    public void testPopEmptyStackException() throws FullStackException {
        StackImpl stack = new StackImpl(20);
        try {
            stack.pop();
            fail("Should have thrown an exception");
        } catch (EmptyStackException _) {}

        try {
            stack.push(1);
            stack.pop();
        } catch (EmptyStackException _) {
            fail();
        }

        try {
            stack.pop();
            fail("Should have thrown an exception");
        } catch (EmptyStackException _) {}
    }

    @Test
    public void testPeek() throws EmptyStackException, FullStackException {
        StackImpl stack = new StackImpl(20);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(stack.peek(), 3);
    }

    @Test
    public void testPeekEmptyStackException() throws FullStackException {
        StackImpl stack = new StackImpl(20);
        try {
            stack.peek();
            fail("Should have thrown an exception");
        } catch (EmptyStackException _) {}

        try {
            stack.push(1);
            stack.peek();
        } catch (EmptyStackException _) {
            fail();
        }
    }

    @Test
    public void testSize() throws FullStackException {
        StackImpl stack = new StackImpl(20);
        assertEquals(stack.size(), 0);
        stack.push(1);
        stack.push(1);
        assertEquals(stack.size(), 2);
    }

    @Test
    public void testIsEmpty() throws FullStackException {
        StackImpl stack = new StackImpl(20);
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }
}

