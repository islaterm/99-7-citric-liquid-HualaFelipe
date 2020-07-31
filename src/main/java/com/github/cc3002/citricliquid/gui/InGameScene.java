package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricliquid.controller.gameflow.GameController;

public class InGameScene extends GameScene {
    /**
     * Creates a InGameScene
     * @param gameController
     */
    public InGameScene(GameController gameController) {
        super(gameController);
        createInGameBase();
    }

    /**
     * Create InGame base (Map + Player Panel)
     */
    private void createInGameBase(){
        createPlayerPanel();
        createMap();
    }

}
