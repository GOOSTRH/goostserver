package me.goost.goostserver.worlds;

import me.goost.goostserver.GoostServer;
import me.goost.goostserver.worldKillDesert.worldEscape;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class updateMap {

    public static void checkTime() {
        World world = Bukkit.getWorld("world");
        if (world != null && world.getTime() == 18000L) {
            worldEscape.escapeAllow = false;
            Bukkit.getLogger().info("Replacing Old Map with a New Map!");
            runUpdateMap("worldKill");
            Bukkit.getLogger().info("Done Replacing Old Map with a New Map!");
        }
    }

    public static void runUpdateMap(String worldName) {
        Plugin plugin = GoostServer.getPlugin();
        File sourceDir = getSourceFile();
        if (sourceDir.exists() && sourceDir.isDirectory()) {
            new WorldReplacementTask(worldName, sourceDir, plugin).runTaskAsynchronously(plugin);
        } else {
            Bukkit.getLogger().severe("Source directory does not exist or is not a directory: " + sourceDir.getAbsolutePath());
        }
    }

    private static File getSourceFile() {
        return new File(new File(Bukkit.getWorldContainer().getParent(), "WorldBackUp/worldKill").getAbsolutePath());
    }

    private static class WorldReplacementTask extends org.bukkit.scheduler.BukkitRunnable {

        private final String worldName;
        private final File backupWorldDir;
        private final JavaPlugin plugin;

        public WorldReplacementTask(String worldName, File backupWorldDir, Plugin plugin) {
            this.worldName = worldName;
            this.backupWorldDir = backupWorldDir;
            this.plugin = (JavaPlugin) plugin;
        }

        @Override
        public void run() {
            try {
                File existingWorldDir = new File(Bukkit.getWorldContainer(), worldName);

                // Unload the existing world
                Bukkit.getScheduler().runTask(plugin, () -> unloadWorld(worldName));

                // Wait a short period to ensure the world is unloaded
                try {
                    Thread.sleep(2000); // 2 seconds
                } catch (InterruptedException e) {
                    Bukkit.getLogger().severe("Interrupted while waiting to delete world: " + e.getMessage());
                }

                // Delete the existing world directory
                deleteWorld(existingWorldDir);

                // Copy the new world directory
                copyWorld(backupWorldDir, existingWorldDir);

                // Load the new world
                Bukkit.getScheduler().runTask(plugin, () -> loadWorld(worldName));

                Bukkit.getLogger().info("World replaced successfully!");

            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to replace world: " + e.getMessage());
                e.printStackTrace();
            }
        }

        private void deleteWorld(File path) {
            if (path.exists()) {
                File[] files = path.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            deleteWorld(file);
                        } else {
                            if (!file.delete()) {
                                Bukkit.getLogger().warning("Failed to delete file: " + file.getAbsolutePath());
                            }
                        }
                    }
                }
                if (!path.delete()) {
                    Bukkit.getLogger().warning("Failed to delete directory: " + path.getAbsolutePath());
                }
            }
        }

        private void copyWorld(File source, File target) throws IOException {
            if (!source.isDirectory()) {
                throw new IllegalArgumentException("Source must be a directory");
            }
            if (!target.exists()) {
                if (!target.mkdirs()) {
                    throw new IOException("Failed to create target directory: " + target.getAbsolutePath());
                }
            }
            for (String child : Objects.requireNonNull(source.list())) {
                File srcFile = new File(source, child);
                File destFile = new File(target, child);
                if (srcFile.isDirectory()) {
                    copyWorld(srcFile, destFile);
                } else {
                    Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }

        private void unloadWorld(String worldName) {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                World targetWorld = Bukkit.getWorld("world");
                Location targetLocation = new Location(targetWorld, -19, 161, 51, 180, 0);

                for (Player player : world.getPlayers()) {
                    player.teleport(targetLocation);
                    for (ItemStack i : player.getInventory()) {
                        if (i != null && !i.getType().isAir()) {
                            player.getWorld().dropItemNaturally(player.getLocation(), i);
                            player.getInventory().remove(i);
                        }
                    }
                }

                Bukkit.unloadWorld(world, false);
            }
        }

        private void loadWorld(String worldName) {
            WorldCreator worldCreator = new WorldCreator(worldName);
            Bukkit.createWorld(worldCreator);

            World killWorld = Bukkit.getWorld("worldKill");
            World world = Bukkit.getWorld("world");
            assert world != null;
            assert killWorld != null;
            killWorld.setTime(world.getTime());
        }
    }
}
