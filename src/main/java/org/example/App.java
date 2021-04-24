package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;



/**
 * JavaFX App
 */
public class App extends Application {


    public ImageView generateSprite(String path){
        ImageView juan = new ImageView(path);
        Image bluej = new Image("blue.png");
        juan.setScaleX(0.05);
        juan.setScaleY(0.05);
        juan.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Juan pressed ");
            juan.setImage(bluej);
            juan.setTranslateX(100.00);
            event.consume();
        });
        return juan;
    }


    @Override
    public void start(Stage stage) {

        final String boardLoc = "b.png";

        Rectangle box = new Rectangle(200,100, Color.BLANCHEDALMOND);

        //Image board = new Image(boardLoc,true);
        ImageView bg = new ImageView(boardLoc);
        ImageView redJuan = generateSprite("red.png");
        redJuan.setX(200);








        StackPane pane = new StackPane(bg,box,redJuan);
        var scene = new Scene(pane, 800, 500);
        stage.setScene(scene);
        stage.show();
    }

}