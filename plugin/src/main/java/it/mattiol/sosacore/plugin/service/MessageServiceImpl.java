package it.mattiol.sosacore.plugin.service;

import it.mattiol.sosacore.api.service.MessageService;
import it.mattiol.sosacore.api.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class MessageServiceImpl implements MessageService {

    private final Map<String, String> messages;
    private final String prefix;

    public MessageServiceImpl(Map<String, String> messages, String prefix) {
        this.messages = Map.copyOf(messages);
        this.prefix = prefix;
    }

    @Override
    public String get(String key) {
        return messages.getOrDefault(key, key);
    }

    @Override
    public void send(Player player, String key) {
        if (player == null || !player.isOnline()) return;
        player.sendMessage(Utils.color(Utils.replacePlaceholders(get(key), prefix, player)));
    }

    @Override
    public void sendRaw(Player player, String message) {
        if (player == null || !player.isOnline()) return;
        player.sendMessage(Utils.color(message));
    }

    @Override
    public void sendList(Player player, List<String> messages) {
        if (player == null || !player.isOnline()) return;
        messages.forEach(line -> player.sendMessage(Utils.color(line)));
    }

    @Override
    public void broadcast(String key) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(Utils.color(Utils.replacePlaceholders(get(key), prefix, player)));
    }
}