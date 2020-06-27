package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.unit.lifestate.ILifeState;

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


    //Defend and evade functions in response to the different types of attackers
    //Player (Player)
    void defendPlayerAttack(final Player attacker);
    void evadePlayerAttack(final Player attacker);
    //Wild Unit
    void defendWildUnitAttack(final WildUnit attacker);
    void evadeWildUnitAttack(final WildUnit attacker);
    //Boss Unit
    void defendBossUnitAttack(final BossUnit attacker);
    void evadeBossUnitAttack(final BossUnit attacker);

    boolean isAlive();
    void goDead();

    void attack(Unit receiver, TypeCombat typeCombat);

    void setLifeState(ILifeState lifeState);


}
