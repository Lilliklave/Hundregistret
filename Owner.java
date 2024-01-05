//Liliana Klavebäck Martinez likl4662@SU.SE

import java.util.*;


public class Owner implements Comparable<Owner> {
    
    private String name;
    private ArrayList<Dog> dogs = new ArrayList<>();


    public Owner(String name){
        String[] names = name.split(" ");

        for(int i = 0; i < names.length; i++){

                names[i] = names[i].toLowerCase();
                names[i] = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);   

                if(i == 0){
                    name = names[i];
                }else{
                    name = name + " " + names[i];
                }

        }

        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        String string = "Owner: " + this.name; 
        return string;  
    
    }
    
    public int compareTo(Owner other){
       return this.name.compareTo(other.name);
    }

 public boolean addDog(Dog dog){
    if(!dogs.contains(dog)){
        dogs.add(dog);
        dog.setOwner(this);
        return true;
    }
    return false;

    }

    public boolean removeDog(Dog dog){
    if(dogs.contains(dog)){
        dogs.remove(dog);
        return true;
    }
    return false;

    }

    public ArrayList<Dog> getDogs(){
        ArrayList<Dog> copyOfdoglist = new ArrayList<Dog>(dogs);

        Comparator<Dog> compareDog = new DogNameComparator();
        DogSorter.sortDogs(compareDog, copyOfdoglist);

        return copyOfdoglist;
    }
}
