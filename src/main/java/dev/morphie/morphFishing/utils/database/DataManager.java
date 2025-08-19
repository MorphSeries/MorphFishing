package dev.morphie.morphFishing.utils.database;

import dev.morphie.morphFishing.MorphFishing;

import java.util.UUID;

public class DataManager {

    private MorphFishing plugin;

    public DataManager(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public Integer getData(UUID uuid, String name) {
        return new SQLite(this.plugin).getData(uuid, name);
    }

    public void updateData(UUID uuid, int data, String name) {
        if (this.plugin.getConfig().getString("StorageMethod").equals("SQLite")) {
            new SQLite(this.plugin).setData(uuid, data, name);
        }
    }

    public void addXP(UUID uuid, int xp) {
        int currentXP = getData(uuid, "mf_xp");
        int requiredXP = getData(uuid, "mf_required_xp");
        int level = getData(uuid, "mf_level");
        int baseIncrease = plugin.getConfig().getInt("PlayerLevel.BaseXPIncrease");
        currentXP += xp;
        while (currentXP >= requiredXP) {
            currentXP -= requiredXP;
            level += 1;
            requiredXP += baseIncrease;
        }
        updateData(uuid, currentXP, "mf_xp");
        updateData(uuid, requiredXP, "mf_required_xp");
        updateData(uuid, level, "mf_level");
    }

    public void addGillings(UUID uuid, int gillings) {
        int currentGillings = getData(uuid, "mf_gillings");
        currentGillings += gillings;
        updateData(uuid, currentGillings, "mf_gillings");
    }
}
