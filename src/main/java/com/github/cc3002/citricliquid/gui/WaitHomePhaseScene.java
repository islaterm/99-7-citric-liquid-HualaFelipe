package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class WaitHomePhaseScene extends  InGameScene {
    private Text movementText;

    /**
     * Create a WaitHomePhaseScene
     * @param gameController
     */
    public WaitHomePhaseScene(GameController gameController) {
        super(gameController);
        create();
    }

    /**
     * Create method
     */
    private void create(){

        movementText = new Text("Remaining movement: "+ gameController.getTurnController().getDice());
        movementText.setStyle("    -fx-font-size: 18px;\n");
        top.getChildren().add(movementText);

        Button moveButton = new Button("Move");

        moveButton.setPrefSize(80, 20);
        moveButton.setStyle("-fx-background-color: \n" +
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
                "    -fx-padding: 5;");

        moveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().moveDecision();
                gameController.getGameGUI().setGameScene(gameController.getTurnController().getState().getPhaseScene());
            }
        });

        top.getChildren().add(moveButton);

        Button endTurnButton = new Button("End Turn");

        endTurnButton.setPrefSize(80, 20);
        endTurnButton.setStyle("-fx-background-color: \n" +
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
                "    -fx-padding: 5;");

        endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().endTurnDecision();
                gameController.getGameGUI().setGameScene(gameController.getTurnController().getState().getPhaseScene());
            }
        });

        top.getChildren().add(endTurnButton);


    }
}
