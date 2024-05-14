package TADs.Stack;

import TADs.List.ListImpl;

public class StackImpl<T extends Comparable<T>> implements Stack<T> {

    private ListImpl<T> list;

    private int size;

    private int top;

    public StackImpl(int size) {
        this.list = new ListImpl<>();
        this.size = size;
        this.top = -1;
    }


    @Override
    public void push(T value) {

    }

    @Override
    public T pop() throws EmptyStackException {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
