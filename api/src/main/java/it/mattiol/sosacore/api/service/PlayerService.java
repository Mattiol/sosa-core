package it.mattiol.sosacore.api.service;

import org.bukkit.entity.Player;

public interface PlayerService {

    void heal(Player player);

    void feed(Player player);

    void freeze(Player player);

    void unfreeze(Player player);

    boolean isFrozen(Player player);
}
