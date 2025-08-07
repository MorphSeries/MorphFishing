package dev.morphie.morphFishing;

import dev.morphie.morphFishing.commands.CommandsManager;
import dev.morphie.morphFishing.events.FishEvent;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.utils.Colorize;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class MorphFishing extends JavaPlugin {

    private FishEvent fe;
    public static Economy econ = null;

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
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", new Object[] { getDescription().getName() }));
            getServer().getPluginManager().disablePlugin(this);
            return;
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

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = (Economy)rsp.getProvider();
        return econ != null;
    }

    public static MorphFishing getInstance() {
        return getPlugin(MorphFishing.class);
    }
}
