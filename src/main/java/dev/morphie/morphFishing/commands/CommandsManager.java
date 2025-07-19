package dev.morphie.morphFishing.commands;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphLib.string.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                    sender.sendMessage(new Colorize().addColor("&3You smell!"));
                } else {
                    sender.sendMessage(new Colorize().addColor("&cOnly players can run this command!"));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                // Sender (Including Console)
                new FishReload(this.plugin).reloadCommand(sender, args);
                return true;
            } else {
                sender.sendMessage(new Colorize().addColor("&cInvlaid Arguments (Morphie replace this with messages.yml)"));
                return true;
            }
        }
        return false;
    }
}
