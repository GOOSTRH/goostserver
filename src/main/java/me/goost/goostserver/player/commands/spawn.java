package me.goost.goostserver.player.commands;

import me.goost.goostserver.GoostServer;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class spawn implements CommandExecutor {

    Location Spawn = new Location(Bukkit.getWorld("world"), -19, 161, 51,180,0);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        GoostServer.getPlugin().getConfig().options().copyDefaults(true);
        new WorldCreator(Objects.requireNonNull(GoostServer.getPlugin().getConfig().getString("/world", "world"))).createWorld();

        if(!(sender instanceof Player player)){
            return true;
        }

        if (player.getWorld()==Spawn.getWorld()){
            WorldCreator c = new WorldCreator("world");
            World world = c.createWorld();
            Location worldLocation = new Location(world, -19, 161, 51,180,0);
            player.teleport(worldLocation);

        }else{
            player.sendMessage(ChatColor.RED + "You can't use /spawn in raid zone!");
        }
        return false;
    }
}
