package dev.morphie.morphFishing.utils.database;

import dev.morphie.morphFishing.MorphFishing;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

public class SQLite {
    private static MorphFishing plugin;
    private static String tablePrefix;
    private static String databasePath;

    public SQLite(MorphFishing plugin) {
        SQLite.plugin = plugin;
        tablePrefix = SQLite.plugin.getConfig().getString("SQLite.TablePrefix", "mf_");
    }

    public void sqliteSetup() {
        try {
            synchronized (this) {
                File dbFile = new File(plugin.getDataFolder(), "database.db");

                // Create parent directories if they don't exist
                if (!dbFile.getParentFile().exists()) {
                    dbFile.getParentFile().mkdirs();
                }

                // Create the database file if it doesn't exist
                if (!dbFile.exists()) {
                    try {
                        dbFile.createNewFile();
                    } catch (IOException e) {
                        throw new SQLException("Could not create database file", e);
                    }
                }

                databasePath = dbFile.getPath();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkStructure() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            if (connection == null) {
                return;
            }

            String sql = "CREATE TABLE IF NOT EXISTS " + this.tablePrefix + "fishdata ("
                    + "uuid TEXT NULL DEFAULT NULL, "
                    + "mf_level INTEGER NOT NULL DEFAULT 0, "
                    + "mf_xp INTEGER NOT NULL DEFAULT 0, "
                    + "gillings INTEGER NOT NULL DEFAULT 0, "
                    + "fish_caught INTEGER NOT NULL DEFAULT 0, "
                    + "common_caught INTEGER NOT NULL DEFAULT 0, "
                    + "rare_caught INTEGER NOT NULL DEFAULT 0, "
                    + "epic_caught INTEGER NOT NULL DEFAULT 0, "
                    + "legendary_caught INTEGER NOT NULL DEFAULT 0, "
                    + "mythic_caught INTEGER NOT NULL DEFAULT 0, "
                    + "UNIQUE(uuid)"
                    + ");";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(UUID uuid) {
        String query = "SELECT 1 FROM `" + tablePrefix + "fishdata` WHERE uuid=?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, uuid.toString());

            try (ResultSet results = statement.executeQuery()) {
                return results.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void createPlayer(final UUID uuid, Player player) {
        String insertQuery = "INSERT INTO `" + tablePrefix + "fishdata` " +
                "(uuid, " + // 1
                "mf_level, " + // 2
                "mf_xp, " + // 3
                "gillings, " + // 4
                "fish_caught, " + // 5
                "common_caught, " + // 6
                "rare_caught, " + // 7
                "epic_caught, " + // 8
                "legendary_caught, " + // 9
                "mythic_caught) " + // 10
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Use a try-with-resources block to manage connection and statement
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement insert = connection.prepareStatement(insertQuery)) {

            if (!playerExists(uuid)) {
                insert.setString(1, uuid.toString());
                insert.setInt(2, 0);
                insert.setInt(3, 0);
                insert.setInt(4, 0);
                insert.setInt(5, 0);
                insert.setInt(6, 0);
                insert.setInt(7, 0);
                insert.setInt(8, 0);
                insert.setInt(9, 0);
                insert.setInt(10, 0);

                insert.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(UUID uuid, int num, String column, String type) {
        String sql = "UPDATE `" + tablePrefix + "fishdata` SET " + column.toLowerCase() + "=? WHERE uuid=?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int data = Integer.parseInt(getData(uuid, column));

            if (type.equalsIgnoreCase("set")) {
                statement.setInt(1, num);
            } else if (type.equalsIgnoreCase("add") || type.equalsIgnoreCase("remove")) {
                statement.setInt(1, data + num);
            }
            statement.setString(2, uuid.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getData(UUID uuid, String data) {
        String sql = "SELECT " + data + " FROM `" + tablePrefix + "fishdata` WHERE uuid=?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, uuid.toString());

            try (ResultSet results = statement.executeQuery()){
                if (results.next() && results.getString(data) != null) {
                    return String.valueOf(results.getString(data));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}