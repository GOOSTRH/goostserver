package me.goost.goostserver.player.commands;

import me.goost.goostserver.SQLiteDB.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.UUID;

import static me.goost.goostserver.GoostServer.plugin;

public class home implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        World playerCurrentWorld = player.getWorld();
        if ("worldKill".equals(playerCurrentWorld.getName())) {
            player.sendMessage("You cannot teleport home while in the Raid zone.");
            return true;
        }

        UUID uuid = player.getUniqueId();
        Location homelocation = null;
        Location initialLocation = player.getLocation().clone();

        try {
            homelocation = Database.findPlayerHomeByUUID(uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assert homelocation != null;
        Double x = homelocation.getX();
        Double y = homelocation.getY();
        Double z = homelocation.getZ();

        player.sendMessage(ChatColor.YELLOW + "Don't move! Teleporting in 3 seconds...");


        Location finalHomelocation = homelocation;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getLocation().distance(initialLocation) > 0.1) {
                    player.sendMessage("Teleportation cancelled because you moved!");
                    return;
                }
                player.teleport(finalHomelocation);
                player.sendMessage("Teleported to home!");
            }
        }.runTaskLater(plugin, 60);
        return false;
    }
}
