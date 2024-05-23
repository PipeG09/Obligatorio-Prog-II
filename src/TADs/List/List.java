package TADs.List;

import Exceptions.ItemNotFoundException;
import TADs.Node.Node;

public interface List<T> {

    void add(T value);

    void add(T value, int index) throws IllegalIndexException;

    void addAll(List<T> List);

    T get(int index) throws IllegalIndexException;

    boolean contains(T value);

    void remove(T value) throws ItemNotFoundException;

    void remove(int index) throws IllegalIndexException;

    int size();

    boolean isEmpty();

    Node<T> getFirst();

    Node<T> getLast();

    void print();
}
