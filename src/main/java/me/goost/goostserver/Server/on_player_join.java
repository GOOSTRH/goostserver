package me.goost.goostserver.Server;

import me.goost.goostserver.SQLDB.Database;
import me.goost.goostserver.player.ChooseJob;
import me.goost.goostserver.player.health;
import me.goost.goostserver.player.level;
import me.goost.goostserver.player.money;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class on_player_join implements Listener {
    @EventHandler
    public void onplayerjoin(PlayerJoinEvent e) {

        Database.loadDataBasePlayer(e.getPlayer());
        health.onplayerjoin(e);
        money.onplayerjoin(e);
        ChooseJob.onplayerjoin(e);
        level.onplayerjoin(e);
        e.getPlayer().sendMessage("Ver 0.010");
    }
}
