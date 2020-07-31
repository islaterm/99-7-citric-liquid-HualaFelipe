package com.github.cc3002.citricjuice.model.unit.lifestate;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Unit;

public abstract class AbstractLifeState implements ILifeState{
    protected Unit unit;

    /**
     * Constructor for an AbstractLifeState
     * @param unit
     */
    public AbstractLifeState(Unit unit){
        this.unit = unit;
    }

}
