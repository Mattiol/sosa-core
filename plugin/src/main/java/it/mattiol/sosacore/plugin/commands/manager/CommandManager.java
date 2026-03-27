package it.mattiol.sosacore.plugin.commands.manager;

import it.mattiol.sosacore.plugin.SosaPlugin;
import it.mattiol.sosacore.plugin.commands.GamemodeCommands;
import it.mattiol.sosacore.plugin.commands.MainCommand;
import it.mattiol.sosacore.plugin.commands.StaffCommands;
import it.mattiol.sosacore.plugin.commands.UserCommands;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

public class CommandManager {

    private final SosaPlugin plugin;
    private final Lamp<BukkitCommandActor> lamp;

    public CommandManager(SosaPlugin plugin) {
        this.plugin = plugin;
        this.lamp = BukkitLamp.builder(plugin)
                .dependency(SosaPlugin.class, plugin)
                .build();

        register();
    }

    private void register() {
        lamp.register(new MainCommand(plugin));
        lamp.register(new GamemodeCommands(plugin));
        lamp.register(new StaffCommands(plugin));
        lamp.register(new UserCommands(plugin));
    }
}