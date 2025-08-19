package dev.morphie.morphFishing.commands;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphFishing.utils.database.SQLite;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public class Gillings {

    private MorphFishing plugin;

    public Gillings(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public void gillingsCommand(CommandSender sender, String[] args) throws IOException, InvalidConfigurationException {
        Player target = null;
        OfflinePlayer offTarget = null;
        UUID targetUUID = null;
        if (sender.hasPermission("morphfishing.admin") || sender.hasPermission("morphfishing.admin.gillings")) {
            try {
                Integer.parseInt(args[3]);
            }
            catch (NumberFormatException e1) {
                sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") +  ConfigManager.getInstance().getMessage("messages", "CorrectUsage.Admin.Gillings")));
                return;
            }
            if (Bukkit.getPlayer(args[2]) != null) {
                target = Bukkit.getPlayer(args[2]);
                targetUUID =  target.getUniqueId();
            } else {
                offTarget = Bukkit.getServer().getOfflinePlayer(args[2]);
                targetUUID = offTarget.getUniqueId();
            }
            if (new SQLite(plugin).playerExists(targetUUID)) {
                if (args[1].equalsIgnoreCase("add")) {
                    if (sender.hasPermission("morphfishing.admin") || sender.hasPermission("morphfishing.admin.gillings.add")) {
                        int currentGillings = new SQLite(plugin).getData(targetUUID, "mf_gillings");
                        int addGillings = Integer.parseInt(args[3]);
                        new SQLite(plugin).setData(targetUUID, currentGillings+=addGillings,"mf_gillings");
                        sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Prefix") +  ConfigManager.getInstance().getMessage("messages", "GillingsAddedMessage")));
                    } else {
                        sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") +  ConfigManager.getInstance().getMessage("messages", "InvalidPermissions")));
                    }
                } else {
                    sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") +  ConfigManager.getInstance().getMessage("messages", "CorrectUsage.Admin.Gillings")));
                }
            } else {
                sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") +  ConfigManager.getInstance().getMessage("messages", "CorrectUsage.Admin.Gillings")));
            }
        } else {
            sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") +  ConfigManager.getInstance().getMessage("messages", "InvalidPermissions")));
        }
    }
}