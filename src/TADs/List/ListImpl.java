package TADs.List;

import Exceptions.ItemNotFoundException;
import TADs.Node.Node;

public class ListImpl<T extends Comparable<T>> implements List<T> {

    Node<T> first;

    Node<T> last;

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
    }

    @Override
    public void add(T value, int index) throws IlegalIndexException {
        /*No se si incluir size aca, Hago un while que sobra, podría poner un if adentro del for*/

        if (index < 0 || index >= size()) {
            throw new IlegalIndexException();
        }

        Node<T> newNode = new Node<>(value);

        if (index == 0 && first != null) {
            first.setPrevious(newNode);
            newNode.setNext(first);
            first = newNode;
        } else if (first == null) {
                add(value);
        } else {
            Node<T> temp = first;
            Node<T> newPrevious = null;
            Node<T> newNext = null;
            for (int i = 0; i <= index - 1; i++) {
                temp = temp.getNext();
                if (i == index - 2) {
                    newPrevious = temp;
                } else if (i == index - 1) {
                    newNext = temp;
                }
            }
            newNode.setPrevious(newPrevious);
            newNode.setNext(newNext);
            newPrevious.setNext(newNode);
            newNext.setPrevious(newNode);
        }
    }

    @Override
    public void addAll(List<T> list) {

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
        return first;
    }

    @Override
    public Node<T> getLast() {
        return last;
    }
}
