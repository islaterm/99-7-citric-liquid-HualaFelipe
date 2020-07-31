package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;
import com.github.cc3002.citricliquid.controller.handlers.IHandler;
import com.github.cc3002.citricliquid.controller.handlers.GameStateHandler;
import com.github.cc3002.citricliquid.gui.EndGameScene;
import com.github.cc3002.citricliquid.gui.GameGUI;
import com.github.cc3002.citricliquid.gui.NormaClearScene;
import com.github.cc3002.citricliquid.model.NormaGoal;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private GameState gameState;
    private IHandler gameStateHandler = new GameStateHandler(this);
    private List<Player> players;
    private List<BossUnit> bosses;
    private List<WildUnit> wilds;
    private List<Panel> panels;
    private int chapter;
    private TurnController turnController;
    private Player winner;
    private GameGUI gameGUI;

    /**
     * Creates a GameController
     */
    public GameController(){
        this.players = new ArrayList<Player>();
        this.bosses = new ArrayList<BossUnit>();
        this.wilds = new ArrayList<WildUnit>();
        this.panels = new ArrayList<Panel>();
        this.chapter = 0;
        //this.turnController = new TurnController(new Player("Null",0,0,0,0),0,this);
        setGameState(new NewGameState());

    }

    /**
     * Set game is gameGUI
     * @param gameGUI
     */
    public void setGameGUI(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    /**
     * Get the gameGUI
     * @return
     */
    public GameGUI getGameGUI() {
        return gameGUI;
    }

    /**
     * Set correctly the gameState
     * @param gameState
     */
    public void setGameState(GameState gameState) {

        this.gameState = gameState;
        this.gameState.setGameController(this);

    }

    /**
     * Get TurnController
     * @return
     */
    public TurnController getTurnController() {
        return turnController;
    }

    /**
     * Create a bonus panel
     * @return
     */
    public Panel createBonusPanel(){
        int id = panels.size();
        Panel panel = new BonusPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a boss panel
     * @return
     */
    public Panel createBossPanel(){
        int id = panels.size();
        Panel panel = new BossPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a Drop panel
     * @return
     */
    public Panel createDropPanel(){
        int id = panels.size();
        Panel panel = new DropPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a Encounter Panel
     * @return
     */
    public Panel createEncounterPanel(){
        int id = panels.size();
        Panel panel = new EncounterPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a Neutral Panel
     * @return
     */
    public Panel createNeutralPanel(){
        int id = panels.size();
        Panel panel = new NeutralPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a Home Panel
     * @return
     */
    public Panel createHomePanel(){
        int id = panels.size();
        Panel panel = new HomePanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Get panels
     * @return
     */
    public List<Panel> getPanels(){
        return panels;
    }

    /**
     * Set a next panel from a panel
     * @param origin
     * @param target
     */
    public void setNextPanel(Panel origin,Panel target){
        origin.addNextPanel(target);
    }

    /**
     * Set multiples next panel from a panel
     * @param origin
     * @param targets
     */
    public void setNextPanel(Panel origin,Panel... targets){
        for(Panel target:targets){
            origin.addNextPanel(target);
        }
    }

    /**
     * Get the players
     * @return
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Get the bosses
     * @return
     */
    public List<BossUnit> getBosses() {
        return bosses;
    }

    /**
     * Get the Wilds
     * @return
     */
    public List<WildUnit> getWilds() {
        return wilds;
    }

    /**
     * Get the player panel
     * @param player
     * @return
     */
    public Panel getPlayerPanel(Player player){
        return player.getPanel();
    }

    /**
     * Creates a new player
     * @param name
     * @param hitPoints
     * @param attack
     * @param defense
     * @param evasion
     * @return
     */
    public Player createPlayer(String name,int hitPoints,int attack,int defense,int evasion){
        Player newPlayer = new Player(name,hitPoints,attack,defense,evasion);
        newPlayer.addChangeGameStateHandler(gameStateHandler);
        players.add(newPlayer);
        return newPlayer;
    }

    /**
     * Set a player into a panel
     * @param player
     * @param panel
     */
    public void setPlayerPanel(Player player, Panel panel){
        player.getPanel().removePlayer(player);
        panel.addPlayer(player);
    }


    /**
     * Create a Wild Unit
     * @param name
     * @param hitPoints
     * @param attack
     * @param defense
     * @param evasion
     * @return
     */
    public WildUnit createWildUnit(String name,int hitPoints,int attack,int defense,int evasion){
        WildUnit newWild = new WildUnit(name,hitPoints,attack,defense,evasion);
        wilds.add(newWild);
        return newWild;
    }

    /**
     * Create a Boss Unit
     * @param name
     * @param hitPoints
     * @param attack
     * @param defense
     * @param evasion
     * @return
     */
    public BossUnit createBossUnit(String name,int hitPoints,int attack,int defense,int evasion){
        BossUnit newBoss = new BossUnit(name,hitPoints,attack,defense,evasion);
        bosses.add(newBoss);
        return newBoss;
    }

    /**
     * Get de player turn owner
     * @return
     */
    public Player getTurnOwner() {
        return turnController.getTurnOwner();
    }

    /**
     * Set the current player norma goal
     * @param goal
     */
    public void setCurrPlayerNormaGoal(NormaGoal goal){
        getTurnOwner().setNormaGoal(goal);
    }

    /**
     * Set a player is Home Panel
     * @param player
     * @param homePanel
     */
    public void setPlayerHome(Player player, Panel homePanel){
        player.setHomePanel(homePanel);
    }

    /**
     * Get the chapter of the game
     * @return
     */
    public int getChapter() {
        return chapter;
    }

    /**
     * End turn of the actual player
     */
    public void endTurn(){

        int playerNumber = players.indexOf(turnController.getTurnOwner()) + 1;

        if(playerNumber == players.size()){
            this.chapter = this.chapter + 1;
            playerNumber = 0;
        }

        turnController = new TurnController(players.get(playerNumber),getChapter(),this);
    }

    /**
     * Get the winner of the game
     * @return
     */
    public Player getWinner(){
        return this.winner;
    }


    /**
     * Start a new game
     */
    public void newGame(){
        this.chapter = 1;
        turnController = new TurnController(players.get(0),getChapter(),this);
    }

    /**
     * Method to run GameStateHandler
     * @param gameState
     */
    public void changeGameStateEvent(GameState gameState) {
        setGameState(gameState);
        if(gameState.isEndGameStateActive()){
            this.winner = gameState.getWinner();
        }
    }

    /**
     * Get the game state
     * @return
     */
    public GameState getGameState() {
        return gameState;
    }
}
