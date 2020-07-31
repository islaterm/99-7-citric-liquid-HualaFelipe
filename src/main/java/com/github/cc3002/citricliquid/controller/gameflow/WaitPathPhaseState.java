package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricliquid.gui.GameScene;
import com.github.cc3002.citricliquid.gui.StartTurnPhaseScene;
import com.github.cc3002.citricliquid.gui.WaitPathPhaseScene;

public class WaitPathPhaseState extends PhaseState {
    /**
     * Call the moveOne method to move one panel when you made the choice
     */
    @Override
    void moveOne(){
        int movement = turnController.getTurnOwner().move(1);
        turnController.setDice(turnController.getDice()-movement);
        if(turnController.getDice()==0){
            turnController.getTurnOwner().getPanel().activatedBy(turnController.getTurnOwner());
            turnController.setPhaseState(new EndTurnPhaseState());
        }
    }

    /**
     * Call the continue movement decision
     */
    @Override
    void continueMovement(){
        turnController.addPassedWaitPathPanel(turnController.getTurnOwner().getPanel());
        turnController.getState().moveOne();
        turnController.getTurnOwner().setPlayerPathChoice(0);
        turnController.getState().changeState(new MovingPhaseState());
        turnController.getState().move();
    }

    /**
     * Change the player path decision
     * @param i
     * @return
     */
    int changePlayerPathDecision(int i){

        if(-1<i && i<turnController.getTurnOwner().getPanel().getNextPanels().size()){
            turnController.getTurnOwner().setPlayerPathChoice(i);
            return i;
        }
        throw new RuntimeException();
    }

    /**
     * return true if the waitPath is active
     * @return
     */
    @Override
    public boolean isWaitPathPhaseActive(){
        return true;
    }

    /**
     * Get the name phase
     * @return
     */
    @Override
    public String getNamePhase(){
        return "Wait Path";
    }

    /**
     * Get the phase scene
     * @return
     */
    @Override
    public GameScene getPhaseScene(){
        return new WaitPathPhaseScene(turnController.getGameController());
    }


}
