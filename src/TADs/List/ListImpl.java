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
    public void add(T value, int index) throws IlegalIndexException {
        if (index < 0 || index > size) {
            throw new IlegalIndexException();
        }
        Node<T> newNode = new Node<>(value);
        Node<T> nextNode = null;
        Node<T> previousNode = null;
        if (index != size) {
            nextNode = this.getNode(index);
            previousNode = nextNode.getPrevious();
            newNode.setNext(nextNode);
            newNode.setPrevious(previousNode);
            nextNode.setPrevious(newNode);
            if (previousNode != null) {
                previousNode.setNext(newNode);
            }
        } else {
            this.add(value);
            size -= 1;
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
    public T get(int position) throws IlegalIndexException {
        return getNode(position).getValue();
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
    public void remove(T value) throws ItemNotFoundException, IlegalIndexException {
        if (contains(value)) {
            throw new ItemNotFoundException();
        }
        int index = 0;
        Node<T> temp = first;

        if (temp.getValue().equals(value)) {
            remove(index);
        }

        while (index < size) {
            temp = temp.getNext();
            index += 1;
            if (temp.getValue().equals(value)) {
                remove(index);
            }
        }
    }


/*@Override
public void remove(int position) throws IlegalIndexException {
    Node<T> temp = this.getFirst();
    Node<T> temp1 = null;
    if (temp == null) {
        throw new IlegalIndexException();
    }
    else {
        if (position == 0) {
            if (temp.getNext() != null) {
                setFirst(temp.getNext());
            } else {
                setFirst(null);
            }
        } else if (position==-1|| position==size-1){
            setLast(getLast().getPrevious());
            getLast().setNext(null);
        } else {
            for (int i = 0; i < position; i++) {
                temp1 = temp;
                if (temp1.getNext() != null) {
                    temp = temp1.getNext();

                } else {
                    throw new IlegalIndexException();
                }
            }
            if (temp.getNext() != null) {
                temp1.setNext(temp.getNext());
                size-=1;
            } else {
                temp1.setNext(null);
                setLast(temp1);
                size-=1;
            }
        }
    }
    }*/

    @Override
    public void remove(int position) throws IlegalIndexException {
        Node<T> removeNode = this.getNode(position);
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void print(){
        Node<T> temp = first;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.getValue() + ", ");
            temp = temp.getNext();
        }
    }

    public Node<T> getNode(int index) throws IlegalIndexException {
        if (index < -1 || index >= size) {
            throw new IlegalIndexException();
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

}
