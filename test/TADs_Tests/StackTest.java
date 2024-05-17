package TADs_Tests;

import TADs.List.List;
import TADs.Node.Node;
import TADs.Stack.EmptyStackException;
import TADs.Stack.FullStackException;
import TADs.Stack.Stack;
import TADs.Stack.StackImpl;
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
        } catch (FullStackException e) {
            fail();
        }

        try {
            stack.push(5);
            fail("Should have thrown an exception");
        } catch (FullStackException e) {}
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
        } catch (EmptyStackException e) {}

        try {
            stack.push(1);
            stack.pop();
        } catch (EmptyStackException e) {
            fail();
        }

        try {
            stack.pop();
            fail("Should have thrown an exception");
        } catch (EmptyStackException e) {}
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
        } catch (EmptyStackException e) {}

        try {
            stack.push(1);
            stack.peek();
        } catch (EmptyStackException e) {
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

