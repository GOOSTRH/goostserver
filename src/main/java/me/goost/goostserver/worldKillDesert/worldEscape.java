package me.goost.goostserver.worldKillDesert;

import me.goost.goostserver.GoostServer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class worldEscape {

    public static Location TARGET_LOCATION = null;

    private static final double TOLERANCE = 3; // Tolerance for checking location (in blocks)
    private static final int TIME_LIMIT_TICKS = 300; // Time limit in ticks (20 ticks = 1 second)

    private static final Map<Player, Long> playerLocationTimeMap = new HashMap<>();
    private static final Map<Player, BukkitRunnable> playerSoundTaskMap = new HashMap<>();

    public static boolean escapeAllow = false;

    public static void startLocationCheck() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!escapeAllow) {return;}
            if (TARGET_LOCATION == null) {return;}
            if (isPlayerAtCoordinates(player, TARGET_LOCATION)) {
                long currentTime = System.currentTimeMillis();
                long arrivalTime = playerLocationTimeMap.getOrDefault(player, currentTime);

                if (!playerLocationTimeMap.containsKey(player)) {
                    playerLocationTimeMap.put(player, currentTime);
                    arrivalTime = currentTime;
                }

                double timeSpent = (currentTime - arrivalTime) / 1000.0; // Convert to seconds
                double timeRemaining = (TIME_LIMIT_TICKS / 20.0) - timeSpent; // Convert ticks to seconds

                if (timeRemaining > 0) {
                    String title = String.format(ChatColor.GREEN+ "%.1f", timeRemaining) + "s";
                    player.sendTitle(title, ChatColor.GRAY + "Escaping...", 0, 20, 10);

                    // Start the sound task if not already started
                    if (!playerSoundTaskMap.containsKey(player)) {
                        BukkitRunnable soundTask = new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_HEARTBEAT, 0.4f, 1);
                            }
                        };
                        soundTask.runTaskTimer(GoostServer.getPlugin(), 0L, 15L); // Run every 0.3 seconds (6 ticks)
                        playerSoundTaskMap.put(player, soundTask);
                    }
                } else {
                    teleportToSpawn(player);
                    playerLocationTimeMap.remove(player);

                    // Cancel the sound task if it exists
                    BukkitRunnable soundTask = playerSoundTaskMap.remove(player);
                    if (soundTask != null) {
                        soundTask.cancel();
                    }
                }
            } else {
                playerLocationTimeMap.remove(player); // Remove player from map if they are not at the target location

                // Cancel the sound task if it exists
                BukkitRunnable soundTask = playerSoundTaskMap.remove(player);
                if (soundTask != null) {
                    soundTask.cancel();
                }
            }
        }
    }

    private static boolean isPlayerAtCoordinates(Player player, Location targetLocation) {
        Location location = player.getLocation();
        return Objects.equals(location.getWorld(), targetLocation.getWorld()) &&
                Math.abs(location.getX() - targetLocation.getX()) <= worldEscape.TOLERANCE &&
                Math.abs(location.getY() - targetLocation.getY()) <= worldEscape.TOLERANCE &&
                Math.abs(location.getZ() - targetLocation.getZ()) <= worldEscape.TOLERANCE;
    }

    private static void teleportToSpawn(Player player) {
        WorldCreator c = new WorldCreator("world");
        World world = c.createWorld();
        Location worldLocation = new Location(world, -19, 161, 51,180,0);
        player.teleport(worldLocation);
        player.sendMessage("Successfully Escaped!");
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.5F, 1.0F);
    }
}