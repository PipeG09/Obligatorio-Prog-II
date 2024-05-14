package TADs.List;

import Exceptions.ItemNotFoundException;
import TADs.Node.Node;

public class ListImpl<T extends Comparable<T>> implements List<T> {
    @Override
    public void add(T value) {

    }

    @Override
    public void add(T value, int index) throws IlegalIndexException {

    }

    @Override
    public void addAll(List<T> List) {

    }

    @Override
    public T get(int position) throws IlegalIndexException {
        return null;
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public void remove(T value) throws ItemNotFoundException {

    }

    @Override
    public void remove(int position) throws IlegalIndexException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Node<T> getFirst() {
        return null;
    }

    @Override
    public Node<T> getLast() {
        return null;
    }
}
