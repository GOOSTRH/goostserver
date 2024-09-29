package me.goost.goostserver.worldKillDesert;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static me.goost.goostserver.GoostServer.plugin;

public class worldBorder {
    public static void checkTime(){
        World world = Bukkit.getWorld("world");
        World worldKill = Bukkit.getWorld("worldKill");
        if (world != null && world.getTime() == 4000L) {
            setEscapepoint();
        }
    }

    public static void setEscapepoint(){
        WorldCreator killWorldc = new WorldCreator("worldKill");

        World killWorld = killWorldc.createWorld();
        assert killWorld != null;
        Location DustOffice2 = new Location(killWorld, -264, 161, 48);
        String DustOffice2Msg = "Helipad on the rooftop of Dust Office 2";

        Location MetroCity = new Location(killWorld, -129, 67, 171);
        String MetroCityMsg = "Metro City entrance in front of the Twin Towers";

        Location CargoBays = new Location(killWorld, -290, 56, 885);
        String CargoBaysMsg = "Between the North and South cargo bays";

        Location School = new Location(killWorld, -600, 56, 457);
        String SchoolMsg = "In front of school";

        Location HighWay = new Location(killWorld, 163, 66, 605);
        String HighWayMsg = "Highway above the water reserve";

        Location Hospital = new Location(killWorld, 4, 91, 479);
        String HospitalMsg = "Helipad on the rooftop of Hospital";


        Location[] EscapeLocations = {DustOffice2,MetroCity,CargoBays, School,HighWay,Hospital};
        String[]  EscapeLocationMessages = {DustOffice2Msg,MetroCityMsg,CargoBaysMsg, SchoolMsg,HighWayMsg,HospitalMsg};

        Random random = new Random();
        int rand = random.nextInt(6);
        Location location = EscapeLocations[rand];
        String locationMessage = EscapeLocationMessages[rand];
        worldEscape.TARGET_LOCATION = location;
        worldEscape.escapeAllow = true;
        sendMessageToWorld("worldKill",locationMessage);
        setWorldBorder("worldKill", 20.0, location.getX(), location.getZ(), (long) (12*50),1400,-193.41,457.43);
    }


    public static void sendMessageToWorld(String worldName,String message) {
        // Get the world by name
        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            Bukkit.getLogger().severe("World " + worldName + " not found!");
            return;
        }

        // Iterate through all online players
        for (Player player : Bukkit.getOnlinePlayers()) {
            // Check if the player is in the specified world
            if (player.getWorld().equals(world)) {
                // Send the message to the player
                player.sendMessage("World Border is now slowly shrinking!!!");
                player.sendMessage("Go to escape point before 0:00! -> " + ChatColor.BOLD + ChatColor.YELLOW + message);
            }
        }
    }
    public static void setWorldBorder(String worldName, double finalSize, double finalX, double finalZ, long time, double initialSize, double initialX, double initialZ) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            WorldBorder border = world.getWorldBorder();
            border.setCenter(initialX, initialZ);
            border.setSize(initialSize);

            double distanceX = finalX - initialX;
            double distanceZ = finalZ - initialZ;
            double sizeChange = finalSize - initialSize;

            double totalDistance = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ);
            double stepSize = 0.1; // Adjust this value for smoother movement
            int steps = (int) (totalDistance / stepSize);

            double stepX = distanceX / steps;
            double stepZ = distanceZ / steps;
            double stepSizeChange = sizeChange / steps;

            new BukkitRunnable() {
                int currentStep = 0;

                @Override
                public void run() {
                    if (currentStep >= steps) {
                        border.setCenter(finalX, finalZ);  // Ensure final position is exactly the target position
                        border.setSize(finalSize, time);  // Ensure final size is set exactly
                        cancel();  // Stop the task
                    } else {
                        double newX = initialX + stepX * currentStep;
                        double newZ = initialZ + stepZ * currentStep;
                        double newSize = initialSize + stepSizeChange * currentStep;

                        border.setCenter(newX, newZ);  // Move the border bit by bit
                        border.setSize(newSize);  // Adjust the size bit by bit

                        currentStep++;
                    }
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if(player.getWorld().getName().equals(worldName)){
                            player.setWorldBorder(border);
                        }
                    }
                }
            }.runTaskTimer(plugin, 0L, 1L);  // Run every tick (20 times per second)

            Bukkit.getLogger().info("World border for " + worldName + " is moving towards " + finalX + ", " + finalZ + " with a final size of " + finalSize);
        } else {
            Bukkit.getLogger().severe("World " + worldName + " not found.");
        }
    }

}
