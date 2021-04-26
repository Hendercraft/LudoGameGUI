package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    Board GameBoard = new Board(6);
    boolean diceLunched = false;
    int diceResult = 0;
    int currentPlayer = 0;
    boolean multiPlayer;

    @Override
    public void start(Stage stage) {
        //Dice display
        Text diceDisplay = new Text("Welcome");
        diceDisplay.setTranslateX(550);
        diceDisplay.setTranslateY(-150);

        //Initialization of the text box when a player win
        Text winText = new Text("");
        winText.setTranslateX(0);
        winText.setTranslateY(-450);


        //Next turn Button
        Button nextTurnButton = new Button("Next Turn");
        nextTurnButton.setOnAction(event -> {
            if(this.GameBoard.isTurnFinished()){
                if(GameBoard.getPlayers().get(currentPlayer).isFinished()){
                    winText.setText(GameBoard.getPlayers().get(currentPlayer).getColor() + " won the game");
                    winText.setFont(new Font("Arial",24));
                    winText.setFill(javafx.scene.paint.Color.BLACK);
                }
                if(diceResult != 6 ){
                    currentPlayer = (currentPlayer + 1 < 4) ? currentPlayer + 1 : 0;
                }
                System.out.println("You finished your turn");
                diceLunched = false;
                GameBoard.setTurnFinished(false);
                diceResult = GameBoard.getD().roll();
                diceDisplay.setText(GameBoard.getPlayers().get(currentPlayer).getColor() + " roll a " + diceResult);
                GameBoard.turn(GameBoard.getPlayers().get(currentPlayer),diceResult,multiPlayer);
            }
            event.consume();
        });
        nextTurnButton.setTranslateX(550);
        nextTurnButton.setScaleX(2);
        nextTurnButton.setScaleY(2);

        //Board image view
        final String boardLoc = "b.png";
        ImageView bg = new ImageView(boardLoc);

        //Initialization of the StackPane
        StackPane pain = new StackPane(bg,nextTurnButton,diceDisplay,winText);
        //Adding Horses to the StackPane
        for(Player player : GameBoard.getPlayers()) {
            for (Horse h : player.getLhorse()) {
                pain.getChildren().add(h);
            }
        }
       //Initialization of the scene
        Scene Game = new Scene(pain, 1500,800);

        //InitialisingStart scene

        Button btnSinglePlayer = new Button("Single Player");
        btnSinglePlayer.setOnAction(event -> {
            stage.close();
            Stage st = new Stage();
            st.setTitle("Ludo Game Gimp Edition : Single Player");
            multiPlayer = false;
            st.setScene(Game);
            st.show();
            event.consume();
        });

        Button btnMultiPlayers = new Button("Multi Player");
        btnMultiPlayers.setOnAction(event -> {
            stage.close();
            Stage st = new Stage();
            st.setTitle("Ludo Game Gimp Edition : Multi Player");
            st.setScene(Game);
            multiPlayer = true;
            st.show();
            event.consume();

        });
        btnMultiPlayers.setTranslateY(-50);

        //Adding buttons to the startingPane
        StackPane startingPane = new StackPane(btnSinglePlayer,btnMultiPlayers);
        //Creating a a starting scene
        Scene StartScene = new Scene(startingPane, 800, 500);
        stage.setTitle("Welcome !!");
        stage.setScene(StartScene);
        //Showing the start window
        stage.show();

        GameBoard.initialiseHorses(); //Put horses on their base tiles
        //Play the 1st turn;
        diceResult = GameBoard.getD().roll();
        diceDisplay.setText("Red roll a " + diceResult);
        GameBoard.turn(GameBoard.getPlayer(Color.RED),diceResult,multiPlayer);

    }

    public static void main(String[] args) {
        App.launch();

    }
}