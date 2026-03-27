package it.mattiol.sosacore.plugin.commands;

import it.mattiol.sosacore.api.utils.Utils;
import it.mattiol.sosacore.plugin.SosaPlugin;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.util.List;

@Command({"sosacore", "sosa", "core"})
public class MainCommand {

    private final SosaPlugin plugin;

    public MainCommand(SosaPlugin plugin) {
        this.plugin = plugin;
    }

    @Command({"sosacore", "sosa", "core"})
    public void main(Player player) {
        List.of(
                " ",
                "  &bSosaCore &8» &7ʀᴜɴɴɪɴɢ &fv" + plugin.getDescription().getVersion(),
                " ",
                "  &7ᴀᴜᴛʜᴏʀs: &fAyoMattiol",
                " ",
                "  &8» &b/core reload &8- &7ʀᴇʟᴏᴀᴅ ᴛʜᴇ ᴘʟᴜɢɪɴ",
                "  &8» &b/core spawn &8- &7ᴛᴇʟᴇᴘᴏʀᴛ ᴛᴏ sᴘᴀᴡɴ",
                " "
        ).forEach(line -> player.sendMessage(Utils.color(line)));
    }

    @Subcommand("reload")
    @CommandPermission("sosa.core.permissions.admin")
    public void reload(Player player) {
        plugin.getConfigManager().reload();
        plugin.messages().send(player, "core.reload");
    }

    @Subcommand("setspawn")
    @CommandPermission("sosa.core.permissions.setspawn")
    public void onSetSpawn(Player player) {
        Location location = player.getLocation();

        plugin.spawn().set(location);
        plugin.messages().send(player, "core.setspawn");
    }

    @Subcommand("uptime")
    public void onUptime(Player player) {
        plugin.uptime();
    }

}