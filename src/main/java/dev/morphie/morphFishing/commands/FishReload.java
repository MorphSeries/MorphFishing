package dev.morphie.morphFishing.commands;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.string.Colorize;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class FishReload {

    private MorphFishing plugin;

    public FishReload(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public void reloadCommand(CommandSender sender, String[] args) {
        if (sender.hasPermission("morphfishing.admin") || sender.hasPermission("morphfishing.reload")) {
            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MorphFishing");
            if (this.plugin != null) {
                this.plugin.reloadConfig();
                new ConfigManager(this.plugin).reloadConfigs();
                sender.sendMessage(new Colorize().addColor(new ConfigManager(this.plugin).getMessage("messages", "Prefix") + new ConfigManager(this.plugin).getMessage("messages", "Commands.Admin.Reload")));
            }
        } else {
            sender.sendMessage(new Colorize().addColor("&cInvalid Permissions! (MORPH CHANGE THIS)"));
        }
    }
}
