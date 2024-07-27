package me.goost.goostserver.skill;

import me.goost.goostserver.GoostServer;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class particle {

    public static void spawn_particle(
            Location loc, Player player, Color color, int amount,
            float radius, float height, double height_period, Float particle_size, int period, boolean up
    ){

        UUID uuid = player.getUniqueId();

        if(up){ // if the Ring goes up
            new BukkitRunnable() {
                private int i = 0;
                public void run() {
                    if(i <= height / height_period) {

                        final double[] locationY = {0};
                        for (int i = 0; i <= height; i++) {

                            double x = radius * Math.cos(locationY[0]);
                            double z = radius * Math.sin(locationY[0]);

                            player.getWorld().spawnParticle(Particle.REDSTONE, loc.add(x, locationY[0], z), amount, new Particle.DustOptions(color, particle_size));

                            locationY[0] = locationY[0] + height_period;
                        }

                        cancel();
                    }
                    ++i;
                }
            }.runTaskTimer(GoostServer.getPlugin(), 0L, period);
        }else{ // if the Ring goes down
            new BukkitRunnable() {
                private int i = 0;
                public void run() {
                    if(i <= height / height_period) {

                        final double[] locationY = {0};
                        for (int i = 0; i <= height; i++) {

                            double x = radius * Math.cos(locationY[0]);
                            double z = radius * Math.sin(locationY[0]);

                            player.getWorld().spawnParticle(Particle.REDSTONE, loc.add(x, locationY[0], z), amount, new Particle.DustOptions(color, particle_size));

                            locationY[0] = locationY[0] - height_period;
                        }

                        cancel();
                    }
                    ++i;
                }
            }.runTaskTimer(GoostServer.getPlugin(), 0L, period);
        }
    }


    public static void spawn_ball_particle(
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


}
