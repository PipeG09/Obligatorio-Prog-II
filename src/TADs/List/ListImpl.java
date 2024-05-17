package TADs.List;

import Exceptions.ItemNotFoundException;
import TADs.Node.Node;

public class ListImpl<T extends Comparable<T>> implements List<T> {

    private Node<T> first;

    private Node<T> last;

    private int size;

    public ListImpl() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public Node<T> getFirst() {
        return first;
    }

    @Override
    public Node<T> getLast() {
        return last;
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

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
        size += 1;
    }

    @Override
    public void add(T value, int index) throws IllegalIndexException {
        if (index < 0 || index > size) {
            throw new IllegalIndexException();
        }

        Node<T> newNode = new Node<>(value);

        if (index != size) {
            Node<T> nextNode = this.getNode(index);
            Node<T> previousNode = nextNode.getPrevious();
            newNode.setNext(nextNode);
            newNode.setPrevious(previousNode);
            nextNode.setPrevious(newNode);
            if (previousNode != null) {
                previousNode.setNext(newNode);
            }
        } else {
            this.add(value);
            return; // so it doesn't add the 1 from below
        }
        if (index == 0) {
            this.setFirst(newNode);
        }
        size += 1;
    }

    @Override
    public void addAll(List<T> list) {
        this.last.setNext(list.getFirst());
        list.getFirst().setPrevious(this.last);
        this.last = list.getLast();
        size += list.size();
    }

    @Override
    public T get(int position) throws IllegalIndexException {
        return getNode(position).getValue();
    }

    @Override
    public boolean contains(T value) {
        Node<T> temp = first;
        while (temp != null) {
            if (temp.getValue().equals(value)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    @Override
    public void remove(T value) throws ItemNotFoundException {
        if (!contains(value)) {
            throw new ItemNotFoundException();
        }
        int index = 0;
        Node<T> temp = first;

        if (temp.getValue().equals(value)) {
            removeNode(temp);
            index -= 1;
        }
        while (index < size()) {
            temp = temp.getNext();
            index += 1;
            if (temp == null) {
                break; //sdsds
            }
            else if (temp.getValue().equals(value)) {
                removeNode(temp);
            }
        }
    }


    @Override
    public void remove(int position) throws IllegalIndexException {
        Node<T> removeNode = this.getNode(position);
        removeNode(removeNode);
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
    public void print() {

        Node<T> temp = first;
        System.out.print("[ ");

        for (int i = 0; i < size-1; i++) {
            System.out.print(temp.getValue() + ", ");
            temp = temp.getNext();
        }
        System.out.print(temp.getValue() + " ]");
    }

    public Node<T> getNode(int index) throws IllegalIndexException {
        if (index < -1 || index >= size) {
            throw new IllegalIndexException();
        }
        Node<T> temp = first;
        if (index == size - 1|| index == -1) {
            return getLast();
        }
        else {
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp;
        }
    }

    public void removeNode(Node<T> removeNode) {
        Node<T> previousNode = removeNode.getPrevious();
        Node<T> nextNode = removeNode.getNext();

        if (removeNode == this.first) {
            if (removeNode == this.last) {
                this.setLast(null);
                this.setFirst(null);
            }
            this.setFirst(nextNode);
        } else if (removeNode == this.last) {
            this.setLast(previousNode);
        }

        if (nextNode != null) {
            nextNode.setPrevious(previousNode);
        }

        if (previousNode != null) {
            previousNode.setNext(nextNode);
        }
        size -= 1;
    }
}
