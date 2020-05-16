package com.github.cc3002.citricjuice.model;

public interface Unit {
    void setSeed(final long seed);
    int roll();
    String getName();
    int getMaxHP();
    int getAtk();
    int getDef();
    int getEvd();
    int getCurrentHP();
    void setCurrentHP(final int newHP);
}
