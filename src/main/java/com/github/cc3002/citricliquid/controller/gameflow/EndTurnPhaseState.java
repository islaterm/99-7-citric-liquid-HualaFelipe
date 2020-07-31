package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricliquid.gui.EndTurnPhaseScene;
import com.github.cc3002.citricliquid.gui.GameScene;
import com.github.cc3002.citricliquid.gui.WaitPathPhaseScene;

public class EndTurnPhaseState extends PhaseState {

    /**
     * return true if the endTurnPhaseState is active
     * @return
     */
    @Override
    public boolean isEndTurnPhaseActive(){
        return true;
    }

    /**
     * Get de name phase
     * @return
     */
    @Override
    public String getNamePhase(){
        return "End Turn";
    }

    /**
     * Gets the GameScene associate
     * @return
     */
    @Override
    public GameScene getPhaseScene(){
        return new EndTurnPhaseScene(turnController.getGameController());
    }

}
