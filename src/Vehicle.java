import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class Vehicle {
    private String key;
    private String owner;
    private ArrayList<Integer> accidentsYear=new ArrayList<>();

    public Vehicle(String key){
        this.key=key;
    }

    public Vehicle(String key, String owner, ArrayList<Integer> accidentsYear) {
        this.key = key;
        this.owner = owner;
        this.accidentsYear = accidentsYear;
    }

    public String getKey() {
        return this.key;
    }

    public void sortAccidentsList(){
        Collections.sort(this.accidentsYear, Collections.reverseOrder());
    }

    public void addAccidents(int year){
        accidentsYear.add(year);
        sortAccidentsList();
    }

    public ArrayList<Integer> getAccidentsYear() {
        return accidentsYear;
    }
}
