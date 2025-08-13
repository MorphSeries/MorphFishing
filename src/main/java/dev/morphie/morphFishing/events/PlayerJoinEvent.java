package dev.morphie.morphFishing.events;

import dev.morphie.morphFishing.MorphFishing;
import dev.morphie.morphFishing.utils.database.SQLite;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class PlayerJoinEvent implements Listener {

    private MorphFishing plugin;

    public PlayerJoinEvent(MorphFishing plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        if (plugin.getConfig().getString("StorageMethod").equals("SQLite")) {
            if (!new SQLite(plugin).playerExists(uuid)) {
                new SQLite(plugin).createPlayer(uuid, player);
            }
        }
    }
}
