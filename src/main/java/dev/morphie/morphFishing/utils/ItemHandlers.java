package dev.morphie.morphFishing.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemHandlers {

    public void giveItem(Player p, World world, Location loc, ItemStack item, String dropType) {
        if (p.getInventory().firstEmpty() == -1) {
            switch (dropType) {
                case "head" -> loc.setY(loc.getY() + 1.0D);
                case "feet" -> loc.setY(loc.getY() + 0.3D);
            };
            world.dropItem(loc, item);
        } else {
            p.getInventory().addItem(new ItemStack(item));
        }
    }
}
