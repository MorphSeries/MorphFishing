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
                this.plugin.getServer().getPluginManager().disablePlugin(plugin);
                this.plugin.getServer().getPluginManager().enablePlugin(plugin);
                sender.sendMessage(new Colorize().addColor("&aPlugin reloaded! (MORPH CHANGE THIS)"));
            }
        } else {
            sender.sendMessage(new Colorize().addColor("&cInvalid Permissions! (MORPH CHANGE THIS)"));
        }
    }
}
