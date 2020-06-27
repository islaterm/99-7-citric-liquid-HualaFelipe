package com.github.cc3002.citricjuice.model.unit;


import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricjuice.model.gameflow.Game;
import com.github.cc3002.citricjuice.model.unit.lifestate.Alive;
import com.github.cc3002.citricjuice.model.unit.lifestate.ILifeState;
import com.github.cc3002.citricliquid.model.NormaGoal;

import java.util.Objects;
import java.util.Scanner;

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
  }

  public HomePanel getHomePanel() {
    return homePanel;
  }

  public NormaGoal getNormaGoal() {
    return normaGoal;
  }

  public void setHomePanel(HomePanel homePanel) {
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
      //END GAME
    }

    Scanner scanner = new Scanner(System.in);
    NormaGoal nG;

    System.out.println("Debes elegir entre \"stars\" o \"wins\"");
    String option = scanner.nextLine();
    while(!(option.equals("stars") || option.equals("wins"))){
      System.out.println("Debes elegir entre \"stars\" o \"wins\"");
      option = scanner.nextLine();
    }
    if(option.equals("stars")){
      nG = NormaGoal.STARS;
    }else{
      nG = NormaGoal.WINS;
    }

    setNormaGoal(nG);

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


  public Panel move(int number){
    Panel panel = this.getPanel();
    Scanner scanner = new Scanner(System.in);
    if(number>0){
      panel.removePlayer(this);
      int counter = number;
      while(counter > 0 ) {

        if(panel.getNextPanels().size()>1){
          //Elegir camino
          int nRoad = panel.getNextPanels().size()+1;

          System.out.println("Debes elegir un camino entre "+nRoad);

          int road = scanner.nextInt();

          while(!(road <= nRoad && road>0)){
            System.out.println("Debes elegir un camino posible entre el 1 y el "+nRoad);
            road = scanner.nextInt();
          }

          panel = panel.getNextPanels().get(road);


        }else if(panel.getNextPanels().size()==1){
          panel = panel.getNextPanels().get(0);
        }

        if(panel.getId() == this.getHomePanel().getId()){
          System.out.println("Debes elegir entre \"normacheck\" o \"seguir\"");
          String option = scanner.nextLine();
          while(!(option.equals("normacheck") || option.equals("seguir"))){
            System.out.println("Debes elegir entre \"normacheck\" o \"seguir\"");
            option = scanner.nextLine();
          }
          if(option.equals("normacheck")){
            break;
          }
        }

        if(!panel.getPlayers().isEmpty()){
          System.out.println("Debes elegir entre \"batalla\" o \"seguir\"");
          String option = scanner.nextLine();
          while(!(option.equals("batalla") || option.equals("seguir"))){
            System.out.println("Debes elegir entre \"batalla\" o \"seguir\"");
            option = scanner.nextLine();
          }
          if(option.equals("batalla")){
            int nPlayers = panel.getPlayers().size()+1;

            System.out.println("Debes elegir a quien atacar entre "+nPlayers+" opciones");
            int battleOption = scanner.nextInt();

            while(!(battleOption <= nPlayers && battleOption>0)){
              System.out.println("Debes elegir a quien atacar entre "+nPlayers+" opciones");
              battleOption = scanner.nextInt();
            }

            System.out.println("Debes elegir entre \"defender\" o \"evadir\"");
            option = scanner.nextLine();
            while(!(option.equals("defender") || option.equals("evadir"))){
              System.out.println("Debes elegir entre \"defender\" o \"evadir\"");
              option = scanner.nextLine();
            }

            if(option.equals("defender")){
              this.attack(panel.getPlayers().get(battleOption),TypeCombat.DEFEND);
            }else{
              this.attack(panel.getPlayers().get(battleOption),TypeCombat.EVADE);
            }

            break;
          }
        }

        counter--;
      }

      panel.addPlayer(this);

    }

    return panel;



  }


}
