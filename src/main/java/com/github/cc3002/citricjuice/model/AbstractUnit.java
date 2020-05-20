package com.github.cc3002.citricjuice.model;

import java.util.Objects;
import java.util.Random;

public abstract class AbstractUnit implements Unit {
    private final Random random;
    private final String name;
    private final int maxHP;
    private int atk;
    private int def;
    private int evd;
    private int currentHP;
    private int stars;
    private int victories;
    private int rollAtk;

    public AbstractUnit(final String name, final int hp, int atk, int def, int evd){
        this.name = name;
        this.maxHP = this.currentHP = hp;
        this.atk=atk;
        this.def=def;
        this.evd = evd;
        random = new Random();
        //IMPLICITO: stars = 0;
    }

    /**
     * Set's the seed for this unit's random number generator.
     * <p>
     * The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }


    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }

    /**
     * Set rollAtk a uniformly distributed random value in [1, 6]
     */
    public void rollAtk() {
        rollAtk= random.nextInt(6) + 1;
    }

    public int getRollAtk() {
        return rollAtk;
    }

    /**
     * Returns the character's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the character's max hit points.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Returns the character's attack points.
     */
    public int getAtk() {
        return atk;
    }
    /**
     * Returns the character's defense points.
     */
    public int getDef() {
        return def;
    }
    /**
     * Returns the character's evasion points.
     */

    public int getEvd() {
        return evd;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setEvd(int evd) {
        this.evd = evd;
    }

    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHP() {
        return currentHP;
    }


    /**
     * Sets the current character's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    public void setCurrentHP(final int newHP) {
        this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }

    /**
     * Increases this player's star count by an amount.
     */
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    /**
     * Returns this player's star count.
     */
    public int getStars() {
        return stars;
    }


    /**
     * Reduces this player's star count by a given amount.
     * <p>
     * The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }

    public int getVictories() {
        return victories;
    }

    /**
     * Increases this unit victories count by an amount.
     */
    public void increaseVictoriesBy(final int amount) {
        victories += amount;
    }


    public void defend(final Unit attacker){
        int rollAtk = this.rollAtk;
        int rollDef = this.roll();
        int damage = Math.max(1,rollAtk+attacker.getAtk()-rollDef-this.getDef());
        this.setCurrentHP(this.getCurrentHP()-damage);
    }

    public void evade(final Unit attacker){
        int rollAtk = this.rollAtk;
        int rollEvd = this.roll();
        int damage;
        if(rollEvd+this.getEvd()>rollAtk+attacker.getAtk()){
            damage = 0;
        }else{
            damage = rollAtk+attacker.getAtk();
        }
        this.setCurrentHP(getCurrentHP()-damage);
    }

    @Override
    public void defendCharacterAttack(final Character attacker){
        defend(attacker);
        koCharacterAttack(attacker);
    }

    @Override
    public void evadeCharacterAttack(final Character attacker){
        evade(attacker);
        koCharacterAttack(attacker);
    }

    @Override
    public void defendWildUnitAttack(WildUnit attacker) {
        defend(attacker);
        koWildUnitAttack(attacker);
    }

    @Override
    public void evadeWildUnitAttack(WildUnit attacker) {
        evade(attacker);
        koWildUnitAttack(attacker);
    }

    @Override
    public void defendBossUnitAttack(BossUnit attacker) {
        defend(attacker);
        koBossUnitAttack(attacker);
    }

    @Override
    public void evadeBossUnitAttack(BossUnit attacker) {
        evade(attacker);
        koBossUnitAttack(attacker);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractUnit)) return false;
        AbstractUnit that = (AbstractUnit) o;
        return getMaxHP() == that.getMaxHP() &&
                getAtk() == that.getAtk() &&
                getDef() == that.getDef() &&
                getEvd() == that.getEvd() &&
                getCurrentHP() == that.getCurrentHP() &&
                getStars() == that.getStars() &&
                Objects.equals(getName(), that.getName());
    }

}
