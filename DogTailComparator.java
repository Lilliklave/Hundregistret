// Liliana Klavebäck Martinez likl4662@SU.SE

import java.util.Comparator;

public class DogTailComparator implements Comparator<Dog> {
    
    public int compare(Dog first, Dog secound){
        if(first.getTailLength() < secound.getTailLength())
        return -1;

        if(first.getTailLength() > secound.getTailLength())
        return 1;

        return 0;

    }
}
