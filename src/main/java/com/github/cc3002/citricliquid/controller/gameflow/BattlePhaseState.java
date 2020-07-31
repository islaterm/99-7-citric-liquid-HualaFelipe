package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.TypeCombat;
import com.github.cc3002.citricjuice.model.unit.Unit;
import com.github.cc3002.citricliquid.gui.BattlePhaseScene;
import com.github.cc3002.citricliquid.gui.GameScene;

public class BattlePhaseState  extends PhaseState{
    private Unit receiver;

    /**
     * Constructor
     * @param receiver
     */
    public BattlePhaseState(Unit receiver) {
        this.receiver = receiver;
    }

    /**
     * Attack method
     */
    @Override
    void attack(){
        turnController.getTurnOwner().attack(receiver,turnController.getTypeCombat());
    }

    /**
     * Counter Attack method
     */
    @Override
    void contraAttack(){
        receiver.attack(turnController.getTurnOwner(),turnController.getTypeCombat());
    }

    /**
     * Roll Attack in Attack
     */
    @Override
    void rollAtkAttack(){
        turnController.getTurnOwner().rollAtk();
    }


    /**
     * Roll defense in Defense
     */
    @Override
    void rollDefAttack(){
        receiver.rollDef();
    }

    /**
     * Roll Attack in counter attack
     */
    @Override
    void rollAtkContraAttack(){
        receiver.rollAtk();
    }

    /**
     * Roll Defense in counter attack
     */
    @Override
    void rollDefContraAttack(){
        turnController.getTurnOwner().rollDef();
    }

    /**
     * Select the type of combat
     * @param typeCombat
     */
    @Override
    void selectTypeCombat(TypeCombat typeCombat){
        turnController.setTypeCombat(typeCombat);
    }

    /**
     * Ends the battle
     */
    @Override
    void endBattle(){
        this.changeState(new EndTurnPhaseState());
    }


    /**
     * Return true if the battle phase is active
     * @return
     */
    @Override
    public boolean isBattlePhaseActive(){
        return true;
    }

    /**
     * Get name phase
     * @return
     */
    @Override
    public String getNamePhase(){
        return "Battle";
    }

    /**
     * Gets the GameScene associate
     * @return
     */
    @Override
    public GameScene getPhaseScene(){
        return new BattlePhaseScene(turnController.getGameController());
    }

    /**
     * Get the Unit receiver of the attack
     * @return
     */
    @Override
    public Unit getReceiver(){
        return receiver;
    }


}
