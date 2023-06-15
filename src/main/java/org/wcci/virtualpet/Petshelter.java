package org.wcci.virtualpet;

import java.util.HashMap;
import java.util.Scanner;

public class Petshelter {

    public HashMap<String, Pet> petshelter = new HashMap<>();
    public HashMap<String, Robotpet> robotpetshelter = new HashMap<>();

    public void petAdd(String petName, Robotpet myPet) {
        robotpetshelter.put(petName, myPet);
    }

    public void petAdd(String petName, Pet myPet) {
        petshelter.put(petName, myPet);
    }

    public void robotpetAdoption(String petName) {
        robotpetshelter.remove(petName);
    }

    public void petAdoption(String petName) {
        petshelter.remove(petName);
    }

    public Robotpet getrobotPet(String petName) {
        return robotpetshelter.get(petName);
    }

    public Pet getPet(String petName) {
        return petshelter.get(petName);
    }

    public void robotshelterpetsList() {
        for (String name : robotpetshelter.keySet()) {
            String key = name.toString();
            String value = robotpetshelter.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public void shelterpetsList() {
        for (String name : petshelter.keySet()) {
            String key = name.toString();
            String value = petshelter.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public void mainTAll() {
        for (String key : robotpetshelter.keySet()) {
            robotpetshelter.get(key).mainT(1);
        }
    }

    public void feedAll() {
        for (String key : petshelter.keySet()) {
            petshelter.get(key).feed(1);
        }
    }

    public Boolean containsRoboPet(String petName) {
        if(robotpetshelter.containsKey(petName)){
            return true;
        }
        return false;
    }

    public boolean containsPet(String petName) {
        if(petshelter.containsKey(petName)){
            return true;
        }
        return false;
    }
}