package it.mattiol.sosacore.api.service;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface SpawnService {

    void set(Location location);

    Location get();

    void teleportToSpawn(Player player);
}