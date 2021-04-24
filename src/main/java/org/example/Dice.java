package org.example;

public class Dice {
    private int nbFaces;

    public Dice(){
        this.nbFaces = 6;
    }

    public Dice(int nbFaces){
        this.nbFaces = nbFaces;
    }

    public int getNbFaces() {
        return this.nbFaces;
    }

    public void setNbFaces(int nbFaces) {
        this.nbFaces = nbFaces;
    }

    public int roll(){
        return (int) (Math.random()*nbFaces) + 1;
    }
}
