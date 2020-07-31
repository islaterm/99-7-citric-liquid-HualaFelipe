package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class WaitFightPhaseScene extends InGameScene {

    private Text movementText;

    /**
     * Create a WaitFightPhaseScene
     * @param gameController
     */
    public WaitFightPhaseScene(GameController gameController) {
        super(gameController);
        create();
    }

    /**
     * Create Method
     */
    private void create(){
        movementText = new Text("Remaining movement: "+ gameController.getTurnController().getDice());
        movementText.setStyle("    -fx-font-size: 18px;\n");
        top.getChildren().add(movementText);

        Button moveButton = new Button("Move");

        moveButton.setPrefSize(160, 20);
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
                "    -fx-padding: 8;");

        moveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.getTurnController().moveDecision();
                gameController.getGameGUI().setGameScene(gameController.getTurnController().getState().getPhaseScene());
            }
        });

        top.getChildren().add(moveButton);

        VBox buttons = new VBox(10);

        buttons.setAlignment(Pos.CENTER);

        Text fightText = new Text("Fight with:");
        buttons.getChildren().add(fightText);


        List<Player> players = gameController.getTurnOwner().getPanel().getPlayers();
        players.remove(gameController.getTurnOwner());

        for(int i=0; i<players.size();i++){
            Button button = new Button(gameController.getTurnOwner().getPanel().getPlayers().get(i).getName());
            int option = i;
            button.setPrefSize(60, 15);
            button.setStyle("-fx-background-color: \n" +
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
                    "    -fx-font-size: 10px;\n" +
                    "    -fx-padding: 5;");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    gameController.getTurnController().changeFightDecision(option);
                    gameController.getTurnController().fightDecision();
                    gameController.getGameGUI().setGameScene(gameController.getTurnController().getState().getPhaseScene());
                }
            });

            buttons.getChildren().add(button);
        }

        top.getChildren().add(buttons);



    }
}
