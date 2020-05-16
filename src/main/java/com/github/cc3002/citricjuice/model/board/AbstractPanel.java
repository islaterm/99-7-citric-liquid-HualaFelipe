package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author
 * @version
 * @since 1.0
 */
public abstract class AbstractPanel implements Panel {
  private final PanelType type;
  private final Set<Panel> nextPanels;

  /**
   * Creates a new panel.
   *
   * @param
   *
   */
  public AbstractPanel(final PanelType type) {
    this.nextPanels = new HashSet<>();
    this.type = type;
  }


  /**
   * Returns a copy of this panel's next ones.
   */
  public Set<Panel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel
   *     the panel to be added.
   */
  public void addNextPanel(final Panel panel) {
    nextPanels.add(panel);
  }

  /**
   * Returns the type of this panel
   */
  public PanelType getType() {
    return type;
  }

}
