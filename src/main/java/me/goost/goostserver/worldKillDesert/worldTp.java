package me.goost.goostserver.worldKillDesert;

import me.goost.goostserver.GoostServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class worldTp implements CommandExecutor {

    WorldCreator killWorldc = new WorldCreator("worldKill");
    World killWorld = killWorldc.createWorld();

    Location TwinCondo = new Location(killWorld, -308 ,200 ,350);
    Location OG = new Location(killWorld, 18, 200, 2);
    Location BKomachi = new Location(killWorld, -77, 200, 399);
    Location Bridge = new Location(killWorld, 158, 200, 604);
    Location House = new Location(killWorld, -102, 200, 783);
    Location Storage = new Location(killWorld, -463, 200, 708);
    Location Castle = new Location(killWorld, -677, 200, 774);
    Location School = new Location(killWorld, -610, 200, 460);
    Location Building = new Location(killWorld, -373, 200, 260);

    Location[] KillLocations = {TwinCondo,OG,BKomachi,Bridge,House,Storage,School,Castle,Building};

    Location Spawn = new Location(Bukkit.getWorld("world"), -19, 161, 51,180,0);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        GoostServer.getPlugin().getConfig().options().copyDefaults(true);
        new WorldCreator(Objects.requireNonNull(GoostServer.getPlugin().getConfig().getString("/worldKill","worldKill"))).createWorld();
        new WorldCreator(Objects.requireNonNull(GoostServer.getPlugin().getConfig().getString("/world", "world"))).createWorld();

        if(!(sender instanceof Player player)){
            return true;
        }

        if(args.length == 0){ // if there is no arguments return and tell instructions
            player.sendMessage("Usage: teleport to another world");
            return false;
        }

        if(args[0].equals("KillZoneTeleport")){

            Random random = new Random();
            Location location = KillLocations[random.nextInt(9)];
            player.teleport(location);

        }else if(args[0].equals("spawn")){

            WorldCreator c = new WorldCreator("world");
            World world = c.createWorld();
            Location worldLocation = new Location(world, -19, 161, 51,180,0);
            player.teleport(worldLocation);
        }
        return false;
    }
}
