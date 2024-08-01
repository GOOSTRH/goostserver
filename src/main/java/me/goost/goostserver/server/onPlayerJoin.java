package me.goost.goostserver.server;

import me.goost.goostserver.SQLiteDB.*;
import me.goost.goostserver.player.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;


public class onPlayerJoin implements Listener {
    @EventHandler
    public void onplayerjoin(PlayerJoinEvent e) throws SQLException {
        Player player = e.getPlayer();

        try {
            checkPlayer.checkPlayersAllDataInDB(player); // check player datas
        } catch (SQLException eve) {
            eve.printStackTrace();
        }

        checkPlayer_.checkPlayer_(player); // check if the player is player_
        Database.loadDataBasePlayer(player);
        PlayerLocation.spawnLocation(player);



        money.onplayerjoin(e);
        ChooseJob.onplayerjoin(e);
        level.onplayerjoin(e);


    }
}
