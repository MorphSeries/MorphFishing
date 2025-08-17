package dev.morphie.morphFishing.utils;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.utils.database.DataManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends PlaceholderExpansion {

    private MorphFishing plugin;

    public PlaceholderAPI(MorphFishing plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "morphfishing";
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        // %someplugin_placeholder%
        return switch (identifier) {
            case "level" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_level"));
            case "xp" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_xp"));
            case "required_xp" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_required_xp"));
            case "gillings" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_gillings"));
            case "fish_caught" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_fish_caught"));
            case "common_caught" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_common_caught"));
            case "rare_caught" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_rare_caught"));
            case "epic_caught" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_epic_caught"));
            case "legendary_caught" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_legendary_caught"));
            case "mythic_caught" -> String.valueOf(new DataManager(plugin).getData(player.getUniqueId(), "mf_mythic_caught"));
            default -> null;
        };
    }

}
