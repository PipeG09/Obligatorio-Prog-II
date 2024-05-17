package TADs_Tests;

import TADs.List.List;
import TADs.Node.Node;
import TADs.Queue.EmptyQueueException;
import TADs.Queue.QueueImpl;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testEnqueue() {
        QueueImpl queue = new QueueImpl();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        List<Integer> list = queue.getList();
        Node<Integer> node = list.getFirst();
        assertEquals(node.getValue(), (Integer) 1);
        assertEquals(node.getNext().getValue(), (Integer) 2);
        assertEquals(node.getNext().getNext().getValue(), (Integer) 3);
        assertEquals(node.getNext().getNext().getNext().getValue(), (Integer) 4);
    }

    @Test
    public void testDequeue() throws EmptyQueueException {
        QueueImpl queue = new QueueImpl();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.dequeue();
        List<Integer> list = queue.getList();
        Node<Integer> node = list.getFirst();
        assertEquals(node.getValue(), (Integer) 2);
        assertEquals(node.getNext().getValue(), (Integer) 3);
        assertEquals(node.getNext().getNext().getValue(), (Integer) 4);
    }

    @Test
    public void testDequeueEmptyQueueException() {
        QueueImpl queue = new QueueImpl();
        try {
            queue.dequeue();
            fail("Should have thrown an exception");
        } catch (EmptyQueueException _) {}

        try {
            queue.enqueue(1);
            queue.dequeue();
        } catch (EmptyQueueException _) {
            fail();
        }

        try {
            queue.dequeue();
            fail("Should have thrown an exception");
        } catch (EmptyQueueException _) {}
    }

    @Test
    public void testIsEmpty() {
        QueueImpl queue = new QueueImpl();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testContains() {
        QueueImpl queue = new QueueImpl();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertTrue(queue.contains(3));
        assertTrue(queue.contains(4));
        assertFalse(queue.contains(5));
        assertFalse(queue.contains(6));
    }
}
