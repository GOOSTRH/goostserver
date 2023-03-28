package me.goost.goostserver.player;

import me.goost.goostserver.GoostServer;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class PlayerDamage implements Listener {
    double reduce_amount = 0.01876172608;
    @EventHandler
    public void IfPlayerGetsDamage(EntityDamageEvent event){

        Entity e = event.getEntity();

        e.sendMessage(""+event.getDamage());

        if (event instanceof EntityDamageByEntityEvent) {
            //Entity damager = ((EntityDamageByEntityEvent) event).getDamager(); <- code for getting the damager
            Entity victim = event.getEntity();
            if (!(victim instanceof Player)){
                double damage = event.getDamage();
                ShowTextIndicator(victim,damage);
            }

        }

        if(e instanceof Player){
            // if the entity is a Player
            Player p = (Player)e;

            double damage = event.getDamage();
            event.setDamage(0.0);

            Player player = (Player)e;
            player.sendMessage("raw: "+damage);

            damage = damage - (def.get_def(player.getUniqueId()) * reduce_amount);
            player.sendMessage("after def: "+damage);
            if(damage < 0){
                damage = 0;
            }


            player.sendMessage(""+damage);
            health.health.replace(player.getUniqueId(), health.health.get(player.getUniqueId())-damage);

            Player victim = (Player) event.getEntity();
            ShowTextIndicator(victim,damage);

            // end of if e instanceof PlayerDamage
        }
    // end of the playerdamage
    }


    public void ShowTextIndicator(Entity victim, double damage){
        // spawns the indicator and changes configurations
        // (max - min + 1) + min
        int x = (int) Math.floor(Math.random()*2);
        int y = (int) Math.floor(Math.random()*2);
        int z = (int) Math.floor(Math.random()*2);

        TextDisplay Indicator =
                (TextDisplay) victim.getWorld().spawn(victim.getLocation().add(x,y,z), TextDisplay.class, it -> {
                    it.setText(String.valueOf((int)damage));
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










