package it.mattiol.sosacore.api.service;

import org.bukkit.entity.Player;

import java.util.List;

public interface MessageService {

    String get(String path);

    void send(Player player, String path);

    void sendRaw(Player player, String message);

    void sendList(Player player, List<String> messages);

    void broadcast(String path);
}