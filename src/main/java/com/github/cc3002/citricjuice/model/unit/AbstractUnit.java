package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.unit.lifestate.Alive;
import com.github.cc3002.citricjuice.model.unit.lifestate.Dead;
import com.github.cc3002.citricjuice.model.unit.lifestate.ILifeState;

import java.util.Objects;
import java.util.Random;

public abstract class AbstractUnit implements Unit {
    private final Random random;
    private final String name;
    private final int maxHP;
    protected int atk;
    protected int def;
    protected int evd;
    private int currentHP;
    private int stars;
    private int victories;
    private int rollAtk;
    private int rollDef;
    private ILifeState lifeState;

    public AbstractUnit(final String name, final int hp, int atk, int def, int evd){
        this.name = name;
        this.maxHP = this.currentHP = hp;
        this.atk=atk;
        this.def=def;
        this.evd = evd;
        random = new Random();
        lifeState = (ILifeState) new Alive(this);
        //IMPLICITO: stars = 0;
    }

    public void setLifeState(ILifeState lifeState) {
        this.lifeState = lifeState;
    }

    public ILifeState getLifeState() {
        return lifeState;
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
        rollAtk = roll();
    }

    public int getRollAtk() {

        return rollAtk;
    }

    public void rollDef() {
        rollDef = roll();
    }

    public int getRollDef() {

        return rollDef;
    }

    /**
     * Returns the Player's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the Player's max hit points.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Returns the Player's attack points.
     */
    public int getAtk() {
        return atk;
    }
    /**
     * Returns the Player's defense points.
     */
    public int getDef() {
        return def;
    }
    /**
     * Returns the Player's evasion points.
     */

    public int getEvd() {
        return evd;
    }


    /**
     * Returns the current hit points of the Player.
     */
    public int getCurrentHP() {
        return currentHP;
    }


    /**
     * Sets the current Player's hit points.
     * <p>
     * The Player's hit points have a constraint to always be between 0 and maxHP, both inclusive.
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
        int rollAtk = attacker.getRollAtk();
        int rollDef = this.rollDef;
        int damage = Math.max(1,rollAtk+attacker.getAtk()-rollDef-this.getDef());
        this.setCurrentHP(this.getCurrentHP()-damage);
    }

    public void evade(final Unit attacker){
        int rollAtk = attacker.getRollAtk();
        int rollEvd = this.rollDef;
        int damage;
        if(rollEvd+this.getEvd()>rollAtk+attacker.getAtk()){
            damage = 0;
        }else{
            damage = rollAtk+attacker.getAtk();
        }
        this.setCurrentHP(getCurrentHP()-damage);
    }

    public void attack(Unit receiver, TypeCombat typeCombat){
        if(receiver.isAlive() && this.isAlive()) {
            if (typeCombat == TypeCombat.DEFEND) {
                this.attackDefend(receiver);
            } else if (typeCombat == TypeCombat.EVADE) {
                this.attackEvade(receiver);
            }
        }
    }

    public boolean isAlive() {
        return lifeState.isAlive();
    }


    //Attack functions
    abstract void attackDefend(final Unit defender);
    abstract void attackEvade(final Unit defender);

    //Functions in response to a possible K.O. in a combat of the different types of attackers
    abstract void koPlayerAttack(final Player attacker);
    abstract void koBossUnitAttack(final BossUnit attacker);
    abstract void koWildUnitAttack(final WildUnit attacker);


    public void defendPlayerAttack(final Player attacker){
        if(attacker.isAlive() && this.isAlive()) {
            defend(attacker);
            koPlayerAttack(attacker);
        }
    }

    public void evadePlayerAttack(final Player attacker){
        if(attacker.isAlive() && this.isAlive()) {
            evade(attacker);
            koPlayerAttack(attacker);
        }
    }

    public void defendWildUnitAttack(WildUnit attacker) {
        if(attacker.isAlive() && this.isAlive()) {
            defend(attacker);
            koWildUnitAttack(attacker);
        }
    }

    public void evadeWildUnitAttack(WildUnit attacker) {
        if(attacker.isAlive() && this.isAlive()) {
            evade(attacker);
            koWildUnitAttack(attacker);
        }
    }

    public void defendBossUnitAttack(BossUnit attacker) {
        if(attacker.isAlive() && this.isAlive()) {
            defend(attacker);
            koBossUnitAttack(attacker);
        }
    }

    public void evadeBossUnitAttack(BossUnit attacker) {
        if(attacker.isAlive() && this.isAlive()) {
            evade(attacker);
            koBossUnitAttack(attacker);
        }
    }

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

    public void goDead(){
        this.setLifeState(new Dead(this));
    }

    public void revive(){
        this.currentHP = this.maxHP;
        this.setLifeState(new Alive(this));
    }

}
