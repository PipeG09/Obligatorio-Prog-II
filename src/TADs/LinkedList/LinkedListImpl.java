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
    public Node<T> findNode(T value){
        return null;
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
       // hacela vos

    }
    @Override
    public void remove(int position) throws IlegalIndexException {
        Node<T> temp = this.getFirst();
        Node<T> temp1 = null;

        if (temp == null) {
            System.out.println("la lista esta vacia ");
        }
        else {
            if (position == 0) {
                if (temp.getNext() != null) {
                    setFirst(temp.getNext());
                } else {
                    setFirst(null);
                }
            } else if (position==-1){
                position=size()-1;
            } else {
                for (int i = 0; i < position; i++) {
                    temp1 = temp;
                    if (temp1.getNext() != null) {
                        temp = temp1.getNext();

                    } else {
                        System.out.print("No existe la posicion " + position);
                        break;
                    }
                }
                if (temp.getNext() != null) {
                    temp1.setNext(temp.getNext());
                } else {
                    temp1.setNext(null);
                    setLast(temp1);
                }
            }
        }
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
