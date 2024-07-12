package me.goost.goostserver.player;

import me.goost.goostserver.player.commands.Job;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.util.HashMap;
import java.util.UUID;



public class health implements Listener{

    static HashMap<UUID, Double> healthy = new HashMap<>();
    static HashMap<UUID, Double> health = new HashMap<>();

    //static HashMap<UUID, Double> addition_health_effect = new HashMap<>();
    //static HashMap<UUID, Double> addition_health_Item = new HashMap<>();

    static HashMap<UUID, Double> healthScale = new HashMap<>();



    public static void checkPlayerHealthAlways(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            // loop through all players

            if (healthy.get(player.getUniqueId()) == null) return;
            // check if Player has a max health value , if non return

            double extra_health_number = 20.0;
            // value that will be set as extra health value

            for (double i = healthy.get(player.getUniqueId()); i >= 101; i -= 100) {
                extra_health_number += 2.0;
                // extra_health_number = extra heart ex. if 101-200 = 11 heart
            }

            if ( healthy.get(player.getUniqueId()) < 100 ){
                // if the Player's max health is lower than 100
                extra_health_number = (healthy.get(player.getUniqueId())/5);
            }

            whenPlayerDies(player);
            // if the Player dies

            whenPlayerAlive(player, extra_health_number);
            // when Player is alive
        }
    }

    private static void whenPlayerDies(Player player){
        if (health.get(player.getUniqueId()) < 1.0 ){
            playerRespawn(player);
        }
    }

    private static void whenPlayerAlive(Player player, double extra_health_number){
        if (health.get(player.getUniqueId()) >= 1){
            healthScale.replace(player.getUniqueId(), extra_health_number);
            player.setHealthScale(extra_health_number);
            player.setHealth((health.get(player.getUniqueId()) / healthy.get(player.getUniqueId())) * 20);
        }
    }


    public static void playerRespawn(Player player){
        player.sendMessage(ChatColor.RED+"YOU DIED");
        player.teleport(new Location(Bukkit.getWorld("World"), -39,120,-48,0,0));
        health.replace(player.getUniqueId(),healthy.get(player.getUniqueId()));
    }


    @EventHandler
    public void playerHeal(EntityRegainHealthEvent event){
        Entity e = event.getEntity();
        if(e instanceof Player){
            // if entity is a Player
            Player player = (Player)e;
            double heal = event.getAmount();
            if(health.get(player.getUniqueId())+heal > healthy.get(player.getUniqueId())){
                health.replace(player.getUniqueId(),healthy.get(player.getUniqueId()));
            }else{
                health.replace(player.getUniqueId(),health.get(player.getUniqueId())+heal);
            }
            event.setCancelled(true);
        }
    }
}





