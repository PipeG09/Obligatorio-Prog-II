package TADs.LinkedList;

import Exceptions.ItemNotFoundException;
import TADs.Node.Node;

public interface List<T extends Comparable<T>> {
    void add(T value);

    void add(T value, int index) throws IlegalIndexException;
    void addAll(List<T> list);

    T get(int position) throws IlegalIndexException;

    boolean contains(T value);

    void remove(T value) throws ItemNotFoundException;
    void remove(int position) throws IlegalIndexException;
    int size();

    boolean isEmpty();
    Node<T> getFirst();
    Node<T> getLast();
}
