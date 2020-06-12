import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Stack;

public class CVR {

    private int threshold;
    private int keyLength;
    //Create AVL
    AVL avl = new AVL();
    //Create Sequence
    Sequence sequence = new Sequence();

    public CVR() {
        //Constructor
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
    public String generate() {
        Random r = new Random();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newKey = "";

        do {
            while (newKey.length() < keyLength) {
                newKey += chars.charAt(r.nextInt(chars.length()));
            }

            //verify if key exist.


        } while (sequence.contains(newKey));


        return newKey;
    }

    /*
    add an entry for the given key and value
     */
    public void add(String key, Vehicle v) {


        //modifier vehicle

    }

    /*
    return the values of the given key
     */
    public void getValues(String key) {

        //Here do a toString maybe to call a certain Vehicle?

    }

    /*
    return the key for the successor of key.
     */
    public String nextKey(String key) {
        return null;
    }


    /*
    return the key for the predecessor of key
     */
    public String prevKey(String key) {
        return null;
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

    }

    /*
    return all keys as a sorted sequence (lexicographic order)
     */
    public ArrayList<String> allKeys() {

        return null;
    }

    public boolean contains(String key) {


        return true;
    }

}
