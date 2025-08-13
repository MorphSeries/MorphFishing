package dev.morphie.morphFishing.utils.database;

import dev.morphie.morphFishing.MorphFishing;

import java.util.UUID;

public class DataManager {

    private MorphFishing plugin;

    public DataManager(MorphFishing plugin) {
        this.plugin = plugin;
    }

    public String getData(UUID uuid, String name) {
//        if (this.plugin.getConfig().getString("StorageMethod").equals("MySQL")) {
//            return new MySQL(this.plugin).getData(uuid, name);
//        } else if(this.plugin.getConfig().getString("StorageMethod").equals("Redis")){
//            return new Redis(this.plugin).getData(uuid, name);
//        } else {
        return new SQLite(this.plugin).getData(uuid, name);
    }

    public void updateData(UUID uuid, int data, String name, String type) {
//        if (this.plugin.getConfig().getString("StorageMethod").equals("MySQL")) {
//            new MySQLConnection(this.plugin).updateData(uuid, data, name, type);
//        } else if(this.plugin.getConfig().getString("StorageMethod").equals("Redis")){
//            new RedisConnection(this.plugin).updateData(uuid, data, name, type);
        if (this.plugin.getConfig().getString("StorageMethod").equals("SQLite")) {
            new SQLite(this.plugin).updateData(uuid, data, name, type);
        }
    }
}
