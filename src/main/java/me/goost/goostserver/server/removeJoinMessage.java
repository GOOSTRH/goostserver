package me.goost.goostserver.server;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class removeJoinMessage implements Listener {
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

    @EventHandler
    public void first_join(PlayerJoinEvent e) {
        if(!e.getPlayer().hasPlayedBefore()){
            e.setJoinMessage(e.getPlayer().getName()+"님(이)가 서버에 처음접속했습니다!");
        }
    }
}
