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

    Board GameBoard;
    boolean turnFinished = false;
    boolean diceLunched = false;
    int diceResult = 0;
    int currentPlayer = 0;


    @Override
    public void start(Stage stage) {

        //Next turn Button
        Button nextTurnButton = new Button("Next Turn");
        nextTurnButton.setOnAction(event -> {
            if(turnFinished){
                if(diceResult != 6 ){
                    currentPlayer = (currentPlayer + 1 < 3) ? currentPlayer + 1 : 0;
                }
                diceLunched = false;
                turnFinished = false;
                diceResult = GameBoard.getD().roll();
                GameBoard.turn(GameBoard.getPlayers().get(currentPlayer),diceResult);
            }
            event.consume();
        });
        nextTurnButton.setTranslateX(800);

        Board GameBoard = new Board(6);
        final String boardLoc = "b.png";

        //Image board = new Image(boardLoc,true);
        ImageView bg = new ImageView(boardLoc);

        ImageView test = new ImageView("yellow.png");
        //GameBoard
        StackPane pain = new StackPane(bg,nextTurnButton);

        /*for (Player player: GameBoard.getPlayers()){
            for(Horse h : player.getLhorse()){
                pain.getChildren().add(h);

            }
        }*/
        pain.getChildren().add(GameBoard.getPlayer(Color.RED).getLhorse().get(0));
        GameBoard.getPlayer(Color.RED).getLhorse().get(0).setTranslateX(50);
        GameBoard.getPlayer(Color.RED).getLhorse().get(0).setTranslateY(-300);




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

            //GameBoard.turn(GameBoard.getPlayer(Color.RED),6);
            System.out.println(bg.getX() + "|-|" + bg.getY());
            event.consume();
        });



        StackPane startingPane = new StackPane(btn);
        var StartScene = new Scene(startingPane, 800, 500);
        stage.setTitle("Welcome !!");
        stage.setScene(StartScene);

        stage.show();






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