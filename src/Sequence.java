import java.util.ArrayList;

/**
 * Sequence ADT that contains all the methods to work with the data
 * Built-on top of an ArrayList with methods from LinkedList
 */
public class Sequence {


    public ArrayList<Vehicle> seqVehicles;

    /**
     * Constructor for the Sequence
     */
    public Sequence() {
        seqVehicles = new ArrayList<>();
    }

    /**
     * Binary Search to Find a Key inside the sorted list.
     * @param key
     * @param low
     * @param high
     * @return integer containing the index (or -1 if not there)
     */
    public int binarySearch(String key, int low, int high) {
        if (high >= low) {
            int middle = low + (high - low) / 2;

            if (this.seqVehicles.get(middle).getKey().compareToIgnoreCase(key) == 0) {
                return middle;
            } else if (this.seqVehicles.get(middle).getKey().compareToIgnoreCase(key) > 0) {
                return binarySearch(key, low, high - 1);
            } else {
                return binarySearch(key, low + 1, high);
            }

        }
        return -1;
    }


    /**
     * Add a (key,value) inside the Sequence
     * @param key
     * @param v Vehicle
     */
    public void add(String key, Vehicle v) {

        if (binarySearch(key, 0, this.seqVehicles.size() - 1) != -1) {
            System.out.println("Duplicate KEY");
            System.exit(0);
        }

        if (seqVehicles.isEmpty()) {
            seqVehicles.add(v);
        } else {
            int j = seqVehicles.size();
            for (; j > 0; j--) {
                if (key.compareToIgnoreCase(seqVehicles.get(j - 1).getKey()) > 0) {
                    break;
                }
            }
            seqVehicles.add(j, v);
        }
    }

    /**
     * Remove Element (from a key)
     * @param key
     * @return True if remove (ok), false otherwise
     */
    public boolean remove(String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index == -1){
           return false;
        } else {
            this.seqVehicles.remove(index);
            return true;
        }
    }

    /**
     * Getter for Values
     * @param key
     * @return Vehicle with the key.
     */
    public Vehicle getValues(String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index == -1){
            System.out.println("Error: No Vehicle with this key.");
        }
        return this.seqVehicles.get(index);
    }

    /**
     * Next Key Element
     * @param key
     * @return String containing the key of the next element in the list
     */
    public String nextKey(String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index ==-1 || index==seqVehicles.size()-1){
            System.out.println("Error: No Vehicle with this key.");
        }
        return this.seqVehicles.get(index+1).getKey();
    }


    /**
     * Previous Key
     * @param key
     * @return previous key string
     */
    public String prevKey(String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index ==-1 || index==0){
            System.out.println("Error: No Vehicle with this key.");
        }
        return this.seqVehicles.get(index-1).getKey();
    }

    /**
     * Function that will return an arraylist containing all the keys inside the list
     * @return arraylist of keys.
     */
    public ArrayList<String> allKeys(){
        ArrayList<String> keysArray= new ArrayList<>();
        for (int i = 0; i < this.seqVehicles.size(); i++) {
            keysArray.add(seqVehicles.get(i).getKey());
        }
        return keysArray;
    }

    /**
     * Clear the arraylist containing all the information. (using an array-list method)
     */
    public void clear() {
        seqVehicles.clear();
    }

}
