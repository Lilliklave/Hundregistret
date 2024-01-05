//Liliana Klavebäck Martinez likl4662@SU.SE
import java.util.Arrays;
import java.util.ArrayList;


public class OwnerCollection {


private Owner[] owners; 
private int size; 


public OwnerCollection (){
    this.owners = new Owner[0];
    this.size = 0;
}

private void extendArraySize(){
    Owner[] newOwners = new Owner[owners.length + 1];
    for(int i = 0; i < owners.length; i++){                //kopierar varje element från owners arrayen till newowners
        newOwners[i] = owners[i];
    }
    owners = newOwners;                                    // innehåller element från owners och tomma platser för att lägga till nya ägare
}


private void shrinkArraySize(){
    Owner[] newOwners = new Owner[owners.length - 1];
    int x = 0;
    for (int i = 0; i < owners.length; i++) {
        if (owners[i] != null) {
        newOwners[x] = owners[i];
        x += 1;
        }
    }
    owners = newOwners;
}


public boolean addOwner(Owner owner){
    if(this.containsOwner(owner)){
        return false;
    }

    if(this.size == owners.length){         // kolla om arrayen är full, isåfall öka arrayens storlek
        this.extendArraySize();
    }
    
    owners[size] = owner;                 // lägger till ny ägare på rätt plats och size pekar alltid på nästa lediga plats
    size += 1;
    return true;
}


public boolean removeOwner(String ownerName){    //ifall namn matchar -> ta bort ägaren genom flytta sista elementet till den positionen där ägaren matchades. Efter flytten sätts sista positionen till null sedan minska storleken med 1.
    Owner ownerObject = this.getOwner(ownerName);

    if(ownerObject == null){
        return false;
    }

    if(ownerObject.getDogs().size() > 0){
        return false;
    }
    
    if(this.containsOwner(ownerObject)){
        for(int i = 0; i < owners.length;  i++){
            if(owners[i].equals(ownerObject)){
                owners[i] = null;
                this.shrinkArraySize();
                return true;
            }
        }  
    }
     return false;
   
}

public boolean removeOwner(Owner ownerObject){    // samma som innan endast att den kollar ifall owner objektet existerar med equalsmetoden
    
    if(ownerObject.getDogs().size() > 0){
        return false;
    }
    
    if(this.containsOwner(ownerObject)){
        for(int i = 0; i < owners.length;  i++){
            if(owners[i].equals(ownerObject)){
                owners[i] = null;
                this.shrinkArraySize();
                return true;
            }
        }  
    }
     return false;



}

public boolean containsOwner(String name){
    return getOwner(name) != null;
}

public boolean containsOwner(Owner owner){
    return this.getOwner(owner.getName()) != null;
}

// Går igenom owners-arrayen och jämför de anginva namnet med ägarnas namn och ifall det matchar ett ägarnamn i listan så returnerar det den  motsvarande Owner-instansen annars returnerar den null.
public Owner getOwner(String name){
    for(int i = 0; i < size; i++){
        if(owners[i].getName().equalsIgnoreCase(name)){
            return owners[i];
        }
    }
    return null;

}

//Skapar en ArrayList av arrayen med hjälp av Arrays-metoden "asList".
public ArrayList<Owner> getOwners(){
    ArrayList<Owner> ownerList = new ArrayList<>();
    Arrays.sort(owners);
    for (int i = 0; i < size; i++) {
        ownerList.add(owners[i]);
    }
    return ownerList;

}



}
