package TADs.Node;



import TADs.List.List;
import TADs.List.ListImpl;

public class NodeBST<K extends Comparable<K>, T> implements Comparable<NodeBST<K, T>> {
    K key;
    T data;

    NodeBST<K, T> leftChild;

    NodeBST<K, T> rightChild;

    public NodeBST(K key, T data) {
        this.key = key;
        this.data = data;
    }

    public K getKey() {return key;}

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeBST<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeBST<K, T> leftchild) {
        this.leftChild = leftchild;
    }

    public NodeBST<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeBST<K, T> rightchild) {
        this.rightChild = rightchild;
    }

    public static <K extends Comparable<K>, T> NodeBST<K, T> findNodeInBinarySearchTree(K key, NodeBST<K, T> root) {
        if (root == null) {
            return null;
        }
        if (root.key.equals(key)) {
            return root;
        }

        if (key.compareTo(root.getKey())<0) {
            if (root.leftChild != null) {
                NodeBST<K, T> left = findNodeInBinarySearchTree(key, root.leftChild);
                if (left != null) {
                    return left;
                }
            }else return null;

        }
        if(key.compareTo(root.getKey())>0) {
            if (root.rightChild != null) {
                NodeBST<K, T> right = findNodeInBinarySearchTree(key, root.rightChild);
                if (right != null) {
                    return right;}

            }else return null;
        }

        return null;
    }

    public NodeBST<K, T> findParentNodeInBinarySearchTree(K key) {

        if (this.key.equals(key)) {
            return null;
        }
        if (this.getLeftChild().key.equals(key) || this.getRightChild().key.equals(key)) {
            return this;
        } else {
            if (this.leftChild != null& key.compareTo(this.getKey())<0) {
                NodeBST<K, T> left = this.getLeftChild().findParentNodeInBinarySearchTree(key);
                if (left != null) {
                    return left;
                }
            }
            if (this.rightChild != null & key.compareTo(this.getKey())>0) {
                NodeBST<K, T> right = this.rightChild.findParentNodeInBinarySearchTree(key);
                if (right != null) {
                    return right;
                }
            }
        }
        return null;
    }

    public int size() {
        int size = 1;
        if (getLeftChild() != null) {
            size += getLeftChild().size();
        }
        if (getRightChild() != null) {
            size += getRightChild().size();
        }
        return size;
    }

    public  int countLeafs() {
        int leafs = 0;
        if (getLeftChild() == null & getRightChild() == null) {
            leafs+=1;
        }
        else if (getLeftChild() != null) {
            leafs+= getLeftChild().countLeafs();
        }
        if (getRightChild()!=null) {
            leafs+= getRightChild().countLeafs();
        }
        return leafs;
    }

    public int countCompleteElements() {
        int size = 0;
        if (getLeftChild() != null) {
            size += getLeftChild().countCompleteElements();
        }
        if (getRightChild() != null) {
            size += getRightChild().countCompleteElements();
        }
        if (getLeftChild() != null & getRightChild() != null) {
            size = size + 1;
        }
        return size;
    }

    public List<K> preOrderFrom(){
        List<K> preOrder = new ListImpl<>();
        preOrder.add(key);
        if (getLeftChild() != null) {
            preOrder.addAll(getLeftChild().preOrderFrom());
        }
        if (getRightChild() != null) {
            preOrder.addAll(getRightChild().preOrderFrom());
        }
        return preOrder;
    }

    public  List<K> inOrderFrom() {
        List<K> inOrder = new ListImpl<>();
        if (getLeftChild() != null) {
            inOrder.addAll(getLeftChild().inOrderFrom());
        }
        inOrder.add(key);
        if (getRightChild() != null) {
            inOrder.addAll(getRightChild().inOrderFrom());
        }
        return inOrder;
    }

    public  List<K> postOrderFrom() {
        List<K> postOrder = new ListImpl<>();
        if (getLeftChild() != null) {
            postOrder.addAll(getLeftChild().postOrderFrom());
        }
        if (getRightChild() != null) {
            postOrder.addAll(getRightChild().postOrderFrom());
        }
        postOrder.add(getKey());
        return postOrder;
    }

    @Override
    public int compareTo(NodeBST<K, T> o) {
        return 0;
    }
}