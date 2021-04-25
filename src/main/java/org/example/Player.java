package org.example;

import javafx.event.Event;

import java.util.ArrayList;

public class Player {
    private Color color;
    private final ArrayList<Horse> Lhorse = new ArrayList<>();
    private final int startingTile;
    private boolean finished;

    public Player(Color color){
        this.color = color;
        for(int i = 0;i < 4;i++){
            Horse current = new Horse(this.color,-1,-1,i);
            Lhorse.add(current);
        }
        this.startingTile = identifyStartingTile(color);
        this.finished = false;
    }

    //Getter

    public Color getColor() {
        return color;
    }

    public ArrayList<Horse> getLhorse() {
        return Lhorse;
    }

    public int getStartingTile() {
        return startingTile;
    }

    public ArrayList<Horse> getPlayableHorses(Dice d, int dr){
        ArrayList<Horse> result = new ArrayList<>();
        for (Horse h: this.getLhorse()){
            if (h.getRelativePosition() != 56) {
                if (h.getRelativePosition() >= 51) {
                    if (h.getRelativePosition() + dr < 57) {
                        result.add(h);
                    }
                } else if (h.getRelativePosition() + dr >= 51 && Board.freePath(h, dr, false)) {
                    result.add(h);
                } else if ((h.getRelativePosition() == -1 && dr == d.getNbFaces()) || (Board.freePath(h, dr, true) && h.getRelativePosition() + dr < 51)) {
                    result.add(h);
                }
            }
        }return result;
    }

    //Check if the player has put all his Horse on it's finishing tile
    public boolean isFinished(){
        if (!this.finished){
            this.finished = true;
            for (Horse juan : this.getLhorse()){
                if(juan.getRelativePosition() != 56){
                    this.finished = false;
                    break;
                }
            }
        }
        return this.finished;
    }

    //Setter
    public void setColor(Color color) {
        this.color = color;
    }
    //Modifier
    public void moveHorse(Horse h, int steps){
        h.addStep(steps);
    }



    public static int identifyStartingTile(Color color) {
        int out = -1;
        switch (color){
            case RED -> out = 0;
            case BLUE -> out = 13;
            case YELLOW -> out = 26;
            case GREEN ->  out = 39;
        }
        return out;
    }


    public String toString(){
        String output = "";
        output+= "This is the " + this.color + " Player\n";
        output+= "It's home tile is " + this.startingTile + " Player\n";
        output+= "Here's a display of it's Horses : \n";
        for(Horse juan : this.Lhorse){
            output+= juan.toString();
        }
        return output;
    }

}
