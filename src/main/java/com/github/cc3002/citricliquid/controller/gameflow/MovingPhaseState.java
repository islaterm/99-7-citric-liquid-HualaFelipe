package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricliquid.gui.GameScene;
import com.github.cc3002.citricliquid.gui.MovingPhaseScene;
import com.github.cc3002.citricliquid.gui.WaitPathPhaseScene;

import java.util.Random;

public class MovingPhaseState extends PhaseState {
    /**
     * Move the actual player the dice number
     */
    @Override
    void move(){
        int movement = turnController.getTurnOwner().move(turnController.getDice());
        turnController.setDice(turnController.getDice()-movement);
        if(turnController.getDice()==0){
            turnController.getTurnOwner().getPanel().activatedBy(turnController.getTurnOwner());

            if(turnController.getTurnOwner().getPanel().getPlayers().size()>1){
                turnController.setDice(-1);
                turnController.setPhaseState(new WaitFightPhaseState());
            }

            if(turnController.getTurnOwner().getPanel().isEncounterPanel()){
                Random r = new Random();
                int randomInt = r.nextInt(turnController.getGameController().getWilds().size()-1);
                turnController.setPhaseState(new BattlePhaseState(turnController.getGameController().getWilds().get(randomInt)));
            }else if(turnController.getTurnOwner().getPanel().isBossPanel()){
                Random r = new Random();
                int randomInt = r.nextInt(turnController.getGameController().getBosses().size()-1);
                turnController.setPhaseState(new BattlePhaseState(turnController.getGameController().getBosses().get(randomInt)));
            }else{
                turnController.setPhaseState(new EndTurnPhaseState());
            }

        }

        if(turnController.getDice()==-1){
            turnController.setPhaseState(new EndTurnPhaseState());
        }


    }

    /**
     * Roll the dice and set the value
     */
    @Override
    void roll(){
        turnController.setDice(turnController.getTurnOwner().roll());
    }

    /**
     * Return true if the movingPhase is active
     * @return
     */
    @Override
    public boolean isMovingPhaseActive(){
        return true;
    }

    /**
     * Get the name phase
     * @return
     */
    @Override
    public String getNamePhase(){
        return "Moving";
    }

    /**
     * Get the GameScene of the phase
     * @return
     */
    @Override
    public GameScene getPhaseScene(){
        return new MovingPhaseScene(turnController.getGameController());
    }

}
