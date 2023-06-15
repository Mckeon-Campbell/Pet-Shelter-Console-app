package org.wcci.virtualpet;

import java.util.Scanner;

public class PetShelterApp {
    public static void main(String[] args) {
        Petshelter petshelter = new Petshelter();
        Petshelter roboPetshelter = new Petshelter();
        Scanner scanner = new Scanner(System.in);
        String input;
        String petName;
        System.out.println("Please turn caps lock to start");

        do{
            System.out.println("Would you like ROBOTIC or ORGANIC pets?");
            System.out.println("Type EXIT to quit");
            input = scanner.next();
            switch (input){
                case "ROBOTIC" :
                    System.out.println("Would you like to ADD, MAINT, WALK");
                    input = scanner.next();
                    switch(input){
                        case "ADD" :
                            System.out.println("Would you like to add a CAT or DOG?");
                            input = scanner.next();
                            switch(input){
                                case "CAT" :
                                    System.out.println("What is your pet's name?");
                                    petName = scanner.next();
                                    Robotpet cat = new Robotpet(petName);
                                    roboPetshelter.petAdd(petName, cat);
                                    break;
                                case "DOG" :
                                    System.out.println("What is your pet's name?");
                                    petName = scanner.next();
                                    Robotpet dog = new Robotpet(petName);
                                    roboPetshelter.petAdd(petName, dog);
                                    break;
                            }
                            break;
                        case "MAINT" :
                            System.out.println("Would you like to maintenance a SPECIFIC pet or ALL pets?");
                            input = scanner.next();
                            switch(input){
                                case "SPECIFIC" :
                                    System.out.println("Who are we maintaining?");
                                    petName = scanner.next();
                                    if(roboPetshelter.containsRoboPet(petName)){
                                        roboPetshelter.getrobotPet(petName).mainT(1);
                                    }
                                    break;
                                case "ALL" :
                                    System.out.println("You maintained all pets");
                                    roboPetshelter.mainTAll();
                                    break;
                            }
                            break;
                        case "WALK" :
                        
                    }
                    break;
                case "ORGANIC" :
                    System.out.println("Would you like to ADD,FEED, WALK");
                    input = scanner.next();
                    switch(input){
                        case "ADD" :
                        case "FEED":
                        case "WALK" :
                }
                
            }
        }while(!"EXIT".equals(input));
    }
}
