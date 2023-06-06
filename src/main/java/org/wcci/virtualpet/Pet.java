package org.wcci.virtualpet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pet {

    public String petName;
    public int hunger;
    public int thirst;
    public int petAge;
    private Map<String, Integer> trainLevel = new HashMap<>();
    private int dehydration;
    public boolean isSitting;
    public int energy;
    private List<Integer> feedingSchedule = new ArrayList<>();

    public Pet(String name) {

        this.petName = name;
        this.petAge = 0;
        this.thirst = 1;
        this.isSitting = false;
        this.energy = 40;
        this.hunger = 90;
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
                this.hunger -= 16;

            } else if (hours >= 9)
                this.hunger -= 30;

        }

    }

    public void hourPassed() {
        this.energy -= 5;
    }

    /** Thirst is on a scale from 0 to 100 */
    public Integer getThirst() {
        return this.thirst;
    }

    public boolean isHungry() {
        if (this.hunger == 40) {
            return true;
        }
        return false;
    }

    public boolean isStarving() {
        if (this.hunger == 25) {
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
        if (this.hunger == 3) {
            return true;
        }
        return false;
    }

    public void commandSit() {

    }

    public void train(String string) {
        if (this.hunger <= 25) {

            if (trainLevel.containsKey(string)) {
                trainLevel.put(string, (trainLevel.get(string) + 1));
            } else {
                trainLevel.put(string, 1);
            }
            this.thirst++;
            this.energy -= 10;
        }
    }

    public double chanceOfSitting() {
        if (trainLevel.containsKey("sitting") && trainLevel.get("sitting") >= 2) {
            return 0.85;
        } else {
            return 0.5;
        }
    }

    public void setAgeMonths(int i) {
        this.petAge = i;
    }

    public void feed(int i) {
    }

    public boolean isDehydrated() {
        if (this.dehydration == 3) {
            return true;
        }
        return false;
    }

    public boolean isOverwatered() {
        return false;
    }

    public boolean isThirsty() {
        if (this.thirst == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void water(int i) {
        thirst -= i;
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
        if (energy < 25) {
            return true;
        } else
            return false;
    }

}
