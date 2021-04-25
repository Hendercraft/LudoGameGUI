package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    Board GameBoard = new Board(6);
    boolean diceLunched = false;
    int diceResult = 0;
    int currentPlayer = 0;
    int i = 0;

    @Override
    public void start(Stage stage) {
        Horse juan = GameBoard.getPlayers().get(0).getLhorse().get(0);
        GameBoard.getTiles(0).addHorse(juan);

        //Next turn Button
        Button nextTurnButton = new Button("Next Turn");
        nextTurnButton.setOnAction(event -> {
            if(this.GameBoard.isTurnFinished()){
                if(diceResult != 6 ){
                    currentPlayer = (currentPlayer + 1 < 4) ? currentPlayer + 1 : 0;
                }
                System.out.println("You try to pass the turn");
                diceLunched = false;
                GameBoard.setTurnFinished(false);
                diceResult = GameBoard.getD().roll();
                GameBoard.turn(GameBoard.getPlayers().get(currentPlayer),diceResult);
            }
            event.consume();
        });
        nextTurnButton.setTranslateX(550);
        nextTurnButton.setScaleX(2);
        nextTurnButton.setScaleY(2);

        //Try
        Button Try = new Button("MoveHorse");
        Try.setOnAction(event -> {


            GameBoard.getTiles(i+1).addHorse(juan);
            GameBoard.getTiles(i).yeetHorse(juan);
            i++;
            event.consume();
        });
        Try.setTranslateX(550);
        Try.setTranslateY(-400);
        Try.setScaleX(2);
        Try.setScaleY(2);

        final String boardLoc = "b.png";

        ImageView bg = new ImageView(boardLoc);

        ImageView test = new ImageView("yellow.png");

        StackPane pain = new StackPane(bg,nextTurnButton,Try);


        for(Player player : GameBoard.getPlayers()) {
            for (Horse h : player.getLhorse()) {
                pain.getChildren().add(h);

            }
        }

        /*pain.getChildren().add(GameBoard.getPlayer(Color.RED).getLhorse().get(0));
        GameBoard.getPlayer(Color.RED).getLhorse().get(0).setTranslateX(50);
        GameBoard.getPlayer(Color.RED).getLhorse().get(0).setTranslateY(-300);*/




        var Game = new Scene(pain, 800,500);


        //Start scene

        Button btn = new Button("Start the game");
        btn.setOnAction(event -> {
            System.out.println("Hello World!");
            stage.close();
            Stage st = new Stage();
            st.setTitle("ppepep");
            st.setScene(Game);
            st.show();
            //GameBoard.gameLoop();


            System.out.println(bg.getX() + "|-|" + bg.getY());
            event.consume();
        });



        StackPane startingPane = new StackPane(btn);
        var StartScene = new Scene(startingPane, 800, 500);
        stage.setTitle("Welcome !!");
        stage.setScene(StartScene);

        stage.show();
        GameBoard.initialiseHorses();
        diceResult = GameBoard.getD().roll();
        GameBoard.turn(GameBoard.getPlayer(Color.RED),diceResult);


       /* yellowJuan.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            stage.close();
            Stage st = new Stage();
            st.setTitle("ppepep");
            st.setScene(Game);
            st.show();
        });
        */


    }

    public static void main(String[] args) {
        App.launch();

    }
}