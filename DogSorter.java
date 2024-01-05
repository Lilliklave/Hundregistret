//Liliana Klavebäck Martinez likl4662@SU.SE

import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {
    
    private static void swapDogs(ArrayList<Dog> doglist, int indexOne, int indexTwo ){
        Dog temp = doglist.get(indexOne);
        doglist.set(indexOne, doglist.get(indexTwo));
        doglist.set(indexTwo, temp);


    }

     private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> doglist, int start){

            int min = start;
            for( int j = start + 1; j < doglist.size(); j++){
                if(comparator.compare(doglist.get(j), doglist.get(min)) < 0){
                    min = j;
                    
                }
                
            }
            return min;
        }

        public static int sortDogs(Comparator<Dog> comparator, ArrayList<Dog> doglist){
            int counter = 0;
            for(int i = 0; i < doglist.size() - 1; i++){
                int min = nextDog(comparator, doglist, i);
                    if(min != i){
                        swapDogs(doglist, i, min);
                        counter++;
                    }
                }
                return counter;
            }
            
        }

   





   
    

