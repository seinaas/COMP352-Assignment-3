import java.util.ArrayList;
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

    public CVR(int threshold, int keyLength) {
        setThreshold(threshold);
        setKeyLength(keyLength);
    }

    /*
    where 100 ≤ Threshold ≤ ~900,000 is an integer number
    that defines when a listing should be implemented with
    a data structure such as a Tree, Hash Table, AVL tree,
    binary tree, if its size is greater than or equal to
    value of Threshold. Otherwise it is implemented as a Sequence.
     */
    public void setThreshold(int threshold) {

        if (threshold < 100 || threshold > 900000) {
            System.out.println("Invalid threshold, your input must be between 100 and 900 000.");
        } else {
            this.threshold = threshold;
        }

    }

    /*
    where 10 ≤ Length ≤ 17 is an integer number that defines the fixed string length of keys.
     */
    public void setKeyLength(int n) {
        if (n >= 10 && n <= 17) {
            keyLength = n;
        } else {
            System.out.println("You cannot set the key length to " + n + "!");
        }
    }

    /*
    Randomly generates a sequence containing n new non-existing keys of alphanumeric characters.
     */
    public ArrayList<String> generate(int n) {
        Random r = new Random();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<String> listOfKeys = new ArrayList<>(n);

        for (int i = 0; i < n; i++ ) {
            String newKey = "";

            do {

                while (newKey.length() < keyLength) {
                    newKey += chars.charAt(r.nextInt(chars.length()));
                }

                //verify if key exist.
            } while (listOfKeys.contains(newKey) || this.allKeys().contains(newKey));
            listOfKeys.add(newKey);

        }

        return listOfKeys;

    }

    /*
    add an entry for the given key and value
     */
    public void add(String key, Vehicle v) {

        boolean wasUsingBig=usingBigADT();
        size++;

        if (wasUsingBig != usingBigADT()){
            convertADT();
        }

        if (usingBigADT()) {
            avl.add(key, v);
        } else {
            sequence.add(key,v);
        }
    }


    /*
    return the values of the given key
     */
    public Vehicle getValues(String key) {
        if (usingBigADT()) {
            return avl.getValues(key);
        } else {
            return sequence.getValues(key);
        }

        //Here do a toString maybe to call a certain Vehicle?

    }

    /*
    return the key for the successor of key.
     */
    public String nextKey(String key) {
        if (usingBigADT()) {
            return avl.nextKey(key);
        } else {
            return sequence.nextKey(key);
        }
    }


    /*
    return the key for the predecessor of key
     */
    public String prevKey(String key) {
        if (usingBigADT()) {
            return avl.prevKey(key);
        } else {
            return sequence.prevKey(key);
        }
    }

    /*
    returns a sequence (sorted in reverse chronological order) of accidents(previously)
    registered with the given key (dates).
     */
    public ArrayList<Integer> prevAccidents(String key) {
        if (usingBigADT()) {
            return avl.getValues(key).getAccidentsYear();
        } else {
            return sequence.getValues(key).getAccidentsYear();
        }

    }

    /*
    remove the entry for the given key
     */
    public void remove(String key) {
        boolean wasUsingBig = usingBigADT();
        if (usingBigADT()) {
            if (avl.remove(key))
                size--;
        } else {
            if (sequence.remove(key))
                size--;
        }

        if (wasUsingBig != usingBigADT()) {
            convertADT();
        }
    }

    /*
    return all keys as a sorted sequence (lexicographic order)
     */
    public ArrayList<String> allKeys() {
        if (usingBigADT()) {
            return avl.allKeys();
        } else {
            return sequence.allKeys();
        }
    }


    private void convertToAVL() {
        ArrayList<String> keysList = sequence.allKeys();
        for (String k : keysList) {
            avl.add(k, sequence.getValues(k));
        }
        sequence.clear();
    }

    private void convertToSequence() {
        ArrayList<String> keysList = avl.allKeys();
        for (String k : keysList) {
            sequence.add(k, avl.getValues(k));
        }
        avl.clear();
    }
    
    private void convertADT(){
        ArrayList<String> keysList;
        if (avl.allKeys().size() == 0) {
            convertToAVL();
        } else {
            convertToSequence();
        }
    }

    private boolean usingBigADT() {
        return size >= threshold;
    }

    public String type() {
        if (usingBigADT()) {
            return "AVL";
        } else {
            return "Sequence";
        }
    }

    public int getSize() {
        return size;
    }
}
