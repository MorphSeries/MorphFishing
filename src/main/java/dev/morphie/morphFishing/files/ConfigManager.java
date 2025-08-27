package dev.morphie.morphFishing.files;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphLib.MorphLib;
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

    // Base Files
    private FileConfiguration messagescfg;
    private File messagesf;
    private FileConfiguration eventscfg;
    private File eventsf;

    // Fish Files
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

    // Menu Files
    private FileConfiguration maincfg;
    private File mainf;
    private FileConfiguration marketcfg;
    private File marketf;
    private FileConfiguration journalcfg;
    private File journalf;
    private FileConfiguration charmscfg;
    private File charmsf;
    private FileConfiguration questscfg;
    private File questf;
    private FileConfiguration workbenchescfg;
    private File workbenchf;

    private ConfigManager() {
    }

    public void loadConfigs() throws IOException, InvalidConfigurationException {
        messagesf = new File(MorphFishing.getInstance().getDataFolder(), "messages.yml");
        eventsf = new File(MorphFishing.getInstance().getDataFolder(), "events.yml");
        commonf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "common.yml");
        raref  = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "rare.yml");
        epicf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "epic.yml");
        legendaryf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "legendary.yml");
        mythicf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Fish" + File.separator, "mythic.yml");
        mainf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Menus" + File.separator, "main.yml");
        marketf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Menus" + File.separator, "market.yml");
        journalf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Menus" + File.separator, "journal.yml");
        charmsf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Menus" + File.separator, "charms.yml");
        questf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Menus" + File.separator, "quests.yml");
        workbenchf = new File(MorphFishing.getInstance().getDataFolder() + File.separator + "Menus" + File.separator, "workbenches.yml");

        if (!messagesf.exists()) {
            MorphFishing.getInstance().saveResource("messages.yml", false);
        }
        if (!eventsf.exists()) {
            MorphFishing.getInstance().saveResource("events.yml", false);
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
        if (!mainf.exists()) {
            MorphFishing.getInstance().saveResource("Menus" + File.separator + "main.yml", false);
        }
        if (!marketf.exists()) {
            MorphFishing.getInstance().saveResource("Menus" + File.separator + "market.yml", false);
        }
        if (!journalf.exists()) {
            MorphFishing.getInstance().saveResource("Menus" + File.separator + "journal.yml", false);
        }
        if (!charmsf.exists()) {
            MorphFishing.getInstance().saveResource("Menus" + File.separator + "charms.yml", false);
        }
        if (!questf.exists()) {
            MorphFishing.getInstance().saveResource("Menus" + File.separator + "quests.yml", false);
        }
        if (!workbenchf.exists()) {
            MorphFishing.getInstance().saveResource("Menus" + File.separator + "workbenches.yml", false);
        }
        messagescfg = new YamlConfiguration();
        eventscfg = new YamlConfiguration();
        commoncfg = new YamlConfiguration();
        rarecfg = new YamlConfiguration();
        epiccfg = new YamlConfiguration();
        legendarycfg = new YamlConfiguration();
        mythiccfg = new YamlConfiguration();
        maincfg = new YamlConfiguration();
        marketcfg = new YamlConfiguration();
        journalcfg = new YamlConfiguration();
        charmscfg = new YamlConfiguration();
        questscfg = new YamlConfiguration();
        workbenchescfg = new YamlConfiguration();

        try {
            messagescfg.load(messagesf);
            eventscfg.load(eventsf);
            commoncfg.load(commonf);
            rarecfg.load(raref);
            epiccfg.load(epicf);
            legendarycfg.load(legendaryf);
            mythiccfg.load(mythicf);
            maincfg.load(mainf);
            marketcfg.load(marketf);
            journalcfg.load(journalf);
            charmscfg.load(charmsf);
            questscfg.load(questf);
            workbenchescfg.load(workbenchf);
            Bukkit.getConsoleSender().sendMessage(new Colorize().addColor("&aSuccessfully loaded all configuration files!"));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            messagescfg.save(messagesf);
            eventscfg.save(eventsf);
            commoncfg.save(commonf);
            rarecfg.save(raref);
            epiccfg.save(epicf);
            legendarycfg.save(legendaryf);
            mythiccfg.save(mythicf);
            maincfg.save(mainf);
            marketcfg.save(marketf);
            journalcfg.save(journalf);
            charmscfg.save(charmsf);
            questscfg.save(questf);
            workbenchescfg.save(workbenchf);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage(String file, String path) {
        return switch (file) {
            case "messages" -> ConfigManager.instance.messagescfg.getString(path);
            case "events" -> ConfigManager.instance.eventscfg.getString(path);
            case "common" -> ConfigManager.instance.commoncfg.getString(path);
            case "rare" -> ConfigManager.instance.rarecfg.getString(path);
            case "epic" -> ConfigManager.instance.epiccfg.getString(path);
            case "legendary" -> ConfigManager.instance.legendarycfg.getString(path);
            case "mythic" -> ConfigManager.instance.mythiccfg.getString(path);
            case "main" -> ConfigManager.instance.maincfg.getString(path);
            case "market" -> ConfigManager.instance.marketcfg.getString(path);
            case "journal" -> ConfigManager.instance.journalcfg.getString(path);
            case "charms" -> ConfigManager.instance.charmscfg.getString(path);
            case "quests" -> ConfigManager.instance.questscfg.getString(path);
            case "workbenches" -> ConfigManager.instance.workbenchescfg.getString(path);
            default -> "Null message";
        };
    }

    public List<String> getMessageList(String file, String path) {
        return switch (file) {
            case "messages" -> ConfigManager.instance.messagescfg.getStringList(path);
            case "events" -> ConfigManager.instance.eventscfg.getStringList(path);
            case "common" -> ConfigManager.instance.commoncfg.getStringList(path);
            case "rare" -> ConfigManager.instance.rarecfg.getStringList(path);
            case "epic" -> ConfigManager.instance.epiccfg.getStringList(path);
            case "legendary" -> ConfigManager.instance.legendarycfg.getStringList(path);
            case "mythic" -> ConfigManager.instance.mythiccfg.getStringList(path);
            case "main" -> ConfigManager.instance.maincfg.getStringList(path);
            case "market" -> ConfigManager.instance.marketcfg.getStringList(path);
            case "journal" -> ConfigManager.instance.journalcfg.getStringList(path);
            case "charms" -> ConfigManager.instance.charmscfg.getStringList(path);
            case "quests" -> ConfigManager.instance.questscfg.getStringList(path);
            case "workbenches" -> ConfigManager.instance.workbenchescfg.getStringList(path);
            default -> null;
        };
    }

    public Boolean getBoolean(String file, String path) {
        return switch (file) {
            case "messages" -> ConfigManager.instance.messagescfg.getBoolean(path);
            case "events" -> ConfigManager.instance.eventscfg.getBoolean(path);
            case "common" -> ConfigManager.instance.commoncfg.getBoolean(path);
            case "rare" -> ConfigManager.instance.rarecfg.getBoolean(path);
            case "epic" -> ConfigManager.instance.epiccfg.getBoolean(path);
            case "legendary" -> ConfigManager.instance.legendarycfg.getBoolean(path);
            case "mythic" -> ConfigManager.instance.mythiccfg.getBoolean(path);
            case "main" -> ConfigManager.instance.maincfg.getBoolean(path);
            case "market" -> ConfigManager.instance.marketcfg.getBoolean(path);
            case "journal" -> ConfigManager.instance.journalcfg.getBoolean(path);
            case "charms" -> ConfigManager.instance.charmscfg.getBoolean(path);
            case "quests" -> ConfigManager.instance.questscfg.getBoolean(path);
            case "workbenches" -> ConfigManager.instance.workbenchescfg.getBoolean(path);
            default -> null;
        };
    }

    public int getInt(String file, String path) {
        return switch (file) {
            case "messages" -> ConfigManager.instance.messagescfg.getInt(path);
            case "events" -> ConfigManager.instance.eventscfg.getInt(path);
            case "common" -> ConfigManager.instance.commoncfg.getInt(path);
            case "rare" -> ConfigManager.instance.rarecfg.getInt(path);
            case "epic" -> ConfigManager.instance.epiccfg.getInt(path);
            case "legendary" -> ConfigManager.instance.legendarycfg.getInt(path);
            case "mythic" -> ConfigManager.instance.mythiccfg.getInt(path);
            case "main" -> ConfigManager.instance.maincfg.getInt(path);
            case "market" -> ConfigManager.instance.marketcfg.getInt(path);
            case "journal" -> ConfigManager.instance.journalcfg.getInt(path);
            case "charms" -> ConfigManager.instance.charmscfg.getInt(path);
            case "quests" -> ConfigManager.instance.questscfg.getInt(path);
            case "workbenches" -> ConfigManager.instance.workbenchescfg.getInt(path);
            default -> 0;
        };
    }

    public static ConfigManager getInstance() {
        return instance;
    }
}
