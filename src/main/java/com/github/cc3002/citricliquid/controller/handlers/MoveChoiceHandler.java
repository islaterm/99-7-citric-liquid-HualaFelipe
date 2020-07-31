package com.github.cc3002.citricliquid.controller.handlers;

import com.github.cc3002.citricliquid.controller.gameflow.PhaseState;
import com.github.cc3002.citricliquid.controller.gameflow.TurnController;

import java.beans.PropertyChangeEvent;

public class MoveChoiceHandler implements IHandler {

    private final TurnController turnController;

    public MoveChoiceHandler(TurnController turnController) {
        this.turnController = turnController;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        turnController.moveChoiceEvent((PhaseState) evt.getNewValue());
    }
}

