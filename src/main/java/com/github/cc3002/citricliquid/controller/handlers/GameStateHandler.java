package com.github.cc3002.citricliquid.controller.handlers;

import com.github.cc3002.citricliquid.controller.gameflow.GameController;
import com.github.cc3002.citricliquid.controller.gameflow.GameState;

import java.beans.PropertyChangeEvent;

public class GameStateHandler implements IHandler {

    private final GameController gameController;

    public GameStateHandler(GameController gameController){
        this.gameController = gameController;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        gameController.changeGameStateEvent((GameState) evt.getNewValue());
    }
}
