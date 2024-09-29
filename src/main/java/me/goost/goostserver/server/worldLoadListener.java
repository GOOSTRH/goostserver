package me.goost.goostserver.server;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
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

            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.getWorld().getName().equals("world")){
                    player.setWorldBorder(border);
                }
            }
        }
        if (world.getName().equals("worldKill")) {
            WorldBorder border = world.getWorldBorder();
            border.setCenter(-193.41, 457.43);
            border.setSize(1400);
            Bukkit.getLogger().info("Worldborder for 'worldKill' initialized.");

            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.getWorld().getName().equals("worldKill")){
                    player.setWorldBorder(border);
                }
            }
        }
        debugWorldBorders();
    }

    public static void debugWorldBorders() {
        for (World world : Bukkit.getWorlds()) {
            WorldBorder border = world.getWorldBorder();
            Bukkit.getLogger().info("From worldloadListener -> \nWorld: " + world.getName() + "\nBorder Center: " + border.getCenter() + "\nSize: " + border.getSize());
        }
    }
}
