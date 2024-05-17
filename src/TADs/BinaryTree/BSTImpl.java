package TADs.BinaryTree;


import Exceptions.*;

import TADs.List.List;
import TADs.List.ListImpl;
import TADs.Node.NodeBST;
import TADs.Queue.EmptyQueueException;
import TADs.Queue.Queue;
import TADs.Queue.QueueImpl;

import static TADs.Node.NodeBST.findNodeInBinarySearchTree;

public class BSTImpl<K extends Comparable<K>,T> implements MyBinarySearchTree<K,T>{

    private final NodeBST<K,T> root;

    public BSTImpl(K key, T value) {
        this.root = new NodeBST<>(key, value);
    }

    public NodeBST<K, T> getRoot() {
        return root;
    }

   @Override
    public T find(K key) {
        NodeBST<K,T> node = findNodeInBinarySearchTree(key,getRoot());
        if (node == null) return null;
        else return node.getData();
    }

    @Override
    public void insert(K key, T data) throws IllegalKeyException {
        // We check that the node does not already exist.
        NodeBST<K,T> node = findNodeInBinarySearchTree(key,root);

        if (node != null) throw new IllegalKeyException();
        node = getRoot();
        boolean added = false;

        while (!added) {
            if (key.compareTo(node.getKey()) < 0){
                if(node.getLeftChild() != null){
                    node=node.getLeftChild();
                }
                else {
                    node.setLeftChild(new NodeBST<>(key,data));
                    added = true;
                }
            }
            else if (key.compareTo(node.getKey()) > 0){
                if(node.getRightChild() != null) {
                    node = node.getRightChild();
                }
                else {
                    node.setRightChild(new NodeBST<>(key,data));
                    added = true;
                }
            }
            else throw new IllegalKeyException();
        }
    }

    @Override
    public void delete(K key) throws ItemNotFoundException  /* This exception is not thrown */ {
        NodeBST<K,T> node = findNodeInBinarySearchTree(key,root);
        if (node == null) throw new ItemNotFoundException();

        // We look for the smallest node in the right subtree.
        NodeBST<K,T> tempNode = node;
        NodeBST<K,T> tempNode1 = node.getRightChild();

        if (tempNode1 == null) { //el nodo no tiene hijo derechp
            NodeBST<K,T> parent = root.findParentNodeInBinarySearchTree(key);
            if (parent.getLeftChild() == node) {
                parent.setLeftChild(node.getLeftChild());
                return;
            }
            if (parent.getRightChild() == node) {
                parent.setRightChild(node.getRightChild());
                return;
            }
        }

        while (tempNode1.getLeftChild() != null) {
            tempNode = tempNode1; // node n
            tempNode1 = tempNode.getLeftChild(); // node n+1, child of the node n
        }

        /* The node to delete has a right child, and this child has no further nodes,
        meaning it never goes down to the left. */

        if (tempNode == node) {
            node.setData(tempNode1.getData());
            node.setKey(tempNode1.getKey());
            node.setRightChild(tempNode1.getRightChild());
        }

        else {
            // We adjust the child of the node that we are going to promote.
            tempNode.setLeftChild(tempNode1.getRightChild());
            //We promote the node to the place of the one we want to delete
            node.setKey(tempNode1.getKey());
            node.setData(tempNode1.getData());
        }
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

   // Function to draw the tree
   @Override
   public void draw() {
       NodeBST<K,T> root = this.root;
       if (root == null)
           return;

       draw(root.getRightChild(), "", true);
       System.out.println(root.getKey());
       draw(root.getLeftChild(), "", false);
   }

    // Auxiliary function to recursively draw a subtree
    private void draw(NodeBST<K,T> node, String prefix, boolean isRight) {
        if (node == null)
            return;

        draw(node.getRightChild(), prefix + (isRight ? "      " : " │    "), true);
        System.out.println(prefix + (isRight ? " ┌── " : " └── ") + node.getKey());
        draw(node.getLeftChild(), prefix + (isRight ? " │    " : "      "), false);
    }

    public List<K> levelOrder() {
        Queue<NodeBST<K,T>> queue = new QueueImpl<>();
        List<K> list = new ListImpl<>();
        queue.enqueue(getRoot());

        while (!queue.isEmpty()) {
            try {
                NodeBST<K,T> temp = queue.dequeue();
                list.add(temp.getKey());
                if(temp.getLeftChild() != null) queue.enqueue(temp.getLeftChild());
                if(temp.getRightChild() != null) queue.enqueue(temp.getRightChild());
            } catch (EmptyQueueException e) {
                break;
            }
        }
        return list;
    }
}
