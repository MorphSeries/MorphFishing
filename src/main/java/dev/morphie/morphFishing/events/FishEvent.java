package dev.morphie.morphFishing.events;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphFishing.utils.ItemHandlers;
import dev.morphie.morphFishing.utils.database.DataManager;
import dev.morphie.morphLib.itemstack.ItemMaker;
import dev.morphie.morphLib.utils.Colorize;
import dev.morphie.morphLib.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class FishEvent implements Listener {

    private MorphFishing plugin;

    public FishEvent(MorphFishing plugin) {
        this.plugin = plugin;
    }

    String name;
    String tier;
    String actualName;
    int xp;

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();
        ItemStack fish;
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if (p.hasPermission("morphfishing.catch")) {
                Biome biome = p.getWorld().getBiome(p.getLocation());
                Inventory inventory = p.getInventory();
                Random rand = new Random();
                int fishChance = rand.nextInt(100);
                if (fishChance <= plugin.getConfig().getInt("ChanceToCatchCustomFish")) {
                    fish = createFish(returnFishTier(), p);
                    String caughtMessage = ConfigManager.getInstance().getMessage("messages", "FishCaughtActionBar").replace("%TIER%", tier).replace("%FISH_NAME%", name);
                    new StringUtils().ActionBar(new Colorize().addColor(caughtMessage), p);
                    new DataManager(plugin).addXP(uuid, this.getXP(actualName));
                    new DataManager(plugin).addGillings(uuid, this.getGillings(actualName));
                    new DataManager(plugin).addFishCaughtStat(uuid, actualName);
                    new ItemHandlers().giveItem(p, p.getWorld(), p.getLocation(), fish, "feet");
                }
            }
        }
    }

    private ItemStack createFish(String type, Player p) {
        Random rand = new Random();
        int fish = rand.nextInt(this.getFish(type));
        Material material = Material.matchMaterial(ConfigManager.getInstance().getMessage(type, type + "." + fish + ".Material"));
        int CustomModelID = ConfigManager.getInstance().getInt(type, type + "." + fish + ".CustomModelID");
        name = new Colorize().addColor(ConfigManager.getInstance().getMessage(type, type + "." + fish + ".Name"));
        tier = new Colorize().addColor(ConfigManager.getInstance().getMessage(type, type + "." + fish + ".Tier"));
        Format f = new SimpleDateFormat("MM/dd/yy");
        String strDate = f.format(new Date());
        String playerName = p.getName();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(new Colorize().addColor("&3Tier&8: " + tier));
        lore.add(new Colorize().addColor("&3Weight&8: &7&k1234 &a" + ConfigManager.getInstance().getMessage(type, type + "." + fish + ".WeightClass")));
        lore.add("");
        for (String s : ConfigManager.getInstance().getMessageList(type, type + "." + fish + ".Description")) {
            lore.add(new Colorize().addColor(s));
        }
        lore.add("");
        if (plugin.getConfig().getBoolean("Settings.FishCaughtBy")) {
            lore.add(new Colorize().addColor("&aCaught by &3" + playerName + " &aon &3" + strDate));
        }
        Boolean glow = ConfigManager.getInstance().getBoolean(type, type + "." + fish + ".Glow");
        return new ItemMaker().makeItem(material, 1, CustomModelID, name, lore, glow, false, p, "morphfish." + type);
    }

    private String returnFishTier() {
        SortedMap<Integer, String> items = new TreeMap<>();
        items.put(plugin.getConfig().getInt("CommonFishChance"), "common");
        items.put(plugin.getConfig().getInt("RareFishChance"), "rare");
        items.put(plugin.getConfig().getInt("EpicFishChance"), "epic");
        items.put(plugin.getConfig().getInt("LegendaryFishChance"), "legendary");
        items.put(plugin.getConfig().getInt("MythicFishChance"), "mythic");

        final Random random = new Random();
        final int actualValue = random.nextInt(100);
        int sum = 0;

        for (Map.Entry<Integer, ? extends String> entry: items.entrySet()) {
            sum += entry.getKey();
            if (sum > actualValue) {
                actualName = entry.getValue();
                break;
            }
        }
        items.clear();
        return actualName;
    }

    private int getFish(String type) {
        int i = 0;
        while (ConfigManager.getInstance().getMessage(type, type + "." + i + ".Name") != null) {
            i++;
        }
        return i;
    }

    private int getXP(String tier) {
        switch (tier) {
            case "common" -> {
                return this.plugin.getConfig().getInt("PlayerLevel.BaseCommonFishXP");
            }
            case "rare" -> {
                return this.plugin.getConfig().getInt("PlayerLevel.BaseRareFishXP");
            }
            case "epic" -> {
                return this.plugin.getConfig().getInt("PlayerLevel.BaseEpicFishXP");
            }
            case "legendary" -> {
                return this.plugin.getConfig().getInt("PlayerLevel.BaseLegendaryFishXP");
            }
            case "mythic" -> {
                return this.plugin.getConfig().getInt("PlayerLevel.BaseMythicFishXP");
            }
        }
        return 0;
    }

    private int getGillings(String tier) {
        switch (tier) {
            case "common" -> {
                return this.plugin.getConfig().getInt("Gillings.BaseCommonGillings");
            }
            case "rare" -> {
                return this.plugin.getConfig().getInt("Gillings.BaseRareGillings");
            }
            case "epic" -> {
                return this.plugin.getConfig().getInt("Gillings.BaseEpicGillings");
            }
            case "legendary" -> {
                return this.plugin.getConfig().getInt("Gillings.BaseLegendaryGillings");
            }
            case "mythic" -> {
                return this.plugin.getConfig().getInt("Gillings.BaseMythicGillings");
            }
        }
        return 0;
    }
}
