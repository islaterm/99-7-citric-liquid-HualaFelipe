package com.github.cc3002.citricjuice.model.gameflow;

public abstract class AbstractPhase implements IPhase {
    protected Turn turn;

    public AbstractPhase(Turn turn){
        this.turn = turn;
    }


}
