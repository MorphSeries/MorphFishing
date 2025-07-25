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

    public void setup() throws IOException {
        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }

        this.messagesf = new File(plugin.getDataFolder(), "messages.yml");
        this.commonf = new File(plugin.getDataFolder() + File.separator + "Fish", "common.yml");
        this.raref = new File(plugin.getDataFolder() + File.separator + "Fish", "rare.yml");
        this.epicf = new File(plugin.getDataFolder() + File.separator + "Fish", "epic.yml");
        this.legendaryf = new File(plugin.getDataFolder()+ File.separator + "Fish", "legendary.yml");
        this.mythicf = new File(plugin.getDataFolder()+ File.separator + "Fish", "mythic.yml");

        if (!messagesf.exists()) {
            this.messagescfg = YamlConfiguration.loadConfiguration(this.messagesf);
            this.messagescfg.save(new File(plugin.getDataFolder(), "messages.yml"));
        } else if (!commonf.exists()) {
            this.commoncfg = YamlConfiguration.loadConfiguration(this.commonf);
            this.commoncfg.save(new File(plugin.getDataFolder(), "common.yml"));
        } else if (!raref.exists()) {
            this.rarecfg = YamlConfiguration.loadConfiguration(this.raref);
            this.rarecfg.save(new File(plugin.getDataFolder(), "rare.yml"));
        } else if (!epicf.exists()) {
            this.epiccfg = YamlConfiguration.loadConfiguration(this.epicf);
            this.epiccfg.save(new File(plugin.getDataFolder(), "epic.yml"));
        } else if (!legendaryf.exists()) {
            this.legendarycfg = YamlConfiguration.loadConfiguration(this.legendaryf);
            this.legendarycfg.save(new File(plugin.getDataFolder(), "legendary.yml"));
        } else if (!mythicf.exists()) {
            this.mythiccfg = YamlConfiguration.loadConfiguration(this.mythicf);
            this.mythiccfg.save(new File(plugin.getDataFolder(), "mythic.yml"));
        }
        loadConfigs();
    }

    public void loadConfigs() {
        this.plugin.configManager.messagescfg = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "messages.yml"));
        this.plugin.configManager.commoncfg = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "common.yml"));
        this.plugin.configManager.rarecfg = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "raref.yml"));
        this.plugin.configManager.epiccfg = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "epic.yml"));
        this.plugin.configManager.legendarycfg = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "legendary.yml"));
        this.plugin.configManager.mythiccfg = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "mythic.yml"));
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
