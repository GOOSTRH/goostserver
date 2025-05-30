package me.goost.goostserver.skill.assassinSkill;

import me.goost.goostserver.skill.check.onground;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class assassinSkill {
    private static HashMap<UUID, Boolean> sneakjump_cooldown = new HashMap<>();

    public static void assasin_skill_check(Player player, UUID uuid){
        // if the Player is sneaking and on ground once and jumping
        if (player.isSneaking() && !player.isOnGround() && onground.groundcheck(uuid)){
            if(onground.groundcheck(uuid)){
                sneakjump_cooldown.replace(player.getUniqueId(),onground.groundcheck(uuid));
                onground.groundreplace(uuid,false);
            }
            super_jump(player);
        }
    }


    public static void super_jump(Player player){

        if(!sneakjump_cooldown.containsKey(player.getUniqueId())){
            sneakjump_cooldown.put(player.getUniqueId(),true);
        }
        if(sneakjump_cooldown.get(player.getUniqueId())){
            player.getWorld().playSound(player.getLocation() , Sound.ENTITY_BAT_TAKEOFF , 1F, (float) 1.2);
            player.setVelocity(new Vector(0,0.4,0));
            player.setVelocity(player.getLocation().getDirection().multiply(1));

            Location loc = player.getLocation();

            //Vector v = Player.getLocation().getDirection();
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(64,64,64), 1);
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.getX(), loc.getY(), loc.getZ(), 5,0.3,0,0.3,dustOptions);

            sneakjump_cooldown.replace(player.getUniqueId(),false);
        }
    }

}
