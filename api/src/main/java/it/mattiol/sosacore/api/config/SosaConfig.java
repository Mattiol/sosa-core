package it.mattiol.sosacore.api.config;

import org.bukkit.Location;

import java.util.List;
import java.util.Map;

public interface SosaConfig {

    long getCooldownMillis();
    double getCapsThreshold();
    List<String> getBlockedWords();
    String getChatFormat();
    String getPrefix();
    Map<String, String> getMessages();
    Location getSpawnLocation();
    void setSpawnLocation(Location location);
}