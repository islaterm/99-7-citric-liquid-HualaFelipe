package com.github.cc3002.citricjuice.model.board;

import java.util.ArrayList;

public class Board {

    private ArrayList<Panel> panels;
    private int activePanel;

    public Board(){
        panels = new ArrayList<Panel>();
        //Initialize activePanelId with -1 refers to none activePanel
        activePanel = -1;
    }

    public void setPanels(ArrayList<Panel> panels) {
        this.panels = panels;
    }

    public ArrayList<Panel> getPanels() {
        return panels;
    }

    public int getActivePanel() {
        return activePanel;
    }

}
