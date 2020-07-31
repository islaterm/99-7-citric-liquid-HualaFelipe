package com.github.cc3002.citricjuice.model.unit;

public class BossUnit extends AbstractNonPlayerUnit {

    /**
     * Create a boss unit
     * @param name
     * @param hp
     * @param atk
     * @param def
     * @param evd
     */
    public BossUnit(final String name, final int hp, int atk, int def, int evd) {
        super(name, hp,atk,def,evd);
    }

    @Override
    public void attackDefend(Unit defender) {
        defender.defendBossUnitAttack(this);
    }

    @Override
    public void attackEvade(Unit defender) {
        defender.evadeBossUnitAttack(this);
    }

    @Override
    public void koPlayerAttack(Player attacker) {
        if(this.getCurrentHP()==0) {
            int stars = this.getStars();
            attacker.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
            attacker.increaseVictoriesBy(3);
            this.goDead();
        }
    }

    @Override
    public void koBossUnitAttack(BossUnit attacker) {
        if(this.getCurrentHP()==0) {
            int stars = this.getStars()/2;
            attacker.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
            attacker.increaseVictoriesBy(3);
            this.goDead();
        }
    }

    @Override
    public void koWildUnitAttack(WildUnit attacker) {
        if(this.getCurrentHP()==0) {
            int stars = this.getStars()/2;
            attacker.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
            attacker.increaseVictoriesBy(3);
            this.goDead();
        }
    }
}
