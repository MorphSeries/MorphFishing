package dev.morphie.morphFishing.files;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {

    private final static ConfigManager instance = new ConfigManager();

    //Files & File Configurations
    private FileConfiguration commoncfg;
    private File commonf;
    private FileConfiguration rarecfg;
    private File raref;
    private FileConfiguration epiccfg;
    private File epicf;
    private FileConfiguration legendarycfg;
    private File legendaryf;
    private FileConfiguration mythiccfg;
    private File mythicf;
    private FileConfiguration messagescfg;
    private File messagesf;

    private ConfigManager() {
    }

    public void loadConfigs() throws IOException, InvalidConfigurationException {
        messagesf = new File(MorphFishing.getInstance().getDataFolder(), "messages.yml");
        commonf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "common.yml");
        raref  = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "rare.yml");
        epicf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "epic.yml");
        legendaryf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "legendary.yml");
        mythicf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "mythic.yml");

        if (!messagesf.exists()) {
            MorphFishing.getInstance().saveResource("messages.yml", false);
        }
        if (!commonf.exists()) {
            MorphFishing.getInstance().saveResource("Fish" + File.separator + "common.yml", false);
        }
        if (!raref.exists()) {
            MorphFishing.getInstance().saveResource("Fish" + File.separator + "rare.yml", false);
        }
        if (!epicf.exists()) {
            MorphFishing.getInstance().saveResource("Fish" + File.separator + "epic.yml", false);
        }
        if (!legendaryf.exists()) {
            MorphFishing.getInstance().saveResource("Fish" + File.separator + "legendary.yml", false);
        }
        if (!mythicf.exists()) {
            MorphFishing.getInstance().saveResource("Fish" + File.separator + "mythic.yml", false);
        }
        messagescfg = new YamlConfiguration();
        commoncfg = new YamlConfiguration();
        rarecfg = new YamlConfiguration();
        epiccfg = new YamlConfiguration();
        legendarycfg = new YamlConfiguration();
        mythiccfg = new YamlConfiguration();

        try {
            messagescfg.load(messagesf);
            commoncfg.load(commonf);
            rarecfg.load(raref);
            epiccfg.load(epicf);
            legendarycfg.load(legendaryf);
            mythiccfg.load(mythicf);
            Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&aSuccessfully loaded all configuration files!"));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            messagescfg.save(messagesf);
            commoncfg.save(commonf);
            rarecfg.save(raref);
            epiccfg.save(epicf);
            legendarycfg.save(legendaryf);
            mythiccfg.save(mythicf);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage(String file, String path) {
        return switch (file) {
            case "messages" -> ConfigManager.instance.messagescfg.getString(path);
            case "common" -> ConfigManager.instance.commoncfg.getString(path);
            case "rare" -> ConfigManager.instance.rarecfg.getString(path);
            case "epic" -> ConfigManager.instance.epiccfg.getString(path);
            case "legendary" -> ConfigManager.instance.legendarycfg.getString(path);
            case "mythic" -> ConfigManager.instance.mythiccfg.getString(path);
            default -> "Null message";
        };
    }

    public List<String> getMessageList(String file, String path) {
        return switch (file) {
            case "messages" -> ConfigManager.instance.messagescfg.getStringList(path);
            case "common" -> ConfigManager.instance.commoncfg.getStringList(path);
            case "rare" -> ConfigManager.instance.rarecfg.getStringList(path);
            case "epic" -> ConfigManager.instance.epiccfg.getStringList(path);
            case "legendary" -> ConfigManager.instance.legendarycfg.getStringList(path);
            case "mythic" -> ConfigManager.instance.mythiccfg.getStringList(path);
            default -> null;
        };
    }

    public Boolean getBoolean(String file, String path) {
        return switch (file) {
            case "messages" -> ConfigManager.instance.messagescfg.getBoolean(path);
            case "common" -> ConfigManager.instance.commoncfg.getBoolean(path);
            case "rare" -> ConfigManager.instance.rarecfg.getBoolean(path);
            case "epic" -> ConfigManager.instance.epiccfg.getBoolean(path);
            case "legendary" -> ConfigManager.instance.legendarycfg.getBoolean(path);
            case "mythic" -> ConfigManager.instance.mythiccfg.getBoolean(path);
            default -> null;
        };
    }

    public int getInt(String file, String path) {
        return switch (file) {
            case "messages" -> ConfigManager.instance.messagescfg.getInt(path);
            case "common" -> ConfigManager.instance.commoncfg.getInt(path);
            case "rare" -> ConfigManager.instance.rarecfg.getInt(path);
            case "epic" -> ConfigManager.instance.epiccfg.getInt(path);
            case "legendary" -> ConfigManager.instance.legendarycfg.getInt(path);
            case "mythic" -> ConfigManager.instance.mythiccfg.getInt(path);
            default -> 0;
        };
    }

    public static ConfigManager getInstance() {
        return instance;
    }
}
