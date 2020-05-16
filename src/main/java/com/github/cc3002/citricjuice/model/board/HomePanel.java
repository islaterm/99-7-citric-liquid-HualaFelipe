package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.Player;
import org.jetbrains.annotations.NotNull;

public class HomePanel extends AbstractPanel{
    public HomePanel(){
        super(PanelType.HOME);
    }

    /**
     * Restores a player's HP in 1.
     */
    private static void applyHealTo(final @NotNull Player player) {
        player.setCurrentHP(player.getCurrentHP() + 1);
    }

    /**
     * Actives the Panel by a player
     *
     * @param player
     */

    public void activatedBy(final Player player) {
        applyHealTo(player);

    }
}
