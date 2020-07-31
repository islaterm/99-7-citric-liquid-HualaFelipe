package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;
import com.github.cc3002.citricliquid.controller.gameflow.EndGameState;
import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import com.github.cc3002.citricliquid.controller.gameflow.NormaClearState;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameGUI extends Application{
    private GameController gameController;
    private Stage primaryStage;
    private GameScene gameScene;
    private int mapColPlaces;
    private int mapRowPlaces;

    /**
     * Get the GameController
     * @return
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Creates all the environment of the game. 4 Players, 3 Wilds, 3 Bosses, 45 panels and 1 Map (Interconnected panels)
     */
    public void setUp(){
        gameController = new GameController();
        gameController.setGameGUI(this);

        Player suguri = gameController.createPlayer("Suguri", 4, 1, -1, 2);
        Player lee = gameController.createPlayer("Lee",4,2,0,1);
        Player mio = gameController.createPlayer("Mio",6,0,-1,1);
        Player lulu = gameController.createPlayer("Lulu",5,0,1,-1);

        WildUnit chicken = gameController.createWildUnit("Chicken",3,-1,-1,1);
        WildUnit roboBall = gameController.createWildUnit("Robo Ball",3,-1,1,-1);
        WildUnit seagull = gameController.createWildUnit("Seagull",3,-1,1,-1);

        BossUnit storeManager = gameController.createBossUnit("Store Manager",8,3,2,-1);
        BossUnit shifuRobot = gameController.createBossUnit("Shifu Robot",7,2,3,-2);
        BossUnit flyingCastle = gameController.createBossUnit("Flying Castle",10,2,1,-3);

        mapColPlaces = 9;
        mapRowPlaces = 9;

        gameController.createBossPanel();
        gameController.createBonusPanel();
        gameController.createEncounterPanel();
        gameController.createNeutralPanel();
        gameController.createHomePanel();
        gameController.createNeutralPanel();
        gameController.createEncounterPanel();
        gameController.createBonusPanel();
        gameController.createBossPanel();

        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createEncounterPanel();

        gameController.createDropPanel();
        gameController.createDropPanel();
        gameController.createDropPanel();

        gameController.createEncounterPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();

        gameController.createHomePanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createEncounterPanel();
        gameController.createBonusPanel();
        gameController.createEncounterPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createHomePanel();

        gameController.createEncounterPanel();
        gameController.createNeutralPanel();
        gameController.createNeutralPanel();

        gameController.createDropPanel();
        gameController.createDropPanel();
        gameController.createDropPanel();

        gameController.createNeutralPanel();
        gameController.createNeutralPanel();
        gameController.createEncounterPanel();

        gameController.createBossPanel();
        gameController.createBonusPanel();
        gameController.createEncounterPanel();
        gameController.createNeutralPanel();
        gameController.createHomePanel();
        gameController.createNeutralPanel();
        gameController.createEncounterPanel();
        gameController.createBonusPanel();
        gameController.createBossPanel();

        gameController.setNextPanel(gameController.getPanels().get(4),gameController.getPanels().get(3),gameController.getPanels().get(5));
        gameController.setNextPanel(gameController.getPanels().get(3),gameController.getPanels().get(2));
        gameController.setNextPanel(gameController.getPanels().get(2),gameController.getPanels().get(1));
        gameController.setNextPanel(gameController.getPanels().get(1),gameController.getPanels().get(0));
        gameController.setNextPanel(gameController.getPanels().get(5),gameController.getPanels().get(6));
        gameController.setNextPanel(gameController.getPanels().get(6),gameController.getPanels().get(7));
        gameController.setNextPanel(gameController.getPanels().get(7),gameController.getPanels().get(8));

        gameController.setNextPanel(gameController.getPanels().get(0),gameController.getPanels().get(9));
        gameController.setNextPanel(gameController.getPanels().get(8),gameController.getPanels().get(11));

        gameController.setNextPanel(gameController.getPanels().get(10),gameController.getPanels().get(4));
        gameController.setNextPanel(gameController.getPanels().get(9),gameController.getPanels().get(12));
        gameController.setNextPanel(gameController.getPanels().get(11),gameController.getPanels().get(14));

        gameController.setNextPanel(gameController.getPanels().get(12),gameController.getPanels().get(15));
        gameController.setNextPanel(gameController.getPanels().get(13),gameController.getPanels().get(10));
        gameController.setNextPanel(gameController.getPanels().get(14),gameController.getPanels().get(17));

        gameController.setNextPanel(gameController.getPanels().get(15),gameController.getPanels().get(18));
        gameController.setNextPanel(gameController.getPanels().get(16),gameController.getPanels().get(13));
        gameController.setNextPanel(gameController.getPanels().get(17),gameController.getPanels().get(26));

        gameController.setNextPanel(gameController.getPanels().get(18),gameController.getPanels().get(19));
        gameController.setNextPanel(gameController.getPanels().get(19),gameController.getPanels().get(20));
        gameController.setNextPanel(gameController.getPanels().get(20),gameController.getPanels().get(21));
        gameController.setNextPanel(gameController.getPanels().get(21),gameController.getPanels().get(22));
        gameController.setNextPanel(gameController.getPanels().get(22),gameController.getPanels().get(16),gameController.getPanels().get(28));
        gameController.setNextPanel(gameController.getPanels().get(23),gameController.getPanels().get(22));
        gameController.setNextPanel(gameController.getPanels().get(24),gameController.getPanels().get(23));
        gameController.setNextPanel(gameController.getPanels().get(25),gameController.getPanels().get(24));
        gameController.setNextPanel(gameController.getPanels().get(26),gameController.getPanels().get(25));

        gameController.setNextPanel(gameController.getPanels().get(27),gameController.getPanels().get(18));
        gameController.setNextPanel(gameController.getPanels().get(28),gameController.getPanels().get(31));
        gameController.setNextPanel(gameController.getPanels().get(29),gameController.getPanels().get(26));

        gameController.setNextPanel(gameController.getPanels().get(30),gameController.getPanels().get(27));
        gameController.setNextPanel(gameController.getPanels().get(31),gameController.getPanels().get(34));
        gameController.setNextPanel(gameController.getPanels().get(32),gameController.getPanels().get(29));

        gameController.setNextPanel(gameController.getPanels().get(33),gameController.getPanels().get(30));
        gameController.setNextPanel(gameController.getPanels().get(34),gameController.getPanels().get(40));
        gameController.setNextPanel(gameController.getPanels().get(35),gameController.getPanels().get(32));

        gameController.setNextPanel(gameController.getPanels().get(36),gameController.getPanels().get(33));
        gameController.setNextPanel(gameController.getPanels().get(37),gameController.getPanels().get(36));
        gameController.setNextPanel(gameController.getPanels().get(38),gameController.getPanels().get(37));
        gameController.setNextPanel(gameController.getPanels().get(39),gameController.getPanels().get(38));
        gameController.setNextPanel(gameController.getPanels().get(40),gameController.getPanels().get(41),gameController.getPanels().get(39));
        gameController.setNextPanel(gameController.getPanels().get(41),gameController.getPanels().get(42));
        gameController.setNextPanel(gameController.getPanels().get(42),gameController.getPanels().get(43));
        gameController.setNextPanel(gameController.getPanels().get(43),gameController.getPanels().get(44));
        gameController.setNextPanel(gameController.getPanels().get(44),gameController.getPanels().get(35));


        gameController.setPlayerHome(suguri,gameController.getPanels().get(4));
        gameController.setPlayerHome(lee,gameController.getPanels().get(18));
        gameController.setPlayerHome(mio,gameController.getPanels().get(26));
        gameController.setPlayerHome(lulu,gameController.getPanels().get(40));


        //Change this lines
        gameController.setPlayerPanel(suguri,gameController.getPanels().get(4)); //10
        gameController.setPlayerPanel(lee,gameController.getPanels().get(18)); //4
        gameController.setPlayerPanel(mio,gameController.getPanels().get(26));
        gameController.setPlayerPanel(lulu,gameController.getPanels().get(40));

        //suguri.increaseStarsBy(15);



    }

    /**
     * Get MapColPlaces
     * @return
     */
    public int getMapColPlaces() {
        return mapColPlaces;
    }

    /**
     * Get MapRowPlaces
     * @return
     */
    public int getMapRowPlaces() {
        return mapRowPlaces;
    }

    /**
     * Start Stage
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        setUp();
        this.primaryStage = primaryStage;

        primaryStage.setTitle("99.7% Citric Liquid");


        setGameScene(new NewGameScene(gameController));


        primaryStage.show();

    }

    /**
     * Set gameScene
     * @param aGameScene
     */
    public void setGameScene(GameScene aGameScene){
        this.gameScene = aGameScene;
        primaryStage.setScene(this.gameScene.getScene());

        if(gameController.getGameState() instanceof NormaClearState){

            this.gameScene = new NormaClearScene(gameController);
            primaryStage.setScene(this.gameScene.getScene());
        }

        if(gameController.getGameState() instanceof EndGameState){
            this.gameScene = new EndGameScene(gameController);
            primaryStage.setScene(this.gameScene.getScene());
        }



    }


    /**
     * Get GameScene
     * @return
     */
    public GameScene getGameScene() {
        return gameScene;
    }

    /**
     * Main for javaFX
     * @param args
     */
    public static void main(String[] args) {

        launch(args);

    }

}
