package dev.morphie.morphFishing.commands;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class CommandHelp {
    private MorphFishing plugin;

    public CommandHelp(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public void helpCommand(CommandSender sender, String[] args) throws IOException, InvalidConfigurationException {
        if (sender.hasPermission("morphfishing.help")) {
            sender.sendMessage(" ");
            sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Commands.Header")));
            sender.sendMessage(" ");
            sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Commands.Main")));
            sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Commands.Menu")));
            sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Commands.Market")));
            if (sender.hasPermission("morphfishing.admin")) {
                sender.sendMessage(" ");
                sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Commands.Admin.Reload")));
            }
            sender.sendMessage(" ");
            sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Commands.Footer")));
            sender.sendMessage(" ");
        } else {
            sender.sendMessage(new Colorize().addColor("&cInvalid Permissions! (MORPH CHANGE THIS)"));
        }
    }
}
