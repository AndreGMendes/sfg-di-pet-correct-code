package com.springframework.pets;




public class PetServiceFactory {

    public PetService getPetService(String petType) {

        switch (petType) {
            case "dog" : System.out.println("Factory made a DOG"); return new DogPetService();
            case "cat" : System.out.println("Factory made a CAT"); return new CatPetService();
            default    : return new DogPetService();
        }

    }
}
