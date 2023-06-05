package org.wcci.virtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PetTest {
    @Test
    void newbornState() {
        Pet pet = new Pet("Bozo");

        assertEquals("Bozo", pet.getName());

        assertEquals(0, pet.ageInYears());

        assertEquals(true, pet.isHungry());
        assertEquals(false, pet.isStarving());
        assertEquals(false, pet.isOverfed());
        assertEquals(false, pet.isDehydrated());
        assertEquals(true, pet.isThirsty());
        assertEquals(false, pet.isOverwatered());

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5);
    }


    @Test void thirstAfterTraining() {
        Pet pet = new Pet("");
        pet.setAgeMonths(1 * 12);

        assertEquals(1, pet.ageInYears());

        pet.water(1);
        assertEquals(false, pet.isThirsty());

        pet.train("sitting");
        assertEquals(true, pet.isThirsty());
    }
    
    @Test void tiredAfterTraining() {
        Pet pet = new Pet("");
        pet.setAgeMonths(1 * 12);

        assertEquals(1, pet.ageInYears());

        pet.water(1);
        pet.feed(1);
        assertEquals(false, pet.isThirsty());
        assertEquals(false, pet.isTired());

        pet.train("sitting");
        assertEquals(false, pet.isTired());
        pet.train("sitting");
        pet.train("sitting");
        assertEquals(true, pet.isTired());
    }

    @Test
    void initialOlderPetState() {
        Pet pet = new Pet("");

        pet.setAgeMonths(2 * 12);

        // Pets start untrained
        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5);
    }

    @Test
    void trainingHelpsOlderPets() {
        Pet pet = new Pet("");

        pet.setAgeMonths(2 * 12);
        assertEquals(2, pet.ageInYears());

        // Training should work if the pet is ready
        pet.feed(1);
        pet.water(1);
        pet.walk();
        pet.train("sitting");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() > 0.8);

        assertEquals(true, pet.isHealthy());
    }
    
    @Test
    void trainingHelpsOlderPetsTheSameSkill() {
        Pet pet = new Pet("");

        pet.setAgeMonths(2 * 12);
        assertEquals(2, pet.ageInYears());

        // Training should work if the pet is ready
        pet.feed(1);
        pet.water(1);
        pet.walk();
        pet.train("fetching");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5); // We only trainied fetching in this case

        assertEquals(true, pet.isHealthy());
    }
    
    @Test
    void trainingDoesntHelpHungryOlderPets() {
        Pet pet = new Pet("");

        pet.setAgeMonths(2 * 12);
        assertEquals(2, pet.ageInYears());

        pet.timePassed(8);

        // Pet should be thirsty and hungry by now
        pet.train("sitting");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5);
    }

    @Test
    void trainingDoesntHelpNewborns() {
        Pet pet = new Pet("");
        pet.train("sitting");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5);
    }

    @Test
    void moreTrainingHelpsMore() {
        Pet pet = new Pet("");

        pet.train("sitting");
        pet.train("sitting");
        pet.train("sitting");
        pet.train("sitting");
        pet.train("sitting");

        pet.commandSit();
        pet.timePassed(1);
        assertEquals(true, pet.chanceOfSitting() > 0.8);
    }

    @Test
    void testHourPassed() {
        Pet pet1 = new Pet("");
        pet1.hourPassed();

        Pet pet2 = new Pet("");
        pet2.timePassed(1);

        assertEquals(pet1.isHungry(), pet2.isHungry());
        assertEquals(pet1.isStarving(), pet2.isStarving());
        assertEquals(pet1.isOverfed(), pet2.isOverfed());
    }


    @Test
    void testFeedingSchedule() {
        Pet pet = new Pet("");
        pet.setFeedingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM

        assertEquals(false, pet.isFedAt(5));
        assertEquals(true, pet.isFedAt(6));
        assertEquals(false, pet.isFedAt(7));
        assertEquals(false, pet.isFedAt(8));
        assertEquals(true, pet.isFedAt(9));
        assertEquals(false, pet.isFedAt(10));
        assertEquals(true, pet.isFedAt(17));
        assertEquals(false, pet.isFedAt(18));
        assertEquals(false, pet.isFedAt(19));
        assertEquals(false, pet.isFedAt(20));
        assertEquals(true, pet.isFedAt(21));
        assertEquals(false, pet.isFedAt(22));
        
        pet.removeFeedingSchedule();
        assertEquals(false, pet.isFedAt(5));
        assertEquals(false, pet.isFedAt(6));
        assertEquals(false, pet.isFedAt(7));
        assertEquals(false, pet.isFedAt(8));
        assertEquals(false, pet.isFedAt(9));
        assertEquals(false, pet.isFedAt(10));
        assertEquals(false, pet.isFedAt(17));
        assertEquals(false, pet.isFedAt(18));
        assertEquals(false, pet.isFedAt(19));
        assertEquals(false, pet.isFedAt(20));
        assertEquals(false, pet.isFedAt(21));
        assertEquals(false, pet.isFedAt(22));
    }

    @Test
    void testFeedingSchedulePassed() {
        Pet pet1 = new Pet("");
        pet1.setWalkingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM
        pet1.timePassed(48);

        Pet pet2 = new Pet("");
        pet2.setFeedingSchedule("8:1, 16:1"); // one bowl at 8AM and one bowl at 4PM
        pet2.setWalkingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM
        pet2.timePassed(48);

        assertEquals(true, pet1.isStarving());
        assertEquals(false, pet2.isStarving());

        assertEquals(false, pet1.isOverfed());
        assertEquals(false, pet2.isOverfed());
    }

    @Test
    void manualFeedingIsFine() {
        Pet pet = new Pet("");

        pet.setWalkingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM

        pet.timePassed(8);
        pet.feed(1);
        pet.timePassed(16);
        pet.feed(1);
        pet.timePassed(8);
        pet.feed(1);
        pet.timePassed(16);
        pet.feed(1);
        assertEquals(false, pet.isHungry());
        pet.timePassed(8);
        assertEquals(true, pet.isHungry());
        assertEquals(false, pet.isStarving());
        assertEquals(false, pet.isOverfed());
        pet.feed(1);
        pet.timePassed(16);
        pet.feed(1);
        assertEquals(false, pet.isStarving());
        assertEquals(false, pet.isOverfed());
    }

    @Test
    void testOverFeedingSchedulePassed() {
        Pet pet1 = new Pet("");
        pet1.timePassed(48);

        Pet pet2 = new Pet("");
        pet2.setFeedingSchedule("8:1, 10:1, 12:1, 14:1, 16:1, 18:1"); // One bowl every two hours
        pet2.timePassed(48);

        assertEquals(true, pet1.isStarving());
        assertEquals(false, pet2.isStarving());

        assertEquals(false, pet1.isOverfed());
        assertEquals(true, pet2.isOverfed());
    }

    @Test
    void testOverFeedingSchedulePassedZero() {
        Pet pet1 = new Pet("");
        pet1.timePassed(48);

        Pet pet2 = new Pet("");
        pet2.setFeedingSchedule("8:1, 10:0, 12:0, 14:0, 16:0, 18:1"); // One bowl every two hours
        pet2.timePassed(48);

        assertEquals(true, pet1.isStarving());
        assertEquals(false, pet2.isStarving());

        assertEquals(false, pet1.isOverfed());
        assertEquals(false, pet2.isOverfed());
    }

    @Test
    void feedingHelps() {
        Pet pet = new Pet("");
        pet.giveSnack();
        assertEquals(false, pet.isHungry());
        assertEquals(false, pet.isStarving());
    }

}
