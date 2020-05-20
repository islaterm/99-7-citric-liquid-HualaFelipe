package com.github.cc3002.citricjuice.model;

public class Character extends AbstractUnit{

    public Character(final String name, final int hp,  int atk,  int def,
                      int evd){
        super(name,hp,atk,def,evd);
    }

    @Override
    public void attackDefend(final Unit defender){
            defender.defendCharacterAttack(this);
    }

    @Override
    public void attackEvade(final Unit defender){
        defender.evadeCharacterAttack(this);
    }

    @Override
    public void koCharacterAttack(final Character attacker){
        if(this.getCurrentHP()==0) {
            int stars = this.getStars() / 2;
            attacker.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
            attacker.increaseVictoriesBy(2);
        }
    }

    @Override
    public void koWildUnitAttack(WildUnit attacker) {
        if(this.getCurrentHP()==0) {
            int stars = this.getStars() / 2;
            attacker.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
            attacker.increaseVictoriesBy(2);
        }
    }

    @Override
    public void koBossUnitAttack(BossUnit attacker) {
        if(this.getCurrentHP()==0) {
            int stars = this.getStars() / 2;
            attacker.increaseStarsBy(stars);
            this.reduceStarsBy(stars);
            attacker.increaseVictoriesBy(2);
        }
    }
}
