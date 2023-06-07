package org.wcci.virtualpet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pet {

    private static final int TRAINENERGYLOSS = 10;
    private static final int OVERFEDLEVEL = 100;

    private static final int HOURLYENERGY = 5;

    private static final int DEHYDRATIONLEVEL = 10;

    private static final int HUNGRYLEVEL = 70;

    private static final int LONGHUNGER = 50;

    private static final int SHORTLUNGER = 10;
    private static final int STARVINGLEVEL = 30;

    private static final int TIREDLEVEL = 25;

    private static final int THIRSTLEVEL = 80;
    public String petName;
    public int hunger;
    public int thirst;
    public int petAge;
    private Map<String, Integer> trainLevel = new HashMap<>();
    public double isSitting;
    public int energy;
    private List<Integer> feedingSchedule = new ArrayList<>();

    public Pet(String name) {

        this.petName = name;
        this.petAge = 0;
        this.thirst = 80;
        this.isSitting = 0;
        this.energy = 40;
        this.hunger = 80;
    }

    /** Hunger is on a scale from 0 to 100 */ // This is a "javadoc"
    public Integer getHunger() {
        return this.hunger;
    }

    /** Allow any number of hours to pass */
    public void timePassed(int hours) {
        if (!feedingSchedule.isEmpty()) {
            this.feed(feedingSchedule.size());
        }
        if (feedingSchedule.isEmpty()) {

            if (hours <= 8) {
                this.hunger -= SHORTLUNGER;

            } else if (hours >= 9)
                this.hunger -= LONGHUNGER;

        }

    }

    public void hourPassed() {
        this.energy -= HOURLYENERGY;
    }

    /** Thirst is on a scale from 0 to 100 */
    public Integer getThirst() {
        return this.thirst;
    }

    public boolean isHungry() {
        if (petAge <= 0) {
            return true;
        } else if (this.hunger <= HUNGRYLEVEL) {
            return true;
        }
        return false;
    }

    public boolean isStarving() {
        if (this.hunger <= STARVINGLEVEL) {
            return true;
        }
        return false;
    }

    public void giveSnack() {
    }

    public void setFeedingSchedule(String string) {
        int initial = 0;
        String state = "blanks";
        string += " ";

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            String newState = computeState(c);

            if (!newState.equals(state)) {
                if (!state.equals("blanks")) {
                    String subString = string.substring(initial, i);
                    feedingSchedule.add(Integer.parseInt(subString));
                }
                initial = i;
                state = newState;
            }
        }
    }

    private static String computeState(char c) {
        if (c == ' ' || c == ',' || c == ':') {
            return "blanks";
        } else if (c >= '0' && c <= '9') {
            return "number";
        } else {
            return "symbol";
        }

    }

    public boolean isOverfed() {
        if (this.hunger > OVERFEDLEVEL) {
            return true;
        }
        return false;
    }

    public void commandSit() {

        if (trainLevel.containsKey("sitting") && trainLevel.get("sitting") > 1)  {
            this.isSitting = 0.85;
        }
        else if(petAge<1)  {
            this.isSitting= 0.5;
        }

        else if (this.hunger <= HUNGRYLEVEL) {
            this.isSitting = 0.5;
        }

        //else if (trainLevel.containsKey("sitting") && trainLevel.get("sitting") >= 1)  {
          //  this.isSitting = 0.85;
        
        else {
            this.isSitting = 0.5;
        }
    }

    public void train(String string) {
        if (this.hunger >= STARVINGLEVEL) {

            if (trainLevel.containsKey(string)) {
                trainLevel.put(string, (trainLevel.get(string) + 1));
            } else {
                trainLevel.put(string, 1);
            }

        }
        adjustThirst(-20);
        this.energy -= TRAINENERGYLOSS;
    }

    private void adjustThirst(int i) {
        thirst += i;
    }

    public double chanceOfSitting() {
        return isSitting;
    }

    public void setAgeMonths(int i) {
        this.petAge = i;
    }

    public void feed(int i) {
        this.hunger += 30;
    }

    public boolean isDehydrated() {
        if (this.thirst <= DEHYDRATIONLEVEL) {
            return true;
        }
        return false;
    }

    public boolean isOverwatered() {
        return false;
    }

    public boolean isThirsty() {
        if (this.thirst <= THIRSTLEVEL) {
            return true;
        } else {
            return false;
        }
    }

    public void water(int i) {
        adjustThirst(+10);
    }

    public Integer ageInYears() {
        return this.petAge / 12;
    }

    public void setWalkingSchedule(String string) {
    }

    public boolean isFedAt(int hour) {
        if (feedingSchedule.contains(hour)) {
            return true;
        } else {
            return false;
        }
    }

    public void removeWalkingSchedule() {
    }

    public String getName() {
        return this.petName;
    }

    public void walk() {
    }

    public void removeFeedingSchedule() {
        feedingSchedule.clear();
    }

    public boolean isHealthy() {
        return true;
    }

    public boolean isTired() {
        if (energy < TIREDLEVEL) {
            return true;
        } else
            return false;
    }

}
