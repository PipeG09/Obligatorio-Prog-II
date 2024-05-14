package TADs.List;

import Exceptions.ItemNotFoundException;
import TADs.Node.Node;

public class ListImpl<T extends Comparable<T>> implements List<T> {
    Node<T> first;
    Node<T> last;

    int size = 0;

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
        if (index < 0 || index > size) {
            throw new IlegalIndexException();
        }

        Node<T> newNode = new Node<>(value);

        if (index == 0 && first != null) {
            first.setPrevious(newNode);
            newNode.setNext(first);
            first = newNode;
        } else if (first == null || index == size) {
                add(value);
        }
        else {
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
        if (list.isEmpty()) {
            return;
        }
        Node<T> temp = list.getFirst();
        add(temp.getValue());
        for (int i = 0; i < list.size(); i++) {
            temp.getNext();
            add(temp.getValue());
        }

    }

    @Override
    public T get(int position) throws IlegalIndexException {
        if (position < 0 || position >= size()) {
            throw new IlegalIndexException();
        }
        Node<T> temp = first;

        for (int i = 0; i < position; i++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public boolean contains(T value) {
        Node<T> temp = first;
        while (temp != null) {
            temp = temp.getNext();
            if (temp.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(T value) throws ItemNotFoundException {

    }

    @Override
    public void remove(int index) throws IlegalIndexException {
        if (index < 0 || index >= size) {
            throw new IlegalIndexException();
        }

            Node<T> temp = first;
            Node<T> newPrevious = null;
            Node<T> newNext = null;
            for (int i = 0; i <= index; i++) {
                temp = temp.getNext();
                if (i == index - 2) {
                    newPrevious = temp;
                } else if (i == index) {
                    newNext = temp;
                }
            }
            newPrevious.setNext(newNext);
            newNext.setPrevious(newPrevious);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
