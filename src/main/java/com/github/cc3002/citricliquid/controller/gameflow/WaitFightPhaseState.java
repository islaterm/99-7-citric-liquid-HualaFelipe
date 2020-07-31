package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricliquid.gui.GameScene;
import com.github.cc3002.citricliquid.gui.WaitFightPhaseScene;
import com.github.cc3002.citricliquid.gui.WaitPathPhaseScene;

public class WaitFightPhaseState extends PhaseState{

    /**
     * Method to call the move decision
     */
    @Override
    void moveDecision() {
        turnController.addPassedWaitFightPanel(turnController.getTurnOwner().getPanel());
        this.changeState(new MovingPhaseState());
    }

    /**
     * Method to call the fight decision
     */
    @Override
    void fightDecision(){
        int i = turnController.getTurnOwner().getPlayerFightChoice();
        if(-1<i && i<turnController.getTurnOwner().getPanel().getPlayers().size()){
            this.changeState(new BattlePhaseState(turnController.getTurnOwner().getPanel().getPlayers().get(turnController.getTurnOwner().getPlayerFightChoice())));
            //turnController.getTurnOwner().setPlayerFightChoice(0);
            return;
        }
        throw new RuntimeException();
    }

    /**
     * Change Fight Decision
     * @param i
     * @return
     */
    @Override
    int changeFightDecision(int i){
        if(0<=i && i<turnController.getTurnOwner().getPanel().getPlayers().size()){
            turnController.getTurnOwner().setPlayerFightChoice(i);
            return i;
        }
        throw new RuntimeException();
    }


    /**
     * return true if the WaitFightPhase is active
     * @return
     */
    @Override
    public boolean isWaitFightPhaseActive(){
        return true;
    }

    /**
     * Return the name phase
     * @return
     */
    @Override
    public String getNamePhase(){
        return "Wait Fight";
    }

    /**
     * Get the phase scene
     * @return
     */
    @Override
    public GameScene getPhaseScene(){
        return new WaitFightPhaseScene(turnController.getGameController());
    }

}
