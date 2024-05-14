package TADs.Node;


import Exceptions.WrongParameters;

public class NodeBST<K extends Comparable<K>, T> {

    K key;

    T data;

    NodeBST<K, T> leftChild;

    NodeBST<K, T> rightChild;

    public NodeBST(K key, T data) {
        this.key = key;
        this.data = data;
    }

    public K getKey() {
        return key;
    }

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
                NodeBST<K, T> izq = findNodeInBinarySearchTree(key, root.leftChild);
                if (izq != null) {
                    return izq;
                }
            }else return null;

        }
        if(key.compareTo(root.getKey())>0) {
            if (root.rightChild != null) {
                NodeBST<K, T> dq = findNodeInBinarySearchTree(key, root.rightChild);
                if (dq != null) return dq;
            }
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
                NodeBST<K, T> izq = this.getLeftChild().findParentNodeInBinarySearchTree(key);
                if (izq != null) {
                    return izq;
                }
            }
            if (this.rightChild != null & key.compareTo(this.getKey())>0) {
                NodeBST<K, T> dq = this.rightChild.findParentNodeInBinarySearchTree(key);
                if (dq != null) {
                    return dq;
                }
            }

        }
        return null;
    }
    public NodeBST<K,T> intercambiar (int left)  throws WrongParameters// 0 para cambiar con derecho, 1 parra cambiar con izquierdo//
    {
        NodeBST<K,T> tempnode=null;
        if(left==0){
            tempnode=getRightChild();
        }
        else if(left==1){
            tempnode=getLeftChild();
        }
        else throw new WrongParameters();

        if (tempnode==null) return null;

        K key=tempnode.getKey();
        tempnode.setKey(this.getKey());
        T data=tempnode.getData();
        tempnode.setData(this.getData());
        this.setKey(key);
        this.setData(data);
        return tempnode;
    }


}