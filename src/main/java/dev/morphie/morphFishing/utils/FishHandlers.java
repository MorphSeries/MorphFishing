package dev.morphie.morphFishing.utils;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadableItemNBT;
import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.utils.Colorize;
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

        ItemStack[] contents = inv.getContents();

        for (ItemStack item : contents) {
            if (item != null) {
                if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) ReadableItemNBT::hasNBTData)) {
                    int x = item.getAmount();
                    while (x > 0) {
                        if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.common"))) {
                            fish++;
                            money = (int) (money + commonPrice);
                            x--;
                        } else if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.rare"))) {
                            fish++;
                            money = (int) (money + rarePrice);
                            x--;
                        } else if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.epic"))) {
                            fish++;
                            money = (int) (money + epicPrice);
                            x--;
                        } else if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.legendary"))) {
                            fish++;
                            money = (int) (money + legendaryPrice);
                            x--;
                        } else if (NBT.get(item, (Function<ReadableItemNBT, Boolean>) nbt -> nbt.hasTag("morphfish.mythic"))) {
                            fish++;
                            money = (int) (money + mythicPrice);
                            x--;
                        }
                    }
                } else {
                    p.getInventory().addItem(new ItemStack[] { item });
                }
            }
        }
        inv.clear();
        if ((money != 0) && (fish != 0)) {
            MorphFishing.econ.depositPlayer(p, money);
            String currencySymbol = plugin.getConfig().getString("Settings.CurrencySymbol");
            p.sendMessage(new Colorize().addColor(ConfigManager.getInstance().getMessage("messages", "Prefix") + "&7You sold &3" + fish + " &7fish for &a" + currencySymbol + money + "&7!"));
        }
    }
}
