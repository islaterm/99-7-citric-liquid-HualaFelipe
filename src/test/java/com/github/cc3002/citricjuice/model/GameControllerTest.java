package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;
import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import com.github.cc3002.citricliquid.model.NormaGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    private GameController  gameController;



    public void makeTestBoard(){
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createHomePanel();
        gameController.createNeutralPanel();
        gameController.createBossPanel();
        gameController.createNeutralPanel();
        gameController.createEncounterPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();


        gameController.setNextPanel(gameController.getPanels().get(0), gameController.getPanels().get(1));
        gameController.setNextPanel(gameController.getPanels().get(1), gameController.getPanels().get(2));
        gameController.setNextPanel(gameController.getPanels().get(2), gameController.getPanels().get(3));
        gameController.setNextPanel(gameController.getPanels().get(3), gameController.getPanels().get(4));
        gameController.setNextPanel(gameController.getPanels().get(4), gameController.getPanels().get(5));
        gameController.setNextPanel(gameController.getPanels().get(5), gameController.getPanels().get(6));
        gameController.setNextPanel(gameController.getPanels().get(6), gameController.getPanels().get(7));
        gameController.setNextPanel(gameController.getPanels().get(7), gameController.getPanels().get(8));



        gameController.setNextPanel(gameController.getPanels().get(8),
                gameController.getPanels().get(9),
                gameController.getPanels().get(10),
                gameController.getPanels().get(11)
        );

        gameController.setNextPanel(gameController.getPanels().get(10),gameController.getPanels().get(12));
        gameController.setNextPanel(gameController.getPanels().get(11),gameController.getPanels().get(13));


    }

    @BeforeEach
    public void setup(){
        gameController = new GameController();
    }

    @Test
    public void gameControllerSetupTest(){

    }

    @Test
    public void createPanelsTest(){
        assertEquals(0,gameController.getPanels().size());

        Panel bonusPanel = gameController.createBonusPanel();
        assertEquals(1,gameController.getPanels().size());
        assertEquals(bonusPanel,gameController.getPanels().get(0));
        assertEquals(0,gameController.getPanels().get(0).getId());

        Panel bossPanel = gameController.createBossPanel();
        assertEquals(2,gameController.getPanels().size());
        assertEquals(bossPanel,gameController.getPanels().get(1));
        assertEquals(1,gameController.getPanels().get(1).getId());

        Panel dropPanel = gameController.createDropPanel();
        assertEquals(3,gameController.getPanels().size());
        assertEquals(dropPanel,gameController.getPanels().get(2));
        assertEquals(2,gameController.getPanels().get(2).getId());

        Panel encounterPanel = gameController.createEncounterPanel();
        assertEquals(4,gameController.getPanels().size());
        assertEquals(encounterPanel,gameController.getPanels().get(3));
        assertEquals(3,gameController.getPanels().get(3).getId());

        Panel homePanel = gameController.createHomePanel();
        assertEquals(5,gameController.getPanels().size());
        assertEquals(homePanel,gameController.getPanels().get(4));
        assertEquals(4,gameController.getPanels().get(4).getId());

        Panel neutralPanel = gameController.createNeutralPanel();
        assertEquals(6,gameController.getPanels().size());
        assertEquals(neutralPanel,gameController.getPanels().get(5));
        assertEquals(5,gameController.getPanels().get(5).getId());

    }

    @Test
    public void createUnitsTest(){
        assertEquals(0,gameController.getPlayers().size());
        assertEquals(0,gameController.getWilds().size());
        assertEquals(0,gameController.getBosses().size());

        Player suguri = gameController.createPlayer("Suguri", 4, 1, -1, 2);
        assertEquals(1,gameController.getPlayers().size());
        Player lee = gameController.createPlayer("Lee",4,2,0,1);
        assertEquals(2,gameController.getPlayers().size());

        WildUnit chicken = gameController.createWildUnit("Chicken",3,-1,-1,1);
        assertEquals(1,gameController.getWilds().size());
        WildUnit roboBall = gameController.createWildUnit("Robo Ball",3,-1,1,-1);
        assertEquals(2,gameController.getWilds().size());
        WildUnit seagull = gameController.createWildUnit("Seagull",3,-1,1,-1);
        assertEquals(3,gameController.getWilds().size());

        BossUnit storeManager = gameController.createBossUnit("Store Manager",8,3,2,-1);
        assertEquals(1,gameController.getBosses().size());
        BossUnit shifuRobot = gameController.createBossUnit("Shifu Robot",7,2,3,-2);
        assertEquals(2,gameController.getBosses().size());
        BossUnit flyingCastle = gameController.createBossUnit("Flying Castle",10,2,1,-3);
        assertEquals(3,gameController.getBosses().size());

    }

    @Test
    public void assignPanelToPlayer(){
        Player suguri = gameController.createPlayer("Suguri", 4, 1, -1, 2);
        Panel neutralPanel = gameController.createNeutralPanel();
        Panel bossPanel = gameController.createBossPanel();

        assertEquals(0,neutralPanel.getPlayers().size());
        assertEquals(0,bossPanel.getPlayers().size());

        gameController.setPlayerPanel(suguri,neutralPanel);
        assertEquals(1,neutralPanel.getPlayers().size());
        assertEquals(0,bossPanel.getPlayers().size());

        assertEquals(neutralPanel,suguri.getPanel());
        assertEquals(neutralPanel.getId(),suguri.getPanel().getId());
        assertTrue(neutralPanel.getPlayers().contains(suguri));

        gameController.setPlayerPanel(suguri,bossPanel);
        assertEquals(0,neutralPanel.getPlayers().size());
        assertEquals(1,bossPanel.getPlayers().size());

        assertEquals(bossPanel,suguri.getPanel());
        assertEquals(bossPanel.getId(),suguri.getPanel().getId());
        assertTrue(bossPanel.getPlayers().contains(suguri));
    }

    @Test
    public void addNextPanelsTest(){
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();


        gameController.setNextPanel(gameController.getPanels().get(0), gameController.getPanels().get(1));

        gameController.setNextPanel(gameController.getPanels().get(1),
                gameController.getPanels().get(2),
                gameController.getPanels().get(3),
                gameController.getPanels().get(4)
        );

        assertEquals(gameController.getPanels().get(0).getNextPanels().get(0),gameController.getPanels().get(1));
        assertEquals(gameController.getPanels().get(0).getNextPanels().get(0).getId(),gameController.getPanels().get(1).getId());
        assertEquals(1,gameController.getPanels().get(0).getNextPanels().size());

        assertEquals(gameController.getPanels().get(1).getNextPanels().get(0),gameController.getPanels().get(2));
        assertEquals(gameController.getPanels().get(1).getNextPanels().get(1),gameController.getPanels().get(3));
        assertEquals(gameController.getPanels().get(1).getNextPanels().get(2),gameController.getPanels().get(4));
        assertEquals(gameController.getPanels().get(1).getNextPanels().get(0).getId(),gameController.getPanels().get(2).getId());
        assertEquals(gameController.getPanels().get(1).getNextPanels().get(1).getId(),gameController.getPanels().get(3).getId());
        assertEquals(gameController.getPanels().get(1).getNextPanels().get(2).getId(),gameController.getPanels().get(4).getId());
        assertEquals(3,gameController.getPanels().get(1).getNextPanels().size());

    }

    @Test
    public void assignHomePanelToPlayerTest(){
        Player suguri = gameController.createPlayer("Suguri", 4, 1, -1, 2);
        Panel homePanel = gameController.createHomePanel();

        gameController.setPlayerHome(suguri,homePanel);

        assertEquals(homePanel,suguri.getHomePanel());
        assertEquals(homePanel.getId(),suguri.getHomePanel().getId());


    }

    @Test
    public void getChapterTest(){
        assertEquals(0,gameController.getChapter());
    }

    @Test
    public void getTurnOwnerTest(){
        Player suguri = gameController.createPlayer("Suguri", 4, 1, -1, 2);
        Player lee = gameController.createPlayer("Lee",4,2,0,1);

        gameController.newGame();

        assertEquals(suguri, gameController.getTurnOwner());
        assertNotEquals(lee,gameController.getTurnOwner());
    }

    @Test
    public void  endTurnTest(){
        Player suguri = gameController.createPlayer("Suguri", 4, 1, -1, 2);
        Player lee = gameController.createPlayer("Lee",4,2,0,1);

        gameController.newGame();

        assertEquals(suguri, gameController.getTurnOwner());
        assertNotEquals(lee,gameController.getTurnOwner());

        gameController.endTurn();

        assertNotEquals(suguri, gameController.getTurnOwner());
        assertEquals(lee,gameController.getTurnOwner());

    }

    @Test
    public void setCurrPlayerNormaGoalTest(){
        Player suguri = gameController.createPlayer("Suguri", 4, 1, -1, 2);
        gameController.newGame();
        assertEquals(NormaGoal.STARS,gameController.getTurnOwner().getNormaGoal());
        gameController.setCurrPlayerNormaGoal(NormaGoal.WINS);
        assertEquals(NormaGoal.WINS,gameController.getTurnOwner().getNormaGoal());
    }






}
