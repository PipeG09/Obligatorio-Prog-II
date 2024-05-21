package TADs.HashTable;

import javax.management.openmbean.KeyAlreadyExistsException;

public interface HashTable<K,T> {
    public void put(K key, T value) throws KeyAlreadyExistsException;
    public T get(K key);
    public void remove(K key);
    public boolean contains(K key);
}
