package dev.morphie.morphFishing.files;

import dev.morphie.morphFishing.MorphFishing;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager implements Listener {
    private MorphFishing plugin;
    public ConfigManager(MorphFishing plugin) {
        this.plugin = plugin;
    }

    //Files & File Configurations
    public FileConfiguration commoncfg;
    public File commonf;
    public FileConfiguration rarecfg;
    public File raref;
    public FileConfiguration epiccfg;
    public File epicf;
    public FileConfiguration legendarycfg;
    public File legendaryf;
    public FileConfiguration mythiccfg;
    public File mythicf;
    public FileConfiguration messagescfg;
    public File messagesf;

    public void setup() {
        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }
        this.messagesf = new File(plugin.getDataFolder(), "messages.yml");
        this.commonf = new File(plugin.getDataFolder() + File.separator + "Fish", "common.yml");
        this.raref = new File(plugin.getDataFolder() + File.separator + "Fish", "rare.yml");
        this.epicf = new File(plugin.getDataFolder() + File.separator + "Fish", "epic.yml");
        this.legendaryf = new File(plugin.getDataFolder()+ File.separator + "Fish", "legendary.yml");
        this.mythicf = new File(plugin.getDataFolder()+ File.separator + "Fish", "mythic.yml");

        this.messagescfg = YamlConfiguration.loadConfiguration(this.messagesf);
        this.commoncfg = YamlConfiguration.loadConfiguration(this.commonf);
        this.rarecfg = YamlConfiguration.loadConfiguration(this.raref);
        this.epiccfg = YamlConfiguration.loadConfiguration(this.epicf);
        this.legendarycfg = YamlConfiguration.loadConfiguration(this.legendaryf);
        this.mythiccfg = YamlConfiguration.loadConfiguration(this.mythicf);

        this.messagescfg.options().copyDefaults(true);
        this.commoncfg.options().copyDefaults(true);
        this.rarecfg.options().copyDefaults(true);
        this.epiccfg.options().copyDefaults(true);
        this.legendarycfg.options().copyDefaults(true);
        this.mythiccfg.options().copyDefaults(true);

        this.saveConfigs();
    }

    public void reloadConfigs() {
        this.saveConfigs();
        this.messagesf = new File(plugin.getDataFolder(), "messages.yml");
        this.commonf = new File(plugin.getDataFolder() + File.separator + "Fish", "common.yml");
        this.raref = new File(plugin.getDataFolder() + File.separator + "Fish", "rare.yml");
        this.epicf = new File(plugin.getDataFolder() + File.separator + "Fish", "epic.yml");
        this.legendaryf = new File(plugin.getDataFolder()+ File.separator + "Fish", "legendary.yml");
        this.mythicf = new File(plugin.getDataFolder()+ File.separator + "Fish", "mythic.yml");
        this.messagescfg = YamlConfiguration.loadConfiguration(this.messagesf);
        this.commoncfg = YamlConfiguration.loadConfiguration(this.commonf);
        this.rarecfg = YamlConfiguration.loadConfiguration(this.raref);
        this.epiccfg = YamlConfiguration.loadConfiguration(this.epicf);
        this.legendarycfg = YamlConfiguration.loadConfiguration(this.legendaryf);
        this.mythiccfg = YamlConfiguration.loadConfiguration(this.mythicf);
        this.messagescfg.options().copyDefaults(false);
        this.commoncfg.options().copyDefaults(false);
        this.rarecfg.options().copyDefaults(false);
        this.epiccfg.options().copyDefaults(false);
        this.legendarycfg.options().copyDefaults(false);
        this.mythiccfg.options().copyDefaults(false);
    }

    public void saveConfigs() {
        this.messagesf = new File(plugin.getDataFolder(), "messages.yml");
        this.commonf = new File(plugin.getDataFolder() + File.separator + "Fish", "common.yml");
        this.raref = new File(plugin.getDataFolder() + File.separator + "Fish", "rare.yml");
        this.epicf = new File(plugin.getDataFolder() + File.separator + "Fish", "epic.yml");
        this.legendaryf = new File(plugin.getDataFolder()+ File.separator + "Fish", "legendary.yml");
        this.mythicf = new File(plugin.getDataFolder()+ File.separator + "Fish", "mythic.yml");
        try {
            this.plugin.configManager.messagescfg.save(messagesf);
            this.plugin.configManager.commoncfg.save(commonf);
            this.plugin.configManager.rarecfg.save(raref);
            this.plugin.configManager.epiccfg.save(epicf);
            this.plugin.configManager.legendarycfg.save(legendaryf);
            this.plugin.configManager.mythiccfg.save(mythicf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMessage(String file, String path) {
        return switch (file) {
            case "messages" -> this.plugin.configManager.messagescfg.getString(path);
            case "common" -> this.plugin.configManager.commoncfg.getString(path);
            case "rare" -> this.plugin.configManager.rarecfg.getString(path);
            case "epic" -> this.plugin.configManager.epiccfg.getString(path);
            case "legendary" -> this.plugin.configManager.legendarycfg.getString(path);
            case "mythic" -> this.plugin.configManager.mythiccfg.getString(path);
            default -> "Null message";
        };
    }

    public List<String> getMessageList(String file, String path) {
        return switch (file) {
            case "messages" -> this.plugin.configManager.messagescfg.getStringList(path);
            case "common" -> this.plugin.configManager.commoncfg.getStringList(path);
            case "rare" -> this.plugin.configManager.rarecfg.getStringList(path);
            case "epic" -> this.plugin.configManager.epiccfg.getStringList(path);
            case "legendary" -> this.plugin.configManager.legendarycfg.getStringList(path);
            case "mythic" -> this.plugin.configManager.mythiccfg.getStringList(path);
            default -> null;
        };
    }

    public Boolean getBoolean(String file, String path) {
        return switch (file) {
            case "messages" -> this.plugin.configManager.messagescfg.getBoolean(path);
            case "common" -> this.plugin.configManager.commoncfg.getBoolean(path);
            case "rare" -> this.plugin.configManager.rarecfg.getBoolean(path);
            case "epic" -> this.plugin.configManager.epiccfg.getBoolean(path);
            case "legendary" -> this.plugin.configManager.legendarycfg.getBoolean(path);
            case "mythic" -> this.plugin.configManager.mythiccfg.getBoolean(path);
            default -> null;
        };
    }

    public int getInt(String file, String path) {
        return switch (file) {
            case "messages" -> this.plugin.configManager.messagescfg.getInt(path);
            case "common" -> this.plugin.configManager.commoncfg.getInt(path);
            case "rare" -> this.plugin.configManager.rarecfg.getInt(path);
            case "epic" -> this.plugin.configManager.epiccfg.getInt(path);
            case "legendary" -> this.plugin.configManager.legendarycfg.getInt(path);
            case "mythic" -> this.plugin.configManager.mythiccfg.getInt(path);
            default -> 0;
        };
    }
}
