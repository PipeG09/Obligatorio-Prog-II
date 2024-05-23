package TADs.HashTable;

import TADs.List.List;
import TADs.List.ListImpl;
import TADs.Node.HashNode;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.LinkedList;

public class HashTableImpl <K,T> implements HashTable <K,T>{
    HashNode<K,T>[] array;
    int size;
    float capacity;

    public HashTableImpl(int size) {
        this.array = new HashNode[size];
        this.size = size;
        this.capacity = 0.0f;
    }

    public HashNode[] getArray() {
        return array;
    }
    public void setArray(HashNode[] array) {
        this.array = array;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    private void refactorHash(int newSize) {
        HashNode<K,T>[] old = getArray();
        setSize(newSize);
        setArray(new HashNode[newSize]);
        setCapacity(0.0f);
        for (HashNode<K, T> ktHashNode : old) {
            if (ktHashNode != null ) {
                if(ktHashNode.getKey() != null) {
                    put(ktHashNode.getKey(), ktHashNode.getValue());
                }
            }
        }

    }

    @Override
    public void put(K key, T value) throws KeyAlreadyExistsException {

        if (capacity>=0.75){
            refactorHash(size*2);
        }

        int hash = key.hashCode();
        int index = hash % size;
        HashNode<K,T>  node = new HashNode<K,T>(key, value);
        //
        if (array[index] == null) {
            array[index] = node;
            capacity+= (float) 1/size;
        }// si este espacio del array esta lleno itero hasta encontrar uno libre
        else {
            int firstSpot= -1;
            // empiezo en 0 para contemplar el caso de que un elemento con la misma key fue eliminado en ese lugar
            // pero debo llegar hasta el proximo vacio para asegurarme que la key no esta ya en el arbol

            for (int i = 0; i < size; i++) {

                if(array[(index + i) % size] == null){
                // si encuentro un espacio vacio ya se que la key no se encuentra en el hash
                    if (firstSpot == -1) { // si no hubo ningun espacio libre antes
                        array[(index + i) % size] = node;
                        return;
                    }
                    else{
                        array[firstSpot]=node;  // si ya habia un espacio libre correspondiente a uno eliminado antes
                        // lo agrego ahi
                        return;
                    } }

                // solo me guardo firstStop si no hubo una stop antes
                else if(array[(index + i) % size].getKey() == null & firstSpot == -1){
                    firstSpot = (index + i) % size; // guardo la pocision vacia

                }
                else if (array[(index + i) % size].getKey().equals(key)){
                    throw new KeyAlreadyExistsException();
                }

            }
        }
    }

    @Override
    public T get(K key) {
        HashNode<K,T> node =getNode(key);
        if (node == null) {
            return null;
        }
        return node.getValue();
    }

    private HashNode<K,T> getNode(K key) {
        int hash = key.hashCode();
        int index = hash % size;
        for (int i=0;i<size;i++) {
            HashNode<K,T> node = array[(index+i)%size];
            if(node==null){
                return null;
            }
            else if (node.getKey()!=null){
                if( node.getKey().equals(key)) {
                return node;
            }}
            // if node.key == null or other key, we keep iterating
        }
        return null;

    }

    @Override
    public void remove(K key) {
       HashNode<K,T> node= getNode(key);
       if (node == null) {
           return;
       }
       else{
           node.setKey(null);
           node.setValue(null);
       }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public List<K> keys() {
        List<K> keyList= new ListImpl<>();
        for (HashNode<K,T> node : array) {
            if(node != null) {
                if(node.getKey() != null) {
                    keyList.add(node.getKey());
                }
            }
        }
        return keyList;
    }
}
