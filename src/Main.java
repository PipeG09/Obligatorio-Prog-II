import Exceptions.ItemNotFoundException;
import TADs.BinaryTree.BSTImpl;
import TADs.BinaryTree.MyBinarySearchTree;
import TADs.BinaryTree.WrongKey;
import TADs.List.IlegalIndexException;
import TADs.List.List;
import TADs.List.ListImpl;
import TADs.Node.NodeBST;

import static org.junit.Assert.assertEquals;

public class Main {
    public static void main(String[] args) throws WrongKey, IlegalIndexException, ItemNotFoundException {
        MyBinarySearchTree<Integer,String> tree=new BSTImpl<>(7,"A");
        tree.insert((Integer) 3,"B");
        tree.insert((Integer) 2,"C");
        tree.insert((Integer) 6,"De");
        tree.insert((Integer) 4,"E");
        tree.insert((Integer) 5,"D");
        tree.insert((Integer) 9,"E");
        tree.insert((Integer) 8,"F");
        tree.insert((Integer) 11,"G");
        tree.insert((Integer) 12,"H");
        tree.insert((Integer) 10,"I");
        tree.delete((Integer) 7);
        tree.draw();


    }
}