package me.goost.goostserver.server;

import me.goost.goostserver.SQLiteDB.Database;
import me.goost.goostserver.SQLiteDB.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Date;

public class onPlayerQuit implements Listener {

    @EventHandler
    public void onplayerQuit(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        String targetWorldName = "worldKill";
        if(player.getWorld().getName().equals(targetWorldName)){
            for(ItemStack i : player.getInventory()){
                if(i != null){
                    player.getWorld().dropItemNaturally(player.getLocation(), i);
                    player.getInventory().remove(i);
                }
            }
        }

        PlayerStats stats = Database.findPlayerStatsByUUID(player.getUniqueId().toString());
        if (stats == null) { // if db does not contain the players info
            stats = new PlayerStats(
                    player.getUniqueId().toString(),
                    false, "", 0, 0, 0.0, new Date(), new Date()
            );
            Database.setPlayerStats(stats);
        } else {
            stats.setLastLogin(new Date());
            Database.updatePlayerStats(stats);
        }

    }
}
