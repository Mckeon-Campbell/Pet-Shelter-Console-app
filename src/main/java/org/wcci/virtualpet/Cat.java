package org.wcci.virtualpet;

public class Cat extends Pet {
    private boolean isLitterboxDirty;

    public Cat(String name){
        super(name);
        this.isLitterboxDirty = false;
    }
}
