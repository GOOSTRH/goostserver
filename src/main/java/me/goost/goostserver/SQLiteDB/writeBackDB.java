package me.goost.goostserver.SQLiteDB;

import me.goost.goostserver.GoostServer;
import me.goost.goostserver.player.level;
import me.goost.goostserver.player.money;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class writeBackDB {
    private static final Map<UUID, PlayerStats> playerDataCache = new ConcurrentHashMap<>();
    private static final long IN_GAME_TICK = 40; // tick per day = 24000


    public static void onEnableWriteBackDB() {
        Bukkit.getLogger().info("WriteBackDB Functioning.");
        startSaveTask();
    }


    public static void onDisableWriteBackDB() {
        Bukkit.getLogger().info("WriteBackDB Stopping.");
        saveAllPlayerData();
    }

    private static void startSaveTask() { // Loops every day in game
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() > 0) {
                    level.calculateAllPlayerLevel(); // calculate All Players level before caching
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        try {
                            updatePlayerCachedData(player.getUniqueId()); // Updating each player's cached data
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    saveAllPlayerData(); // After updating cached data, updated the cached data to DB
                }
            }
        }.runTaskTimer(GoostServer.getPlugin(), IN_GAME_TICK, IN_GAME_TICK);
    }

    public static void saveAllPlayerData() { // saving ALL the cached player data into the DB
        for (Map.Entry<UUID, PlayerStats> entry : playerDataCache.entrySet()) {
            try {
                savePlayerData(entry.getKey(), entry.getValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void savePlayerData(UUID uuid, PlayerStats stats) throws SQLException {
        // saving the cached player data into the DB
        Database.updatePlayerStats(stats);
    }

    public static void updatePlayerCachedData(UUID uuid) throws SQLException {

        // only update, Bank, Exp, level

        //PlayerStats stats = null;
        PlayerStats stats = Database.findPlayerStatsByUUID(uuid.toString());

        stats.setBank(money.GetBank(uuid));
        stats.setExperience(level.getExperience(uuid));
        stats.setLevel(level.getLevel(uuid));

        updatePlayerData(uuid, stats);
    }

    public static void updatePlayerData(UUID uuid, PlayerStats stats) { // updating the cached data from other files/ ex.exp, money
        playerDataCache.put(uuid, stats);
    }
}