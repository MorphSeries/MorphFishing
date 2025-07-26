package dev.morphie.morphFishing.events;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.itemstack.ItemMaker;
import dev.morphie.morphLib.string.Colorize;
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

import java.util.ArrayList;
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
        if (player.hasPermission("morphfishing.catch")) {
            Biome biome = player.getWorld().getBiome(player.getLocation());
            Inventory inventory = player.getInventory();
            Random rand = new Random();
            int chance = rand.nextInt(100);
            if (chance <= plugin.getConfig().getInt("CommonFishChance")) {
                fish = createFish("common");
                dropFish(player.getWorld(), player.getLocation(), fish);
            }
        }
    }

    private ItemStack createFish(String type) {
        Random rand = new Random();
        Double weight = rand.nextDouble(20.00);
        Material material = Material.matchMaterial(new ConfigManager(plugin).getMessage(type, type + ".0.Material"));
        int CustomModelID = new ConfigManager(plugin).getInt(type, type + ".0.CustomModelID");
        String name = new ConfigManager(plugin).getMessage(type, type + ".0.Name");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        for (String s : new ConfigManager(plugin).getMessageList(type, type + ".0.Description")) {
            lore.add(new Colorize().addColor(s));
        }
        lore.add("&3Weight&8: " + weight + new ConfigManager(plugin).getMessage(type, type + ".0.WeightClass"));
        Boolean glow = new ConfigManager(plugin).getBoolean(type, type + ".0.Glow");
        return new ItemMaker().makeItem(material, 1, CustomModelID, name, lore, glow, false);
    }

    private void dropFish (World world, Location loc, ItemStack fish) {
        loc.setY(loc.getY() + 1.0D);
        world.dropItem(loc, fish);
    }
}
