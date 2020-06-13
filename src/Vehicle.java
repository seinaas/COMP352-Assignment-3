import java.util.Stack;

public class Vehicle {
    private String key;
    public Stack<Integer> accidentsYear = new Stack<Integer>();

    public Vehicle(String key){
        this.key=key;
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "key='" + key + '\'' +
                '}';
    }
}
