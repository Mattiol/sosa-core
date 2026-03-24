package it.mattiol.sosacore.plugin.config;

import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurationStore;
import it.mattiol.sosacore.api.config.SosaConfig;
import it.mattiol.sosacore.plugin.config.impl.SosaConfigImpl;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;

public class ConfigManager {

    private final Path configPath;
    private final YamlConfigurationStore<SosaConfigImpl> store;
    private SosaConfigImpl config;

    public ConfigManager(JavaPlugin plugin) {
        this.configPath = plugin.getDataFolder().toPath().resolve("config.yml");
        this.store = new YamlConfigurationStore<>(
                SosaConfigImpl.class,
                YamlConfigurationProperties.newBuilder()
                        .header("SosaCore Configuration")
                        .build()
        );
        reload();
    }

    public void reload() {
        this.config = store.update(configPath);
    }

    public void save() {
        store.save(config, configPath);
    }

    public void saveSpawnLocation(Location location) {
        if (location == null || location.getWorld() == null) return;
        config.setSpawnLocation(location);
        save();
    }

    public SosaConfig get() {
        return config;
    }
}