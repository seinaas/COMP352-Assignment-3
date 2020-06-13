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

    /**
     * Constructor of the CVR (Default-Values)
     */
    public CVR() {
        threshold = 120;
        keyLength = 11;
    }

    /**
     * Constructor with Parameters
     *
     * @param threshold Size where we change ADT
     * @param keyLength Length of each key
     */
    public CVR(int threshold, int keyLength) {
        setThreshold(threshold);
        setKeyLength(keyLength);
    }


    /**
     * Where 100 ≤ Threshold ≤ ~900,000 is an integer number
     * that defines when a listing should be implemented with
     * a data structure such as a Tree, Hash Table, AVL tree,
     * binary tree, if its size is greater than or equal to
     * value of Threshold. Otherwise it is implemented as a Sequence.
     *
     * @param threshold
     */
    public void setThreshold(int threshold) {

        if (threshold < 100 || threshold > 900000) {
            System.out.println("Invalid threshold, your input must be between 100 and 900 000.");
        } else {
            this.threshold = threshold;
        }

    }


    /**
     * where 10 ≤ Length ≤ 17 is an integer number that defines the fixed string length of keys.
     *
     * @param n
     */
    public void setKeyLength(int n) {
        if (n >= 10 && n <= 17) {
            keyLength = n;
        } else {
            System.out.println("You cannot set the key length to " + n + "!");
        }
    }

    /**
     * Randomly generates a sequence containing n new non-existing keys of alphanumeric characters.
     *
     * @param n (nb of elements)
     * @return List of Keys
     */
    public ArrayList<String> generate(int n) {
        Random r = new Random();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<String> listOfKeys = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
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


    /**
     * Add an entry for the given key and value
     *
     * @param key
     * @param v   Vehicle V
     */
    public void add(String key, Vehicle v) {

        boolean wasUsingBig = usingBigADT();
        size++;

        if (wasUsingBig != usingBigADT()) {
            convertADT();
        }

        if (usingBigADT()) {
            avl.add(key, v);
        } else {
            sequence.add(key, v);
        }
    }

    /**
     * Return the values (vehicle Object) of the given key
     *
     * @param key
     * @return vehicle Object (of the key)
     */
    public Vehicle getValues(String key) {
        if (usingBigADT()) {
            return avl.getValues(key);
        } else {
            return sequence.getValues(key);
        }

        //Here do a toString maybe to call a certain Vehicle?

    }

    /**
     * return the key for the successor of key.
     *
     * @param key
     * @return key of successor
     */
    public String nextKey(String key) {
        if (usingBigADT()) {
            return avl.nextKey(key);
        } else {
            return sequence.nextKey(key);
        }
    }

    /**
     * Return the key for the predecessor of key
     *
     * @param key
     * @return key of predecessor
     */
    public String prevKey(String key) {
        if (usingBigADT()) {
            return avl.prevKey(key);
        } else {
            return sequence.prevKey(key);
        }
    }


    /**
     * returns a sequence (sorted in reverse chronological order) of accidents(previously)
     * registered with the given key (dates).
     *
     * @param key
     * @return sequence sorted of accidents from a key
     */
    public ArrayList<Integer> prevAccidents(String key) {
        if (usingBigADT()) {
            return avl.getValues(key).getAccidentsYear();
        } else {
            return sequence.getValues(key).getAccidentsYear();
        }

    }

    /**
     * Remove the entry for the given key
     *
     * @param key
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

    /**
     * Return all keys as a sorted sequence (lexicographic order)
     *
     * @return list of all keys sorted
     */
    public ArrayList<String> allKeys() {
        if (usingBigADT()) {
            return avl.allKeys();
        } else {
            return sequence.allKeys();
        }
    }

    /**
     * Convert Sequence to AVL if size > Threshold
     */
    private void convertToAVL() {
        ArrayList<String> keysList = sequence.allKeys();
        for (String k : keysList) {
            avl.add(k, sequence.getValues(k));
        }
        sequence.clear();
    }

    /**
     * Convert AVL to Sequence if size < Threshold
     */
    private void convertToSequence() {
        ArrayList<String> keysList = avl.allKeys();
        for (String k : keysList) {
            sequence.add(k, avl.getValues(k));
        }
        avl.clear();
    }

    /**
     * Convert ADT depending on the nb of elements inside, when we add/remove
     */
    private void convertADT() {
        ArrayList<String> keysList;
        if (avl.allKeys().size() == 0) {
            convertToAVL();
        } else {
            convertToSequence();
        }
    }

    /**
     * Method that will decide which ADT we are using inside our function
     * @return true if we are using AVL, false otherwise
     */
    private boolean usingBigADT() {
        return size >= threshold;
    }

    /**
     * Method to return the name of the ADT we are using
     * @return string containing type of ADT
     */
    public String type() {
        if (usingBigADT()) {
            return "AVL";
        } else {
            return "Sequence";
        }
    }

    /**
     * Getter for the Size
     * @return nb of elements inside the list
     */
    public int getSize() {
        return size;
    }
}
