package dev.morphie.morphFishing;

import dev.morphie.morphLib.string.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class MorphFishing extends JavaPlugin {

    private File commonf, raref, epicf, legendaryf, mythicf;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&7_______________________________________________________"));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3   _____                      .__    ___________.__       .__    .__                "));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3  /     \\   _________________ |  |__ \\_   _____/|__| _____|  |__ |__| ____    ____ "));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3 /  \\ /  \\ /  _ \\_  __ \\____ \\|  |  \\ |    __)  |  |/  ___/  |  \\|  |/    \\  / ___\\"));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3/    Y    (  <_> )  | \\/  |_> >   Y  \\|     \\   |  |\\___ \\|   Y  \\  |   |  \\/ /_/  >"));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3\\____|__  /\\____/|__|  |   __/|___|  /\\___  /   |__/____  >___|  /__|___|  /\\___  /"));
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&3        \\/             |__|        \\/     \\/            \\/     \\/        \\//_____/"));
        createConfigs();
        createFishConfigs();
        Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&7_______________________________________________________"));
    }

    @Override
    public void onDisable() {
        
    }

    private void createConfigs() {
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

    private void createFishConfigs() {
        File subfolder = new File(getDataFolder(), "Fish");
        if (!subfolder.exists()) {
            subfolder.mkdir();
        }

        commonf = new File(getDataFolder(), "common.yml");
        raref = new File(getDataFolder(), "rare.yml");
        epicf = new File(getDataFolder(), "epic.yml");
        legendaryf = new File(getDataFolder(), "legendary.yml");
        mythicf = new File(getDataFolder(), "mythic.yml");

        if (!commonf.exists()) { saveResource("Fish/common.yml", false); }
        if (!raref.exists()) { saveResource("Fish/rare.yml", false); }
        if (!epicf.exists()) { saveResource("Fish/epic.yml", false); }
        if (!legendaryf.exists()) { saveResource("Fish/legendary.yml", false); }
        if (!mythicf.exists()) { saveResource("Fish/mythic.yml", false); }

        FileConfiguration common = new YamlConfiguration();
        FileConfiguration rare = new YamlConfiguration();
        FileConfiguration epic = new YamlConfiguration();
        FileConfiguration legendary = new YamlConfiguration();
        FileConfiguration mythic = new YamlConfiguration();

        try {
            common.load(commonf);
            rare.load(raref);
            epic.load(epicf);
            legendary.load(legendaryf);
            mythic.load(mythicf);
            Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&7Successfully loaded all Fish configuration files."));
        }
        catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
