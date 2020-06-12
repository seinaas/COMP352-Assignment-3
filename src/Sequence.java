import java.util.ArrayList;

public class Sequence {


    public ArrayList<Vehicle> seqVehicles;

    public Sequence() {
        seqVehicles = new ArrayList<>();
    }

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

    public boolean remove(String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index == -1){
           return false;
        } else {
            this.seqVehicles.remove(index);
            return true;
        }
    }

    public boolean contains(String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index == -1){
            return false;
        } else {
            return true;
        }
    }

    public Vehicle getVehicle (String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index == -1){
            System.out.println("Error: No Vehicle with this key.");
        }
        return this.seqVehicles.get(index);
    }

    public String nextKey(String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index ==-1 || index==seqVehicles.size()-1){
            System.out.println("Error: No Vehicle with this key.");
        }
        return this.seqVehicles.get(index+1).getKey();
    }


    public String prevKey(String key){
        int index=binarySearch(key,0, this.seqVehicles.size()-1);

        if (index ==-1 || index==0){
            System.out.println("Error: No Vehicle with this key.");
        }
        return this.seqVehicles.get(index-1).getKey();
    }

    public ArrayList<String> allKeys(){
        ArrayList<String> keysArray= new ArrayList<>();
        for (int i = 0; i < this.seqVehicles.size(); i++) {
            keysArray.add(seqVehicles.get(i).getKey());
        }
        return keysArray;
    }

    public ArrayList<Vehicle> allVehicles(){
        ArrayList<Vehicle> vArray=new ArrayList<>();
        for (int i = 0; i < this.seqVehicles.size(); i++) {
            vArray.add(seqVehicles.get(i));

        }
        return vArray;
    }

    public void clear() {
        seqVehicles.clear();
    }

}
