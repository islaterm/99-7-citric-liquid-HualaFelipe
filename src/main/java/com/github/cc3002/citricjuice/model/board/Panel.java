package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public interface Panel{
    LinkedList<Panel> getNextPanels();
    void addNextPanel(final Panel panel);
    void activatedBy(final Player player) ;
    ArrayList<Player> getPlayers();

    Player addPlayer(Player player);
    Player removePlayer(Player player);

    int getId();

    void setHome(Player player);

    boolean isEncounterPanel();
    boolean isBossPanel();

    String getImageUrl();

}