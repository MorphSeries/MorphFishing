package dev.morphie.morphFishing.events;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.itemstack.ItemMaker;
import dev.morphie.morphLib.utils.Colorize;
import dev.morphie.morphLib.utils.StringUtils;
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
import java.time.LocalDate;
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
                int chance = rand.nextInt(100);
                if (chance <= plugin.getConfig().getInt("CommonFishChance")) {
                    fish = createFish("common", player);
                    dropFish(player.getWorld(), player.getLocation(), fish);
                }
            }
        }
    }

    private ItemStack createFish(String type, Player player) {
        Random rand = new Random();
        Double weight = rand.nextDouble(20.00);
        Material material = Material.matchMaterial(ConfigManager.getInstance().getMessage(type, type + ".0.Material"));
        int CustomModelID = ConfigManager.getInstance().getInt(type, type + ".0.CustomModelID");
        String name = new Colorize().addColor(ConfigManager.getInstance().getMessage(type, type + ".0.Name"));
        String tier = new Colorize().addColor(ConfigManager.getInstance().getMessage(type, type + ".0.Tier"));
        Format f = new SimpleDateFormat("MM/dd/yy");
        String strDate = f.format(new Date());
        String playerName = player.getName();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(new Colorize().addColor("&3Tier&8: " + tier));
        lore.add(new Colorize().addColor("&3Weight&8: &7" + Math.round(weight * 10) / 10.0 + " " + ConfigManager.getInstance().getMessage(type, type + ".0.WeightClass")));
        lore.add("");
        for (String s : ConfigManager.getInstance().getMessageList(type, type + ".0.Description")) {
            lore.add(new Colorize().addColor(s));
        }
        lore.add("");
        lore.add(new Colorize().addColor("&aCaught by &3" + playerName + " &aon &3" + strDate));
        Boolean glow = ConfigManager.getInstance().getBoolean(type, type + ".0.Glow");
        return new ItemMaker().makeItem(material, 1, CustomModelID, name, lore, glow, false);
    }

    private void dropFish (World world, Location loc, ItemStack fish) {
        loc.setY(loc.getY() + 1.0D);
        world.dropItem(loc, fish);
    }
}
