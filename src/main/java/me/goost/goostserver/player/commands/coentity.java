package me.goost.goostserver.player.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class coentity implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player player)){
            return true;
        }
        if(!player.isOp()){return false;} // if the player isn't OP return false

        if(args.length == 0){ // if there is no arguments return and tell instructions
            player.sendMessage("Usage: /coentity sz");
            return false;
        }

        Location location = player.getLocation();
        String customName = ChatColor.BLUE + "Special Zombie"; // Custom name with color

        switch (args[0]) {
            case "sz" -> {
                player.sendMessage("Spawned in Sepecial Zombie");
                if (location.getWorld() == null) {
                    return false;
                }

                // Spawn the zombie
                Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);

                // Set the custom name and make it visible
                zombie.setCustomName(customName);
                zombie.setCustomNameVisible(true);
            }
            default -> player.sendMessage("this command does not exist.");
        }

        return true;
    }
}