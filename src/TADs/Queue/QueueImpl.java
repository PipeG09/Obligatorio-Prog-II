package TADs.Queue;

import TADs.List.IlegalIndexException;
import TADs.List.List;
import TADs.List.ListImpl;

public class QueueImpl<T extends Comparable<T>> implements Queue<T>{

    List<T> list;

    public QueueImpl() {
        this.list = new ListImpl<>();
    }

    /* Para aclaracion, ver que la queue agrega al final de una lista y saca al principio
     por la forma en que estan hechos los add y remove de List*/

    @Override
    public void enqueue(T element) {
    list.add(element);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        T element = null;
        try {
            if (list.getFirst()== null){
                throw new EmptyQueueException();
            }
            else {
                T value = list.get(0);
                try {
                    list.remove(0);
                } catch (IlegalIndexException _) {}
                return value;
            }
        } catch (IlegalIndexException _){
            return null;
        }


    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
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
