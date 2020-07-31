package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.unit.Player;

public class BossPanel extends AbstractPanel{
    public BossPanel(int id){
        super(id);
    }

    /**
     * Actives the Panel by a player
     *
     * @param player
     */

    public void activatedBy(final Player player) {
        //Battle with a Boss Unit
    }

    @Override
    public boolean isBossPanel(){
        return true;
    }

    @Override
    public String getImageUrl() {
        return "/bossPanel.gif";
    }

}