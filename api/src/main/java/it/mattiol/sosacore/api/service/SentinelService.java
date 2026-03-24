package it.mattiol.sosacore.api.service;

import org.bukkit.entity.Player;

public interface SentinelService {

    boolean checkMessage(Player player, String message);

    boolean isChatEnabled();

    void setChatEnabled(boolean enabled);
}