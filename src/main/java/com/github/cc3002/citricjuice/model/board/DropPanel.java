package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.unit.Player;
import org.jetbrains.annotations.NotNull;


public class DropPanel extends AbstractPanel{
    public DropPanel(int id){
        super(id);
    }

    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     */
    private static void applyDropTo(final @NotNull Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }

    /**
     * Actives the Panel by a player
     *
     * @param player
     */

    public void activatedBy(final Player player) {
        applyDropTo(player);
    }

    @Override
    public String getImageUrl() {
        return "/dropPanel.gif";
    }

}
