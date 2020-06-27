package com.github.cc3002.citricjuice.model.gameflow.phases;

import com.github.cc3002.citricjuice.model.gameflow.AbstractPhase;
import com.github.cc3002.citricjuice.model.gameflow.Turn;

public class Phase2 extends AbstractPhase {

    public Phase2(Turn turn) {
        super(turn);
    }

    @Override
    public void start() {
        turn.getPlayer().increaseStarsBy(turn.getChapter().getGame().getChapters().size()/5+1);
    }

}
