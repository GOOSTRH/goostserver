package me.goost.goostserver.skill.archer;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ac_skill {

    public static void demon_super_jump(Player player){

        if(!sneakjump_cooldown.containsKey(player.getUniqueId())){
            sneakjump_cooldown.put(player.getUniqueId(),true);
        }
        if(sneakjump_cooldown.get(player.getUniqueId())){
            player.getWorld().playSound(player.getLocation() , Sound.ENTITY_BAT_TAKEOFF , 1F, (float) 1.2);
            player.setVelocity(new Vector(0,0.4,0));
            player.setVelocity(player.getLocation().getDirection().multiply(1));

            Location loc = player.getLocation();

            //Vector v = player.getLocation().getDirection();
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(64,64,64), 1);
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 5,0.3,0,0.3,dustOptions);

            sneakjump_cooldown.replace(player.getUniqueId(),false);
        }
    }

}
