package TADs.Queue;

import TADs.LinkedList.IlegalIndexException;
import TADs.LinkedList.LinkedListImpl;
import TADs.LinkedList.List;

public class QueueImpl<T extends Comparable<T>> implements Queue<T>{
    List<T> list;

    public QueueImpl() {
        this.list = new LinkedListImpl<>();
    }

    // Para aclaracion, ver que la queue agrega al final de una lista y saca al principio
    // por la forma en que estan hechos los add y remove de LinkedList
    @Override
    public void enqueue(T element) {
    list.add(element);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (list.getFirst()==null){
            throw new EmptyQueueException();
        }
        else{
            T value=list.getFirst().getValue();
            try {
                list.removeFromPosition(0);
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
