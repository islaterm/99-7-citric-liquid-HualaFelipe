package com.github.cc3002.citricjuice.model.gameflow;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.ArrayList;

public class Game {

    private ArrayList<Chapter> chapters;
    private ArrayList<Player> players;
    private Player turnOwner;
    private Player winner;

    public Game(ArrayList<Player> players){
        this.players = players;
        this.chapters = new ArrayList<Chapter>();
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getTurnOwner() {
        return turnOwner;
    }

    public void setTurnOwner(Player turnOwner) {
        this.turnOwner = turnOwner;
    }

    public void start(){
        Chapter chapter = new Chapter(this,chapters.size()+1);
        chapter.start();
    }

    public Chapter addChapter(Chapter chapter){
        chapters.add(chapter);
        return chapter;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Turn getActualTurn(){
        int nChapters = this.getChapters().size();
        int nTurns = this.getChapters().get(nChapters).getTurns().size();
        return this.getChapters().get(nChapters).getTurns().get(nTurns);
    }

    public void endGame(Player player){
        setWinner(player);
    }



}
