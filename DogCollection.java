//Liliana Klavebäck Martinez likl4662@SU.SE

import java.util.ArrayList;
import java.util.Iterator;

public class DogCollection {

    private ArrayList<Dog> doglist = new ArrayList<>();
    
    public boolean addDog(Dog dog){
        if(!containsDog(dog.getName())){
        doglist.add(dog);
        return true;    
        }
        return false;
        
    }

    //Tar bort en hund fr˚an samlingen om denna existerar. Returnerar om hunden togs bort eller inte.

    public boolean removeDog(String name){
        Dog removeDog = this.getDog(name);
        if(removeDog != null && removeDog.getOwner() == null){
            doglist.remove(removeDog);
            return true;
        }else{
            return false;
        }
    }

    public boolean removeDog(Dog dog){
        if(dog != null && doglist.contains(dog) && dog.getOwner()== null){
            doglist.remove(dog);
            return true;
        }else{
            return false;
        }
    }



    public boolean containsDog(String name){
        
            if(this.getDog(name) != null){
                return true;
            }
            
        
            return false;
    }

    public boolean containsDog(Dog dogObject){
        if(this.getDog(dogObject.getName()) != null){
                return true;
            }
            
        
            return false;
    }

    public Dog getDog (String name){
        for(Dog d : doglist){
            if(d.getName().equalsIgnoreCase(name)){
                return d;
            }
        }
        return null;

    }

    public ArrayList<Dog> getDogs(){
        ArrayList<Dog> copyOfList = new ArrayList<>(doglist);
        DogNameComparator dogNameComparator = new DogNameComparator();
        DogSorter.sortDogs(dogNameComparator , copyOfList);
        return copyOfList;
        
    }


    public ArrayList<Dog> getDogsWithMinimumTailLength(double minTailLength){
        ArrayList<Dog> doglistCopy = new ArrayList<Dog>(doglist);
        DogNameComparator dogNameComparator = new DogNameComparator();
        DogSorter.sortDogs(dogNameComparator , doglistCopy);

        Iterator<Dog> iterator = doglistCopy.iterator();
        while (iterator.hasNext()) {
            Dog d = iterator.next();
            if (d.getTailLength() < minTailLength) {
                iterator.remove();
            }
        }
        return doglistCopy;


    }

}