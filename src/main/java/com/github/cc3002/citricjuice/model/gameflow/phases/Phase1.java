package com.github.cc3002.citricjuice.model.gameflow.phases;

import com.github.cc3002.citricjuice.model.gameflow.AbstractPhase;
import com.github.cc3002.citricjuice.model.gameflow.Turn;

public class Phase1 extends AbstractPhase {

    public Phase1(Turn turn) {
        super(turn);
    }

    @Override
    public void start() {
        if(turn.getPlayer().getLifeState().isDead()){
            int roll = turn.getPlayer().roll();

            if(roll >= 7-turn.getChapter().getGame().getChapters().size()){
                turn.getPlayer().getLifeState().changeState();
            }else{
                this.turn.endTurn();
            }

        }
    }
}
