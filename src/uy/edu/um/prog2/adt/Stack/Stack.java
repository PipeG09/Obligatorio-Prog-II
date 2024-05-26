package uy.edu.um.prog2.adt.Stack;

public interface Stack<T extends Comparable<T>> {

    void push(T value) throws FullStackException;

    T pop() throws EmptyStackException;

    T peek() throws EmptyStackException;

    int size();

    boolean isEmpty();
}
