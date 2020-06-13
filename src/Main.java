import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;

/**
 * Question 1: Discuss how both the time and space complexity change for each of the methods above if
 * the underlying structure of your CVR is an array or a linked list?
 * ====
 *
 */

/**
 * Question 2: Write a detailed report about your design decisions and specification of your CVR ADT
 * including a rationale and comments about assumptions and semantics.
 * ====
 */

/**
 * Question 3: Demonstrate the functionality of your CVR by documenting at least 10 different but
 * representative data sets. These examples should demonstrate all cases of your CVR ADT functionality
 * (e.g., all operations of your ADT for different sizes).
 * ====
 */
public class Main {
    public static void main(String[] args) {
        int counter = 1850;
        CVR cvr = new CVR(100, 10);

        for (String key : cvr.generate(99)) {
            Vehicle vehicle = new Vehicle(key, "abc-" + key);
            cvr.add(key, vehicle);
            vehicle.addAccident(counter++);
            vehicle.addAccident(counter++);
            vehicle.addAccident(counter++);

        }
        System.out.println("Current Size -> "+cvr.getSize());

        System.out.println("=======================================");
        System.out.println("Testing Previous/Next (Sequence) for the element #4:");
        System.out.println(cvr.allKeys());
        System.out.println(cvr.prevKey(cvr.allKeys().get(3)));
        System.out.println(cvr.nextKey(cvr.allKeys().get(3)));
        System.out.println("=======================================");


        System.out.println("Displaying ADT:\n"+cvr.allKeys());
        System.out.println("ADT Type: "+cvr.type());
        System.out.println("Current Size -> "+cvr.getSize());
        System.out.println("Let's add 1 element, will be equal to Threshold");
        String keyTemp=cvr.generate(1).get(0);
        cvr.add(keyTemp, new Vehicle(keyTemp, "SEINA"));
        System.out.println("The key: "+keyTemp+" was added inside the ADT.");
        System.out.println("Current Size -> "+cvr.getSize());
        System.out.println("Displaying ADT:\n"+cvr.allKeys());
        System.out.println("ADT Type: "+cvr.type());
        System.out.println("Removing 1 key will put us below the Threshold -> switch back to Sequence");
        cvr.remove(keyTemp);
        System.out.println("ADT Type: "+cvr.type());
        System.out.println("Displaying ADT:\n"+cvr.allKeys());
        System.out.println("==========================================================================");
        System.out.println("Displaying a list of Previous Accidents for the element #96");
        System.out.println(cvr.getValues(cvr.allKeys().get(95)));
        System.out.println(cvr.prevAccidents(cvr.allKeys().get(95)));
        System.out.println("==========================================================================");
        System.out.println("Current Size -> "+cvr.getSize());
        String keyTemp2=cvr.generate(1).get(0);
        cvr.add(keyTemp, new Vehicle(keyTemp, "Martin"));
        System.out.println("We are now adding a new element, and switching back to -> "+cvr.type());
        System.out.println("Current Size -> "+cvr.getSize());
        System.out.println("==========================================================================");
        System.out.println("Testing Previous/Next (AVL) for the element #4:");
        System.out.println(cvr.allKeys());
        System.out.println("Previous= "+cvr.prevKey(cvr.allKeys().get(3)));
        System.out.println("Next= "+cvr.nextKey(cvr.allKeys().get(3)));
        System.out.println("==========================================================================");
        System.out.println("Displaying a list of Previous Accidents for the element #1");
        System.out.println(cvr.getValues(cvr.allKeys().get(0)));
        System.out.println(cvr.prevAccidents(cvr.allKeys().get(0)));
        System.out.println("==========================================================================");
        System.out.println(cvr.allKeys());
        System.out.println("Current Size: "+cvr.getSize()+"Removing 3 elements (going back to Sequence)");
        cvr.remove(cvr.allKeys().get(0));
        cvr.remove(cvr.allKeys().get(0));
        cvr.remove(cvr.allKeys().get(0));
        System.out.println("New size: "+cvr.getSize()+" Here is the new ADT - "+cvr.type());
        System.out.println(cvr.allKeys());

    }
}
