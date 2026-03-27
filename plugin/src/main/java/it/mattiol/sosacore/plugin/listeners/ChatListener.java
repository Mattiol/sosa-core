package it.mattiol.sosacore.plugin.listeners;

import it.mattiol.sosacore.plugin.SosaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final SosaPlugin plugin;

    public ChatListener(SosaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage().trim();

        if(!plugin.sentinel().isChatEnabled()) {
            event.setCancelled(true);
            return;
        }

        if (!plugin.sentinel().checkMessage(event.getPlayer(), message)) {
            event.setCancelled(true);
            return;
        }

        if (plugin.chat() == null) return;

        String formatted = plugin.chat().formatMessage(event.getPlayer(), message);
        if (formatted == null) {
            event.setCancelled(true);
            return;
        }

        event.setFormat(formatted);
        event.setMessage(message);
    }
}