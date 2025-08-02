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

import java.util.ArrayList;
import java.util.UUID;

public class MainMenu {

    private MorphFishing plugin;
    public MainMenu(MorphFishing plugin) {
        this.plugin = plugin;
    }

    String[] guiSetup = ConfigManager.getInstance().getMessageList("main", "MenuLayout").toArray(new String[0]);

    public void openMainGUI(Player p) {
        InventoryGui gui = new InventoryGui(plugin, p, new Colorize().addColor(ConfigManager.getInstance().getMessage("main", "MenuName")), guiSetup);
        UUID uuid = p.getUniqueId();

        //Filler Item
        if (ConfigManager.getInstance().getBoolean("main", "FillerItem.Enabled")) {
            gui.setFiller(new ItemMaker().makeItem(Material.matchMaterial(ConfigManager.getInstance().getMessage("main", "FillerItem.Material")), 1, ConfigManager.getInstance().getInt("main", "FillerItem.CustomModelID"), "", null, false, false));
        }

        // Glass Item 1
        gui.addElement(new StaticGuiElement('1',
                this.buildGuiItem("MenuItems.1"),
                ConfigManager.getInstance().getInt("main", "MenuItems.1.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.1.Name"))
        ));

        // Glass Item 2
        gui.addElement(new StaticGuiElement('2',
                this.buildGuiItem("MenuItems.2"),
                ConfigManager.getInstance().getInt("main", "MenuItems.2.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.2.Name"))
        ));

        // Glass Item 3
        gui.addElement(new StaticGuiElement('3',
                this.buildGuiItem("MenuItems.3"),
                ConfigManager.getInstance().getInt("main", "MenuItems.3.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.3.Name"))
        ));

        // Glass Item 4
        gui.addElement(new StaticGuiElement('4',
                this.buildGuiItem("MenuItems.4"),
                ConfigManager.getInstance().getInt("main", "MenuItems.4.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.4.Name"))
        ));

        // Glass Item 5
        gui.addElement(new StaticGuiElement('5',
                this.buildGuiItem("MenuItems.5"),
                ConfigManager.getInstance().getInt("main", "MenuItems.5.Amount"),
                click -> {
                    return true;
                },
                new Colorize().addColor(ConfigManager.getInstance().getMessage("main",  "MenuItems.5.Name"))
        ));


        gui.show(p);
    }

    private ItemStack buildGuiItem(String path) {
        Material material = Material.matchMaterial(ConfigManager.getInstance().getMessage("main", path + ".Material"));
        int amount = ConfigManager.getInstance().getInt("main", path + ".Amount");
        int CustomModelID =  ConfigManager.getInstance().getInt("main", path + ".CustomModelID");
        boolean glow = ConfigManager.getInstance().getBoolean("main", path + ".Glow");
        ArrayList<String> lore = new ArrayList<String>();
        for (String s : ConfigManager.getInstance().getMessageList("main", path + ".Lore")) {
            lore.add(new Colorize().addColor(s));
        }
        return new ItemMaker().makeItem(material, amount, CustomModelID, "", lore, glow, false);
    }
}
