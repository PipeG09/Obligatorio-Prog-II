package TADs.LinkedList;

import Exceptions.ItemNotFoundException;

public interface List<T> {

    void add(T value);

    void add(T value, int index) throws IlegalIndexException;

    T get(int position) throws IlegalIndexException;

    boolean contains(T value);

    void remove(T value) throws ItemNotFoundException;

    int size();

    boolean isEmpty();
}
