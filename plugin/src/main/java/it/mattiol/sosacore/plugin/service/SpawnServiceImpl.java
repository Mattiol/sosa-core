package it.mattiol.sosacore.plugin.service;

import it.mattiol.sosacore.api.service.SpawnService;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnServiceImpl implements SpawnService {

    private Location spawnLocation;

    @Override
    public void set(Location location) {
        if (location == null) throw new IllegalArgumentException("Spawn location cannot be null");
        this.spawnLocation = location.clone();
    }

    @Override
    public Location get() {
        if (spawnLocation == null) throw new IllegalStateException("Spawn location has not been set yet");
        return spawnLocation.clone();
    }

    @Override
    public void teleportToSpawn(Player player) {
        if (player == null || !player.isOnline()) return;
        if (spawnLocation == null) throw new IllegalStateException("Spawn location has not been set yet");
        player.teleport(spawnLocation.clone());
    }
}