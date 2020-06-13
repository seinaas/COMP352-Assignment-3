import java.util.ArrayList;
import java.util.Collections;


public class Vehicle {
    private String key;
    private String owner;
    private ArrayList<Integer> accidentsYear=new ArrayList<>();

    public Vehicle(String key){
        this.key=key;
    }

    public Vehicle(String key, String owner) {
        this.key = key;
        this.owner = owner;
    }


    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "key='" + key + '\'' +
                '}';
    public void sortAccidentsList(){
        Collections.sort(this.accidentsYear, Collections.reverseOrder());
    }

    public void addAccident(int year){
        accidentsYear.add(year);
        sortAccidentsList();
    }

    public ArrayList<Integer> getAccidentsYear() {
        return accidentsYear;
    }
}
