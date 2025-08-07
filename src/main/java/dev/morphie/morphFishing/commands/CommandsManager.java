package dev.morphie.morphFishing.commands;

import dev.morphie.morphFishing.MorphFishing;
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
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidConfigurationException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    sender.sendMessage(new Colorize().addColor("&cOnly players can run this command!"));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                // Sender (Including Console)`
                try {
                    new FishReload(this.plugin).reloadCommand(sender, args);
                } catch (IOException | InvalidConfigurationException e) {
                    throw new RuntimeException(e);
                }
                return true;
            } else if (args[0].equalsIgnoreCase("menu")) {
                if (sender instanceof Player) {
                    Player p = ((Player) sender).getPlayer();
                    new MainMenu(this.plugin).openMainGUI(p);
                    return true;
                } else {
                    sender.sendMessage(new Colorize().addColor("&cOnly players can run this command!"));
                }
            } else if (args[0].equalsIgnoreCase("market")) {
                if (sender instanceof Player) {
                    Player p = ((Player) sender).getPlayer();
                    new Market(plugin).openMarketGUI(p);
                    return true;
                } else {
                    sender.sendMessage(new Colorize().addColor("&cOnly players can run this command!"));
                }
            } else {
                sender.sendMessage(new Colorize().addColor("&cInvlaid Arguments (Morphie replace this with messages.yml)"));
                return true;
            }
        }
        return false;
    }
}
