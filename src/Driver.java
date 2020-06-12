public class Driver {
    public static void main(String[] args) {
        AVL tree = new AVL();

        tree.add("A94V62IFM2", new Vehicle("A94V62IFM2"));
        tree.add("E2JG90PA5F", new Vehicle("E2JG90PA5F"));
        tree.add("AAMNF3INWW", new Vehicle("AAMNF3INWW"));
        tree.add("FEN39FS20P", new Vehicle("FEN39FS20P"));
        tree.add("IYN28FB25E", new Vehicle("IYN28FB25E"));


        tree.allKeys();
        tree.remove("AAMNF3INWW");
        System.out.println();
        tree.allKeys();
    }
}
