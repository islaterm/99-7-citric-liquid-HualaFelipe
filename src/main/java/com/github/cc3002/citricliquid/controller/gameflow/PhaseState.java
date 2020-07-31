package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricjuice.model.unit.TypeCombat;
import com.github.cc3002.citricjuice.model.unit.Unit;
import com.github.cc3002.citricliquid.controller.handlers.IHandler;
import com.github.cc3002.citricliquid.gui.GameScene;

import java.beans.PropertyChangeSupport;

public class PhaseState {
    protected TurnController turnController;

    /**
     * Set the turn controller
     * @param turnController
     */
    public void setTurnController(TurnController turnController) {
        this.turnController = turnController;
    }

    /**
     * Change the state of a PhaseState
     * @param aPhaseState
     */
    protected void changeState(PhaseState aPhaseState) {
        turnController.setPhaseState(aPhaseState);
    }

    /**
     * Launch an error (RuntimeException())
     */
    void error() { throw new RuntimeException(); }

    /**
     * Default error for all the method
     */
    void startTurn(){error();}
    void roll(){error();}
    void move(){error();}
    void continueMovement(){error();}
    void moveDecision() {
        error();
    }
    void endTurnDecision(){
        error();
    }

    int changePlayerPathDecision(int i){
        throw new RuntimeException();
    }
    void fightDecision(){error();}
    int changeFightDecision(int i){
        throw new RuntimeException();
    }

    void attack(){
        error();
    }

    void rollAtkAttack(){
        error();
    }

    void rollDefAttack(){
        error();
    }
    void selectTypeCombat(TypeCombat typeCombat){
        error();
    }

    void rollAtkContraAttack(){
        error();
    }
    void rollDefContraAttack(){
        error();
    }

    void contraAttack(){
        error();
    }

    void endBattle(){
        error();
    }
    void rollRecovery(){
        error();
    }

    void moveOne(){
            error();
    }

    public String getNamePhase(){
        return "Phase";
    }
    public GameScene getPhaseScene(){
        throw new RuntimeException();
    }

    public Unit getReceiver(){
        throw new RuntimeException();
    }

    /**
     * All the states set default false
     */
    public boolean isStarTurnPhaseActive(){
        return false;
    }

    public boolean isMovingPhaseActive(){
        return false;
    }

    public boolean isRecoveryPhaseActive(){
        return false;
    }

    public boolean isWaitFightPhaseActive(){
        return false;
    }

    public boolean isBattlePhaseActive(){
        return false;
    }

    public boolean isWaitHomePhaseActive(){
        return false;
    }

    public boolean isWaitPathPhaseActive(){
        return false;
    }

    public boolean isEndTurnPhaseActive(){
        return false;
    }




}
