package dev.morphie.morphFishing.menus;

import de.themoep.inventorygui.DynamicGuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.files.ConfigManager;
import dev.morphie.morphLib.utils.Colorize;
import org.bukkit.Material;
import org.bukkit.entity.Player;

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

        Material material;
        int amount;
        int CustomModelID;
        String name;
        ArrayList<String> lore;
        boolean glow;

        // Glass Item 1

    }


}
