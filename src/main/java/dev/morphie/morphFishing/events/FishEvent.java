package dev.morphie.morphFishing.events;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.itemstack.ItemMaker;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class FishEvent implements Listener {

    private MorphFishing plugin;

    public FishEvent(MorphFishing plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        ItemStack fish;
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if (player.hasPermission("morphfishing.catch")) {
                Biome biome = player.getWorld().getBiome(player.getLocation());
                Inventory inventory = player.getInventory();
                Random rand = new Random();
                int fishChance = rand.nextInt(100);
                if (fishChance <= plugin.getConfig().getInt("ChanceToCatchCustomFish")) {
                    int chance = rand.nextInt(100);
                    if (chance <= plugin.getConfig().getInt("MythicFishChance")) {
                        fish = createFish("mythic", player);
                        dropFish(player.getWorld(), player.getLocation(), fish);
                    } else if (chance <= plugin.getConfig().getInt("LegendaryFishChance")) {
                        fish = createFish("legendary", player);
                        dropFish(player.getWorld(), player.getLocation(), fish);
                    } else if (chance <= plugin.getConfig().getInt("EpicFishChance")) {
                        fish = createFish("epic", player);
                        dropFish(player.getWorld(), player.getLocation(), fish);
                    } else if (chance <= plugin.getConfig().getInt("RareFishChance")) {
                        fish = createFish("rare", player);
                        dropFish(player.getWorld(), player.getLocation(), fish);
                    } else if (chance <= plugin.getConfig().getInt("CommonFishChance")) {
                        fish = createFish("common", player);
                        dropFish(player.getWorld(), player.getLocation(), fish);
                    }
                }
            }
        }
    }

    private ItemStack createFish(String type, Player player) {
        Random rand = new Random();
        int fish = rand.nextInt(this.getFish(type));
        Material material = Material.matchMaterial(ConfigManager.getInstance().getMessage(type, type + "." + fish + ".Material"));
        int CustomModelID = ConfigManager.getInstance().getInt(type, type + "." + fish + ".CustomModelID");
        String name = new Colorize().addColor(ConfigManager.getInstance().getMessage(type, type + "." + fish + ".Name"));
        String tier = new Colorize().addColor(ConfigManager.getInstance().getMessage(type, type + "." + fish + ".Tier"));
        Format f = new SimpleDateFormat("MM/dd/yy");
        String strDate = f.format(new Date());
        String playerName = player.getName();
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
        return new ItemMaker().makeItem(material, 1, CustomModelID, name, lore, glow, false, player);
    }

    private void dropFish (World world, Location loc, ItemStack fish) {
        loc.setY(loc.getY() + 1.0D);
        world.dropItem(loc, fish);
    }

    private int getFish(String type) {
        int i = 0;
        while (ConfigManager.getInstance().getMessage(type, type + "." + i + ".Name") != null) {
            i++;
        }
        i--;

        return i;
    }
}
