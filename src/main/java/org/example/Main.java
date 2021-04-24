package org.example;



public class Main {

    public static void main(String[] args) {
        Board GameBoard = new Board(6);

       /* Player red = GameBoard.getPlayer(Color.RED);
        Player blue = GameBoard.getPlayer(Color.BLUE);

        blue.getLhorse().get(0).setRelativePosition(37);
        blue.getLhorse().get(0).setAbsolutePosition(50);
        GameBoard.getTiles(50).addHorse(blue.getLhorse().get(0));

        blue.getLhorse().get(1).setRelativePosition(37);
        blue.getLhorse().get(1).setAbsolutePosition(50);
        GameBoard.getTiles(50).addHorse(blue.getLhorse().get(1));

        red.getLhorse().get(0).setAbsolutePosition(47);
        red.getLhorse().get(0).setRelativePosition(47);
        GameBoard.getTiles(47).addHorse(red.getLhorse().get(0));

        red.getLhorse().get(1).setAbsolutePosition(47);
        red.getLhorse().get(1).setRelativePosition(47);
        GameBoard.getTiles(47).addHorse(red.getLhorse().get(1)); */

        GameBoard.gameLoop();
        App.launch();
    }
}
