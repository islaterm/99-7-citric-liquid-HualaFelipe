package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricliquid.gui.GameScene;
import com.github.cc3002.citricliquid.gui.RecoveryPhaseScene;
import com.github.cc3002.citricliquid.gui.WaitPathPhaseScene;

public class RecoveryPhaseState extends PhaseState {
    /**
     * Roll Recovery for revive the current player
     */
    @Override
    void rollRecovery(){
        int roll = turnController.getTurnOwner().roll();
        int r = 6-(turnController.getChapter()-1);

        if(roll>=r){
            turnController.getTurnOwner().revive();
            this.changeState(new MovingPhaseState());
        }else{
            this.changeState(new EndTurnPhaseState());
        }
    }

    /**
     * Return true if the RecoveryPhase is active
     * @return
     */
    @Override
    public boolean isRecoveryPhaseActive(){
        return true;
    }

    /**
     * Get the name phase
     * @return
     */
    @Override
    public String getNamePhase(){
        return "Recovery";
    }

    /**
     * Get the phase scene
     * @return
     */
    @Override
    public GameScene getPhaseScene(){
        return new RecoveryPhaseScene(turnController.getGameController());
    }
}
