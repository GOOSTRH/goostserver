package me.goost.goostserver.server;

import me.goost.goostserver.SQLiteDB.*;
import me.goost.goostserver.player.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;
import java.util.Date;


public class onPlayerJoin implements Listener {
    @EventHandler
    public void onplayerjoin(PlayerJoinEvent e) throws SQLException {
        Player player = e.getPlayer();
        Database.loadDataBasePlayer(player); // load DB data into the game

        try {
            checkPlayer.checkPlayersAllDataInDB(player, false); // check player datas
            checkPlayer.checkPlayersAllDataInHomeDB(player); // check player home datas
        } catch (SQLException eve) {
            eve.printStackTrace();
        }

        checkPlayer_.checkPlayer_(player); // check if the player is player_
        PlayerLocation.spawnLocation(player);

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

        money.onplayerjoin(e);
        ChooseJob.onplayerjoin(e);
        level.onplayerjoin(e);


    }
}
