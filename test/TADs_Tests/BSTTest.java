package TADs_Tests;

import Exceptions.ItemNotFoundException;
import TADs.BinaryTree.BSTImpl;
import TADs.BinaryTree.WrongKey;
import TADs.Node.NodeBST;
import org.junit.Test;
import static org.junit.Assert.*;

public class BSTTest {
    @Test
    public void testInsert() throws WrongKey {
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
    public void insertWrongKey()   {
        BSTImpl<Integer, String> tree = new BSTImpl<>(4, "A");
        try {
            tree.insert(4,"n");
            fail("Deberia saltar Exception");
        } catch (WrongKey e) {
                // si salta la excpetion esta bien por ende no hago nada
        }

        try {
            tree.insert(2,"f");
        } catch (WrongKey e) {
            fail();
        }

        // intento agregar un elemento con la misma key
        try {
            tree.insert(2,"5");
            fail("Deberia saltar Exception");
        } catch (WrongKey e) {
            //;
        }

    }

    @Test
    public void testDeleteRoot() throws WrongKey, ItemNotFoundException {
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
    public void testDeleteLeaf() throws WrongKey, ItemNotFoundException {
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
    public void testDelete() throws WrongKey, ItemNotFoundException {
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
    public void testDelete2() throws WrongKey, ItemNotFoundException {
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
    public void countsTests() throws WrongKey {
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
