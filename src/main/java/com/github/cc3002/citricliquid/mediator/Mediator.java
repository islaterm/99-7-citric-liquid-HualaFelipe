package com.github.cc3002.citricliquid.mediator;

import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricjuice.model.board.AbstractPanel;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Unit;
import com.github.cc3002.citricjuice.model.unit.WildUnit;
import com.github.cc3002.citricliquid.controller.GameController;
import com.github.cc3002.citricliquid.model.NormaGoal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mediator class to connect the game'S controller with a test suite.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 2.0-rc.1
 * @since 2.0
 */
public class Mediator {

  private final GameController controller = new GameController();

  // From here on you have to modify the code with calls to the methods of your implementation of
  // the game's controller.
  // You'll have to modify the methods body and replace the <Object> parameters of the mediator
  // classes in the methods to the actual types you have defined.
  // For example:
  //    Change:
  //      public class MediatorPanel<T extends Object>
  //    With:
  //      public class MediatorPanel<T extends Panel>

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // YOU CAN ONLY MODIFY THE SEGMENTS COMMENTED WITH A (!) AND THE GENERIC TYPES AS STATED IN THE
  // PREVIOUS EXAMPLE.
  // *** ANY OTHER CHANGE WILL BE PENALIZED ***
  //////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Creates a new bonus panel, adds it to the game and returns the created panel inside a
   * mediator.
   */
  public MediatorPanel<Panel> createBonusPanel(int id) {
    return new MediatorPanel<>(controller.createBonusPanel(id));
  }

  /**
   * Creates a new boss panel, adds it to the game and returns the created panel inside a mediator.
   */
  public MediatorPanel<Panel> createBossPanel(int id) {
    return new MediatorPanel<>(controller.createBossPanel(id));
  }

  /**
   * Creates a new drop panel, adds it to the game and returns the created panel inside a mediator.
   */
  public MediatorPanel<Panel> createDropPanel(int id) {
    return new MediatorPanel<>(controller.createDropPanel(id));
  }

  /**
   * Creates a new encounter panel, adds it to the game and returns the created panel inside a
   * mediator.
   */
  public MediatorPanel<Panel> createEncounterPanel(int id) {
    return new MediatorPanel<>(controller.createEncounterPanel(id));
  }

  /**
   * Creates a new home panel, adds it to the game and returns the created panel inside a mediator.
   */
  public MediatorPanel<Panel> createHomePanel(int id) {
    return new MediatorPanel<>(controller.createHomePanel(id));
  }

  /**
   * Creates a new neutral panel, adds it to the game and returns the created panel inside a
   * mediator.
   */
  public MediatorPanel<Panel> createNeutralPanel(int id) {
    return new MediatorPanel<>(controller.createNeutralPanel(id));
  }

  /**
   * Creates a new Player, locates it in a panel and returns a pair with the created player and it's
   * associated panel, each one in a mediator object.
   */
  public Pair<MediatorPlayer<Player>, MediatorPanel<Panel>> createPlayer(
          MediatorPanel<Panel> mediatorPanel, String name, int hitPoints, int attack, int defense,
      int evasion) {
    MediatorPlayer<Player> mediatorPlayer = new MediatorPlayer<>(
            controller.createPlayer(name, hitPoints, attack, defense, evasion, mediatorPanel.panel));
    return new Pair<>(mediatorPlayer, mediatorPanel);
  }

  /**
   * Creates a new wild unit, adds it to the game and returns the created unit.
   */
  public MediatorWildUnit<WildUnit> createWildUnit(String name, int hitPoints, int attack, int defense,
                                                   int evasion) {
    return new MediatorWildUnit<>(controller.createWildUnit(name, hitPoints, attack, defense, evasion));
  }

  /**
   * Creates a new wild unit, adds it to the game and returns the created unit.
   */
  public MediatorBoss<BossUnit> createBossUnit(String name, int hitPoints, int attack, int defense,
                                               int evasion) {
    return new MediatorBoss<>(controller.createBossUnit(name, hitPoints, attack, defense, evasion));
  }

  /**
   * Adds a new next panel to another and returns the first one wrapped in a mediator.
   */
  public MediatorPanel<Panel> setNextPanel(MediatorPanel<Panel> origin, MediatorPanel<Panel> target) {
    controller.setNextPanel(origin.panel, target.panel);
    return origin;
  }

  /**
   * Returns a list with all the panels in the game wrapped inside a mediator object.
   */
  public List<MediatorPanel<Panel>> getPanels() {
    var panels = new ArrayList<MediatorPanel<Panel>>();
    for (var panel : controller.getPanels()) {
      panels.add(new MediatorPanel<>(panel));
    }
    return panels;
  }

  /**
   * Returns the winner of the game or {@code null} if there's no winner yet.
   */
  @Contract(pure = true)
  @Nullable
  public MediatorPlayer<Player> getWinner() {
    // (!) Implement this method
    var player = new MediatorPlayer<>(controller.getWinner());
    return player;
  }

  /**
   * Makes the turn owner move a random number of panels and returns the turn owner and the panel it
   * landed each one wrapped in a mediator.
   */
  public Pair<MediatorPlayer<Player>, MediatorPanel<Panel>> movePlayer() {
    controller.movePlayer();
    var player = new MediatorPlayer<>(controller.getTurnOwner());
    var panel = new MediatorPanel<>(controller.getPlayerPanel(player.unit));
    return new Pair<>(player, panel);
  }

  /**
   * Sets the norma goal of the turn owner and returns the player wrapped in a mediator.
   */
  public MediatorPlayer<Player> setNormaGoal(NormaGoal goal) {
    controller.setCurrPlayerNormaGoal(goal);
    return new MediatorPlayer<>(controller.getTurnOwner());
  }

  /**
   * Sets a player's home panel.
   */
  public void setPlayerHome(final MediatorPlayer<Player> player, final MediatorPanel<Panel> homePanel) {
    controller.setPlayerHome(player.unit, (HomePanel) homePanel.panel);
  }

  /**
   * Returns the current chapter.
   */
  public int getChapter() {
    return controller.getChapter();
  }

  public MediatorPlayer<Player> getTurnOwner() {
    return new MediatorPlayer<>(controller.getTurnOwner());
  }

  /**
   * Ends the current player's turn
   */
  public void endTurn() {
    controller.endTurn();
  }

  /**
   * Utility method to create a player based on another.
   */
  public Pair<MediatorPlayer<Player>, MediatorPanel<Panel>> createPlayer(
      final MediatorPanel<Panel> panel, final @NotNull MediatorPlayer<Player> player) {
    return createPlayer(panel, player.getName(), player.getMaxHP(), player.getAtk(),
                        player.getDef(), player.getEvd());
  }


  /**
   * Mediator class to wrap panels into a format compliant to that of the mediator.
   *
   * @param <T>
   *     The panel's class.
   */
  public static class MediatorPanel<T extends Panel> {
    private final T panel;

    public MediatorPanel(T panel) {
      this.panel = panel;
    }

    /**
     * Returns this panel's next panels wrapped in a MediatorPanel object.
     */
    public List<MediatorPanel<Panel>> getNextPanels() {
      var nextPanels = new ArrayList<MediatorPanel<Panel>>();
      for (var nextPanel : panel.getNextPanels()) {
        nextPanels.add(new MediatorPanel<>(nextPanel));
      }
      return nextPanels;
    }

    /**
     * Returns the players located in this panel wrapped in a mediator.
     */
    @Contract(pure = true)
    @NotNull
    public List<MediatorPlayer<Player>> getPlayers() {
      var players = new ArrayList<MediatorPlayer<Player>>();
      for (var player : panel.getPlayers()) {
        players.add(new MediatorPlayer<>(player));
      }
      return players;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final MediatorPanel<Panel> that = (MediatorPanel<Panel>) o;
      return panel.equals(that.panel);
    }

    @Override
    public int hashCode() {
      return Objects.hash(panel);
    }
  }

  /**
   * Mediator class to wrap units into a format compliant to that of the mediator.
   *
   * @param <T>
   *     The unit's class.
   */
  public static class MediatorUnit<T extends Unit> {
    protected T unit;

    public MediatorUnit() {
      throw new UnsupportedOperationException("This constructor is present just as a placeholder.");
    }

    public MediatorUnit(T unit) {
      this.unit = unit;
    }

    public T getUnit() {
      return unit;
    }

    public String getName() {
      return unit.getName();
    }

    public int getMaxHP() {
      return unit.getMaxHP();
    }

    public int getAtk() {
      return unit.getAtk();
    }

    public int getDef() {
      return unit.getDef();
    }

    public int getEvd() {
      return unit.getEvd();
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final MediatorUnit<?> that = (MediatorUnit<?>) o;
      return unit.equals(that.getUnit());
    }

    @Override
    public int hashCode() {
      return Objects.hash(unit);
    }
  }

  /**
   * Mediator class to wrap players into a format compliant to that of the mediator.
   *
   * @param <T>
   *     The player's class.
   */
  public static class MediatorPlayer<T extends Player> extends MediatorUnit<T> {
    public MediatorPlayer(String name, int hitPoints, int attack, int defense, int evasion) {
      super((T) new Player(name, hitPoints, attack, defense, evasion));
    }

    public MediatorPlayer(final T player) {
      super(player);
    }

    /**
     * Returns the amount of stars of the player.
     */
    public int getStars() {
      return unit.getStars();
    }

    /**
     * Returns the player's norma goal.
     */
    public NormaGoal getNormaGoal() {
      return unit.getNormaGoal();
    }

    /**
     * Returns the player's norma level.
     */
    public int getNormaLevel() {
      return unit.getNormaLevel();
    }
  }

  /**
   * Mediator class to wrap wild units into a format compliant to that of the mediator.
   *
   * @param <T>
   *     The unit's class.
   */
  public static class MediatorWildUnit<T extends WildUnit> extends MediatorUnit<T> {
    public MediatorWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
      this((T) new WildUnit(name, hitPoints, attack, defense, evasion));
    }

    public MediatorWildUnit(final T unit) {
      super(unit);
    }
  }

  /**
   * Mediator class to wrap bosses into a format compliant to that of the mediator.
   *
   * @param <T>
   *     The boss' class.
   */
  public static class MediatorBoss<T extends BossUnit> extends MediatorUnit<T> {
    public MediatorBoss(String name, int hitPoints, int attack, int defense, int evasion) {
      super((T) new BossUnit(name, hitPoints, attack, defense, evasion));
    }
    public MediatorBoss(final T bossUnit) {
      super(bossUnit);
    }
  }

  /**
   * Utility class that represents an heterogeneous pair of objects.
   */
  public static class Pair<T1, T2> {
    private final T1 first;
    private final T2 second;

    public Pair(final T1 first, final T2 second) {
      this.first = first;
      this.second = second;
    }

    public T1 getFirst() {
      return first;
    }

    public T2 getSecond() {
      return second;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final Pair<?, ?> pair = (Pair<?, ?>) o;
      return Objects.equals(first, pair.first) &&
             Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
    }

    @Override
    public String toString() {
      return "(" + first.toString() + ", " + second.toString() + ")";
    }
  }
}
