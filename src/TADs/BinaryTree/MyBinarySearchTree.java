package TADs.BinaryTree;

import Exceptions.ItemNotFoundException;

import TADs.LinkedList.List;

public interface MyBinarySearchTree<K extends Comparable<K>,T> {

    T find(K key) throws ItemNotFoundException;

    void insert(K key, T data) throws WrongKey;

    void delete(K key) throws ItemNotFoundException;

    int size();

    int countLeaf();

    int countCompleteElements();

    List<K> inOrder();

    List<K> preOrder();

    List<K> postOrder();
}
