package me.goost.goostserver;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class remove_join_message implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage("");

    }
    @EventHandler
    public void join(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("");

    }
}
