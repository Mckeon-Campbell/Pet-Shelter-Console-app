package org.wcci.virtualpet;

public class Robotpet  {
    public int oil = 80;
    public int mT = 80;
    private String petName;

    public Robotpet(String name) {
        this.petName = name;
    }

    public int getOil() {
        return this.oil;
    }

    public int getmT() {
        return this.mT;
    }

    public void walk() {
        this.oil -= 20;
        this.mT -= 20;
    }

    public boolean needsOil() {
        if (this.oil <= 60) {
            return true;
        } else {
            return false;
        }
    }

    public boolean needsmT() {
        if (this.mT <= 60) {
            return true;
        } else {
            return false;
        }
    }
    public void mainT(int i){
        this.oil+=20;
        this.mT+=20;
    }

}
