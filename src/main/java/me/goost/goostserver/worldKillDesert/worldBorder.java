package me.goost.goostserver.worldKillDesert;

import org.bukkit.*;
import org.bukkit.entity.Player;

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
    public static void setWorldBorder(String worldName, double size, double x, double z, Long time, double initialSize, double initialx, double initialz) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            //-193.41 457.43
            //-264 160 48
            WorldBorder border = world.getWorldBorder();
            border.setCenter(initialx,initialz);
            border.setSize(initialSize);

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                border.setCenter(x, z); // Update to the new center
                border.setSize(size, time); // Set the size of the world border over time
            }, 20L); // Wait for 1 second (20 ticks) before applying the changes
            Bukkit.getLogger().info("World border for " + worldName + " set to " + size);
        } else {
            Bukkit.getLogger().severe("World " + worldName + " not found.");
        }
    }
}
