package me.goost.goostserver.skill.bladeMaster;

import me.goost.goostserver.player.mana;
import me.goost.goostserver.skill.Items;
import me.goost.goostserver.skill.assets.assets;
import me.goost.goostserver.skill.skillsManaUses;
import me.goost.goostserver.skill.particle;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class bladeMasterSkill {

    public static HashMap<UUID, Long> fireBall_cooldown = new HashMap<>();

    public static int fireBall_cooldowntime = 1;


    public static void onPlayerUse(PlayerInteractEvent e){
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        if(player.getItemInHand().equals(Items.BladeMaster_knife)){ // if the player is holding a blademaster sword
            if(mana.getMana(player.getUniqueId()) >= 30){
                if(fireBall_cooldown.containsKey(uuid)){

                    // when it's *NOT* the first time the Player using this skill
                    long secondleft = ((fireBall_cooldown.get(uuid) / 1000) + fireBall_cooldowntime) - (System.currentTimeMillis() / 1000);
                    // cooldown seconds left for skill
                    if(secondleft > 0){
                        // if still in cooldown
                        player.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Skill in Cooldown - "+ getFireBallCooldown(player.getUniqueId())+"s left");
                    }else{
                        // if ready to use skill
                        bladeMasterFireBall(player);
                        setFireBallCooldown(player.getUniqueId());
                    }
                }else{
                    // when it's the first time the Player using this skill
                    bladeMasterFireBall(player);
                    setFireBallCooldown(player.getUniqueId());
                }
            }else{
                player.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Insufficient MP");
            }
        }//else if(){}
    }


    public static void setFireBallCooldown(UUID player){
        fireBall_cooldown.put(player, System.currentTimeMillis());
    }

    public static long getFireBallCooldown(UUID player){
        return ((fireBall_cooldown.get(player) / 1000) + fireBall_cooldowntime) - (System.currentTimeMillis() / 1000);
    }

    public static void bladeMasterFireBall(Player player){
        mana.removeMana(player.getUniqueId(), skillsManaUses.BladeMaster_FireBall_skill());

        assets.fireFireBall(player, 4.0f, 2,true);
/*
        particle.spawnColorBallParticle(
                player.getLocation(),
                player,
                Color.fromRGB(127, 0, 255),
                Color.fromRGB(255,102,255),
                200,
                0.5f,
                0.8f,
                0.5f,
                2
        );
*/
        Location loc = player.getLocation();
        int count = 1000; // One particle at a time
        float inradius = 1.0f; // Spiral radius inr
        float enradius = 3.0f; // Spiral radius enr
        float height = 3.0f; // Spiral height
        int revolutions = 30; // Number of spirals
        int points = 100; // Number of points per revolution
        int time = 20; // Duration of the spiral effect in ticks

        particle.spawnSpiralParticle(loc, player, count, inradius, enradius, height, revolutions, points, Particle.FLAME, time);
    }
}
