package org.example;


import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Board {

    private static final ArrayList<Player> players = generatePlayers();
    private static final ArrayList<Tile> tiles = generateTiles();
    private static final ArrayList<Tile> redHomeTiles = generateHomeTiles();
    private static final ArrayList<Tile> blueHomeTiles = generateHomeTiles();
    private static final ArrayList<Tile> yellowHomeTiles = generateHomeTiles();
    private static final ArrayList<Tile> greenHomeTiles = generateHomeTiles();


    private Dice d;



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

            if(i == 24){
                x = tiles.get(i-1).getX() - 50.0;
                y = tiles.get(i-1).getY();
            }else if(i == 25){
                x = tiles.get(i-1).getX() - 50.0;
                y = tiles.get(i-1).getY();
            }
            else {
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

    private static ArrayList<Tile> generateHomeTiles(){
        ArrayList<Tile> homeTiles = new ArrayList<>();
        for(int i = 0;i<6;i++){
            homeTiles.add(new Tile());
            homeTiles.get(i).setSafe(true);
        }
        return homeTiles;
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

        nextTile.addHorse(h);
        currentTile.yeetHorse(h);
        h.setAbsolutePosition(nextTilePos);
        h.addStep(dr);

        nextTile.clearContent(h.getColor());
    }


    /*public void translateHorseHome(Horse h,int dr){
        ArrayList<Tile> homeTiles = getHomeTiles(h.getColor());
        Tile currentTile = (h.getAbsolutePosition() != 999) ? tiles.get(h.getAbsolutePosition()) : homeTiles.get(h.getRelativePosition()-51);

        int nextIndex = h.getRelativePosition() + dr - 51;
        Tile nextTile = homeTiles.get(nextIndex);

        nextTile.addHorse(h);
        currentTile.yeetHorse(h);
        h.addStep(dr);
        h.setAbsolutePosition(999);
    }
    */


    public void moveHorse(Horse h, int dr) {
        if (h.getAbsolutePosition() == -1) { //Checking if the horse hasn't been out yet
            int startingTileIndex = getPlayer(h.getColor()).getStartingTile();
            Tile startingTile = getTiles(startingTileIndex);
            startingTile.addHorse(h);
            h.setAbsolutePosition(startingTileIndex);
            h.setRelativePosition(0);


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
        ArrayList <Horse> playableHorse = player.getPlayableHorses(this.d,dr);
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
                int horseID = -1;
               /* Scanner sc = new Scanner(System.in);

                String input = sc.nextLine();
                int id = Integer.parseInt(input);
                desiredHorse = playableHorse.get(id);*/
                for (Horse h : playableHorse){
                    ImageView sprite  = h;
                    EventHandler<MouseEvent> mouseClick = new EventHandler<>(){
                        public void handle(javafx.scene.input.MouseEvent event){
                            //moveHorse(h,dr);
                            System.out.println("Juan pressed");
                            for(Horse h : playableHorse) {
                                h.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                            }
                        }
                    };
                    sprite.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseClick);
                }
                while (horseID == -1){}
                moveHorse(playableHorse.get(horseID),dr);
            }else{
                desiredHorse = Bot.botPlay(playableHorse);
                System.out.println("Bot " + player.getColor());
                System.out.println("He played :");
                System.out.println(desiredHorse);
                System.out.println("press enter to continue");

            }

        }else{
            System.out.println("You have no horse to move, passing turn");
        }
        //TODO userSelection

        //if()
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

}
