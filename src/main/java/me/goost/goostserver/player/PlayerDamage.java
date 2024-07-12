package me.goost.goostserver.player;

import me.goost.goostserver.GoostServer;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;


public class PlayerDamage implements Listener {
    double reduce_amount = 0.01876172608;



    @EventHandler
    public void IfPlayerGetsDamage(EntityDamageEvent event){ // if a entity gets damaged in any way

        Entity e = event.getEntity();
        e.sendMessage(""+event.getDamage());

        Entity victim = event.getEntity();// if the VICTIM is a Entity no matter if its a player or whatsoever
        if (!(victim instanceof Player)){
            double damage = event.getDamage();
            ShowTextIndicator(victim,damage);
        }

        if(e instanceof Player){ // if the VICTIM is a Player

            double damage = event.getDamage();
            // get damage

            event.setDamage(0.0);
            // set damage to 0.0

            Player player = (Player)e;
            player.sendMessage("raw dmg: "+damage);

            damage = damage - (def.get_def(player.getUniqueId()) * reduce_amount);
            damage = Math.round(damage * 100d) / 100d;// reduce the 'damaged amount' to the player's health

            player.sendMessage("aft def dmg: "+damage);
            if(damage < 1){
                damage = 0;
            }
            // if the damage is lower than 1 damage = 0

            player.sendMessage(""+damage);
            health.health.replace(player.getUniqueId(), health.health.get(player.getUniqueId())-damage);

            ShowTextIndicator(player,damage);

            // end of if e instanceof PlayerDamage
        }
    // end of the playerdamage
    }


    public void ShowTextIndicator(Entity victim, double damage){ // textindicator without damager
        // spawns the indicator and changes configurations
        // (max - min + 1) + min
        damage = Math.round(damage);
        int x = (int) Math.floor(Math.random()*2);
        int y = (int) Math.floor(Math.random()*2);
        int z = (int) Math.floor(Math.random()*2);

        double finalDamage = damage;
        TextDisplay Indicator =
                (TextDisplay) victim.getWorld().spawn(victim.getLocation().add(x,y,z), TextDisplay.class, it -> {
                    it.setText(String.valueOf((int)finalDamage));
                    it.setTextOpacity((byte)0);
                    it.setBillboard(Display.Billboard.CENTER);
        });


        // kills the indicator
        Plugin plugin = GoostServer.plugin;
        double delay_time = (0.85 * 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                Indicator.remove();
            }
        }.runTaskLater(plugin, (long)delay_time);
    }


}










