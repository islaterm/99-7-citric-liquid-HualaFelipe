package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.Player;

import java.util.Set;

public interface Panel{
    Set<Panel> getNextPanels();
    void addNextPanel(final Panel panel);
    PanelType getType();
    void activatedBy(final Player player) ;
}