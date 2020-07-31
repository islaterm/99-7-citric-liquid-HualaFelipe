package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricliquid.gui.GameScene;
import com.github.cc3002.citricliquid.gui.WaitHomePhaseScene;
import com.github.cc3002.citricliquid.gui.WaitPathPhaseScene;

public class WaitHomePhaseState extends PhaseState{

    public WaitHomePhaseState() {
        super();
    }

    /**
     * Method to call a move decision
     */
    @Override
    void moveDecision() {
        this.turnController.setPassedWaitHomePhase(true);
        this.changeState(new MovingPhaseState());
    }

    /**
     * Method to call an end turn decision (HomePanel)
     */
    @Override
    void endTurnDecision(){
        this.turnController.setPassedWaitHomePhase(true);
        turnController.getTurnOwner().getPanel().activatedBy(turnController.getTurnOwner());
        this.changeState(new EndTurnPhaseState());
    }


    /**
     * Return true if the home phase is active
     * @return
     */
    @Override
    public boolean isWaitHomePhaseActive(){
        return true;
    }

    /**
     * Get the name phase
     * @return
     */
    @Override
    public String getNamePhase(){
        return "Wait Home";
    }

    /**
     * Get the GameScene
     * @return
     */
    @Override
    public GameScene getPhaseScene(){
        return new WaitHomePhaseScene(turnController.getGameController());
    }

}
