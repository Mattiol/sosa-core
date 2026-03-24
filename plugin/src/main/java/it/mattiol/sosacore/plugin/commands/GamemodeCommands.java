package it.mattiol.sosacore.plugin.commands;

import it.mattiol.sosacore.plugin.SosaPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Optional;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class GamemodeCommands {

    private final SosaPlugin plugin;

    public GamemodeCommands(SosaPlugin plugin) {
        this.plugin = plugin;
    }

    @Command({"gmc", "gmcreative"})
    @CommandPermission("sosa.core.permissions.gmc")
    public void gmc(Player player, @Optional Player target) {
        Player affected = target != null ? target : player;
        affected.setGameMode(GameMode.CREATIVE);
        plugin.messages().send(player, "gamemode.creative");
        if (target != null) plugin.messages().send(target, "gamemode.creative");
    }

    @Command({"gms", "gmsurvival"})
    @CommandPermission("sosa.core.permissions.gms")
    public void gms(Player player, @Optional Player target) {
        Player affected = target != null ? target : player;
        affected.setGameMode(GameMode.SURVIVAL);
        plugin.messages().send(player, "gamemode.survival");
        if (target != null) plugin.messages().send(target, "gamemode.survival");
    }

    @Command({"gma", "gmadventure"})
    @CommandPermission("sosa.core.permissions.gma")
    public void gma(Player player, @Optional Player target) {
        Player affected = target != null ? target : player;
        affected.setGameMode(GameMode.ADVENTURE);
        plugin.messages().send(player, "gamemode.adventure");
        if (target != null) plugin.messages().send(target, "gamemode.adventure");
    }

    @Command({"gmsp", "gmspectator"})
    @CommandPermission("sosa.core.permissions.gmsp")
    public void gmsp(Player player, @Optional Player target) {
        Player affected = target != null ? target : player;
        affected.setGameMode(GameMode.SPECTATOR);
        plugin.messages().send(player, "gamemode.spectator");
        if (target != null) plugin.messages().send(target, "gamemode.spectator");
    }
}