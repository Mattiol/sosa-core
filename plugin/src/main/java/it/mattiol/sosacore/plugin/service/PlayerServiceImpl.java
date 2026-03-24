package it.mattiol.sosacore.plugin.service;

import it.mattiol.sosacore.api.service.PlayerService;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerServiceImpl implements PlayerService {

    private final Set<UUID> frozen = ConcurrentHashMap.newKeySet();

    @Override
    public void heal(Player player) {
        player.setHealth(20.0);
    }

    @Override
    public void feed(Player player) {
        player.setFoodLevel(20);
    }

    @Override
    public void freeze(Player player) {
        frozen.add(player.getUniqueId());
    }

    @Override
    public void unfreeze(Player player) {
        frozen.remove(player.getUniqueId());
    }

    public boolean isFrozen(Player player) {
        return frozen.contains(player.getUniqueId());
    }
}