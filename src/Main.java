import Exceptions.ItemNotFoundException;
import TADs.BinaryTree.BSTImpl;
import TADs.BinaryTree.WrongKey;
import TADs.List.IlegalIndexException;
import TADs.List.List;
import TADs.Node.NodeBST;

import static org.junit.Assert.assertEquals;

public class Main {
    public static void main(String[] args) throws WrongKey, IlegalIndexException, ItemNotFoundException {
        BSTImpl<Integer,String> tree=new BSTImpl<>(4,"A");
        tree.insert((Integer) 2,"B");
        tree.insert((Integer) 3,"C");
        tree.insert((Integer) 1,"D");
        tree.insert((Integer) 5,"E");
        tree.insert((Integer) 7,"F");
        tree.delete(4);
        List<Integer> list=tree.levelOrder();
        tree.draw(list);
    }
}