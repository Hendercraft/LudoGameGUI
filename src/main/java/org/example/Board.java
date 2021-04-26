package org.example;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Board {

    private static final ArrayList<Player> players = generatePlayers();
    private static final ArrayList<Tile> tiles = generateTiles();

    private static final ArrayList<Tile> redBaseTiles = generateBaseTiles(Color.RED);
    private static final ArrayList<Tile> blueBaseTiles = generateBaseTiles(Color.BLUE);
    private static final ArrayList<Tile> yellowBaseTiles = generateBaseTiles(Color.YELLOW);
    private static final ArrayList<Tile> greenBaseTiles = generateBaseTiles(Color.GREEN);
    //Home cases (ladder included)
    private static final ArrayList<Tile> redHomeTiles = generateHomeTiles(Color.RED);
    private static final ArrayList<Tile> blueHomeTiles = generateHomeTiles(Color.BLUE);
    private static final ArrayList<Tile> yellowHomeTiles = generateHomeTiles(Color.YELLOW);
    private static final ArrayList<Tile> greenHomeTiles = generateHomeTiles(Color.GREEN);
    private boolean turnFinished = false;


    private Dice d;

    public boolean isTurnFinished(){return this.turnFinished;}
    public void setTurnFinished(boolean b){ this.turnFinished = b;}
    public Board(){ this.d = new Dice(); }

    public Board(int nbFaces){
        this.d = new Dice(nbFaces);
    }

    private static ArrayList<Tile> generateTiles(){
        ArrayList<Tile> tiles = new ArrayList<>();
        for(int i = 0;i<52;i++){
            tiles.add(new Tile());
        }
        //Starting tiles coordinates RBYG
        tiles.get(0).setX(50.0);
        tiles.get(0).setY(-300.0);

        tiles.get(13).setX(300.0);
        tiles.get(13).setY(50.0);

        tiles.get(26).setX(-50.0);
        tiles.get(26).setY(300.0);

        tiles.get(39).setX(-300.0);
        tiles.get(39).setY(-50.0);
        //From red Starting Tile to blue Starting Tile
        for(int i = 1; i<5;i++){
            double x = tiles.get(0).getX();
            double y = tiles.get(i-1).getY() + 50 ;
            tiles.get(i).setX(x);
            tiles.get(i).setY(y);

        }

        for(int i = 5; i < 13;i++){
            double x;
            double y;

            if(i == 11) {
                x = tiles.get(i-1).getX();
                y = tiles.get(4).getY() + 100.0;
            }else if(i == 12){
                x = tiles.get(i-1).getX();
                y = tiles.get(4).getY() + 150.0;
            }
            else{
                x = tiles.get(i-1).getX() + 50.0;
                y = tiles.get(4).getY() + 50.0;
            }
            tiles.get(i).setX(x);
            tiles.get(i).setY(y);
        }
        //From blue to Starting Tile to yellow
        for(int i = 14;i<18;i++){
            double x = tiles.get(i-1).getX() - 50.0;
            double y = tiles.get(13).getY();
            tiles.get(i).setX(x);
            tiles.get(i).setY(y);
        }

        for(int i = 18;i < 26;i++){
            double x;
            double y;

            if(i == 24 || i == 25){
                x = tiles.get(i-1).getX() - 50.0;
                y = tiles.get(i-1).getY();
            }else {
                x = tiles.get(17).getX() - 50;
                y = tiles.get(i-1).getY() + 50;
            }
            tiles.get(i).setX(x);
            tiles.get(i).setY(y);
        }

        //From yellow Starting tile to green Starting tile

        for (int i = 27;i<31;i++){
            double x = tiles.get(26).getX();
            double y = tiles.get(i-1).getY() - 50;
            tiles.get(i).setX(x);
            tiles.get(i).setY(y);
        }

        for(int i = 31;i<39;i++){
            double x;
            double y;

            if(i == 37 ||i == 38){
                x = tiles.get(i-1).getX();
                y = tiles.get(i-1).getY() - 50;
            } else {
                x = tiles.get(i-1).getX() - 50;
                y = tiles.get(30).getY() -50;
            }
            tiles.get(i).setX(x);
            tiles.get(i).setY(y);

        }
        //From green starting tile to red starting tile

        for (int i = 40; i<44;i++){
            double x = tiles.get(i-1).getX() + 50;
            double y = tiles.get(39).getY();
            tiles.get(i).setX(x);
            tiles.get(i).setY(y);
        }

        for(int i = 44;i < 52;i++){
            double x;
            double y;
            if(i == 50 || i == 51){
                x = tiles.get(i-1).getX() + 50;
                y = tiles.get(i-1).getY();
            }else{
                x = tiles.get(43).getX() + 50;
                y = tiles.get(i-1).getY() - 50;
            }

            tiles.get(i).setX(x);
            tiles.get(i).setY(y);
        }



        // setting safe tiles
        tiles.get(0).setSafe(true);
        tiles.get(8).setSafe(true);
        tiles.get(13).setSafe(true);
        tiles.get(21).setSafe(true);
        tiles.get(26).setSafe(true);
        tiles.get(34).setSafe(true);
        tiles.get(39).setSafe(true);
        tiles.get(47).setSafe(true);

        return tiles;
    }

    private static ArrayList<Tile> generateHomeTiles(Color color){
        ArrayList<Tile> homeTiles = new ArrayList<>();

        for(int i = 0;i<6;i++){
            homeTiles.add(new Tile());
            homeTiles.get(i).setSafe(true);
        }
        double x;
        double y;
        switch (color){
            case RED :
                x = 0;
                y = -300;
                homeTiles.get(0).setX(x);
                homeTiles.get(0).setY(y);

                for(int i = 1;i<6;i++){
                    y+=50;
                    homeTiles.get(i).setX(x);
                    homeTiles.get(i).setY(y);
                }
                break;
            case BLUE:
                x = 300;
                y = 0;
                homeTiles.get(0).setX(x);
                homeTiles.get(0).setY(y);
                for(int i = 1;i<6;i++){
                    x-=50;
                    homeTiles.get(i).setX(x);
                    homeTiles.get(i).setY(y);
                }
                break;
            case YELLOW:
                x = 0;
                y = 300;
                homeTiles.get(0).setX(x);
                homeTiles.get(0).setY(y);
                for(int i = 1;i<6;i++){
                    y+=50;
                    homeTiles.get(i).setX(x);
                    homeTiles.get(i).setY(y);
                }
                break;
            case GREEN:
                x = -300;
                y = 0;
                homeTiles.get(0).setX(x);
                homeTiles.get(0).setY(y);
                for(int i = 1;i<6;i++){
                    x+=50;
                    homeTiles.get(i).setX(x);
                    homeTiles.get(i).setY(y);
                }
                break;
        }

        return homeTiles;
    }

    private static ArrayList<Tile> generateBaseTiles(Color color){
        ArrayList<Tile> baseTiles = new ArrayList<>();
        for(int i = 0;i<4;i++){
            baseTiles.add(new Tile());
        }
        double x;
        double y;
        switch (color) {
            case RED -> {
                x = 150;
                y = -225;
                for (int i = 0; i < 4; i++) {
                    baseTiles.get(i).setX(x + 50 * i);
                    baseTiles.get(i).setY(y);
                }
            }
            case BLUE -> {
                x = 150;
                y = 225;
                for (int i = 0; i < 4; i++) {
                    baseTiles.get(i).setX(x + 50 * i);
                    baseTiles.get(i).setY(y);
                }
            }
            case YELLOW -> {
                x = -150;
                y = 225;
                for (int i = 0; i < 4; i++) {
                    baseTiles.get(i).setX(x - 50 * i);
                    baseTiles.get(i).setY(y);
                }
            }
            case GREEN -> {
                x = -150;
                y = -225;
                for (int i = 0; i < 4; i++) {
                    baseTiles.get(i).setX(x - 50 * i);
                    baseTiles.get(i).setY(y);
                }
            }
        }

        return baseTiles;
    }

    private static ArrayList<Player> generatePlayers(){
        ArrayList<Player> players = new ArrayList<>();
        for (Color c: Color.values()) {
            players.add(new Player(c));
        }
        return players;
    }

    //getter
    public Dice getD() {
        return d;
    }

    public ArrayList<Player> getPlayers(){return players;}

    public Tile getTiles(int index){
        if(index >= 0 && index < 52){
            return tiles.get(index);
        }else {
            System.err.println("# Size-type error in getCase method (Board class) #");
            return null;
        }
    }

    public Player getPlayer(Color color){
        Player out = getPlayers().get(0);
        switch (color){
            case RED -> out = getPlayers().get(0);
            case BLUE -> out = getPlayers().get(1);
            case YELLOW -> out = getPlayers().get(2);
            case GREEN -> out = getPlayers().get(3);
        }return  out;
    }

    public static ArrayList<Tile> getHomeTiles(Color color){
        ArrayList<Tile> homeTiles = null;
        switch (color){
            case RED -> homeTiles = redHomeTiles;
            case BLUE -> homeTiles = blueHomeTiles;
            case GREEN -> homeTiles = greenHomeTiles;
            case YELLOW -> homeTiles = yellowHomeTiles;
        }return homeTiles;
    }

    public static  ArrayList<Tile> getBaseTiles(Color color){
        ArrayList<Tile> baseTiles = null;
        switch (color){
            case RED -> baseTiles = redBaseTiles;
            case BLUE -> baseTiles = blueBaseTiles;
            case GREEN -> baseTiles = greenBaseTiles;
            case YELLOW -> baseTiles = yellowBaseTiles;
        }return baseTiles;
    }
    //setter

    public void setD(Dice d) {
        this.d = d;
    }

    //Game logic


    public static boolean freePath(Horse h, int dr, boolean mode){
        if (h.getAbsolutePosition() == -1){
            return  false;
        }
        //the block case is basically handled because according to the rules it can always move on a tile
        Tile currentTile = tiles.get(h.getAbsolutePosition());
        int nextTilePos;
        if (mode){
            nextTilePos = (h.getAbsolutePosition() + dr < 52 ) ? h.getAbsolutePosition() + dr : h.getAbsolutePosition() + dr - 52;
        }else{
            nextTilePos = h.getAbsolutePosition() + (50 - h.getRelativePosition());
        }
        boolean result = true;
        if(currentTile.getNumberOfHorseOfColor(h.getColor()) == 1 || (currentTile.getNumberOfHorseOfColor(h.getColor()) > 1 && dr % 2 != 0)){
            for(int i = h.getAbsolutePosition()+1; i <= nextTilePos; i++){
                Tile temporaryTile = tiles.get(i);
                if(temporaryTile.isThereBlock()){
                    result = false;
                }
            }
        }
        return result;
    }


    public void translateBlock(Horse h,int dr,boolean mode){
        //Initialisation
        Tile currentTile;
        if (mode) {
            currentTile = getTiles(h.getAbsolutePosition());
        }else{
            ArrayList<Tile> homeTiles = getHomeTiles(h.getColor());
            currentTile = (h.getAbsolutePosition() != 999) ? tiles.get(h.getAbsolutePosition()) : homeTiles.get(h.getRelativePosition()-51);
        }
        int modDr = (dr % 2 == 0) ? dr / 2 : dr;
        this.translateHorse(h,modDr,mode);
        if(dr % 2 == 0){
            for (Horse tempHorse : currentTile.getContent()){
                if(tempHorse.getColor() == h.getColor()){
                    this.translateHorse(tempHorse,modDr,mode);
                    break;
                }
            }
        }
    }

    public void translateHorse(Horse h,int dr, boolean mode){
        //Initializing variable
        int nextTilePos;
        Tile currentTile;
        Tile nextTile;

        //Fixe an issue where a block with even dr will be having mode as true while going on homeTiles
        if (!mode && h.getRelativePosition() + dr - 51 < 0) mode = true;

        if(mode) { //normal movement
            currentTile = getTiles(h.getAbsolutePosition());

            nextTilePos = (h.getAbsolutePosition() + dr < 52) ? h.getAbsolutePosition() + dr : h.getAbsolutePosition() + dr - 52;
            nextTile = getTiles(nextTilePos);
        }else{ //Home movement
            ArrayList<Tile> homeTiles = getHomeTiles(h.getColor());
            currentTile = (h.getAbsolutePosition() != 999) ? tiles.get(h.getAbsolutePosition()) : homeTiles.get(h.getRelativePosition()-51);
            nextTilePos = h.getRelativePosition() + dr - 51;
            nextTile = homeTiles.get(nextTilePos);
            nextTilePos = 999;
        }
        nextTile.clearContent(h.getColor());
        nextTile.addHorse(h);
        currentTile.yeetHorse(h);
        h.setAbsolutePosition(nextTilePos);
        h.addStep(dr);


    }

    public void moveHorse(Horse h, int dr) {
        if (h.getAbsolutePosition() == -1) { //Checking if the horse hasn't been out yet
            int startingTileIndex = getPlayer(h.getColor()).getStartingTile();
            Tile startingTile = getTiles(startingTileIndex);
            startingTile.addHorse(h);
            h.setAbsolutePosition(startingTileIndex);
            h.setRelativePosition(0);
            getBaseTiles(h.getColor()).get(h.getHorseId()).yeetHorse(h);

        }else if(h.getRelativePosition() + dr >= 51) { //On Home or going to be on home

            ArrayList<Tile> homeTiles = getHomeTiles(h.getColor());
            Tile currentTile = (h.getAbsolutePosition() != 999) ? tiles.get(h.getAbsolutePosition()) : homeTiles.get(h.getRelativePosition()-51);
            if (currentTile.getNumberOfHorseOfColor(h.getColor()) > 1) {
                translateBlock(h, dr,false);
            } else {
                translateHorse(h, dr,false);
            }


        }else { //Otherwise move it normally
            Tile currentTile = getTiles(h.getAbsolutePosition());

            if (currentTile.getNumberOfHorseOfColor(h.getColor()) > 1) {
                translateBlock(h, dr,true);
            } else {
                translateHorse(h, dr,true);
            }
        }

    }

    public void turn(Player player,int dr) {
        System.out.println("It's "+ player.getColor() + " Turn");
        System.out.println("You rolled a " + dr);
        ArrayList<Horse> playableHorse = player.getPlayableHorses(this.d,dr);
        System.out.println("Vous pouvez ainsi jouer :");

        if(playableHorse.size() != 0) {
            int n = 0;
            //play the user turn
            for (Horse h : playableHorse) {
                System.out.println(n);
                System.out.println(h.toString());
                n++;
            }
            Horse desiredHorse;
            if(player.getColor() == Color.RED){
                for (Horse h : playableHorse){
                    EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>(){
                        public void handle(javafx.scene.input.MouseEvent event){
                            if(!turnFinished) {
                                System.out.println("Juan pressed" + dr);
                                moveHorse(h, dr);
                                turnFinished = true;
                                for (Horse h : playableHorse){
                                    h.fireEvent(event);
                                }
                            }
                            h.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                            event.consume();
                        }
                    };
                    h.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseClick);
                }
            }else{
                desiredHorse = Bot.botPlay(playableHorse);
                moveHorse(desiredHorse,dr);
                turnFinished = true;
            }
        }else{
            System.out.println("You have no horse to move, passing turn");
            turnFinished = true;
        }

    }

    public void gameLoop(){
        ArrayList<Player> winOrder = new ArrayList<>();
        while (winOrder.size() != 3){
            for (Player player : getPlayers()){
                int dR = getD().getNbFaces();
                while (dR == getD().getNbFaces() && !player.isFinished()){
                    dR = getD().roll();
                    turn(player, dR);
                }
                if (!winOrder.contains(player) && player.isFinished()){
                    winOrder.add(player);
                }
            }
        }

        System.out.println("The game is finshed here's the result : ");
        for (Player p : winOrder){
            System.out.println(p.getColor());
        }

    } //TODO

    public void initialiseHorses() {
        /*for (Player p : getPlayers()) {
            for (Horse h : p.getLhorse()) {
                ArrayList<Tile> baseTiles = getBaseTiles(p.getColor());
                baseTiles.get(h.getHorseId()).addHorse(h);
            }
        }*/
        for (Color c : Color.values()){
            Player currentPlayer = this.getPlayer(c);
            for (Horse h : currentPlayer.getLhorse()){
                getBaseTiles(c).get(h.getHorseId()).addHorse(h);
            }

        }
    }


}
