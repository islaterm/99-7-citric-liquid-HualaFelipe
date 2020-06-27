package com.github.cc3002.citricjuice.model.gameflow.phases;

import com.github.cc3002.citricjuice.model.gameflow.AbstractPhase;
import com.github.cc3002.citricjuice.model.gameflow.Turn;

public class Phase6 extends AbstractPhase {

    public Phase6(Turn turn) {
        super(turn);
    }

    @Override
    public void start() {
        turn.getPlayer().getPanel().activatedBy(turn.getPlayer());
    }

}
