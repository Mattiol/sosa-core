package it.mattiol.sosacore.api.service;

import org.bukkit.entity.Player;

public interface ChatService {

    String formatMessage(Player player, String message);

    void broadcastFormattedMessage(Player player, String message);
}