package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricliquid.model.NormaGoal;

public class NormaClearState extends GameState{

    /**
     * Select the norma goal for the current player
     * @param normaGoal
     */
    @Override
    public void selectNormaGoal(NormaGoal normaGoal){
        gameController.setCurrPlayerNormaGoal(normaGoal);
        this.changeState(new InGameState());
    }

    /**
     * Return true if NormaCheckState is active
     * @return
     */
    @Override
    public boolean isNormaCheckStateActive(){
        return true;
    }
}
