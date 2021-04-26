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
        if(this.getNumberOfHorseOfColor(juan.getColor()) > 1){
            for(int i = 0; i < this.content.size(); i++){
                Horse h = this.content.get(i);
                h.setScaleX(0.015);
                h.setScaleY(0.015);
                double y = this.y - i*10;
                double x = this.x;
                h.setTranslateX(x);
                h.setTranslateY(y);
            }
        }else{
            juan.setScaleX(0.02);
            juan.setScaleY(0.02);
            juan.setTranslateX(this.x);
            juan.setTranslateY(this.y);
        }

    }

    public void yeetHorse(Horse juan){
        this.content.remove(juan);
    }

    public void clearContent(Color color){
        if(!this.isSafe()){
            for(int i = 0; i < this.getContent().size();i++){
                Horse tempHorse = this.getContent().get(i);
                if(tempHorse.getColor() != color){
                    tempHorse.setRelativePosition(-1);
                    tempHorse.setAbsolutePosition(-1);
                    Board.getBaseTiles(tempHorse.getColor()).get(tempHorse.getHorseId()).addHorse(tempHorse);
                    this.yeetHorse(tempHorse);
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
