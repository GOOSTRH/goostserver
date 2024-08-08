package me.goost.goostserver.server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class PlayerLocation {
    static World world;

    public static void spawnLocation(Player player){
        world = Bukkit.getWorld("world");
        if (world == null) {
            Bukkit.getLogger().severe("World 'world' not found!");
            return;
        }
        Location Spawn = new Location(world, -19, 161, 51,180,0);
        player.teleport(Spawn);
    }
}
