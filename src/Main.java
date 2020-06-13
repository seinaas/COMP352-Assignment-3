import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();

        tree.add("A94V62IFM2", new Vehicle("A94V62IFM2"));
        tree.add("E2JG90PA5F", new Vehicle("E2JG90PA5F"));
        tree.add("AAMNF3INWW", new Vehicle("AAMNF3INWW"));
        tree.add("FEN39FS20P", new Vehicle("FEN39FS20P"));
        tree.add("IYN28FB25E", new Vehicle("IYN28FB25E"));


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
    }
}
