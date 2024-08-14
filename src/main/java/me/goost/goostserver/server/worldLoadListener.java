package me.goost.goostserver.server;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class worldLoadListener implements Listener {
    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        World world = event.getWorld();
        if (world.getName().equals("world")) {
            WorldBorder border = world.getWorldBorder();
            border.setCenter(0, 0);
            border.setSize(2000);
            Bukkit.getLogger().info("Worldborder for 'world' initialized.");
        }
        if (world.getName().equals("worldKill")) {
            WorldBorder border = world.getWorldBorder();
            border.setCenter(-193.41, 457.43);
            border.setSize(1400);
            Bukkit.getLogger().info("Worldborder for 'worldKill' initialized.");
        }
        debugWorldBorders();
    }

    public static void debugWorldBorders() {
        for (World world : Bukkit.getWorlds()) {
            WorldBorder border = world.getWorldBorder();
            Bukkit.getLogger().info("World: " + world.getName() + " - Border Center: " + border.getCenter() + " - Size: " + border.getSize());
        }
    }
}
