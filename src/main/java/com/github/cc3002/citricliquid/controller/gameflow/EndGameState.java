package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricjuice.model.unit.Player;

public class EndGameState extends GameState {
    private Player winner;

    /**
     * Create an EndGameState
     * @param player
     */
    public EndGameState(Player player) {
        this.winner=player;
    }

    /**
     * Get de winner
     * @return
     */
    @Override
    Player getWinner(){
        return  this.winner;
    }


    /**
     * Return true if the EndGameState is active
     * @return
     */
    @Override
    public boolean isEndGameStateActive(){
        return true;
    }



}
