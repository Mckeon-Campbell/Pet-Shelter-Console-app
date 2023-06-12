package org.wcci.virtualpet;

public class Dog extends Pet {
    private boolean isCageDirty;

    public Dog(String name){
        super(name);
        this.isCageDirty = false;
    }
}
