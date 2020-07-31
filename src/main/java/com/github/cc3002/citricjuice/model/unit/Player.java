package com.github.cc3002.citricjuice.model.unit;


import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.NullPanel;
import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricliquid.controller.gameflow.*;
import com.github.cc3002.citricliquid.controller.handlers.IHandler;
import com.github.cc3002.citricliquid.model.NormaGoal;

import java.beans.PropertyChangeSupport;
import java.util.Objects;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class Player extends AbstractUnit{
  private int normaLevel;
  private Panel panel;
  private HomePanel homePanel;
  private NormaGoal normaGoal;
  private TurnController turnController;
  private int playerPathChoice;
  private int playerFightChoice;
  private PropertyChangeSupport turnStateChangeNotification = new PropertyChangeSupport(this);
  private PropertyChangeSupport gameStateChangeNotification = new PropertyChangeSupport(this);

  /**
   * Creates a new Player.
   *
   * @param name
   *     the Player's name.
   * @param hp
   *     the initial (and max) hit points of the Player.
   * @param atk
   *     the base damage the Player does.
   * @param def
   *     the base defense of the Player.
   * @param evd
   *     the base evasion of the Player.
   */
  public Player(final String name, final int hp, final int atk, final int def, final int evd){
    super(name,hp,atk,def, evd);
    normaLevel = 1;
    normaGoal = NormaGoal.STARS;
    panel = new NullPanel();
  }

  public void setPlayerPathChoice(int playerPathChoice) {
    this.playerPathChoice = playerPathChoice;
  }

  public int getPlayerPathChoice() {
    return playerPathChoice;
  }

  public int getPlayerFightChoice() {
    return playerFightChoice;
  }

  public void setPlayerFightChoice(int playerFightChoice) {
    this.playerFightChoice = playerFightChoice;
  }

  public HomePanel getHomePanel() {
    return homePanel;
  }

  public NormaGoal getNormaGoal() {
    return normaGoal;
  }

  public void setHomePanel(Panel homePanel) {
    homePanel.setHome(this);
  }

  public void setHomeHomePanel(HomePanel homePanel) {
    this.homePanel = homePanel;
  }



  public void setNormaGoal(NormaGoal normaGoal) {
    this.normaGoal = normaGoal;
  }

  public Panel getPanel() {
    return panel;
  }

  public void setPanel(Panel panel) {
    this.panel = panel;
  }

  public void setTurnController(TurnController turnController) {
    this.turnController = turnController;
  }

  public void setAtk(int atk) {
    this.atk = atk;
  }

  public void setDef(int def) {
    this.def = def;
  }

  public void setEvd(int evd) {
    this.evd = evd;
  }



  /**
   * Returns the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */

  public void normaClear() {
    normaLevel++;
    if(normaLevel==6){
      gameStateChangeNotification.firePropertyChange("END_GAME", new InGameState(), new EndGameState(this));
    }
    gameStateChangeNotification.firePropertyChange("NORMA_CLEAR", new InGameState(), new NormaClearState());

  }

  public void normaCheck(){

    if(this.normaGoal == NormaGoal.STARS){
      if(this.getStars() > 10 && this.normaLevel == 1){
        normaClear();
      }else if(this.getStars() > 30 && this.normaLevel == 2){
        normaClear();
      }else if(this.getStars() > 70 && this.normaLevel == 3){
        normaClear();
      }else if(this.getStars() > 120 && this.normaLevel == 4){
        normaClear();
      }else if(this.getStars() > 200 && this.normaLevel == 5){
        normaClear();
      }

    }else if(this.normaGoal == NormaGoal.WINS){
      if(this.getVictories() > 2 && this.normaLevel == 2){
        normaClear();
      }else if(this.getVictories() > 5 && this.normaLevel == 3){
        normaClear();
      }else if(this.getVictories() > 9 && this.normaLevel == 4){
        normaClear();
      }else if(this.getVictories() > 14 && this.normaLevel == 5){
        normaClear();
      }

    }

  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Player)) return false;
    if (!super.equals(o)) return false;
    Player player = (Player) o;
    return getNormaLevel() == player.getNormaLevel() &&
            getStars() == player.getStars();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getNormaLevel(), getStars());
  }

  /**
   * Returns a copy of this Player.
   */
  public Player copy() {
    return new Player(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }

  @Override
  public void attackDefend(final Unit defender){
    defender.defendPlayerAttack(this);
  }

  @Override
  public void attackEvade(final Unit defender){
    defender.evadePlayerAttack(this);
  }

  @Override
  public void koPlayerAttack(final Player attacker){
    if(this.getCurrentHP()==0) {
      int stars = this.getStars() / 2;
      attacker.increaseStarsBy(stars);
      this.reduceStarsBy(stars);
      attacker.increaseVictoriesBy(2);
      this.goDead();
    }
  }

  @Override
  public void koWildUnitAttack(WildUnit attacker) {
    if(this.getCurrentHP()==0) {
      int stars = this.getStars() / 2;
      attacker.increaseStarsBy(stars);
      this.reduceStarsBy(stars);
      attacker.increaseVictoriesBy(2);
      this.goDead();
    }
  }

  @Override
  public void koBossUnitAttack(BossUnit attacker) {
    if(this.getCurrentHP()==0) {
      int stars = this.getStars() / 2;
      attacker.increaseStarsBy(stars);
      this.reduceStarsBy(stars);
      attacker.increaseVictoriesBy(2);
      this.goDead();
    }
  }

  public int move(int moveNumber){
    if(turnController.getState().isMovingPhaseActive() || turnController.getState().isWaitPathPhaseActive()){
      return defaultMove(moveNumber);
    }
    throw new RuntimeException();
  }

  public int defaultMove(int moveNumber){
    for(int i = 0; i<moveNumber; i++){
      if(checkFightChoiceEvent() && !turnController.containsPassedWaitFightPanel(getPanel())){
          return i;
      }else if(checkHomeChoiceEvent() && !turnController.getPassedWaitHomePhase()){
        return i;
      }else if(checkPathChoiceEvent() && !turnController.containsPassedWaitPathPanel(getPanel())){
          return i;
      }else{
        moveOne();
      }
    }
    return moveNumber;
  }

  public void moveOne(){
      Panel panel = this.getPanel();
      this.setPanel(panel.getNextPanels().get(this.playerPathChoice));
      this.getPanel().addPlayer(this);
      panel.removePlayer(this);
  }

  public boolean checkPathChoiceEvent(){
    if(this.getPanel().getNextPanels().size()>1){
      turnStateChangeNotification.firePropertyChange("PATH_CHOICE", this.turnController.getState(), new WaitPathPhaseState());
      return true;
    }
    return false;
  }

  public boolean checkFightChoiceEvent(){
    if(this.getPanel().getPlayers().size()>1){
      turnStateChangeNotification.firePropertyChange("FIGHT_CHOICE", this.turnController.getState(), new WaitFightPhaseState());
      return true;
    }
    return false;
  }

  public boolean checkHomeChoiceEvent(){
    if(this.getPanel().getId() == this.getHomePanel().getId() && this.getPanel().equals(this.getHomePanel())){
      turnStateChangeNotification.firePropertyChange("HOME_CHOICE", this.turnController.getState(), new WaitHomePhaseState());
      return true;
    }
    return false;
  }

  public void addMovePlayerHandler(IHandler movePlayerHandler) {
    turnStateChangeNotification.addPropertyChangeListener(movePlayerHandler);

  }

  public void addChangeGameStateHandler(IHandler changeGameStateHandler) {
    gameStateChangeNotification.addPropertyChangeListener(changeGameStateHandler);

  }


}
