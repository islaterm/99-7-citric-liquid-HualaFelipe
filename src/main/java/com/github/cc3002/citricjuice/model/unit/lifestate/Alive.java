package com.github.cc3002.citricjuice.model.unit.lifestate;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Unit;

public class Alive extends AbstractLifeState {
    public Alive(Unit unit){
        super(unit);
    }

    @Override
    public void changeState() {
        unit.setLifeState(new Dead(unit));
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public boolean isDead() {
        return false;
    }
}
