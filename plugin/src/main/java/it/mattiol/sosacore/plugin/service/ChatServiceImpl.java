package it.mattiol.sosacore.plugin.service;

import it.mattiol.sosacore.api.service.ChatService;
import it.mattiol.sosacore.api.utils.Utils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatServiceImpl implements ChatService {

    private static final String DEFAULT_PREFIX = "";
    private static final String DEFAULT_SUFFIX = "";
    private static final String FORMAT = "%prefix%%player%%suffix%&r: %message%";

    private final LuckPerms luckPerms;

    public ChatServiceImpl(LuckPerms luckPerms) {
        if (luckPerms == null) throw new IllegalArgumentException("LuckPerms instance cannot be null");
        this.luckPerms = luckPerms;
    }

    @Override
    public String formatMessage(Player player, String message) {
        if (player == null || !player.isOnline()) return null;
        if (message == null || message.isBlank()) return null;

        CachedMetaData meta = getMetaData(player);

        String prefix = meta.getPrefix() != null ? meta.getPrefix() : DEFAULT_PREFIX;
        String suffix = meta.getSuffix() != null ? meta.getSuffix() : DEFAULT_SUFFIX;

        return Utils.color(FORMAT
                .replace("%prefix%", prefix)
                .replace("%player%", player.getName())
                .replace("%suffix%", suffix)
                .replace("%message%", message));
    }

    @Override
    public void broadcastFormattedMessage(Player player, String message) {
        String formatted = formatMessage(player, message);
        if (formatted == null) return;
        Bukkit.broadcastMessage(formatted);
    }

    private CachedMetaData getMetaData(Player player) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) return luckPerms.getUserManager()
                .loadUser(player.getUniqueId())
                .join()
                .getCachedData()
                .getMetaData();

        return user.getCachedData().getMetaData();
    }
}