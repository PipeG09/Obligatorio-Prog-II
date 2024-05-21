package TADs.HashTable;

import TADs.Node.HashNode;

import javax.management.openmbean.KeyAlreadyExistsException;

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
            put(ktHashNode.getKey(), ktHashNode.getValue());
        }

    }

    @Override
    public void put(K key, T value) throws KeyAlreadyExistsException {

        if (capacity>=0.75){
            refactorHash(size*2);
        }
        if (contains(key)){
            throw new KeyAlreadyExistsException();
        }
        int hash = key.hashCode();
        int index = hash % size;
        HashNode<K,T>  node = new HashNode<K,T>(key, value);
        //
        if (array[index] == null) {
            array[index] = node;
        } // si este espacio del array esta lleno itero hasta encontrar uno libre
        for (int i=1;i<size;i++){
            if (array[(index+i)%size]==null || array[(index+i)%size].getKey()==null) {
                array[(index+i)%size] = node;
                capacity+= (float) 1 /size;
                return;
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
            if (node.getKey().equals(key)) {
                return node;
            }
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
}
