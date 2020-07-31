package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.unit.Player;

public class EncounterPanel extends AbstractPanel{
    public EncounterPanel(int id){
        super(id);
    }

    /**
     * Actives the Panel by a player
     *
     * @param player
     */

    @Override
    public void activatedBy(final Player player) {
        //Battle with a Wild Unit
    }

    @Override
    public boolean isEncounterPanel(){
        return true;
    }

    @Override
    public String getImageUrl() {
        return "/encounterPanel.gif";
    }


}