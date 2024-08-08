package me.goost.goostserver.SQLiteDB;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;
import java.util.Date;

public class dataBaseListener implements Listener {



    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        try {
            PlayerStats stats = Database.findPlayerStatsByUUID(player.getUniqueId().toString());
            if (stats == null) { // if db does not contain the players info
                stats = new PlayerStats(
                        player.getUniqueId().toString(),
                        false, "", 0, 0, 0.0, new Date(), new Date()
                );
                Database.setPlayerStats(stats);
            } else {
                stats.setBank(stats.getBank() + 1);
                Database.updatePlayerStats(stats);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void updateDataBasePlayerClassAndPlayer_(Player player, String str) {
        try {
            PlayerStats stats = Database.findPlayerStatsByUUID(player.getUniqueId().toString());
            if (stats == null) { // if db does not contain the players info
                stats = new PlayerStats(
                        player.getUniqueId().toString(),
                        true, str, 0, 0, 0.0, new Date(), new Date()
                );
                Database.setPlayerStats(stats);
            } else {
                stats.setPlayer(true);
                stats.setClass_(str);
                Database.updatePlayerStats(stats);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
