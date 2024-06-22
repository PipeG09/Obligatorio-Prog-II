package uy.edu.um.prog2.adt.HashTable;

import uy.edu.um.prog2.adt.BinaryTree.IllegalKeyException;
import uy.edu.um.prog2.adt.List.List;

import javax.management.openmbean.KeyAlreadyExistsException;

public interface HashTable<K ,T> {

    public void put(K key, T value) throws KeyAlreadyExistsException;

    public T get(K key);

    public void remove(K key);

    public boolean contains(K key);

    public List<K> keys();

    void setValueForKey(K key, T value) throws IllegalKeyException;

    int size();

    float loadFactor();
}
