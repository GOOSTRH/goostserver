package me.goost.goostserver.player.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class level implements CommandExecutor {
    public static HashMap<UUID, Double> level = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;

        if(args[0].equals("check")){
            player.sendMessage(""+level.get(player.getUniqueId()));

        }else if(args[0].equals("set")){
            if (args[1] == null || args[1].equals("")) {
                player.sendMessage("wtf");
            }else if(Integer.parseInt( args[1]) >= 0){
                level.put(player.getUniqueId(),Double.parseDouble(args[1]));
            }
        }


        return true;
    }

}
