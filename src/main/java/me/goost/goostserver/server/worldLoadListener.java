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
            border.setCenter(0,0);
            border.setSize(2000);
            Bukkit.getLogger().info("Worldborder for wold initialized.");
            Bukkit.getLogger().info("World name = " + world.getName());
        }
    }
}
