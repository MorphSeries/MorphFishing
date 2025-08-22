package dev.morphie.morphFishing.commands;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphFishing.menus.MainMenu;
import dev.morphie.morphFishing.menus.Market;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CommandsManager implements CommandExecutor {

    private MorphFishing plugin;

    public CommandsManager(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fish") || cmd.getName().equalsIgnoreCase("morphfish") || cmd.getName().equalsIgnoreCase("mf")) {
            if (args.length == 0) {
                // Player Only
                if (sender instanceof Player) {
                    try {
                        new CommandHelp(this.plugin).helpCommand(sender, args);
                    } catch (IOException | InvalidConfigurationException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("admin")) {
                if (args.length == 1) {
                    // Sender (Including Console)`
                    try {
                        new CommandAdmin(this.plugin).helpCommand(sender, args);
                    } catch (IOException | InvalidConfigurationException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") + ConfigManager.getInstance().getMessage("messages", "CorrectUsage.Admin.Reload")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (args.length == 1) {
                    // Sender (Including Console)`
                    try {
                        new CommandReload(this.plugin).reloadCommand(sender, args);
                    } catch (IOException | InvalidConfigurationException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") + ConfigManager.getInstance().getMessage("messages", "CorrectUsage.Admin.Reload")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("menu")) {
                if (args.length == 1) {
                    if (sender instanceof Player) {
                        Player p = ((Player) sender).getPlayer();
                        new MainMenu(this.plugin).openMainGUI(p);
                    } else {
                        sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") + ConfigManager.getInstance().getMessage("messages", "PlayerOnlyCommand")));
                    }
                } else {
                    sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") + ConfigManager.getInstance().getMessage("messages", "CorrectUsage.Menu")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("market")) {
                if (args.length == 1) {
                    if (sender instanceof Player) {
                        Player p = ((Player) sender).getPlayer();
                        new Market(plugin).openMarketGUI(p);
                    } else {
                        sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") + ConfigManager.getInstance().getMessage("messages", "PlayerOnlyCommand")));
                    }
                } else {
                    sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") + ConfigManager.getInstance().getMessage("messages", "CorrectUsage.Market")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("gillings")) {
                try {
                    new CommandGillings(plugin).gillingsCommand(sender, args);
                } catch (IOException | InvalidConfigurationException e) {
                    throw new RuntimeException(e);
                }
                return true;
            } else {
                sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") + ConfigManager.getInstance().getMessage("messages", "InvalidArguments")));
                return true;
            }
        }
        return false;
    }
}
