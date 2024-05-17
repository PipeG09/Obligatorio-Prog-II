package TADs.Queue;

public interface Queue<T extends Comparable<T>> {

    void enqueue(T value);

    T dequeue() throws EmptyQueueException;

    boolean contains(T value);

    int size();

    boolean isEmpty();
}
