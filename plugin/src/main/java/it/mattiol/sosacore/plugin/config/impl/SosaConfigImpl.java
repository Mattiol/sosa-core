package it.mattiol.sosacore.plugin.config.impl;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import it.mattiol.sosacore.api.config.SosaConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SosaConfigImpl implements SosaConfig {

    @Comment("Plugin prefix used in messages")
    private String prefix = "&8[&bSosa&8] &r";

    @Comment("Cooldown between messages in milliseconds")
    private long cooldownMillis = 2000L;

    @Comment("Percentage of uppercase letters to trigger caps lock filter (0.0 - 1.0)")
    private double capsThreshold = 0.7;

    @Comment("List of blocked words")
    private List<String> blockedWords = List.of("badword1", "badword2");

    @Comment("Chat format. Supports %prefix%, %player%, %suffix%, %message%")
    private String chatFormat = "%prefix%%player%%suffix%&r: %message%";

    @Comment("Plugin messages")
    private Map<String, String> messages = new HashMap<>(Map.of(
            "sentinel.chat-disabled", "%prefix% &cLa chat è disabilitata.",
            "sentinel.cooldown", "%prefix% &cStai scrivendo troppo velocemente.",
            "sentinel.blocked-word", "%prefix% &cMessaggio non consentito.",
            "sentinel.caps", "%prefix% &cNon usare tutte lettere maiuscole.",
            "core.reload", "%prefix% &aPlugin ricaricato.",
            "gamemode.creative", "%prefix% &7ɢᴀᴍᴇᴍᴏᴅᴇ sᴇᴛ ᴛᴏ &bᴄʀᴇᴀᴛɪᴠᴇ&7.",
            "gamemode.survival", "%prefix% &7ɢᴀᴍᴇᴍᴏᴅᴇ sᴇᴛ ᴛᴏ &aꜱᴜʀᴠɪᴠᴀʟ&7.",
            "gamemode.adventure", "%prefix% &7ɢᴀᴍᴇᴍᴏᴅᴇ sᴇᴛ ᴛᴏ &6ᴀᴅᴠᴇɴᴛᴜʀᴇ&7.",
            "gamemode.spectator", "%prefix% &7ɢᴀᴍᴇᴍᴏᴅᴇ sᴇᴛ ᴛᴏ &8sᴘᴇᴄᴛᴀᴛᴏʀ&7."
    ));

    @Comment("Spawn location")
    private SpawnLocation spawn = new SpawnLocation();

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public long getCooldownMillis() {
        return cooldownMillis;
    }

    @Override
    public double getCapsThreshold() {
        return capsThreshold;
    }

    @Override
    public List<String> getBlockedWords() {
        return blockedWords;
    }

    @Override
    public String getChatFormat() {
        return chatFormat;
    }

    @Override
    public Map<String, String> getMessages() {
        return messages;
    }

    @Override
    public Location getSpawnLocation() {
        if (spawn == null || spawn.world == null) return null;
        var world = Bukkit.getWorld(spawn.world);
        if (world == null) return null;
        return new Location(world, spawn.x, spawn.y, spawn.z, spawn.yaw, spawn.pitch);
    }

    @Override
    public void setSpawnLocation(Location location) {
        if (location == null || location.getWorld() == null) return;
        this.spawn = new SpawnLocation();
        this.spawn.world = location.getWorld().getName();
        this.spawn.x = location.getX();
        this.spawn.y = location.getY();
        this.spawn.z = location.getZ();
        this.spawn.yaw = location.getYaw();
        this.spawn.pitch = location.getPitch();
    }

    @Configuration
    public static final class SpawnLocation {
        private String world = "world";
        private double x = 0.0;
        private double y = 64.0;
        private double z = 0.0;
        private float yaw = 0.0f;
        private float pitch = 0.0f;
    }
}