package dev.morphie.morphFishing.commands;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;
import java.util.List;

public class CommandAdmin {
    private MorphFishing plugin;

    public CommandAdmin(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public void helpCommand(CommandSender sender, String[] args) throws IOException, InvalidConfigurationException {
        if (sender.hasPermission("morphfishing.admin") || sender.hasPermission("morphfishing.admin.help")) {
            List<String> adminMenu = ConfigManager.getInstance().getMessageList("messages", "AdminCommandMenu");
            for (String menu : adminMenu) {
                sender.sendMessage(new Colorize().addColor(menu));
            }
        } else {
            sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") +  ConfigManager.getInstance().getMessage("messages", "InvalidPermissions")));
        }
    }
}
