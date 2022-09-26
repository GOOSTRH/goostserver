package me.goost.goostserver.player;

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

    //static HashMap<UUID, Double> addony = new HashMap<>();

    static HashMap<UUID, Double> healthscale = new HashMap<>();




    public static void playerrespawn(Player player){
        player.sendMessage("너죽음");
        player.teleport(new Location(Bukkit.getWorld("World"), -59,67,8));
        health.replace(player.getUniqueId(),healthy.get(player.getUniqueId()));
    }



    @EventHandler
    public void playerheal(EntityRegainHealthEvent event){
        Entity e = event.getEntity();
        if(e instanceof Player){
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













