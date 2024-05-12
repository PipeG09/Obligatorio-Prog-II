package TADs.Node;

public class BinaryTreeNode<K, T> {

    private K key;

    private T value;

    private BinaryTreeNode<K, T> leftChild;

    private BinaryTreeNode<K, T> rightChild;

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

    public BinaryTreeNode<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
