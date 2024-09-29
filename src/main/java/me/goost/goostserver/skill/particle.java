package me.goost.goostserver.skill;

import me.goost.goostserver.GoostServer;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.UUID;

public class particle {


    public static void spawnColorBallParticle(
            Location loc, Player player, Color color1, Color color2, int amount,
            float radius, float height, Float particle_size, int time
    ){
        loc.add(0, 1, 0);
        new BukkitRunnable() {
            private int i = 1;
            public void run() {
                if(i >= time) {
                    cancel();
                }
                ++i;
                Particle.DustTransition dustTransition = new Particle.DustTransition(color1, color2, particle_size);
                player.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, amount, radius, height , radius, dustTransition);
            }
        }.runTaskTimer(GoostServer.getPlugin(), 0L, 1);
    }

    public static void spawnSpiralParticle(
            Location loc, Player player, int count,
            float initialRadius, float endRadius, float gap, int revolutions, int points, Particle particle, int time
    ) {
        // Get the direction the player is looking at
        Vector direction = loc.getDirection().normalize();

        double gapIncrement = gap / (revolutions * points); // Gap between each point
        double angleIncrement = Math.PI * 2 / points; // Angle step between each point in a revolution
        double distanceIncrement = 0.1; // Faster increment for distance (control speed)

        new BukkitRunnable() {
            double currentHeight = 0; // Start at ground level
            double distance = 0; // Distance along the direction player is facing
            double inradius = initialRadius; // Starting radius
            double enRadius = endRadius; // Starting radius

            double xOffset = 0;
            double zOffset = 0;

            int i = 0;
            @Override
            public void run() { // loop
                if (currentHeight > gap || distance > time) {
                    this.cancel(); // Cancel task after the spiral completes
                    return;
                }

                if ((i + inradius) <= enRadius){ // if
                    // Calculate offsets for the spiral based on the angle and growing radius
                    xOffset = inradius + i;
                    zOffset = inradius + i;
                    i += 0.2;
                }

                // Move along the player's direction and apply the offsets
                Location currentLocation = loc.clone().add(direction.clone().multiply(distance));
                currentLocation.add(xOffset, currentHeight, zOffset);

                // Spawn the particle effect at the calculated location
                player.getWorld().spawnParticle(particle, currentLocation, count, 0, 0, 0, 0);

                // Update for the next point in the spiral
                currentHeight += gapIncrement; // Increment the height
                distance += distanceIncrement; // Move further in the direction the player is looking
            }
        }.runTaskTimer(GoostServer.getPlugin(), 0, 1); // Repeat task every tick (1 tick = 50ms)
    }
}
