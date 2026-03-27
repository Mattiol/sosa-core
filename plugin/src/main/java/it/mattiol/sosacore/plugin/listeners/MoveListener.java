package it.mattiol.sosacore.plugin.listeners;

import it.mattiol.sosacore.plugin.SosaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    private final SosaPlugin plugin;

    public MoveListener(SosaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(!plugin.players().isFrozen(player)) return;

        event.setCancelled(true);
    }
}
