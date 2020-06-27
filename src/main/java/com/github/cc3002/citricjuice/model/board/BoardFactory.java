package com.github.cc3002.citricjuice.model.board;

import java.util.ArrayList;


public class BoardFactory {

    private Board board;
    private int factoryPosition;

    public BoardFactory(Board board){
        this.board = board;
        factoryPosition = board.getPanels().size();
    }

    public Panel addPanel(Panel panel){
        ArrayList<Panel> panels = board.getPanels();
        panels.add(panel);
        board.setPanels(panels);
        return panel;
    }

    public Panel addPanelInLine(Panel panel){
        board.getPanels().get(factoryPosition).addNextPanel(panel);
        addPanel(panel);
        factoryPosition = board.getPanels().size();
        return panel;
    }

    public void addLine(ArrayList<Panel> panels){
        for(int i=0; i<panels.size();i++){
            addPanelInLine(panels.get(i));
        }
    }


    public Panel createBonusPanel(int id){
        return addPanel(new BonusPanel(id));
    }

    public Panel createBossPanel(int id){
        return addPanel(new BossPanel(id));
    }

    public Panel createDrawPanel(int id){
        return addPanel(new DrawPanel(id));
    }

    public Panel createDropPanel(int id){
        return addPanel(new DropPanel(id));
    }

    public Panel createEncounterPanel(int id){
        return addPanel(new EncounterPanel(id));
    }

    public Panel createNeutralPanel(int id){
        return addPanel(new NeutralPanel(id));
    }

    public Panel createHomePanel(int id){
        return addPanel(new HomePanel(id));
    }











}
