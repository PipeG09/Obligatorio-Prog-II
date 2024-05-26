package TADs_Tests;

import Exceptions.ItemNotFoundException;
import uy.edu.um.prog2.adt.BinaryTree.BSTImpl;
import uy.edu.um.prog2.adt.BinaryTree.IllegalKeyException;
import uy.edu.um.prog2.adt.Node.NodeBST;
import org.junit.Test;
import static org.junit.Assert.*;

public class BSTTest {

    @Test
    public void testInsert() throws IllegalKeyException {
        BSTImpl<Integer, String> tree = new BSTImpl<>(4, "A");
        tree.insert((Integer) 2, "B");
        tree.insert((Integer) 3, "C");
        tree.insert((Integer) 1, "D");
        tree.insert((Integer) 5, "E");
        tree.insert((Integer) 7, "F");
        NodeBST<Integer, String> root = tree.getRoot();
        assertEquals(root.getKey(), (Integer) 4);
        assertEquals(root.getLeftChild().getKey(), (Integer) 2);
        assertEquals(root.getLeftChild().getRightChild().getKey(), (Integer) 3);
        assertEquals(root.getLeftChild().getLeftChild().getKey(), (Integer) 1);
        assertEquals(root.getRightChild().getKey(), (Integer) 5);
        assertEquals(root.getRightChild().getRightChild().getKey(), (Integer) 7);
    }

    @Test
    public void insertIllegalKeyException() {
        BSTImpl<Integer, String> tree = new BSTImpl<>(4, "A");
        try {
            tree.insert(4,"n");
            fail("Should have thrown an exception");
        } catch (IllegalKeyException _) {} // if the exception is thrown, that's fine, so I don't do anything

        try {
            tree.insert(2,"f");
        } catch (IllegalKeyException _) {
            fail();
        }

        // We try to add an element with the same key
        try {
            tree.insert(2,"5");
            fail("Should have thrown an exception");
        } catch (IllegalKeyException _) {} // if the exception is thrown, that's fine, so I don't do anything

    }

    @Test
    public void testDeleteRoot() throws IllegalKeyException, ItemNotFoundException {
        BSTImpl<Integer, String> tree = new BSTImpl<>(4, "A");
        tree.insert((Integer) 2, "B");
        tree.insert((Integer) 3, "C");
        tree.insert((Integer) 1, "D");
        tree.insert((Integer) 5, "E");
        tree.insert((Integer) 7, "F");
        tree.delete((Integer) 4);
        NodeBST<Integer, String> root = tree.getRoot();
        assertEquals(root.getKey(), (Integer) 5);
        assertEquals(root.getLeftChild().getKey(), (Integer) 2);
        assertEquals(root.getLeftChild().getRightChild().getKey(), (Integer) 3);
        assertEquals(root.getLeftChild().getLeftChild().getKey(), (Integer) 1);
        assertEquals(root.getRightChild().getKey(), (Integer) 7);
    }

    @Test
    public void testDeleteLeaf() throws IllegalKeyException, ItemNotFoundException {
        BSTImpl<Integer, String> tree = new BSTImpl<>(4, "A");
        tree.insert((Integer) 2, "B");
        tree.insert((Integer) 3, "C");
        tree.insert((Integer) 1, "D");
        tree.insert((Integer) 5, "E");
        tree.insert((Integer) 7, "F");
        tree.delete((Integer) 3);
        NodeBST<Integer, String> root = tree.getRoot();
        assertEquals(root.getKey(), (Integer) 4);
        assertEquals(root.getLeftChild().getKey(), (Integer) 2);
        assertNull(root.getLeftChild().getRightChild());
        assertEquals(root.getLeftChild().getLeftChild().getKey(), (Integer) 1);
        assertEquals(root.getRightChild().getKey(), (Integer) 5);
        assertEquals(root.getRightChild().getRightChild().getKey(), (Integer) 7);
    }

    @Test
    public void testDelete() throws IllegalKeyException, ItemNotFoundException {
        BSTImpl<Integer, String> tree = new BSTImpl<>(7, "A");
        tree.insert((Integer) 3, "B");
        tree.insert((Integer) 2, "C");
        tree.insert((Integer) 6, "De");
        tree.insert((Integer) 4, "E");
        tree.insert((Integer) 5, "D");
        tree.insert((Integer) 8, "E");
        tree.delete((Integer) 3);
        NodeBST<Integer, String> root = tree.getRoot();
        assertEquals(root.getLeftChild().getKey(), (Integer) 4);
        assertEquals(root.getLeftChild().getRightChild().getKey(), (Integer) 6);
        assertEquals(root.getLeftChild().getRightChild().getLeftChild().getKey(), (Integer) 5);
        assertEquals(root.getLeftChild().getLeftChild().getKey(), (Integer) 2);
    }

    @Test
    public void testDelete2() throws IllegalKeyException, ItemNotFoundException {
        BSTImpl<Integer,String> tree=new BSTImpl<>(7,"A");
        tree.insert((Integer) 3,"B");
        tree.insert((Integer) 2,"C");
        tree.insert((Integer) 6,"De");
        tree.insert((Integer) 4,"E");
        tree.insert((Integer) 5,"D");
        tree.insert((Integer) 9,"E");
        tree.insert((Integer) 8,"F");
        tree.insert((Integer) 10,"G");
        tree.insert((Integer) 11,"H");
        tree.delete((Integer) 9);
        NodeBST<Integer, String> root = tree.getRoot();
        assertEquals(root.getRightChild().getKey(), (Integer) 10);
        assertEquals(root.getRightChild().getRightChild().getKey(),(Integer) 11);
        assertEquals(root.getRightChild().getLeftChild().getKey(), (Integer) 8);
    }

    @Test
    public void countsTests() throws IllegalKeyException {
        BSTImpl<Integer,String> tree=new BSTImpl<>(7,"A");
        tree.insert((Integer) 3,"B");
        tree.insert((Integer) 2,"C");
        tree.insert((Integer) 6,"De");
        tree.insert((Integer) 4,"E");
        tree.insert((Integer) 5,"D");
        tree.insert((Integer) 9,"E");
        tree.insert((Integer) 8,"F");
        tree.insert((Integer) 10,"G");
        tree.insert((Integer) 11,"H");
        int leafs= tree.countLeaf();
        assertEquals(leafs,4);
        assertEquals(tree.countCompleteElements(),3);
    }
}
