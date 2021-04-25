package org.example;

import java.util.ArrayList;


public class Tile {

    private ArrayList<Horse> content;
    private boolean safe;
    private double x;
    private double y;
    public Tile() {
        this.safe = false;
        this.content = new ArrayList<Horse>();
    }

    public ArrayList<Horse> getContent(){
        return this.content;
    }

    public void addHorse(Horse juan){
            this.content.add(juan);
    }

    public void yeetHorse(Horse juan){
        this.content.remove(juan);
    }

    public void clearContent(Color color){
        if(!this.isSafe()){
            for(int i = 0; i < this.getContent().size();i++){
                Horse tempHrose = this.getContent().get(i);
                if(tempHrose.getColor() != color){
                    tempHrose.setRelativePosition(-1);
                    tempHrose.setAbsolutePosition(-1);
                    this.yeetHorse(tempHrose);
                }
            }
        }
    }

    public int getSize(){
        return this.content.size();
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean isSafe(){
        return this.safe;
    }

    public String toString(){
        String output ="the tile is safe ?\n" + this.safe +"\n";
        for (Horse h : this.content){
             output += h.toString();
        }
        return output;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getNumberOfHorseOfColor(Color color){
        int i = 0;
        for (Horse h : this.getContent()){
            i = (h.getColor() == color) ? i+1 : i ;
        }return  i;
    }


    public boolean isThereBlock(){
        boolean out =false;
        for (Color c : Color.values()){
            if (this.getNumberOfHorseOfColor(c) >= 2){
                out = true;
            }
        }
        return out;
    }

}
