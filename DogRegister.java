// Liliana Klavebäck Martinez likl4662@SU.SE

import java.util.ArrayList;
import java.util.Comparator;

public class DogRegister {

    private static final String REGISTER_OWNER = "register new owner";
    private static final String REMOVE_OWNER = "remove owner";
    private static final String REGISTER_DOG = "register new dog";
    private static final String REMOVE_DOG = "remove dog";
    private static final String LIST_DOGS = "list dogs";
    private static final String LIST_OWNERS = "list owners";
    private static final String INCREASE_AGE = "increase age";
    private static final String GIVE_DOG_TO_OWNER = "give dog to owner";
    private static final String REMOVE_DOG_FROM_OWNER = "remove dog from owner";
    private static final String EXIT_COMMAND = "exit";

    private OwnerCollection ownerCollection = new OwnerCollection();
    private DogCollection dogCollection = new DogCollection();


    private InputReader input = new InputReader();
    private boolean running = true;

    private void runProgram(){
        initialize();
        runCommandLoop();
        shutDown();
    }

    private void initialize(){
    
        System.out.println("Welcome to the dog register!");
        System.out.println("The following commands are available:");
        System.out.println("* Register new dog");
        System.out.println("* Remove dog");
        System.out.println("* Register new owner");
        System.out.println("* Remove owner");
        System.out.println("* List dogs");
        System.out.println("* List owners");
        System.out.println("* Increase age");
        System.out.println("* Give dog to owner");
        System.out.println("* Remove dog from owner");
        System.out.println("* Exit");
        System.out.println();
        }

    

    private void runCommandLoop(){
       // System.out.println("Running command loop");

        String command;

        
            while(running){
            command = readCommand().toLowerCase();
            handleCommand(command);

            }
    }
    

    private String readCommand(){
        return input.handleText("Enter command").toLowerCase();
        
    }

    private void handleCommand(String command) {
        switch (command) {
            case REGISTER_OWNER:
                registerNewOwner();
                break;
            case REMOVE_OWNER:
                removeOwner();
                break;
            case REGISTER_DOG:
                registerNewDog();
                break;
            case REMOVE_DOG:
                removeDog();
                break;
            case LIST_DOGS:
                listDogs();
                break;
            case LIST_OWNERS:
                listOwners();
                break;
            case INCREASE_AGE:
                increaseAge();
                break;
            case GIVE_DOG_TO_OWNER:
                giveDogToOwner();
                break;
            case REMOVE_DOG_FROM_OWNER:
                removeDogFromOwner();
                break;
            case EXIT_COMMAND:
                System.out.println("Dog register shut down");
                running = false;  
                break;
    
            default:
                System.out.println("Error: Wrong command!");
                break;
        }
    }

  

    private void registerNewOwner(){
        String ownerName = input.handleText("Enter owner name");

        while(ownerName.isBlank()){    //loopa tills ett giltigt namn skrivs in
            System.out.println("Error: Name cannot be blank. Please enter a valid name.");
            ownerName = input.handleText("Enter owner name");
        }

        if(ownerCollection.containsOwner(ownerName)){   // kollar om ägaren redan är reggad
            System.out.println("Error: An owner with the name '" + ownerName + "' already exists in the register." );
            return;
        }

        Owner newOwner = new Owner(ownerName); // skapa och lägg till nya ägaren i registret
        ownerCollection.addOwner(newOwner);
        System.out.println("The owner " + newOwner.getName() + " has been added to the register");
    }
    
    
    private void removeOwner(){
        if(ownerCollection.getOwners().size() == 0){   //anropar metod i OwnerCollection som returnerar arraylist baserad på innehållet i owners arrayen och kollar ifall ägarlistan är tom
            System.out.println("Error: No owners in the register");
            return;
        }
        String ownerName = input.handleText("Name");

        while(ownerName.isBlank()){    //loopa tills ett giltigt namn skrivs in
            System.out.println("Error: Name cannot be blank. Please enter a valid name.");
            ownerName = input.handleText("Enter owner name");
        }
    

         Owner ownerToRemove = ownerCollection.getOwner(ownerName);  //kollar ifall ägare som ska tas bort existerar i ägarsamlingen, om inte ge error meddelande
        if (ownerToRemove == null) {
            System.out.println("Error: The owner " + ownerName + " does not exist");
            return;
    }
        for(Dog dog : ownerToRemove.getDogs()){  // för varje hund som ägs av en ägare som ska tas bort så sätter vi hundens ägare till null och tar bort den från hundsamlingen. Hanterar uppdatering när hundägare tas bort
            dog.setOwner(null);
            dogCollection.removeDog(dog);
        }

        ownerCollection.removeOwner(ownerToRemove);
        System.out.println("The owner " + ownerToRemove.getName() + " has been removed from the register");
    
        }

      
    
    private void registerNewDog() {
        String dogName = input.handleText("Enter dog name");

        while(dogName.isBlank()){    //loopa tills ett giltigt namn skrivs in
            System.out.println("Error: Name cannot be blank. Please enter a valid name.");
            dogName = input.handleText("Enter dog name");
        }

        if(dogCollection.containsDog(dogName)){   // kollar om hunden redan är reggad
            System.out.println("Error: An dog with the name '" + dogName + "' already exists in the register." );
            return;
        }

        String breed = input.handleText("Breed");

        while(breed.isBlank()){     //hanterar scenario ifall breed stringen är tom
            System.out.println("Error: A blank string is not allowed. Please try again!");
            breed = input.handleText("Breed");

        }

        int age = input.handleInteger("Age");
        int weight = input.handleInteger("Weight");

        Dog newDog = new Dog(dogName, breed, age, weight); // skapar ett nytt Dog objekt som läggs till i dogcollection
        dogCollection.addDog(newDog);
        System.out.println("The dog " + newDog.getName() + " has been added to the register");
    }
    
    
    private void removeDog() {
        if(dogCollection.getDogs().size() == 0){   
            System.out.println("Error: No dogs in the register");
            return;
        }
        String dogName = input.handleText("Name");

        while(dogName.isBlank()){    //loopa tills ett giltigt namn skrivs in
            System.out.println("Error: Name cannot be blank. Please enter a valid name.");
            dogName = input.handleText("Enter dog name");
        }
        
        Dog dogToRemove = dogCollection.getDog(dogName);  //kollar ifall hund som ska tas bort existerar i hundsamlingen, om inte ge error meddelande
        if (dogToRemove == null) {
            System.out.println("Error: The dog named " + dogName + " does not exist");
            return;
    }

        if(dogToRemove.getOwner() != null){     // ifall hunden som ska tas borts har en ägare, tar bort hunden från ägarens lista.
            dogToRemove.getOwner().removeDog(dogToRemove);
        }

        dogToRemove.setOwner(null);  //Ta bort ägaren från hunden som attribut, för att kunna ta bort hunden. (annars är den kvar hos hunden).

        dogCollection.removeDog(dogToRemove);
        System.out.println("This dog " + dogToRemove.getName() + " has been removed from the register");

    }
    
    private void listDogs() {
        ArrayList<Dog> dogs = dogCollection.getDogs();
        if (dogs.isEmpty()) {
            System.out.println("Error: No dogs in register");
            return;
            }
        
        double tailLength = input.handleDouble("Enter minimum tail length");
        if (tailLength < 0) {
            System.out.println("Error: Tail length cannot be negative. Please enter a positive value.");
             return;
            }
        
        dogs = dogCollection.getDogsWithMinimumTailLength(tailLength);
        dogs.sort(Comparator.comparingDouble(Dog::getTailLength)); // Sortera efter svanslängd

       
        
        
        System.out.printf("%-10s %-10s %-5s %-7s %-5s %-10s%n", "Name", "Breed", "Age", "Weight", "Tail", "Owner");
        System.out.println("===============================================");

        for (Dog dog : dogs) {
            String ownerName = (dog.getOwner() != null) ? dog.getOwner().getName() : "";
            System.out.printf("%-10s %-10s %-5d %-7d %-5s %-10s%n", 
                dog.getName(), 
                dog.getBreed(), 
                dog.getAge(), 
                dog.getWeight(), 
                dog.getTailLength(), 
                ownerName);
     }
      

    }
    
    private void listOwners() {
        ArrayList<Owner> ownersInRegister = ownerCollection.getOwners();
        
        if(ownersInRegister.isEmpty()){
            System.out.println("Error: No owners in the register");
            return;
        }

        System.out.printf("%-10s %-10s%n", "Name", "Dogs");
        System.out.println("========================================");
        
        for(Owner owner : ownersInRegister){
            StringBuilder dogNames = new StringBuilder();
        for(Dog dog : owner.getDogs()){
            if (dogNames.length() > 0) {
                dogNames.append(", ");
            }
            dogNames.append(dog.getName());
        }
        System.out.printf("%-10s %-10s%n", owner.getName(), dogNames.toString());
    }

        }    
    
        
    private void increaseAge() {
        if (dogCollection.getDogs().size() == 0) {
            System.out.println("Error: No dogs in register");
            return;
        }

        String dogName = input.handleText("Enter dog name");
        while(dogName.isBlank()){
            System.out.println("Error: Name cannot be blank. Please enter a valid name.");
            dogName = input.handleText("Enter dog name");
        }

        Dog dog = dogCollection.getDog(dogName);
        if(dog == null){
            System.out.println("Error: Dog with name " + dogName + "does not exist");
            return;
        }

        dog.updateAge(1);
        System.out.println("The dog " + dog.getName() + " is now one year older");
 
    }
    
    
    private void giveDogToOwner() {
        if(dogCollection.getDogs().isEmpty()){
            System.out.println("Error: No dogs in register");
            return;
        }

        if(ownerCollection.getOwners().isEmpty()){
            System.out.println("Error: No dogs in register"); 
            return;
        }

        String dogName = getValidInput("Enter dog name");
        Dog dog = getValidDog(dogName);
        if(dog == null || dog.getOwner() != null){
            if(dog.getOwner() != null){
                System.out.println("Error: Dog " + dog.getName() + " already has an owner");
            }
            return;
        }

        String ownerName = getValidInput("Name of owner");
        Owner owner = getValidoOwner(ownerName);
        if(owner == null) return;

        owner.addDog(dog);
        dog.setOwner(owner);
        System.out.println("Dog " + dog.getName() + " is now owned by " + owner.getName());




    //     if (dogCollection.getDogs().size() == 0) {                  //kolla ifEmpty metoden
    //         System.out.println("Error: No dogs in register"); // kör tillsammans med owners nedanför
    //         return;
    //     }
        
    //     if(ownerCollection.getOwners().size() == 0){
    //         System.out.println("Error: No owners in register");
    //         return;
    //     }

    //     String dogName = input.handleText("Enter dog name"); // kolla ifall denna är onödig, Testa deras program.
    //     while (dogName.isBlank()) {
    //     System.out.println("Error: Dog name cannot be blank. Please try again!");
    //     dogName = input.handleText("Enter dog name");
    //     }

    //     Dog dog = dogCollection.getDog(dogName);
    //     if (dog == null) {
    //     System.out.println("Error: Dog named " + dogName + " does not exist");
    //     return;
    // }

    //     if (dog.getOwner() != null) {
    //     System.out.println("Error: Dog " + dogName + " already has an owner");
    //     return;
    // }

    //     String ownerName = input.handleText("Name of owner");   // kolla om denna också är överflödig
    //     while (ownerName.isBlank()) {
    //         System.out.println("Error: Owner name cannot be blank. Please try again!");

    //     ownerName = input.handleText("Name of owner");
    // }

    //     Owner owner = ownerCollection.getOwner(ownerName);
    //     if (owner == null) {
    //         System.out.println("Error: Owner named " + ownerName + " does not exist");
    //         return;
    // }
    //     owner.addDog(dog);
    //     dog.setOwner(owner);
    //     System.out.println("Dog " + dog.getName() + " is now owned by " + owner.getName());  
    }
    
    private void removeDogFromOwner() {
        if (dogCollection.getDogs().size() == 0) {          // kör ihop dog och owner som ovanstående meetod
            System.out.println("Error: No dogs in register");
            return;
        }

        if(ownerCollection.getOwners().size() == 0){
            System.out.println("Error: No owners in register");
            return;
        }
    
        String dogName = input.handleText("Name of dog").trim(); // kolla om den också är överflödig
        while (dogName.isBlank()) {
            System.out.println("Error: Dog name cannot be blank");
            dogName = input.handleText("Name of dog").trim();
        }
    
        Dog dog = dogCollection.getDog(dogName);
        if (dog == null) {
            System.out.println("Error: Dog named " + dogName + " does not exist");
            return;
        }
    
        if (dog.getOwner() == null) {
            System.out.println("Error: Dog " + dogName + " does not have an owner");
            return;
        }
    
        Owner owner = ownerCollection.getOwner(dog.getOwner().getName());
        owner.removeDog(dog);
        dog.setOwner(null);
        System.out.println("The dog " + dog.getName() + " now has no owner");
    }



    private String getValidInput(String message){        //kontrollerar ifall inmatningen är tom -> skriver ut error medd + ber om ny inmatning. korrekt inmatning -> returnerar texten. 
        String inputStr = input.handleText(message).trim();
        while(inputStr.isEmpty()){
            System.out.println("Error: Input cannot be blank. Please try again!");
            inputStr = input.handleText(message).trim();
        }
        return inputStr;
    }

    private Dog getValidDog(String dogName){        // kollar ifall hundnamn finns i hundsamling, om inte -> felmedd + null. Annars returnerar hunden om den finns.
        Dog dog = dogCollection.getDog(dogName);
        if(dog == null){
            System.out.println("Error: Dog named " + dogName + " does not exist");
        }
        return dog;
    }

    private Owner getValidoOwner(String name){      //exakt samma som ovanstående fast med ägare.
        Owner owner = ownerCollection.getOwner(name);
        if(owner == null){
            System.out.println("Error: Owner named " + name + " does not exist");
        }
        return owner;
    }


    private void shutDown(){
        System.out.println("Shutting down");

    }
    
    public static void main(String[] args){
        new DogRegister().runProgram();

    }
    
}


