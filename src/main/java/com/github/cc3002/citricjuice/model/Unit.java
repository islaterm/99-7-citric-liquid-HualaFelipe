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
    //Attack functions
    void attackDefend(final Unit defender);
    void attackEvade(final Unit defender);

    //Defend and evade functions in response to the different types of attackers
    //Character (Player)
    void defendCharacterAttack(final Character attacker);
    void evadeCharacterAttack(final Character attacker);
    //Wild Unit
    void defendWildUnitAttack(final WildUnit attacker);
    void evadeWildUnitAttack(final WildUnit attacker);
    //Boss Unit
    void defendBossUnitAttack(final BossUnit attacker);
    void evadeBossUnitAttack(final BossUnit attacker);

    //Functions in response to a posible K.O. in a combat of the different types of attackers
    void koCharacterAttack(final Character attacker);
    void koBossUnitAttack(final BossUnit attacker);
    void koWildUnitAttack(final WildUnit attacker);

}
