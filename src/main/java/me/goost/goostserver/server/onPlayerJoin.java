package me.goost.goostserver.server;

import me.goost.goostserver.SQLDB.Database;
import me.goost.goostserver.player.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class onPlayerJoin implements Listener {
    @EventHandler
    public void onplayerjoin(PlayerJoinEvent e) {

        checkPlayer.checkPlayer(e.getPlayer());
        Database.loadDataBasePlayer(e.getPlayer());
        money.onplayerjoin(e);
        ChooseJob.onplayerjoin(e);
        level.onplayerjoin(e);
        e.getPlayer().sendMessage("Ver 0.010");


    }
}
