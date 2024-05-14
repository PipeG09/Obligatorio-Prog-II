import Exceptions.ItemNotFoundException;
import TADs.BinaryTree.BSTImpl;
import TADs.BinaryTree.WrongKey;
import TADs.List.IlegalIndexException;
import TADs.List.List;
import TADs.List.ListImpl;
import TADs.Node.NodeBST;

import static org.junit.Assert.assertEquals;

public class Main {
    public static void main(String[] args) throws WrongKey, IlegalIndexException, ItemNotFoundException {
        List<Integer> lista = new ListImpl<>();
        List<Integer> lista2 = new ListImpl<>();
        lista.add(1);
        lista.add(2);
        lista2.add(3);
        lista2.add(4);
        lista.addAll(lista2);
        lista.remove(1);
        lista.print();

    }
}