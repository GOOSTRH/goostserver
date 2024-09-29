package me.goost.goostserver.skill.assets;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

public class assets {

    // directional fireball
    public static void fireFireBall(Player player, Float explosionRadius, int speed, boolean incendiary) {
        Location location = player.getEyeLocation();
        Vector direction = location.getDirection();

        Fireball fireball = player.getWorld().spawn(location, Fireball.class);

        // Set the fireball's velocity to go in the direction the player is looking
        fireball.setVelocity(direction.multiply(speed));  // Adjust the speed as necessary
        fireball.setYield(explosionRadius);  // Set explosion radius
        fireball.setIsIncendiary(incendiary);  // Set if fireball should ignite blocks

        fireball.setShooter(player); // set the player as the shooter

    }
}
