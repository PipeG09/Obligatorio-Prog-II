import Exceptions.ItemNotFoundException;
import TADs.BinaryTree.BSTImpl;
import TADs.BinaryTree.IllegalKeyException;
import TADs.BinaryTree.MyBinarySearchTree;
import TADs.HashTable.HashTableImpl;
import TADs.List.IllegalIndexException;
import TADs.List.List;
import TADs.List.ListImpl;
import TADs.Node.NodeBST;

import static org.junit.Assert.*;

public class Main {
    public static void main(String[] args) throws IllegalKeyException, ItemNotFoundException {
        HashTableImpl<Integer,String> hash=new HashTableImpl<>(10);
        hash.put(0,"A");
        hash.put(1,"B");
        hash.put(2,"C");
        hash.put(3,"D");
        hash.put(4,"E");
        hash.put(5,"F");
        hash.remove(3);
        boolean a =hash.contains(3);
        String aa= (hash.get(3));


        ;


    }
}