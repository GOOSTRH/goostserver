package me.goost.goostserver.player;

import me.goost.goostserver.GoostServer;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class PlayerDamage implements Listener {


    @EventHandler
    public void IfEntityDamaged(EntityDamageEvent event){ // if a entity gets damaged in any way

        Entity e = event.getEntity();

        Entity victim = event.getEntity();// if the VICTIM is an Entity no matter if its a player or whatsoever
        if (!(victim instanceof Player)){// if the victim is not a player, show text indicator
            double damage = event.getDamage();
            ShowTextIndicator(victim,damage);
        }

        if(e instanceof Player){ // if the VICTIM is a Player

            double damage = event.getDamage();
            // get damage

            event.setDamage(0.0);
            // set damage to 0.0

            Player player = (Player)e;
            double dmgAfterCalculate = damage * (def.GetDef(player.getUniqueId()) / 1000.00);
            damage = damage - dmgAfterCalculate;
            damage = Math.round(damage * 100d) / 100d;// reduce the 'damaged amount' to the player's health

            if(damage < 1){
                damage = 0;
            }
            // if the damage is lower than 1 damage = 0

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










