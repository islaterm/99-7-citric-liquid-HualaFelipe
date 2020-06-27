package com.github.cc3002.citricjuice.model.unit.lifestate;

import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Unit;

public class Dead extends AbstractLifeState {

    public Dead(Unit unit){
        super(unit);
    }

    @Override
    public void changeState() {
        unit.setLifeState(new Alive(unit));
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public boolean isDead() {
        return true;
    }


}
