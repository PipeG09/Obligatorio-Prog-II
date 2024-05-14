package TADs.LinkedList;


import Exceptions.ItemNotFoundException;
import TADs.Node.Node;

public class LinkedListImpl<T extends Comparable<T>> implements List <T>{
    private Node<T> first;
    private Node<T> last;

    public Node<T> getFirst() {
        return first;
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public Node<T> getLast() {
        return last;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

    @Override
    public void add(T value) {

    }
    @Override
    public void addAll(List<T> list){
        last.setNext(list.getFirst());
    }

    @Override
    public void add(T value, int index) throws IlegalIndexException {

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
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }
}
