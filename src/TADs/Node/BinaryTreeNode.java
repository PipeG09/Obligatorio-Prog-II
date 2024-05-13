package TADs.Node;

public class BinaryTreeNode<T, K extends Comparable<K>> {

    private K key;

    private T value;

    private BinaryTreeNode<T, K> leftChild;

    private BinaryTreeNode<T, K> rightChild;

    public BinaryTreeNode(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public BinaryTreeNode<T, K> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T, K> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<T, K> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T, K> rightChild) {
        this.rightChild = rightChild;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
