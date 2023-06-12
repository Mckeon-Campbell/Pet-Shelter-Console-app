package org.wcci.virtualpet;

import java.util.HashMap;
import java.util.Scanner;

public class Petshelter {
    
    public HashMap<String, Pet> petshelter = new HashMap<>();

    public void petAdd(String petName, Pet myPet){
        petshelter.put(petName, myPet);
    }

    public void petAdoption(String petName){
        petshelter.remove(petName);
    }
    public Pet getPet(String petName){
        return petshelter.get(petName);
    }
    public void shelterpetsList(){
        for(String name:petshelter.keySet()){
            String key = name.toString();
            String value = petshelter.get(name).toString();
            System.out.println(key + " " + value);
        }
    }
    public void feedAll(){
        for(String key : petshelter.keySet()){
            petshelter.get(key).feed(1);
        }
        }
    

    public static void main(String[] args) {
        Petshelter petshelter = new Petshelter();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("What is the pet's name?");
            String petName = scanner.next();
            Pet pet = new Pet(petName);
            petshelter.petAdd(petName, pet);
            petshelter.petAdoption(petName);
            petshelter.getPet(petName);
        }
        petshelter.shelterpetsList();
        petshelter.feedAll();
    }
}