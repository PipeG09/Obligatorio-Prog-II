package uy.edu.um.prog2.adt.BinaryTree;

import Exceptions.ItemNotFoundException;

import uy.edu.um.prog2.adt.List.List;

public interface MyBinarySearchTree<K extends Comparable<K>,T> {

    T find(K key) throws ItemNotFoundException;

    void insert(K key, T data) throws IllegalKeyException;

    void delete(K key) throws ItemNotFoundException;

    int size();

    int countLeaf();

    int countCompleteElements();

    List<K> inOrder();

    List<K> preOrder();

    List<K> postOrder();

    void draw();
}
