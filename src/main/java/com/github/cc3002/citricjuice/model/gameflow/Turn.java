package com.github.cc3002.citricjuice.model.gameflow;

import com.github.cc3002.citricjuice.model.gameflow.phases.*;
import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.ArrayList;

public class Turn {
    private Player player;
    private ArrayList<IPhase> phases;
    private Chapter chapter;
    private int actualPhase;

    public Turn(Chapter chapter, Player player){

        this.chapter = chapter;
        this.player=player;
        actualPhase = 0;
        phases = new ArrayList<IPhase>();
        phases.add(new Phase1(this));
        phases.add(new Phase2(this));
        phases.add(new Phase3(this));
        phases.add(new Phase4(this));
        phases.add(new Phase5(this));
        phases.add(new Phase6(this));

    }

    public void start(){
        for(; actualPhase<phases.size(); actualPhase++){
            phases.get(actualPhase).start();
        }
    }

    public void setActualPhase(int actualPhase) {
        this.actualPhase = actualPhase;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<IPhase> getPhases() {
        return phases;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void endTurn(){
        this.setActualPhase(this.getPhases().size());
    }
}
