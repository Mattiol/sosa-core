package it.mattiol.sosacore.plugin.service;

import it.mattiol.sosacore.api.service.MessageService;
import it.mattiol.sosacore.api.service.SentinelService;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SentinelServiceImpl implements SentinelService {

    private final MessageService messageService;
    private final Map<UUID, Long> lastMessage = new ConcurrentHashMap<>();
    private final Set<String> blockedWords;

    private final long cooldownMillis;
    private final double capsThreshold;

    private volatile boolean chatEnabled = true;

    public SentinelServiceImpl(List<String> blockedWords, long cooldownMillis, double capsThreshold, MessageService messageService) {
        this.blockedWords = blockedWords != null
                ? blockedWords.stream().map(String::toLowerCase).collect(Collectors.toUnmodifiableSet())
                : Set.of();
        this.cooldownMillis = cooldownMillis;
        this.capsThreshold = capsThreshold;
        this.messageService = messageService;
    }

    @Override
    public boolean checkMessage(Player player, String message) {
        if (player == null || !player.isOnline() || message == null) return false;

        if (!chatEnabled) {
            messageService.send(player, "sentinel.chat-disabled");
            return false;
        }

        if (isOnCooldown(player)) {
            messageService.send(player, "sentinel.cooldown");
            return false;
        }

        if (containsBlockedWords(message)) {
            messageService.send(player, "sentinel.blocked-word");
            return false;
        }

        if (isCaps(message)) {
            messageService.send(player, "sentinel.caps");
            return false;
        }

        updateLastMessage(player);
        return true;
    }

    @Override
    public void setChatEnabled(boolean enabled) {
        this.chatEnabled = enabled;
    }

    @Override
    public boolean isChatEnabled() {
        return chatEnabled;
    }

    private boolean isOnCooldown(Player player) {
        long now = System.currentTimeMillis();
        return lastMessage.getOrDefault(player.getUniqueId(), 0L) + cooldownMillis > now;
    }

    private void updateLastMessage(Player player) {
        lastMessage.put(player.getUniqueId(), System.currentTimeMillis());
    }

    private boolean containsBlockedWords(String message) {
        String lower = message.toLowerCase();
        return blockedWords.stream().anyMatch(lower::contains);
    }

    private boolean isCaps(String message) {
        int upper = 0, total = 0;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                total++;
                if (Character.isUpperCase(c)) upper++;
            }
        }

        return total > 0 && (double) upper / total > capsThreshold;
    }
}