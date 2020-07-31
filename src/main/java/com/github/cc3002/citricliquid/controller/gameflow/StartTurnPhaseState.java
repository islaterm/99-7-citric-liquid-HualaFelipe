package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricliquid.gui.GameScene;
import com.github.cc3002.citricliquid.gui.StartTurnPhaseScene;

public class StartTurnPhaseState extends PhaseState {
    /**
     * Method to start a turn
     */
    @Override
    void startTurn(){

        if(turnController.getTurnOwner().getPanel().getId()  == turnController.getTurnOwner().getHomePanel().getId()){
            turnController.setPassedWaitHomePhase(true);
        }

        if(turnController.getTurnOwner().getLifeState().isDead()){
            this.changeState(new RecoveryPhaseState());
        }else{
            turnController.getTurnOwner().increaseStarsBy(turnController.getChapter()/5+1);
            this.changeState(new MovingPhaseState());
        }
    }

    /**
     * return true if the startTurnPhase is active
     * @return
     */
    @Override
    public boolean isStarTurnPhaseActive(){
        return true;
    }

    /**
     * Return the name phase
     * @return
     */
    @Override
    public String getNamePhase(){
        return "Start Turn";
    }

    /**
     * Get the phase scene
     * @return
     */
    @Override
    public GameScene getPhaseScene(){
        return new StartTurnPhaseScene(turnController.getGameController());
    }

}
