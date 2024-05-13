package TADs.Node;

public class NodeBST<T, K extends Comparable<K>> {

    private K key;

    private T value;

    private NodeBST<T, K> leftChild;

    private NodeBST<T, K> rightChild;

    public NodeBST(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public NodeBST<T, K> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeBST<T, K> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeBST<T, K> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeBST<T, K> rightChild) {
        this.rightChild = rightChild;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
