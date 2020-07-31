package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.TypeCombat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurnControllerTest {
    private Player suguri;
    private Player lee;
    private TurnController turnController;
    private int chapter;
    private Panel panel1;
    private Panel panel2;
    private Panel panel3;
    private Panel panel4;
    private Panel panel5;
    private Panel panel6;
    private Panel panel7;
    private Panel panel8;
    private Panel panel9;
    private Panel panel10;
    private Panel panel11;
    private Panel panel12;
    private Panel panel13;
    private Panel panel14;




    @BeforeEach
    public void setUp(){
        suguri = new Player("Suguri", 4, 1, -1, 2);
        lee = new Player("Lee",4,2,0,1);
        chapter = 2;
        GameController gameController = new GameController();
        turnController = new TurnController(suguri, chapter, gameController);
        panel1 = new NeutralPanel(1);
        panel2 = new NeutralPanel(2);
        panel3 = new HomePanel(3);
        panel4 = new NeutralPanel(4);
        panel5 = new BossPanel(5);
        panel6 = new NeutralPanel(6);
        panel7 = new EncounterPanel(7);
        panel8 = new NeutralPanel(8);
        panel9 = new NeutralPanel(9);
        panel10 = new NeutralPanel(10);
        panel11 = new NeutralPanel(11);
        panel12 = new NeutralPanel(12);
        panel13 = new NeutralPanel(13);
        panel14 = new NeutralPanel(14);


        panel1.addNextPanel(panel2);
        panel2.addNextPanel(panel3);
        panel3.addNextPanel(panel4);
        panel4.addNextPanel(panel5);
        panel5.addNextPanel(panel6);
        panel6.addNextPanel(panel7);
        panel7.addNextPanel(panel8);
        panel8.addNextPanel(panel9);

        panel9.addNextPanel(panel10);
        panel9.addNextPanel(panel11);
        panel9.addNextPanel(panel12);

        panel11.addNextPanel(panel13);
        panel12.addNextPanel(panel14);



        suguri.setHomePanel(panel3);

    }
    @Test
    public void testStartTurnAlive(){
        assertTrue(turnController.getState().isStarTurnPhaseActive());
        turnController.startTurn();
        assertEquals(1,suguri.getStars());
        assertFalse(turnController.getState().isStarTurnPhaseActive());
        assertTrue(turnController.getState().isMovingPhaseActive());
    }

    @Test
    public void testStartTurnDead(){
        suguri.goDead();
        assertTrue(turnController.getState().isStarTurnPhaseActive());
        turnController.startTurn();
        assertFalse(turnController.getState().isStarTurnPhaseActive());
        assertTrue(turnController.getState().isRecoveryPhaseActive());
    }

    @Test
    public void moveOneTest(){
        panel1.addPlayer(suguri);
        assertEquals(panel1.getId(),suguri.getPanel().getId());
        assertNotEquals(panel2.getId(),suguri.getPanel().getId());
        assertEquals(1,panel1.getPlayers().size());
        assertEquals(0,panel2.getPlayers().size());
        suguri.moveOne();
        assertEquals(panel2.getId(),suguri.getPanel().getId());
        assertNotEquals(panel1.getId(),suguri.getPanel().getId());
        assertEquals(0,panel1.getPlayers().size());
        assertEquals(1,panel2.getPlayers().size());
    }

    @Test
    public void pathDecisionMoveTest(){
        panel8.addPlayer(suguri);
        turnController.startTurn();

        turnController.setDice(4);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel9.getId());
        assertEquals(3,turnController.getDice());
        assertTrue(turnController.getState().isWaitPathPhaseActive());

    }


    @Test
    public void fightDecisionMoveTest(){
        panel8.addPlayer(suguri);
        panel9.addPlayer(lee);
        turnController.startTurn();


        turnController.setDice(4);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel9.getId());
        assertEquals(3,turnController.getDice());
        assertTrue(turnController.getState().isWaitFightPhaseActive());

        turnController.getState().moveDecision();
        assertTrue(turnController.getState().isMovingPhaseActive());

        turnController.getState().move();
        assertTrue(turnController.getState().isWaitPathPhaseActive());


    }

    @Test
    public void fightDecisionBattleTest(){
        panel8.addPlayer(suguri);
        panel9.addPlayer(lee);
        turnController.startTurn();


        turnController.setDice(4);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel9.getId());
        assertEquals(3,turnController.getDice());
        assertTrue(turnController.getState().isWaitFightPhaseActive());

        turnController.getState().changeFightDecision(0);
        turnController.getState().fightDecision();
        assertTrue(turnController.getState().isBattlePhaseActive());

        turnController.getState().rollAtkAttack();
        turnController.getState().rollDefAttack();
        turnController.getState().selectTypeCombat(TypeCombat.DEFEND);

        turnController.getState().attack();

        turnController.getState().rollAtkContraAttack();
        turnController.getState().rollDefContraAttack();
        turnController.getState().selectTypeCombat(TypeCombat.EVADE);

        turnController.getState().contraAttack();

        turnController.getState().endBattle();

        assertTrue(turnController.getState().isEndTurnPhaseActive());


    }

    @Test
    public void homeDecisionMoveTest1(){
        panel1.addPlayer(suguri);
        turnController.startTurn();


        turnController.setDice(4);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel3.getId());
        assertEquals(2,turnController.getDice());
        assertTrue(turnController.getState().isWaitHomePhaseActive());

    }

    @Test
    public void homeDecisionMoveTest2(){
        panel1.addPlayer(suguri);
        turnController.startTurn();


        turnController.setDice(2);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel3.getId());
        assertEquals(0,turnController.getDice());
        assertTrue(turnController.getState().isEndTurnPhaseActive());

    }

    @Test
    public void waitHomePhaseMoveTest(){
        panel1.addPlayer(suguri);
        turnController.startTurn();


        turnController.setDice(4);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel3.getId());

        assertTrue(turnController.getState().isWaitHomePhaseActive());
        assertFalse(turnController.getState().isMovingPhaseActive());
        turnController.getState().moveDecision();
        assertTrue(turnController.getState().isMovingPhaseActive());
        assertFalse(turnController.getState().isWaitHomePhaseActive());


    }

    @Test
    public void waitHomePhaseMoveEndTurnTest(){
        panel1.addPlayer(suguri);
        turnController.startTurn();


        turnController.setDice(4);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel3.getId());

        assertTrue(turnController.getState().isWaitHomePhaseActive());
        assertFalse(turnController.getState().isEndTurnPhaseActive());
        turnController.getState().endTurnDecision();
        assertTrue(turnController.getState().isEndTurnPhaseActive());
        assertFalse(turnController.getState().isWaitHomePhaseActive());
    }


    @Test
    public void waitHomeWaitPathPhaseTest(){
        panel8 = new NeutralPanel(8);
        panel9 = new HomePanel(9);

        panel8.addNextPanel(panel9);
        panel8.addPlayer(suguri);


        panel9.addNextPanel(panel10);
        panel9.addNextPanel(panel11);
        panel9.addNextPanel(panel12);
        suguri.setHomePanel(panel9);


        turnController.startTurn();

        turnController.setDice(4);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel9.getId());
        assertEquals(3,turnController.getDice());
        assertTrue(turnController.getState().isWaitHomePhaseActive());

        turnController.getState().moveDecision();
        assertTrue(turnController.getState().isMovingPhaseActive());

        turnController.getState().move();
        assertTrue(turnController.getState().isWaitPathPhaseActive());

    }


    @Test
    public void waitFightWaitHomeWaitPathPhaseTest(){
        panel8 = new NeutralPanel(8);
        panel9 = new HomePanel(9);

        panel8.addNextPanel(panel9);


        panel9.addNextPanel(panel10);
        panel9.addNextPanel(panel11);
        panel9.addNextPanel(panel12);
        suguri.setHomePanel(panel9);

        panel8.addPlayer(suguri);
        panel9.addPlayer(lee);
        turnController.startTurn();


        turnController.setDice(2);
        turnController.getState().move();

        assertEquals(suguri.getPanel().getId(),panel9.getId());
        assertEquals(1,turnController.getDice());
        assertTrue(turnController.getState().isWaitFightPhaseActive());

        turnController.getState().moveDecision();
        assertTrue(turnController.getState().isMovingPhaseActive());

        turnController.getState().move();
        assertTrue(turnController.getState().isWaitHomePhaseActive());

        turnController.getState().moveDecision();
        assertTrue(turnController.getState().isMovingPhaseActive());

        turnController.getState().move();
        assertTrue(turnController.getState().isWaitPathPhaseActive());

        assertEquals(turnController.getTurnOwner().getPlayerPathChoice(),0);
        turnController.getState().changePlayerPathDecision(1);
        assertEquals(turnController.getTurnOwner().getPlayerPathChoice(),1);

        turnController.getState().continueMovement();
        assertTrue(turnController.getState().isEndTurnPhaseActive());



    }








}
