package it.mattiol.sosacore.plugin;

import it.mattiol.sosacore.api.SosaAPI;
import it.mattiol.sosacore.api.service.*;
import it.mattiol.sosacore.plugin.commands.manager.CommandManager;
import it.mattiol.sosacore.plugin.config.ConfigManager;
import it.mattiol.sosacore.plugin.listeners.ChatListener;
import it.mattiol.sosacore.plugin.service.*;
import net.luckperms.api.LuckPerms;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class SosaPlugin extends JavaPlugin implements SosaAPI {

    private ConfigManager configManager;

    private PlayerServiceImpl players;
    private MessageServiceImpl messages;
    private SpawnServiceImpl spawn;
    private SentinelServiceImpl sentinel;
    private ChatServiceImpl chat;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);

        this.messages = new MessageServiceImpl(
                configManager.get().getMessages(),
                configManager.get().getPrefix()
        );

        this.players  = new PlayerServiceImpl();
        this.spawn    = new SpawnServiceImpl();

        this.sentinel = new SentinelServiceImpl(
                configManager.get().getBlockedWords(),
                configManager.get().getCooldownMillis(),
                configManager.get().getCapsThreshold(),
                messages
        );

        LuckPerms luckPerms = getLuckPerms();
        if (luckPerms == null) getLogger().warning("LuckPerms not found! Chat formatting will be disabled.");
        else this.chat = new ChatServiceImpl(luckPerms);

        if (configManager.get().getSpawnLocation() != null) spawn.set(configManager.get().getSpawnLocation());

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);

        new CommandManager(this);

        List.of(
                "\u001B[36m  ███████╗ ██████╗ ███████╗ █████╗ ",
                "\u001B[36m  ██╔════╝██╔═══██╗██╔════╝██╔══██╗",
                "\u001B[36m  ███████╗██║   ██║███████╗███████║",
                "\u001B[36m  ╚════██║██║   ██║╚════██║██╔══██║",
                "\u001B[36m  ███████║╚██████╔╝███████║██║  ██║",
                "\u001B[36m  ╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝",
                "\u001B[0m",
                "\u001B[0m  Version: \u001B[36m" + getDescription().getVersion(),
                "\u001B[0m  Author:  \u001B[36m" + String.join(", ", getDescription().getAuthors()),
                "\u001B[0m"
        ).forEach(line -> getLogger().info(line + "\u001B[0m"));
    }

    @Override
    public void onDisable() {
        getLogger().info("SosaCore disabled.");
    }

    private LuckPerms getLuckPerms() {
        if (getServer().getPluginManager().getPlugin("LuckPerms") == null) return null;
        RegisteredServiceProvider<LuckPerms> provider = getServer()
                .getServicesManager()
                .getRegistration(LuckPerms.class);
        return provider != null ? provider.getProvider() : null;
    }

    @Override public PlayerService players()   { return players; }
    @Override public MessageService messages()  { return messages; }
    @Override public SpawnService spawn()       { return spawn; }
    @Override public SentinelService sentinel() { return sentinel; }
    @Override public ChatService chat()         { return chat; }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}