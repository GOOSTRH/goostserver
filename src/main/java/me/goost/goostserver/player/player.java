package me.goost.goostserver.player;

import me.goost.goostserver.GoostServer;

import me.lemonypancakes.bukkit.common.actionbarapi.ActionBarAPI;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;



import static me.goost.goostserver.player.health.*;


public class player implements Listener {
    @EventHandler
    public void damage(EntityDamageEvent event){

        Entity e = event.getEntity();


        e.sendMessage(""+event.getDamage());

        if (event instanceof EntityDamageByEntityEvent) {
            //Entity damager = ((EntityDamageByEntityEvent) event).getDamager();
            Entity victim = event.getEntity();
            if (!(victim instanceof Player)){
                double damage = event.getDamage();
                show_entity_indicator(victim,damage);
            }

        }

        if(e instanceof Player){ // if the entity is a player

            Player p = (Player)e;

            double damage = event.getDamage();
            event.setDamage(0.0);

            Player player = (Player)e;
            player.sendMessage("raw: "+damage);

            damage = damage - (def.get_def(player.getUniqueId()) * 0.1876172608);
            player.sendMessage("after def: "+damage);
            if(damage < 0){
                damage = 0;
            }

            ActionBarAPI.sendMessage(player,
                    ChatColor.RED +
                            String.valueOf(Math.round(health.get(player.getUniqueId()))) +
                            "/" +
                            (healthy.get(player.getUniqueId()))+
                            "❤ -"+Math.round(damage)+"       "+
                            ChatColor.GREEN+
                            def.get_def(player.getUniqueId()) +"🛡       "+
                            ChatColor.BLUE+
                            "200/200 ⁂"

            );

            player.sendMessage(""+damage);
            health.replace(player.getUniqueId(),health.get(player.getUniqueId())-damage);

            Player victim = (Player) event.getEntity();
            show_entity_indicator(victim,damage);





        }// end of if e instanceof player

    }// end of the playerdamage



    public void show_entity_indicator(Entity victim,double damage){

        Vector v = new Vector();
        v.setX((0.0D + Math.random() - Math.random())/2.2);
        v.setY(Math.random()/2);
        v.setX((0.0D + Math.random() - Math.random())/2.2);

        ArmorStand indicator = (ArmorStand) victim.getWorld()
                .spawnEntity(victim.getLocation().add(0,1,0),
                        EntityType.ARMOR_STAND);

        indicator.setVisible(false);
        indicator.setCustomNameVisible(true);
        indicator.setVelocity(v);
        indicator.setCustomName(ChatColor.RED+"-"+Math.round(damage));
        indicator.setSmall(true);
        indicator.setBasePlate(false);
        indicator.setMarker(true);

        Plugin plugin = GoostServer.plugin;
        long delay_time = 10L;
        new BukkitRunnable() {
            @Override
            public void run() {
                // run this code in 5 seconds
                indicator.remove();
            }
        }.runTaskLater(plugin, delay_time);
    }

}










