package TADs.Queue;

public interface Queue<T> {

    void enqueue(T value);

    T dequeue();

    boolean contains(T value);

    int size();

}
