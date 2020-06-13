import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();


        System.out.println(tree.allKeys());
        tree.remove("AAMNF3INWW");
        System.out.println();
        System.out.println(tree.allKeys());

        tree.getValues("E2JG90PA5F");
        System.out.println(tree.nextKey("A94V62IFM2"));
        System.out.println(tree.prevKey("FEN39FS20P"));

        tree.clear();
        System.out.println(tree.allKeys());

        System.out.println("==================================");
        Sequence sq=new Sequence();
        sq.add("A294V62IFM2", new Vehicle("A294V62IFM2"));
        sq.add("E2JG90PA5F", new Vehicle("E2JG90PA5F"));
        sq.add("AAMNfF3INWW", new Vehicle("AAMNfF3INWW"));
        sq.add("FEN39FS20P", new Vehicle("FEN39FS20P"));
        sq.add("IYN28FB25E", new Vehicle("IYN28FB25E"));
        sq.add("a", new Vehicle("a"));
        sq.add("b", new Vehicle("b"));




        ArrayList<String> list=sq.allKeys();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        sq.remove("A294V62IFM2");
        System.out.println();
        ArrayList<String> list2=sq.allKeys();
        for(int i=0;i<list2.size();i++){
            System.out.println(list2.get(i));
        }

        System.out.println("Sorted List of Accidents");
        sq.getValues("AAMNfF3INWW").addAccident(2020);
        sq.getValues("AAMNfF3INWW").addAccident(2002);
        sq.getValues("AAMNfF3INWW").addAccident(2006);
        System.out.println(sq.prevKey("FEN39FS20P"));

    }
}
