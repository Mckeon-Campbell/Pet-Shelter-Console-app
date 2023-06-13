package org.wcci.virtualpet;

public class Cat extends Pet {
    private boolean isLitterboxDirty;
    private boolean hasWalked;

    public Cat(String name){
        super(name);
        this.isLitterboxDirty = false;
        this.hasWalked = false;
    }
    public void cleanLitter(){
        this.isLitterboxDirty = false;
    }
    public void walk(){
        this.hasWalked = true;
    }
}
