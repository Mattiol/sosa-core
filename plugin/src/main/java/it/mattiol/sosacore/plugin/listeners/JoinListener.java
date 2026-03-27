package it.mattiol.sosacore.plugin.listeners;

import it.mattiol.sosacore.plugin.SosaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final SosaPlugin plugin;

    public JoinListener(SosaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.players().feed(player);
        plugin.players().heal(player);
    }
}
