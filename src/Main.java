import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int counter = 1850;
        CVR cvr = new CVR(100, 10);
        ArrayList<String> keys = cvr.generate(10);

        for (String key : keys) {
            Vehicle vehicle = new Vehicle(key, "abc-" + key);
            cvr.add(key, vehicle);
            vehicle.addAccident(counter++);
        }

        System.out.println(cvr.allKeys());
    }
}
