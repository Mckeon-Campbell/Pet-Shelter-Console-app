package org.wcci.virtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RobotpetTest {
    @Test
    void testwalk(){
        Robotpet dog = new Robotpet("dog");
        assertEquals(false, dog.needsOil());
        assertEquals(false, dog.needsmT());
        dog.walk();
        assertEquals(true, dog.needsOil());
        assertEquals(true, dog.needsmT());
        dog.mainT(1);
        assertEquals(false, dog.needsOil());
        assertEquals(false, dog.needsmT());

    }
}
