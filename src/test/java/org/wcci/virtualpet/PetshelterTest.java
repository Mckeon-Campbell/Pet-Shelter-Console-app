package org.wcci.virtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.wcci.virtualpet.Petshelter;

public class PetshelterTest {
    @Test
    void addPet(){
        Petshelter petshelter = new Petshelter();
        Pet lion = new Pet("lion");
        petshelter.petAdd("Simba", lion);
        Pet cat = new Pet("cat");
        petshelter.petAdd("Lily", cat);
        petshelter.shelterpetsList();

    }

    @Test
    void feedAll(){
        Petshelter petshelter = new Petshelter();
        Pet lion = new Pet("Lion");
        petshelter.petAdd("Simba", lion);
        Pet cat = new Pet("cat");
        petshelter.petAdd("Lily", cat);
        petshelter.feedAll();
        assertEquals(false, lion.isHungry());
    }
}
