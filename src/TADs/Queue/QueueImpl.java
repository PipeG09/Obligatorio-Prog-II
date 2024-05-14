package TADs.Queue;

import Exceptions.ItemNotFoundException;
import TADs.List.IlegalIndexException;
import TADs.List.List;
import TADs.Node.Node;

public class QueueImpl<T extends Comparable<T>> implements Queue<T>{
    List<T> list;

    public QueueImpl() {
        this.list = new List<T>() {
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
        };
    }

    /* Para aclaracion, ver que la queue agrega al final de una lista y saca al principio
     por la forma en que estan hechos los add y remove de List*/

    @Override
    public void enqueue(T element) {
    list.add(element);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (list.getFirst() == null){
            throw new EmptyQueueException();
        }
        else {
            T value = list.getFirst().getValue();
            try {
                list.remove(0);
            } catch (IlegalIndexException _) {}
            return value;
        }

    }

    @Override
    public boolean isEmpty() {
        return list.getFirst()==null;
    }

    @Override
    public boolean contains(T value) {
        return list.contains(value);
    }


    @Override
    public int size() {
        return list.size();
    }
}
