package TADs.Stack;

public class StackImpl<T extends Comparable<T>> implements Stack<T> {

    private Object[] array;

    private int size;

    private int top = -1;

    public StackImpl(int size) {
        this.array = new Object[size];
        this.size = size;
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
