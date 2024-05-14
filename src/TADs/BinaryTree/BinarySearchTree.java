package TADs.BinaryTree;

import Exceptions.ItemNotFoundException;
import TADs.LinkedList.List;

public interface BinarySearchTree<T, K extends Comparable<K>> {

    T find(K key) throws ItemNotFoundException;

    void insert(K key, T data) throws ItemNotFoundException;

    void delete(K key) throws ItemNotFoundException;

    int size();

    int countLeaf();

    int countCompleteElements();

    List<K> inOrder();

    List<K> preOrder();

    List<K> postOrder();
}
