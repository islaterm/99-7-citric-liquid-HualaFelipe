package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.Player;

import java.util.LinkedList;

public interface Panel{
    LinkedList<Panel> getNextPanels();
    void addNextPanel(final Panel panel);
    void activatedBy(final Player player) ;
}