package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import javafx.scene.text.Text;

public class EndGameScene extends GameScene {

    /**
     * Constructor for a EndGameScene
     * @param gameController
     */
    public EndGameScene(GameController gameController) {
        super(gameController);
        create();
    }

    /**
     * Create method
     */
    private void create() {
        top.getChildren().add(new Text("Fin del juego: \nGanador: "+gameController.getWinner().getName()));
    }


}
