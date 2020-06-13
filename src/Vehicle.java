import java.util.ArrayList;
import java.util.Collections;

/**
 * Vehicle Class containing all the information for each car.
 * They will then be stored inside an ADT.
 */
public class Vehicle {
    private String key;
    private String owner;
    private ArrayList<Integer> accidentsYear = new ArrayList<>();

    /**
     * Constructor for Vehicle with the key only
     * @param key
     */
    public Vehicle(String key) {
        this.key = key;
    }

    /**
     * Constructor for Vehicle with Owner/Key
     * @param key
     * @param owner
     */
    public Vehicle(String key, String owner) {
        this.key = key;
        this.owner = owner;
    }


    /**
     * Getter
     * @return key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * ToString
     * @return all information of the vehicle object
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "key='" + key + '\'' +
                ", owner='" + owner + '\'' +
                ", accidentsYear=" + accidentsYear +
                '}';
    }

    /**
     * Sorting of the Accidents List using Built-in Sorting Functions
     */
    public void sortAccidentsList() {
        Collections.sort(this.accidentsYear, Collections.reverseOrder());
    }

    /**
     * Adding Accident to the list
     * @param year
     */
    public void addAccident(int year) {
        accidentsYear.add(year);

    }

    /**
     * Getter for the list of accidents (that is sorted)
     * @return sort List
     */
    public ArrayList<Integer> getAccidentsYear() {
        sortAccidentsList();
        return accidentsYear;
    }
}

