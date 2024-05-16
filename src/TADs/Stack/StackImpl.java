package TADs.Stack;

import TADs.List.ListImpl;
import TADs.List.IlegalIndexException;


public class StackImpl<T extends Comparable<T>> implements Stack<T> {

    private final ListImpl<T> list;

    private final int size;

    private int top;


    public StackImpl(int size) {
        this.list = new ListImpl<>();
        this.size = size;
        this.top = -1;
    }


    @Override
    public void push(T value) throws FullStackException{
        if (top == size - 1) {
            throw new FullStackException();
        }
        list.add(value);
        top+=1;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (top == -1) {
            throw new EmptyStackException();
        }
        try {
            T removed = list.getLast().getValue();
            list.remove(top);
            top -= 1;
            return removed;
        } catch (IlegalIndexException e) { /*Que poner*/
            throw new EmptyStackException();
        }
    }

    @Override
    public T peek() throws EmptyStackException {
        if (top == -1) {
            throw new EmptyStackException();
        }
        return list.getLast().getValue();
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public ListImpl<T> getList() {
        return list;
    }

    public int getSize() {
        return size;
    }

    public int getTop() {
        return top;
    }
}
