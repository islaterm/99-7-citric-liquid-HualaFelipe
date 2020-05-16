package com.github.cc3002.citricjuice.model;

import java.util.Random;

public class AbstractUnit implements Unit {
    private final Random random;
    private final String name;
    private final int maxHP;
    private final int atk;
    private final int def;
    private final int evd;
    private int currentHP;

    public AbstractUnit(final String name, final int hp, final int atk, final int def,
                        final int evd){
        this.name = name;
        this.maxHP = this.currentHP = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        random = new Random();
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


}
