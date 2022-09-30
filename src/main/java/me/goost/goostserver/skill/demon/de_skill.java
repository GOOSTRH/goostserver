package me.goost.goostserver.skill.demon;

import me.goost.goostserver.skill.check.onground;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class de_skill {
    private static HashMap<UUID, Boolean> sneakjump_cooldown = new HashMap<>();

    public static void demon_skill_check(Player player,UUID uuid){
        // if the player is sneaking and on ground once and jumping
        if (player.isSneaking() && !player.isOnGround() && onground.groundcheck(uuid)){
            if(onground.groundcheck(uuid)){
                de_skill.sneakjump_cooldown.replace(player.getUniqueId(),onground.groundcheck(uuid));
                onground.groundreplace(uuid,false);
            }
            de_skill.demon_super_jump(player);
        }
    }


    public static void demon_super_jump(Player player){

        if(!sneakjump_cooldown.containsKey(player.getUniqueId())){
            sneakjump_cooldown.put(player.getUniqueId(),true);
        }
        if(sneakjump_cooldown.get(player.getUniqueId())){
            player.getWorld().playSound(player.getLocation() , Sound.ENTITY_BAT_TAKEOFF , 1F, (float) 1.2);
            player.setVelocity(new Vector(0,0.4,0));
            player.setVelocity(player.getLocation().getDirection().multiply(1));

            Location loc = player.getLocation();

            Vector direction = player.getLocation().getDirection();
            loc.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 0,-direction.getX(),-direction.getY(),-direction.getZ(), 1.5);

            sneakjump_cooldown.replace(player.getUniqueId(),false);
        }
    }
}










