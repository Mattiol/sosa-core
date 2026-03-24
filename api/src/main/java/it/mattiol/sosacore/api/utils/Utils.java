package it.mattiol.sosacore.api.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public final class Utils {

    private Utils() {}

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String replacePlaceholders(String message, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) message = message.replace(entry.getKey(), entry.getValue());

        return message;
    }

    public static String replacePlaceholders(String message, String prefix, Player player) {
        return replacePlaceholders(message, Map.of(
                "%prefix%", prefix,
                "%player%", player != null ? player.getName() : ""
        ));
    }
}