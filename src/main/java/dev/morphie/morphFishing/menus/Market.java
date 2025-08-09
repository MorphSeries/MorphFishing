package dev.morphie.morphFishing.menus;

import de.themoep.inventorygui.GuiStorageElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphFishing.utils.MarketHandler;
import dev.morphie.morphLib.itemstack.ItemMaker;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class Market {

    private MorphFishing plugin;
    public Market(MorphFishing plugin) {
        this.plugin = plugin;
    }

    String[] guiSetup = ConfigManager.getInstance().getMessageList("market", "MenuLayout").toArray(new String[0]);
    String lore;

    public void openMarketGUI(Player p) {
        InventoryGui gui = new InventoryGui(plugin, p, new Colorize().addColor(ConfigManager.getInstance().getMessage("market", "MenuName")), guiSetup);
        UUID uuid = p.getUniqueId();

        // Glass Item 1
        gui.addElement(new StaticGuiElement('1',
                this.buildGuiItem("MenuItems.1", p),
                ConfigManager.getInstance().getInt("market", "MenuItems.1.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("market",  "MenuItems.1.Name")),
                lore
        ));

        // Glass Item 2
        gui.addElement(new StaticGuiElement('2',
                this.buildGuiItem("MenuItems.2", p),
                ConfigManager.getInstance().getInt("market", "MenuItems.2.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("market",  "MenuItems.2.Name")),
                lore
        ));

        // Glass Item 3
        gui.addElement(new StaticGuiElement('3',
                this.buildGuiItem("MenuItems.3", p),
                ConfigManager.getInstance().getInt("market", "MenuItems.3.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("market",  "MenuItems.3.Name")),
                lore
        ));

        // Information
        gui.addElement(new StaticGuiElement('i',
                this.buildGuiItem("MenuItems.i", p),
                ConfigManager.getInstance().getInt("market", "MenuItems.i.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("market",  "MenuItems.i.Name")),
                lore
        ));

        // Back
        gui.addElement(new StaticGuiElement('b',
                this.buildGuiItem("MenuItems.b", p),
                ConfigManager.getInstance().getInt("market", "MenuItems.b.Amount"),
                click -> {
                    new MainMenu(plugin).openMainGUI(p);
                    InventoryGui.clearHistory(p);
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("market",  "MenuItems.b.Name")),
                lore
        ));

        // Sell Fish
        gui.addElement(new StaticGuiElement('s',
                this.buildGuiItem("MenuItems.s", p),
                ConfigManager.getInstance().getInt("market", "MenuItems.s.Amount"),
                click -> {
                    p.closeInventory();
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("market",  "MenuItems.s.Name")),
                lore
        ));

        // Bait Market
        gui.addElement(new StaticGuiElement('m',
                this.buildGuiItem("MenuItems.m", p),
                ConfigManager.getInstance().getInt("market", "MenuItems.m.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("market",  "MenuItems.m.Name")),
                lore
        ));

        int slots = ConfigManager.getInstance().getInt("market", "SellSlots");
        Inventory sellInv = Bukkit.createInventory(null, slots);
        gui.addElement(new GuiStorageElement('f', sellInv));
        gui.setCloseAction(close -> {
            new MarketHandler(plugin).payFish(sellInv, p);
            return false; // Don't go back to the previous GUI (true would automatically go back to the previously opened one)
        });

        gui.show(p);
    }

    private ItemStack buildGuiItem(String path, Player p) {
        Material material = Material.matchMaterial(ConfigManager.getInstance().getMessage("market", path + ".Material"));
        int amount = ConfigManager.getInstance().getInt("market", path + ".Amount");
        int CustomModelID =  ConfigManager.getInstance().getInt("market", path + ".CustomModelID");
        boolean glow = ConfigManager.getInstance().getBoolean("market", path + ".Glow");
        ArrayList<String> loreList = new ArrayList<String>();
        for (String s : ConfigManager.getInstance().getMessageList("market", path + ".Lore")) {
            loreList.add(new Colorize().addColor(s));
        }
        lore = String.join("\n", loreList);
        return new ItemMaker().makeItem(material, amount, CustomModelID, "", loreList, glow, false, p, null);
    }
}
