// Liliana Klavebäck Martinez likl4662@SU.SE

public class Dog {
    
    private static final double DACHSHUND_LENGTH = 3.7;

    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private Owner owner;


    public Dog(String name, String breed, int age, int weight){
        this.name = normalize(name);
        this.breed = normalize(breed);
        this.age = age;
        this.weight = weight;
        this.tailLength = setTailLength();

    }
    



    private String normalize(String input){
        String[] names = input.split(" ");

        for(int i = 0; i < names.length; i++){

                names[i] = names[i].toLowerCase();
                names[i] = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);   

                if(i == 0){
                    input = names[i];
                }else{
                    input = input + " " + names[i];
                }

        }

        return input;
    }

public String getName(){
    return this.name;

}

public String getBreed(){
    return this.breed;
}

public int getAge(){
    return this.age;
}

public int getWeight(){
    return this.weight;
}

public double getTailLength(){
    return this.tailLength;
}

private double setTailLength() {
     if(this.breed.equalsIgnoreCase("tax")|| this.breed.equalsIgnoreCase("dachshund") || this.breed.equalsIgnoreCase("mäyräkoira") || this.breed.equalsIgnoreCase("teckel")){
        return DACHSHUND_LENGTH;

    }else{
        return this.age * (this.weight/10.0);
    }
}

public String toString(){
    String string = "owner: " + this.owner + "\nName: " + this.name + "\nBreed: " + this.breed + "\nAge: " + this.age + "\nWeight: " + this. weight + "\ntailLength: " + (float)this.tailLength;
    return string;

}

public void updateAge (int years){
    if(years < 1){
        System.out.print("Age can not be zero or negative");
    }

    if(years > 0){
        if(this.age > Integer.MAX_VALUE - years){
            this.age = Integer.MAX_VALUE;
        }else{
            this.age += years;
        }
    
    this.tailLength = setTailLength();
    }
}

public boolean setOwner(Owner newOwner){
    if(newOwner == null){       // om parametern är null, ta bort hunden från ägaren och ta bort ägaren från den här hunden
        if(this.owner != null){ 
        this.owner.removeDog(this);     //om hunden har en ägare -> kör denna kod, annars hade det krashat.
        }
        this.owner = null;
        return false;
    }
    if(this.owner == null){  // om den här hunden inte har en ägare, så sätts en ägare
        this.owner = newOwner;
        newOwner.addDog(this);
    }
    return true;

}



public Owner getOwner(){
    return this.owner;
    
}


}