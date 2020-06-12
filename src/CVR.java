import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;

public class CVR {

    private int threshold;
    private int keyLength;
    private int size = 0;
    //Create AVL
    AVL avl = new AVL();
    //Create Sequence
    Sequence sequence = new Sequence();

    public CVR() {
        threshold = 120;
        keyLength = 11;
    }

    public CVR(int threshold, int keyLength) throws Exception {
        setTreshold(threshold);
        setKeyLength(keyLength);
    }

    /*
        where 100 ≤ Threshold ≤ ~900,000 is an integer number
        that defines when a listing should be implemented with
        a data structure such as a Tree, Hash Table, AVL tree,
        binary tree, if its size is greater than or equal to
        value of Threshold. Otherwise it is implemented as a Sequence.
         */
    public void setTreshold(int threshold) throws Exception {

        if (threshold < 100 || threshold > 900000) {
            throw new Exception("Invalid threshold, your input must be between 100 and 900 000.");
        } else {
            this.threshold = threshold;
        }

    }

    /*
    where 10 ≤ Length ≤ 17 is an integer number that defines the fixed string length of keys.
     */
    public void setKeyLength(int n) throws Exception {
        if (n >= 10 && n <= 17) {
            keyLength = n;
        } else {
            throw new Exception("You cannot set the key length to " + n + "!");
        }
    }

    /*
    Randomly generates a sequence containing n new non-existing keys of alphanumeric characters.
     */
    public ArrayList<String> generate(int n) {
        Random r = new Random();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<String> listOfKeys = new ArrayList<>(n);

        for (int i = 0; i < n; ) {
            String newKey = "";

            do {
                while (newKey.length() < keyLength) {
                    newKey += chars.charAt(r.nextInt(chars.length()));
                }
                //verify if key exist.
            } while (listOfKeys.contains(newKey) || this.allKeys().contains(newKey));

        }

        return listOfKeys;

    }

    /*
    add an entry for the given key and value
     */
    public void add(String key, Vehicle v) {
        size++;

        //modifier vehicle

    }

    /*
    return the values of the given key
     */
    public Vehicle getValues(String key) {
        if (usingBigADT()){
            return avl.getVehicle(key); //To Do Seina
        } else {
            return sequence.getVehicle(key);
        }


        return null;
        //Here do a toString maybe to call a certain Vehicle?

    }

    /*
    return the key for the successor of key.
     */
    public String nextKey(String key) {
        if (usingBigADT()){
            return avl.nextKey(key);
        } else {
            return sequence.nextKey(key);
        }
    }


    /*
    return the key for the predecessor of key
     */
    public String prevKey(String key) {
        if (usingBigADT()){
            return avl.prevKey(key);
        } else {
            return sequence.prevKey(key);
        }
    }

    /*
    returns a sequence (sorted in reverse chronological order) of accidents(previously)
    registered with the given key (dates).
     */
    public Stack<Integer> prevAccidents(String key) {

        return null;
    }

    /*
    remove the entry for the given key
     */
    public void remove(String key) {
        if (usingBigADT()) {
            if (avl.remove(key))//ToDo: Make it boolean and make sure you can actually remove it
                size--;
        } else {
            if (sequence.remove(key))
                size--;
        }
    }

    /*
    return all keys as a sorted sequence (lexicographic order)
     */
    private ArrayList<String> allKeys() {
        if (usingBigADT()) {
            return avl.allKeys(); //seina will fix it.
        } else {
            return sequence.allKeys();
        }
    }


    public boolean usingBigADT() {
        return size >= threshold;
    }

}
