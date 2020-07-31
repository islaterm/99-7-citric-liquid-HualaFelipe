package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.unit.Player;

public class NeutralPanel extends AbstractPanel{
    public NeutralPanel(int id){
        super(id);
    }

    /**
     * Actives the Panel by a player
     *
     * @param player
     */

    public void activatedBy(final Player player) {

    }

    @Override
    public String getImageUrl() {
        return "/neutralPanel.gif";
    }

}
