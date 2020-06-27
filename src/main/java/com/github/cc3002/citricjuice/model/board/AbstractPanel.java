package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author
 * @version
 * @since 1.0
 */

public abstract class AbstractPanel implements Panel {
  private final LinkedList<Panel> nextPanels;
  private ArrayList<Player> players;
  private int id;


  /**
   * Creates a new panel.
   *
   * @param
   *
   */
  public AbstractPanel(int id) {
      this.id=id;
      this.nextPanels = new LinkedList<Panel>();
  }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player addPlayer(Player player){
      players.add(player);
      player.setPanel(this);
      return player;
    }

    public Player removePlayer(Player player){
      players.remove(player);
      player.setPanel(null);
      return player;
    }

    public int getId() {
        return id;
    }

    /**
   * Returns a copy of this panel's next ones.
   * @return
   */
  public LinkedList<Panel> getNextPanels() {
      LinkedList<Panel> copy = new LinkedList<Panel>();
      Panel[] array = new Panel[0];
      array = nextPanels.toArray(array);
      for(int i=0; i<array.length;i++){
          copy.add(array[i]);
      }

      return (copy);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel
   *     the panel to be added.
   */
  public void addNextPanel(final Panel panel) {
    Object panelActual = panel;
    boolean alrreadyExist = false;
      Object[] array = nextPanels.toArray();

    for(int i=0; i<array.length;i++){
        if(array[i]==panelActual){
            alrreadyExist=true;
        }
    }

    if(!alrreadyExist) {
        nextPanels.add(panel);
    }

  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof AbstractPanel)) return false;
        AbstractPanel that = (AbstractPanel) o;
        return Objects.equals(getNextPanels(), that.getNextPanels());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNextPanels());
    }
}
