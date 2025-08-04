package dev.morphie.morphFishing.menus;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.itemstack.ItemMaker;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.BiConsumer;

public class MainMenu {

    private MorphFishing plugin;
    public MainMenu(MorphFishing plugin) {
        this.plugin = plugin;
    }

    String[] guiSetup = ConfigManager.getInstance().getMessageList("main", "MenuLayout").toArray(new String[0]);
    String lore;

    public void openMainGUI(Player p) {
        InventoryGui gui = new InventoryGui(plugin, p, new Colorize().addColor(ConfigManager.getInstance().getMessage("main", "MenuName")), guiSetup);
        UUID uuid = p.getUniqueId();

        //Filler Item
        if (ConfigManager.getInstance().getBoolean("main", "FillerItem.Enabled")) {
            gui.setFiller(new ItemMaker().makeItem(Material.matchMaterial(ConfigManager.getInstance().getMessage("main", "FillerItem.Material")), 1, ConfigManager.getInstance().getInt("main", "FillerItem.CustomModelID"), "", null, false, false, p));
        }

        // Glass Item 1
        gui.addElement(new StaticGuiElement('1',
                this.buildGuiItem("MenuItems.1", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.1.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.1.Name")),
                lore
        ));

        // Glass Item 2
        gui.addElement(new StaticGuiElement('2',
                this.buildGuiItem("MenuItems.2", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.2.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.2.Name")),
                lore
        ));

        // Glass Item 3
        gui.addElement(new StaticGuiElement('3',
                this.buildGuiItem("MenuItems.3", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.3.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.3.Name")),
                lore
        ));

        // Glass Item 4
        gui.addElement(new StaticGuiElement('4',
                this.buildGuiItem("MenuItems.4", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.4.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.4.Name")),
                lore
        ));

        // Glass Item 5
        gui.addElement(new StaticGuiElement('5',
                this.buildGuiItem("MenuItems.5", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.5.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.5.Name")),
                lore
        ));

        // Fish Market
        gui.addElement(new StaticGuiElement('s',
                this.buildGuiItem("MenuItems.s", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.s.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.s.Name")),
                lore
        ));

        // Fish Market
        gui.addElement(new StaticGuiElement('m',
                this.buildGuiItem("MenuItems.m", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.m.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.m.Name")),
                lore
        ));

        // Fisherman's Journal
        gui.addElement(new StaticGuiElement('j',
                this.buildGuiItem("MenuItems.j", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.j.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.j.Name")),
                lore
        ));

        // Quests
        gui.addElement(new StaticGuiElement('q',
                this.buildGuiItem("MenuItems.q", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.q.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.q.Name")),
                lore
        ));

        // Events
        gui.addElement(new StaticGuiElement('e',
                this.buildGuiItem("MenuItems.e", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.e.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.e.Name")),
                lore
        ));

        // Fisherman's Bag
        gui.addElement(new StaticGuiElement('b',
                this.buildGuiItem("MenuItems.b", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.b.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.b.Name")),
                lore
        ));

        // Charms
        gui.addElement(new StaticGuiElement('c',
                this.buildGuiItem("MenuItems.c", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.c.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.c.Name")),
                lore
        ));

        // Lures & Bait
        gui.addElement(new StaticGuiElement('l',
                this.buildGuiItem("MenuItems.l", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.l.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.l.Name")),
                lore
        ));

        // Workbenches
        gui.addElement(new StaticGuiElement('w',
                this.buildGuiItem("MenuItems.w", p),
                ConfigManager.getInstance().getInt("main", "MenuItems.w.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.w.Name")),
                lore
        ));

        gui.show(p);
    }

    private ItemStack buildGuiItem(String path, Player p) {
        Material material = Material.matchMaterial(ConfigManager.getInstance().getMessage("main", path + ".Material"));
        int amount = ConfigManager.getInstance().getInt("main", path + ".Amount");
        int CustomModelID =  ConfigManager.getInstance().getInt("main", path + ".CustomModelID");
        boolean glow = ConfigManager.getInstance().getBoolean("main", path + ".Glow");
        ArrayList<String> loreList = new ArrayList<String>();
        for (String s : ConfigManager.getInstance().getMessageList("main", path + ".Lore")) {
            loreList.add(new Colorize().addColor(s));
        }
        lore = String.join("\n", loreList);
        return new ItemMaker().makeItem(material, amount, CustomModelID, "", loreList, glow, false, p);
    }
}
