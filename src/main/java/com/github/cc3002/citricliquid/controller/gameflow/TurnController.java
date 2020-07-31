package com.github.cc3002.citricliquid.controller.gameflow;

import com.github.cc3002.citricjuice.model.board.Panel;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.TypeCombat;
import com.github.cc3002.citricliquid.controller.handlers.IHandler;
import com.github.cc3002.citricliquid.controller.handlers.MoveChoiceHandler;
import com.github.cc3002.citricliquid.gui.GameGUI;

import java.util.ArrayList;
import java.util.List;

public class TurnController {
    private PhaseState state;
    private Player player;
    private int chapter;
    private int dice;
    private IHandler moveChoiceHandler = new MoveChoiceHandler(this);
    private boolean passedWaitHomePhase;
    private List<Panel> passedWaitFightPanels;
    private List<Panel> passedWaitPathPanels;
    private TypeCombat typeCombat;
    private GameController gameController;


    /**
     * Constructor for a TurnController
     * @param player
     * @param chapter
     * @param gameController
     */
    public TurnController(Player player, int chapter, GameController gameController){
        this.player = player;
        this.chapter = chapter;
        this.gameController = gameController;
        this.player.setTurnController(this);
        this.dice = -1;
        this.passedWaitHomePhase = false;
        this.passedWaitFightPanels = new ArrayList<Panel>();
        this.passedWaitPathPanels = new ArrayList<Panel>();
        this.setPhaseState(new StartTurnPhaseState());
        player.addMovePlayerHandler(moveChoiceHandler);
    }

    /**
     * Get the type combat
     * @return
     */
    public TypeCombat getTypeCombat() {
        return typeCombat;
    }

    /**
     * Set type combat
     * @param typeCombat
     */
    public void setTypeCombat(TypeCombat typeCombat) {
        this.typeCombat = typeCombat;
    }

    /**
     * Get passedWaitHomePhase parameter
     * @return
     */
    public boolean getPassedWaitHomePhase() {
        return passedWaitHomePhase;
    }

    /**
     * Set passedWaitHomePhase parameter
     * @return
     */
    public void setPassedWaitHomePhase(boolean passedWaitHomePhase) {
        this.passedWaitHomePhase = passedWaitHomePhase;
    }

    /**
     * Add passedWaitPathPanel
     * @param aPanel
     */
    public void addPassedWaitPathPanel(Panel aPanel){
        this.passedWaitPathPanels.add(aPanel);
    }

    /**
     * return containsPassedWaitPathPanel
     * @param aPanel
     * @return
     */
    public boolean containsPassedWaitPathPanel(Panel aPanel){

        boolean contains = false;

        for(int i=0; i<this.passedWaitPathPanels.size();i++){
            if(this.passedWaitPathPanels.get(i).getId()==aPanel.getId()){
                contains = true;
            }
        }

        return contains;
    }

    /**
     * Add a panel to addPassedWaitFightPanel List
     * @param aPanel
     */
    public void addPassedWaitFightPanel(Panel aPanel){
        this.passedWaitFightPanels.add(aPanel);
    }

    /**
     * Search if containsPassedWaitFightPanel List contains the panel
     * @param aPanel
     * @return
     */
    public boolean containsPassedWaitFightPanel(Panel aPanel){

        boolean contains = false;

        for(int i=0; i<this.passedWaitFightPanels.size();i++){
            if(this.passedWaitFightPanels.get(i).getId()==aPanel.getId()){
                contains = true;
            }
        }

        return contains;
    }


    /**
     * Method for handler
     * @param phaseState
     */
    public void moveChoiceEvent(PhaseState phaseState){
        setPhaseState(phaseState);
    }

    /**
     * Set dice parameter
     * @param dice
     */
    public void setDice(int dice) {
        this.dice = dice;
    }

    /**
     * Get dice parameter
     * @return
     */
    public int getDice() {
        return dice;
    }

    /**
     * Set the PhaseState
     * @param aPhaseState
     */
    void setPhaseState(PhaseState aPhaseState){
        state = aPhaseState;
        state.setTurnController(this);
    }

    /**
     * Get the PhaseState
     * @return
     */
    public PhaseState getState() {
        return state;
    }

    /**
     * Get the turn owner player
     * @return
     */
    public Player getTurnOwner(){
        return player;
    }

    /**
     * Get the chapter game
     * @return
     */
    public int getChapter() {
        return chapter;
    }

    /**
     * State methods
     */
    public void startTurn(){
        state.startTurn();
    }

    public void move(){state.move();}
    public void roll(){state.roll();}
    public void continueMovement(){state.continueMovement();}
    public void moveDecision() {
        state.moveDecision();
    }
    public void endTurnDecision(){
        state.endTurnDecision();
    }

    public int changePlayerPathDecision(int i){
        return state.changePlayerPathDecision(i);
    }
    public void fightDecision(){state.fightDecision();}
    public int changeFightDecision(int i){
        return state.changeFightDecision(i);
    }

    public void attack(){
        state.attack();
    }

    public void rollAtkAttack(){
        state.rollAtkAttack();
    }

    public void rollDefAttack(){
        state.rollDefAttack();
    }
    public void selectTypeCombat(TypeCombat typeCombat){
        state.selectTypeCombat(typeCombat);
    }

    public void rollAtkContraAttack(){
        state.rollAtkContraAttack();
    }
    public void rollDefContraAttack(){
        state.rollDefContraAttack();
    }

    public void contraAttack(){
        state.contraAttack();
    }

    public void endBattle(){
        state.endBattle();
    }
    public void rollRecovery(){
        state.rollRecovery();
    }


    public GameController getGameController() {
        return gameController;
    }
}
