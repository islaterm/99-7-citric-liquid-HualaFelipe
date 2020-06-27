package com.github.cc3002.citricjuice.model.gameflow;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.ArrayList;

public class Chapter {
    private ArrayList<Turn> turns;
    private int number;
    private Game game;

    public Chapter(Game game,int number){
         this.game = game;
         this.number = number;
    }

    public void start(){
        ArrayList<Player> players = game.getPlayers();
        for(int i=0 ; i <players.size();i++){
            game.setTurnOwner(players.get(i));
            Turn actualTurn = new Turn(this,players.get(i));
            actualTurn.start();
            addTurn(actualTurn);
        }
        end();
    }

    public void end(){
        game.addChapter(this);
        Chapter chapter = new Chapter(game,game.getChapters().size()+1);
        chapter.start();
    }

    public Turn addTurn(Turn turn){
        turns.add(turn);
        return turn;
    }

    public ArrayList<Turn> getTurns() {
        return turns;
    }

    public int getNumber() {
        return number;
    }

    public Game getGame() {
        return game;
    }
}
