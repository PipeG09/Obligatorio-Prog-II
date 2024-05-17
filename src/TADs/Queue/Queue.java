package TADs.Queue;

import TADs.List.List;

public interface Queue<T extends Comparable<T>> {

    void enqueue(T value);

    T dequeue() throws EmptyQueueException;

    boolean contains(T value);

    int size();

    boolean isEmpty();

    List<T> getList();
}
