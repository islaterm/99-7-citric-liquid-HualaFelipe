package com.github.cc3002.citricliquid.controller.gameflow;

public class NewGameState extends GameState {

    /**
     * Launch a new game
     */
    @Override
    public void newGame(){
        gameController.newGame();
        changeState(new InGameState());
    }

    /**
     * Return true if NewGameState Is Active
     * @return
     */
    @Override
    public boolean isNewGameStateActive(){
        return true;
    }
}
