package org.example;

import javafx.scene.image.ImageView;

public class Horse extends ImageView {

    private Color color;
    private int relativePosition; // from -1 to 51,52-55, -1 : home, 52-56 : arrow
    private int absolutePosition; // 0-51, negative might be the starting position
    private final int Hid;

    public Horse(Color color, int relativePosition, int absolutePosition, int id){
        super(generatePath(color));
        this.color = color;
        this.relativePosition = relativePosition;
        this.absolutePosition = absolutePosition;
        this.Hid = id;
        super.setScaleX(0.05);
        super.setScaleY(0.05);

    }


    //Getter
    public Color getColor() {
        return color;
    }


    public int getAbsolutePosition() {
        return absolutePosition;
    }

    public int getHorseId() {
        return Hid;
    }

    public int getRelativePosition() {
        return relativePosition;
    }

    //setter

    public void setColor(Color color){
        this.color = color;
    }
    public void setRelativePosition(int relativePosition){
        this.relativePosition = relativePosition;
    }

    public void setAbsolutePosition(int absolutePosition){
        this.absolutePosition = absolutePosition;
    }

    //Modifier
    public void addStep(int step){
        this.relativePosition += step;
    }

    //public void setSafe(boolean safe){ this.safe = safe; }


    //Checker

    //public boolean isSafe() {return safe;}


    public String toString(){
        String output = "";
        output+= "This is a " + this.color + " Horse\n";
        output+= "It's the " + this.Hid + " horse\n";
        output+= "He has traveled " + this.relativePosition + " so far\n";
        output+= "He is on the " + this.absolutePosition + " Case\n";
        //output+= "Is he safe ?\n" +this.safe + "\n";
        return  output;
    }



    public static String generatePath (Color color){
        String path;
        switch (color){
            case RED -> path = "red.png";
            case BLUE -> path = "blue.png";
            case YELLOW -> path = "yellow.png";
            case GREEN ->  path = "green.png";
            default -> path = "redb.png";
        }
        return path;
    }
}
