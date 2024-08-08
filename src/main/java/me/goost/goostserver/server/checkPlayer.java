package me.goost.goostserver.server;

import me.goost.goostserver.SQLiteDB.Database;
import me.goost.goostserver.SQLiteDB.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Date;

public class checkPlayer {

    // This method checks if the player's data exists (IN DB) and initializes it if not.
    public static void checkPlayersAllDataInDB(Player player) throws SQLException {


        PlayerStats playerStats = Database.findPlayerStatsByUUID(player.getUniqueId().toString());

        if (playerStats == null) {
            // Initialize new player data with default values
            playerStats = new PlayerStats(
                    player.getUniqueId().toString(),
                    false,   // Player status
                    "",      // Class
                    0,       // Bank
                    0,       // Level
                    0.0,     // Experience
                    new Date(), // Last Login
                    new Date()  // Last Logout
            );
            Database.setPlayerStats(playerStats);
            Bukkit.getLogger().info("Initialized data for new player: " + player.getName());
        }else{
            if (playerStats.getExperience() == null) {
                playerStats.setExperience(0.0);
            }
            if (playerStats.getLevel() == null) {
                playerStats.setLevel(0);
            }
            if (playerStats.getBank() == null) {
                playerStats.setBank(0);
            }
            if (playerStats.getPlayer() == null) {
                playerStats.setPlayer(false);
            }
        }

        Database.setPlayerStats(playerStats);

        Database.updateIngameWithDatabase(playerStats);
    }

    // This method checks if the player's data exists (IN HOME DB) and initializes it if not.
    public static void checkPlayersAllDataInHomeDB(Player player) throws SQLException {


        Location playerLocation = Database.findPlayerHomeByUUID(player.getUniqueId());

        if (playerLocation == null) {
            // Initialize new player data with default values
            playerLocation = new Location(Bukkit.getWorld("world"),-19.0, 161.0, 51.0);
            Database.setPlayerHome(player.getUniqueId(), playerLocation);
            Bukkit.getLogger().info("Initialized data for new player: " + player.getName());
        }else{
            if ((Double)playerLocation.getX() == null||(Double)playerLocation.getY() == null||(Double)playerLocation.getZ() == null) {
                playerLocation = new Location(Bukkit.getWorld("world"),-19.0, 161.0, 51.0);
            }
        }

        Database.setPlayerHome(player.getUniqueId(), playerLocation);
    }
}
