package TADs.Stack;

public interface Stack<T> {

    void push(T value);

    T pop() throws EmptyStackException;

    T peek();

    int size();
}
