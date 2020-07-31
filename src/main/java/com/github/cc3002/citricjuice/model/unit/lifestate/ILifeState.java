package com.github.cc3002.citricjuice.model.unit.lifestate;

public interface ILifeState {
    void changeState();
    boolean isAlive();
    boolean isDead();
}
