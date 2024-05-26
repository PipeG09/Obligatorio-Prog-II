package uy.edu.um.prog2.adt.Queue;

import uy.edu.um.prog2.adt.List.IllegalIndexException;
import uy.edu.um.prog2.adt.List.List;
import uy.edu.um.prog2.adt.List.ListImpl;

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
            } catch (IllegalIndexException _) {}
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

    public List<T> getList() {
        return list;
    }
}
