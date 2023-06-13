package org.wcci.virtualpet;

public class Dog extends Pet {
    private boolean isCageDirty;
    private boolean hasWalked;

    public Dog(String name){
        super(name);
        this.isCageDirty = false;
        this.hasWalked = false;
    }
    public void cleanCage(){
        this.isCageDirty = false;
    }
    public void walk(){
        this.hasWalked = true;
    }
}
