package TADs.Queue;

import TADs.List.IlegalIndexException;
import TADs.List.List;
import TADs.List.ListImpl;

public class QueueImpl<T extends Comparable<T>> implements Queue<T>{

    private final List<T> list;

    public QueueImpl() {
        this.list = new ListImpl<>();
    }

    /* For clarification, note that the queue adds to the end of a list and removes from the beginning
    because of the way the add and remove methods of List are implemented. */

    @Override
    public void enqueue(T element) {
    list.add(element);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        T value = null;
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        else {
            try {
                value = list.get(0);
                list.remove(0);
            } catch (IlegalIndexException _) {}
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(T value) {
        return list.contains(value);
    }


    @Override
    public int size() {
        return list.size();
    }
}
