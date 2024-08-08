package me.goost.goostserver.player.commands;

import me.goost.goostserver.SQLiteDB.Database;
import me.goost.goostserver.SQLiteDB.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class sethome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)){
            return true;
        }

        World playerCurrentWorld = player.getWorld();
        if ("worldKill".equals(playerCurrentWorld.getName())) {
            player.sendMessage("You cannot set home in the Raid zone.");
            return true;
        }

        Location homeLocation = player.getLocation();
        Double x = homeLocation.getX();
        Double y = homeLocation.getY();
        Double z = homeLocation.getZ();

        try {
            Location location = Database.findPlayerHomeByUUID(player.getUniqueId());
            if (location == null) { // if db does not contain the players info
                Database.setPlayerHome(player.getUniqueId(),homeLocation);
            } else {
                location = homeLocation;
                Database.setPlayerHome(player.getUniqueId(),location);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        player.sendMessage(ChatColor.BLUE+"Home Set at x="+x+" y="+y+" z="+z+"!");

        return false;
    }
}
