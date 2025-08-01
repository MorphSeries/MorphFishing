package dev.morphie.morphFishing;

import dev.morphie.morphFishing.commands.CommandsManager;
import dev.morphie.morphFishing.events.FishEvent;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class MorphFishing extends JavaPlugin {

    private FishEvent fe;

    @Override
    public void onEnable() {
        this.fe = new FishEvent(this);
        getServer().getPluginManager().registerEvents(this.fe, this);
        Objects.requireNonNull(getCommand("fish")).setExecutor(new CommandsManager(this));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&7_______________________________________________________"));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3   _____                      .__    ___________.__       .__    .__                "));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3  /     \\   _________________ |  |__ \\_   _____/|__| _____|  |__ |__| ____    ____ "));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3 /  \\ /  \\ /  _ \\_  __ \\____ \\|  |  \\ |    __)  |  |/  ___/  |  \\|  |/    \\  / ___\\"));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3/    Y    (  <_> )  | \\/  |_> >   Y  \\|     \\   |  |\\___ \\|   Y  \\  |   |  \\/ /_/  >"));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3\\____|__  /\\____/|__|  |   __/|___|  /\\___  /   |__/____  >___|  /__|___|  /\\___  /"));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3        \\/             |__|        \\/     \\/            \\/     \\/        \\//_____/"));
        createConfig();
        try {
            ConfigManager.getInstance().loadConfigs();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&7_______________________________________________________"));
    }

    @Override
    public void onDisable() {
        
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getConfig().options().copyDefaults(true);
                saveDefaultConfig();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MorphFishing getInstance() {
        return getPlugin(MorphFishing.class);
    }
}
