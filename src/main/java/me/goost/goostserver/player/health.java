package me.goost.goostserver.player;

import me.goost.goostserver.GoostServer;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.util.HashMap;
import java.util.UUID;



public class health implements Listener{

    public static HashMap<UUID, Double> maxHealth = new HashMap<>();
    public static HashMap<UUID, Double> health = new HashMap<>();


    public static Double GetMaxHp(UUID uuid){
        return maxHealth.get(uuid);
    };

    public static void AddMaxHp(UUID uuid, Double num){
        maxHealth.put(uuid, GetMaxHp(uuid)+num);
    };
    public static void SetMaxHp(UUID uuid, Double num){
        maxHealth.put(uuid,num);
    };

    static HashMap<UUID, Double> healthScale = new HashMap<>();



    public static void checkPlayerHealthAlways(){
        // loop through all players
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(health.get(player.getUniqueId()) > GetMaxHp(player.getUniqueId())){
                health.put(player.getUniqueId(), GetMaxHp(player.getUniqueId()));
            }




            // value that will be set as extra health value
            double extra_health_number = 20.0;

            for (double i = maxHealth.get(player.getUniqueId()); i >= 101; i -= 100) {
                extra_health_number += 2.0; // extra_health_number = extra heart ex. if 101-200 = 11 heart
            }

            if ( maxHealth.get(player.getUniqueId()) < 100 ){
                // if the Player's max health is lower than 100
                extra_health_number = (maxHealth.get(player.getUniqueId())/5);
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
            player.setHealth((health.get(player.getUniqueId()) / maxHealth.get(player.getUniqueId())) * 20);
        }
    }


    public static void playerRespawn(Player player){
        player.sendMessage(ChatColor.RED+"YOU DIED");
        player.teleport(new Location(Bukkit.getWorld("World"), -19, 161, 51,180,0));
        health.replace(player.getUniqueId(), maxHealth.get(player.getUniqueId()));
    }


    @EventHandler
    public void playerHeal(EntityRegainHealthEvent event){
        event.setCancelled(true);
        Entity e = event.getEntity();
        if(e instanceof Player){
            // if entity is a Player
            Player player = (Player)e;
            double heal = event.getAmount();
            if(health.get(player.getUniqueId())+heal > maxHealth.get(player.getUniqueId())){
                health.replace(player.getUniqueId(), maxHealth.get(player.getUniqueId()));
            }else{
                health.replace(player.getUniqueId(),health.get(player.getUniqueId())+heal);
            }
            event.setCancelled(true);
        }
    }
}





