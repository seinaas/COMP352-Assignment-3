import java.util.Stack;

public class Vehicle {
    private String key;
    private String owner;
    private Stack<Integer> accidentsYear = new Stack<Integer>();

    public Vehicle(String key){
        this.key=key;
    }

    public Vehicle(String key, String owner, Stack<Integer> accidentsYear) {
        this.key = key;
        this.owner = owner;
        this.accidentsYear = accidentsYear;
    }

    public String getKey() {
        return this.key;
    }
}
