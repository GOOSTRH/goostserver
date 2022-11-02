package me.goost.goostserver.player;

import me.goost.goostserver.GoostServer;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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


            player.sendMessage(""+damage);
            health.replace(player.getUniqueId(),health.get(player.getUniqueId())-damage);

            Player victim = (Player) event.getEntity();
            show_entity_indicator(victim,damage);





        }// end of if e instanceof player

    }// end of the playerdamage



    public void show_entity_indicator(Entity victim,double damage){

        Vector v = new Vector();
        v.setX(0);
        v.setY(0.3);
        v.setX(0);

        double max = 0.5;
        double min = -0.5;
        double range = max - min + 1;

        double x = (Math.random() * range)-1,
                y = (Math.random() * range)-1,
                z = (Math.random() * range)-1;

        ArmorStand indicator = (ArmorStand) victim.getWorld()
                .spawnEntity(victim.getLocation().add(x,y+1,z),
                        EntityType.ARMOR_STAND);

        indicator.setVisible(false);
        //indicator.setVelocity(v);
        indicator.setCustomNameVisible(true);
        indicator.setCustomName(ChatColor.RED+""+Math.round(damage));
        indicator.setSmall(true);
        indicator.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20, 1),true);
        indicator.setBasePlate(false);

        Plugin plugin = GoostServer.plugin;
        double delay_time = (0.85 * 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                indicator.remove();
            }
        }.runTaskLater(plugin, (long)delay_time);
    }

}










