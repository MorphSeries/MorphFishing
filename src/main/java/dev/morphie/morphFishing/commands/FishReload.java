package dev.morphie.morphFishing.commands;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.string.Colorize;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class FishReload {

    private MorphFishing plugin;

    public FishReload(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public void reloadCommand(CommandSender sender, String[] args) throws IOException, InvalidConfigurationException {
        if (sender.hasPermission("morphfishing.admin") || sender.hasPermission("morphfishing.reload")) {
            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MorphFishing");
            if (this.plugin != null) {
                this.plugin.reloadConfig();
                ConfigManager.getInstance().loadConfigs();
                sender.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Prefix") + ConfigManager.getInstance().getMessage("messages", "Commands.Admin.Reload")));
            }
        } else {
            sender.sendMessage(new Colorize().addColor("&cInvalid Permissions! (MORPH CHANGE THIS)"));
        }
    }
}
