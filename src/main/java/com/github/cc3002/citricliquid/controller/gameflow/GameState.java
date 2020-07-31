package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricliquid.model.NormaGoal;

public class GameState {
    protected GameController gameController;

    /**
     * Set GameController
     * @param gameController
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Change the game state
     * @param aGameState
     */
    protected void changeState(GameState aGameState) {
        gameController.setGameState(aGameState);
    }

    /**
     * Launch RuntimeException() error
     */
    void error() { throw new RuntimeException(); }

    /**
     * Start a new Game (default error)
     */
    public void newGame(){
        error();
    }

    /**
     * Get the game winner  (default error)
     * @return
     */
    Player getWinner(){
        throw new RuntimeException();
    }

    /**
     * Select Norma Goal of the actual player (default error)
     * @param normalGoal
     */
    public void selectNormaGoal(NormaGoal normalGoal){
        throw new RuntimeException();
    }

    public boolean isNewGameStateActive(){
        return false;
    }
    public boolean isEndGameStateActive(){
        return false;
    }
    public boolean isInGameStateActive(){
        return false;
    }
    public boolean isNormaCheckStateActive(){
        return false;
    }

}
