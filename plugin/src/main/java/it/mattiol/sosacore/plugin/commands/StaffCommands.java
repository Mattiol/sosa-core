package it.mattiol.sosacore.plugin.commands;

import it.mattiol.sosacore.plugin.SosaPlugin;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class StaffCommands {

    private final SosaPlugin plugin;

    public StaffCommands(SosaPlugin plugin) {
        this.plugin = plugin;
    }

    @Command("freeze")
    @CommandPermission("sosa.core.permissions.freeze")
    public void onFreeze(Player player, Player target) {
        plugin.players().freeze(target);
        plugin.messages().send(player, "freeze.success");
        plugin.messages().send(target, "freeze.frozen");
    }

    @Command("unfreeze")
    @CommandPermission("sosa.core.permissions.freeze")
    public void onUnFreeze(Player player, Player target) {
        plugin.players().unfreeze(target);
        plugin.messages().send(player, "freeze.success-unfrozen");
        plugin.messages().send(target, "freeze.unfrozen");
    }

    @Command("lockchat")
    @CommandPermission("sosa.core.permissions.lockchat")
    public void onLockChat(Player player) {

        if(!plugin.sentinel().isChatEnabled()) {
            plugin.sentinel().setChatEnabled(true);
            plugin.messages().send(player, "sentinel.lockchat-disabled");
        } else {
            plugin.sentinel().setChatEnabled(false);
            plugin.messages().send(player, "sentinel.lockchat-enabled");
        }
    }
}
