package com.github.cc3002.citricliquid.controller;

import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.gameflow.Game;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.WildUnit;
import com.github.cc3002.citricliquid.model.NormaGoal;

import java.util.ArrayList;

public class GameController {

    private Board board;
    private BoardFactory boardFactory;
    private ArrayList<Player> players;
    private ArrayList<BossUnit> bosses;
    private ArrayList<WildUnit> wilds;
    private int chapter;
    private Game game;






    public GameController(){
        board = new Board();
        boardFactory = new BoardFactory(board);
    }

    public Player getWinner(){
        return game.getWinner();
    }

    public Panel createBonusPanel(int id){
        return boardFactory.createBonusPanel(id);
    }

    public Panel createBossPanel(int id){
        return boardFactory.createBossPanel(id);
    }

    public Panel createDrawPanel(int id){
        return boardFactory.createDrawPanel(id);
    }

    public Panel createDropPanel(int id){
        return boardFactory.createDropPanel(id);
    }

    public Panel createEncounterPanel(int id){
        return boardFactory.createEncounterPanel(id);
    }

    public Panel createNeutralPanel(int id){
        return boardFactory.createNeutralPanel(id);
    }

    public Panel createHomePanel(int id){
        return boardFactory.createHomePanel(id);
    }

    public Panel setNextPanel(Panel origin,Panel target){
        origin.addNextPanel(target);
        return origin;
    }

    public Panel getPlayerPanel(Player player){
        return player.getPanel();
    }

    public ArrayList<Panel> getPanels(){
        return board.getPanels();
    }

    public Player createPlayer(String name,int hitPoints,int attack,int defense,int evasion,Panel panel){
        Player newPlayer = new Player(name,hitPoints,attack,defense,evasion);
        newPlayer.setPanel(panel);
        players.add(newPlayer);
        return newPlayer;
    }

    public WildUnit createWildUnit(String name,int hitPoints,int attack,int defense,int evasion){
        WildUnit newWild = new WildUnit(name,hitPoints,attack,defense,evasion);
        wilds.add(newWild);
        return newWild;
    }

    public BossUnit createBossUnit(String name,int hitPoints,int attack,int defense,int evasion){
        BossUnit newBoss = new BossUnit(name,hitPoints,attack,defense,evasion);
        bosses.add(newBoss);
        return newBoss;
    }

    public Player getTurnOwner() {
        return game.getTurnOwner();
    }

    public void setCurrPlayerNormaGoal(NormaGoal goal){
        game.getTurnOwner().setNormaGoal(goal);
    }

    public void setPlayerHome(Player player, HomePanel homePanel){
        player.setHomePanel(homePanel);
    }

    public int getChapter() {
        return game.getChapters().size();
    }

    public void newGame(){
        this.game = new Game(players);
    }

    public void movePlayer(){
        int roll = this.game.getTurnOwner().roll();
        this.game.getTurnOwner().move(roll);
    }

    public void endTurn(){
        game.getActualTurn().endTurn();
    }


}
