package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the players of the game.
 *
 * @author Felipe Huala
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class CombatTest {
    private Player suguri;
    private Player lee;
    private WildUnit chicken;
    private WildUnit roboBall;
    private WildUnit seagull;
    private BossUnit storeManager;
    private BossUnit shifuRobot;
    private BossUnit flyingCastle;

    @BeforeEach
    public void setUp() {

        suguri = new Player("Suguri", 4, 1, -1, 2);
        lee = new Player("Lee",4,2,0,1);
        chicken = new WildUnit("Chicken",3,-1,-1,1);
        roboBall = new WildUnit("Robo Ball",3,-1,1,-1);
        seagull = new WildUnit("Seagull",3,-1,1,-1);
        storeManager = new BossUnit("Store Manager",8,3,2,-1);
        shifuRobot = new BossUnit("Shifu Robot",7,2,3,-2);
        flyingCastle = new BossUnit("Flying Castle",10,2,1,-3);

        suguri.increaseStarsBy(10);
        lee.increaseStarsBy(10);
        chicken.increaseStarsBy(10);
        roboBall.increaseStarsBy(10);
        seagull.increaseStarsBy(10);
        storeManager.increaseStarsBy(10);
        shifuRobot.increaseStarsBy(10);
        flyingCastle.increaseStarsBy(10);


    }

    @RepeatedTest(500)
    public void AttackDefendTest(){
        final long testSeed = new Random().nextLong();

        suguri.setSeed(testSeed);
        lee.setSeed(testSeed);
        chicken.setSeed(testSeed);
        roboBall.setSeed(testSeed);
        seagull.setSeed(testSeed);
        storeManager.setSeed(testSeed);
        shifuRobot.setSeed(testSeed);
        flyingCastle.setSeed(testSeed);

        suguri.rollAtk();
        lee.rollAtk();
        chicken.rollAtk();
        roboBall.rollAtk();
        seagull.rollAtk();
        storeManager.rollAtk();
        shifuRobot.rollAtk();
        flyingCastle.rollAtk();

        suguri.rollDef();
        lee.rollDef();
        chicken.rollDef();
        roboBall.rollDef();
        seagull.rollDef();
        storeManager.rollDef();
        shifuRobot.rollDef();
        flyingCastle.rollDef();

        assertTrue(suguri.getRollAtk()<=6 && suguri.getRollAtk()>0,suguri.getRollAtk()+" está afuera del rango");


        //attackDefend

        suguri.attackDefend(lee);
        assertTrue(lee.getCurrentHP() >=0 && lee.getCurrentHP() <=3,"Se escapó el Hp de lee");
        assertTrue(suguri.getStars()==10 || suguri.getStars() ==15,"Se escaparaon las estrellas de suguri");
        assertTrue(lee.getStars()==5 || lee.getStars()==10,"Se escaparaon las estrellas de lee");
        assertTrue(suguri.getVictories()>=0 && suguri.getVictories()<=2, "Se escaparon las Victories de Suguri");


        suguri.attackDefend(chicken);
        assertTrue(chicken.getCurrentHP() >=0 && chicken.getCurrentHP() <=2,"Se escapó el Hp de chiken");
        assertTrue(suguri.getStars()==10 ||suguri.getStars()==15 || suguri.getStars()==20 || suguri.getStars() ==25,"Se escaparaon las estrellas de suguri");
        assertTrue(suguri.getVictories()>=0 && suguri.getVictories()<=3 , "Se escaparon las Victories de Suguri");

        lee.attackDefend(storeManager);
        assertTrue(storeManager.getCurrentHP()>=3 && storeManager.getCurrentHP()<=8,"se escapo el Hp de store manager"+storeManager.getCurrentHP());
        assertTrue(lee.getStars()==5 || lee.getStars()==10,"Se escaparaon las estrellas de lee");
        assertTrue(lee.getVictories()==0, "Se escaparon las Victories de Lee");




    }

    @RepeatedTest(500)
    public void AttackEvadeTest(){
        final long testSeed = new Random().nextLong();

        suguri.setSeed(testSeed);
        lee.setSeed(testSeed);
        chicken.setSeed(testSeed);
        roboBall.setSeed(testSeed);
        seagull.setSeed(testSeed);
        storeManager.setSeed(testSeed);
        shifuRobot.setSeed(testSeed);
        flyingCastle.setSeed(testSeed);

        suguri.rollAtk();
        lee.rollAtk();
        chicken.rollAtk();
        roboBall.rollAtk();
        seagull.rollAtk();
        storeManager.rollAtk();
        shifuRobot.rollAtk();
        flyingCastle.rollAtk();

        assertTrue(suguri.getRollAtk()<=6 && suguri.getRollAtk()>0,suguri.getRollAtk()+" está afuera del rango");

        suguri.attackEvade(lee);
        assertTrue(lee.getCurrentHP() >=0 && lee.getCurrentHP() <=4,"Se escapó el Hp de lee");
        assertTrue(suguri.getStars()==10 || suguri.getStars() ==15,"Se escaparaon las estrellas de suguri");
        assertTrue(lee.getStars()==5 || lee.getStars()==10,"Se escaparaon las estrellas de lee");
        assertTrue(suguri.getVictories()>=0 && suguri.getVictories()<=2, "Se escaparon las Victories de Suguri");

        suguri.attackEvade(chicken);
        assertTrue(chicken.getCurrentHP() >=0 && chicken.getCurrentHP() <=3,"Se escapó el Hp de chicken");
        assertTrue(suguri.getStars()==10 ||suguri.getStars()==15 || suguri.getStars()==20 || suguri.getStars() ==25,"Se escaparaon las estrellas de suguri");
        assertTrue(suguri.getVictories()>=0 && suguri.getVictories()<=3 , "Se escaparon las Victories de Suguri");

        lee.attackEvade(storeManager);
        assertTrue(storeManager.getCurrentHP()>=0 && storeManager.getCurrentHP()<=8,"se escapo el Hp de store manager");
        assertTrue(lee.getStars()==5 || lee.getStars()==10 || lee.getStars()==15 || lee.getStars()==20,"Se escaparaon las estrellas de lee");
        assertTrue(lee.getVictories()==0 || lee.getVictories()==3, "Se escaparon las Victories de Lee");

    }



}