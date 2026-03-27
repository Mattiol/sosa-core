package it.mattiol.sosacore.plugin.commands;

import it.mattiol.sosacore.plugin.SosaPlugin;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class UserCommands {

    private final SosaPlugin plugin;

    public UserCommands(SosaPlugin plugin) {
        this.plugin = plugin;
    }

    @Command("spawn")
    @CommandPermission("sosa.core.permissions.spawn")
    public void onSpawn(Player player){
        plugin.spawn().teleportToSpawn(player);
        plugin.messages().send(player, "core.spawn");
    }
}
