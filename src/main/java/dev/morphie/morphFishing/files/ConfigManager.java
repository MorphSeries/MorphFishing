package dev.morphie.morphFishing.files;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphLib.string.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {

    private MorphFishing plugin = MorphFishing.getPlugin(MorphFishing.class);

    //Files & File Configurations
    public FileConfiguration commoncfg = new YamlConfiguration();
    public File commonf;
    public FileConfiguration rarecfg = new YamlConfiguration();
    public File raref;
    public FileConfiguration epiccfg = new YamlConfiguration();
    public File epicf;
    public FileConfiguration legendarycfg = new YamlConfiguration();
    public File legendaryf;
    public FileConfiguration mythiccfg = new YamlConfiguration();
    public File mythicf;

    public void createConfigs() {
        commonf = new File(plugin.getDataFolder() + File.separator + "Fish", "common.yml");
        raref = new File(plugin.getDataFolder() + File.separator + "Fish", "rare.yml");
        epicf = new File(plugin.getDataFolder() + File.separator + "Fish", "epic.yml");
        legendaryf = new File(plugin.getDataFolder()+ File.separator + "Fish", "legendary.yml");
        mythicf = new File(plugin.getDataFolder()+ File.separator + "Fish", "mythic.yml");

        if (!commonf.exists()) { plugin.saveResource("Fish/common.yml", false); }
        if (!raref.exists()) { plugin.saveResource("Fish/rare.yml", false); }
        if (!epicf.exists()) { plugin.saveResource("Fish/epic.yml", false); }
        if (!legendaryf.exists()) { plugin.saveResource("Fish/legendary.yml", false); }
        if (!mythicf.exists()) { plugin.saveResource("Fish/mythic.yml", false); }

        try {
            commoncfg.load(commonf);
            rarecfg.load(raref);
            epiccfg.load(epicf);
            legendarycfg.load(legendaryf);
            mythiccfg.load(mythicf);
            Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&7Successfully loaded all Fish configuration files."));
        }
        catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfigs() {
        commoncfg = YamlConfiguration.loadConfiguration(commonf);
        rarecfg = YamlConfiguration.loadConfiguration(raref);
        epiccfg = YamlConfiguration.loadConfiguration(epicf);
        legendarycfg = YamlConfiguration.loadConfiguration(legendaryf);
        mythiccfg = YamlConfiguration.loadConfiguration(mythicf);
        commoncfg.options().copyDefaults(true);
        rarecfg.options().copyDefaults(true);
        epiccfg.options().copyDefaults(true);
        legendarycfg.options().copyDefaults(true);
        mythiccfg.options().copyDefaults(true);
    }

    public String getString(String file, String path) {
        return switch (file) {
            case "common" -> commoncfg.getString(path);
            case "rare" -> rarecfg.getString(path);
            case "epic" -> epiccfg.getString(path);
            case "legendary" -> legendarycfg.getString(path);
            case "mythic" -> mythiccfg.getString(path);
            default -> "Null message";
        };
    }

    public List<String> getMessageList(String file, String path) {
        return switch (file) {
            case "common" -> commoncfg.getStringList(path);
            case "rare" -> rarecfg.getStringList(path);
            case "epic" -> epiccfg.getStringList(path);
            case "legendary" -> legendarycfg.getStringList(path);
            case "mythic" -> mythiccfg.getStringList(path);
            default -> null;
        };
    }
}
