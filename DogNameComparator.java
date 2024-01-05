// Liliana Klavebäck Martinez likl4662@SU.SE

import java.util.Comparator;

public class DogNameComparator implements Comparator<Dog> {
    
    public int compare(Dog firstName, Dog secoundName){
        return firstName.getName().compareTo(secoundName.getName());

    }

}
