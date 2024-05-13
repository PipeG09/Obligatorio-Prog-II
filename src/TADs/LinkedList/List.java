package TADs.LinkedList;

import Exceptions.ItemNotFoundException;

public interface List<T> {

    void add(T value);

    T get(int position);

    boolean contains(T value);

    void remove(T value) throws IlegalIndexException;

    int size();
}
