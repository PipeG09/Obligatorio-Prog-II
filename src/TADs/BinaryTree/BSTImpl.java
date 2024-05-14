package TADs.BinaryTree;


import Exceptions.*;

import TADs.List.List;
import TADs.Node.NodeBST;

import static TADs.Node.NodeBST.findNodeInBinarySearchTree;

public class BSTImpl<K extends Comparable<K>,T> implements MyBinarySearchTree<K,T>{
    private NodeBST<K,T> root;

    public BSTImpl(K key, T value) {
        this.root = new NodeBST<>(key, value);
    }

    public NodeBST<K, T> getRoot() {
        return root;
    }

   @Override
    public T find(K key) {
        NodeBST<K,T> node=findNodeInBinarySearchTree(key,getRoot());
        if(node==null) return null;
        else return node.getData();
    }

    @Override
    public void insert(K key, T data) throws WrongKey {
        // Nos fijamos que l nodo no exista ya
        NodeBST<K,T> node=findNodeInBinarySearchTree(key,root);
        if(node!=null) throw new WrongKey();
        node = getRoot();
        boolean added = false;
        while (!added){
            if (key.compareTo(node.getKey())<0){
                if(node.getLeftChild()!=null){
                    node=node.getLeftChild();
                }
                else {
                    node.setLeftChild( new NodeBST<>(key,data) );
                    added = true;
                }
            }
            else if (key.compareTo(node.getKey())>0){
                if(node.getRightChild()!=null) {
                    node = node.getRightChild();
                }
                else {
                    node.setRightChild(new NodeBST<>(key,data) );
                    added = true;
                }
            }
            else throw new WrongKey();
        }
    }

    @Override
    public void delete(K key) throws ItemNotFoundException  /* esta exception no salta*/ {
        NodeBST<K,T> node=findNodeInBinarySearchTree(key,root);
        if(node==null) throw new ItemNotFoundException();

        // Busco el menor nodo del subarbol derecho
        NodeBST<K,T> tempnode=node;
        NodeBST<K,T> tempnode1=node.getRightChild();
        if (tempnode1 == null) { //el nodo no tiene hijo derechp

        }
        while (tempnode1.getLeftChild()!=null) {
            tempnode=tempnode1; // nodo n
            tempnode1=tempnode.getLeftChild(); //nodo n+1, hijo de nodo n
        }

        if (tempnode1!=null & tempnode==node){ // el nodo a borrar solo tiene un hijo derecho, osea no bajo nunca a la izq
            node.setData(tempnode1.getData());
            node.setKey(tempnode1.getKey());
            node.setRightChild(null);
        }
        tempnode.setLeftChild(tempnode1.getRightChild()); // Ajusto hijos del nodo que voy a subir
        // Subo el nodo al lugar del que quiero eliminar
        node.setKey(tempnode1.getKey());
        node.setData(tempnode1.getData());
    }

    @Override
    public int size() {
        return root.size();
    }

    @Override
    public int countLeaf() {
        return root.countLeafs();
    }

    @Override
    public int countCompleteElements() {
        return root.countCompleteElements();
    }

    @Override
    public List<K> inOrder() {
        return root.inOrderFrom();
    }

    @Override
    public List<K> preOrder() {
        return root.preOrderFrom();
    }

    @Override
    public List<K> postOrder() {
        return root.postOrderFrom();
    }
}
