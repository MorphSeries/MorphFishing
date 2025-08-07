package dev.morphie.morphFishing.utils;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadableItemNBT;
import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class FishHandlers {

    private MorphFishing plugin;
    public FishHandlers(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public void payFish(Inventory inv, Player p) {
        int fish = 0;
        int money = 0;

        double commonPrice = plugin.getConfig().getDouble("CommonFishPrice");
        double rarePrice = plugin.getConfig().getDouble("RareFishPrice");
        double epicPrice = plugin.getConfig().getDouble("EpicFishPrice");
        double legendaryPrice = plugin.getConfig().getDouble("LegendaryFishPrice");
        double mythicPrice = plugin.getConfig().getDouble("MythicFishPrice");

        if (inv.getSize() > 26) {
            for (int i = 0; i <= inv.getSize() - 1; i++) {
                ItemStack item = inv.getItem(i);
                if (item != null) {
                    int x = item.getAmount();
                    while (x > 0) {
                        x--;
                        if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.common"))) {
                            fish++;
                            money = (int) (money + commonPrice);
                        } else if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.rare"))) {
                            fish++;
                            money = (int) (money + rarePrice);
                        } else if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.epic"))) {
                            fish++;
                            money = (int) (money + epicPrice);
                        } else if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.legendary"))) {
                            fish++;
                            money = (int) (money + legendaryPrice);
                        } else if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.mythic"))) {
                            fish++;
                            money = (int) (money + mythicPrice);
                        } else {
                            p.getInventory().addItem(new ItemStack[] { item });
                        }
                    }
                }
            }
            if ((money != 0) && (fish != 0)) {
                MorphFishing.econ.depositPlayer(p, money);
                String currencySymbol = plugin.getConfig().getString("Settings.CurrencySymbol");
                p.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Prefix") + "&7You sold &3" + fish + " &7fish for &a" + currencySymbol + money + "&7!"));
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ConfigManager.getInstance().getMessage("messages", "ErrorPrefix") + "Assigned 'f' slots are greater than 27! Please reduce to 3 inventory rows in the menulayout of the market.yml.");
        }
    }
}
