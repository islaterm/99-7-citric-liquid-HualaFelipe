package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public class NullPanel implements Panel {

    @Override
    public LinkedList<Panel> getNextPanels() {
        return null;
    }

    @Override
    public void addNextPanel(Panel panel) {

    }

    @Override
    public void activatedBy(Player player) {

    }

    @Override
    public ArrayList<Player> getPlayers() {
        return null;
    }

    @Override
    public Player addPlayer(Player player) {
        return null;
    }

    @Override
    public Player removePlayer(Player player) {
        return null;
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public void setHome(Player player) {
        return;
    }

    @Override
    public boolean isEncounterPanel() {
        return false;
    }

    @Override
    public boolean isBossPanel() {
        return false;
    }

    @Override
    public String getImageUrl() {
        return null;
    }
}
