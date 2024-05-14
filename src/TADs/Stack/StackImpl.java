package TADs.Stack;

import TADs.List.ListImpl;
import TADs.List.IlegalIndexException;


public class StackImpl<T extends Comparable<T>> implements Stack<T> {

    private ListImpl<T> list;

    private final int size; /*Lo hago final porque nunca lo toco*/

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
    }

    @Override
    public T pop() throws EmptyStackException {
        if (top == -1) {
            throw new EmptyStackException();
        }
        try {
            T removed = list.getLast().getValue();
            list.remove(top);
            return removed;
        } catch (IlegalIndexException e) {
            throw new EmptyStackException(); /*Esto nunca salta ni idea que hacer */
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
}
