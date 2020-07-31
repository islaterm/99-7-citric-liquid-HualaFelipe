package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class NewGameScene extends GameScene {
    /**
     * Creates a NewGameScene
     * @param gameController
     */
    public NewGameScene(GameController gameController) {
        super(gameController);
        create();
    }

    /**
     * Create method
     */
    private void create(){

        Text label1 = new Text("Hello Welcome to 99.7% Citric Liquid");
        top.getChildren().add(label1);

        Button newGame = new Button("Start Game");

        newGame.setPrefSize(160, 20);
        newGame.setStyle("-fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.newGame();
                gameController.getGameGUI().setGameScene(gameController.getTurnController().getState().getPhaseScene());
            }
        });

        center.getChildren().add(newGame);



    }

}
