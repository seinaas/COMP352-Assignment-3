import java.util.Hashtable;
import java.util.Random;

public class CVR {

    private int threshold;
    private int keyLength;
    //Create HashTable
    private Hashtable<String, Object> hashCVR = new Hashtable<String, Object>();
    //Create Sequence


    public CVR (){
        //Constructor
    }

    public void setTreshold(int threshold) throws Exception {

        if (threshold  < 0){
            throw new Exception("Invalid threshold, your input must be bigger than 0.");
        } else {
            this.threshold=threshold;
        }

    }

    public void setKeyLength(int n) {
        if (n>=10 && n <= 17) {
            keyLength = n;
        } else {
            System.out.println("You cannot set the key length to " + n + "!");
        }
    }

    public String generate() {
        Random r = new Random();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newKey = "";



        return newKey;
    }


    public void add (String key, Vehicle v){





        //modifier vehicle

    }

    public boolean contains (String key){


        return true;
    }

}
