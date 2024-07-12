package me.goost.goostserver.player.commands;


import me.goost.goostserver.player.level;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class colevel implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!sender.isOp()){sender.sendMessage(ChatColor.RED+"You don't have permission to access this command!"); return true;}
        if(!(sender instanceof Player)){return true;}

        Player player = (Player) sender;

        if(args[0].equals("check")){player.sendMessage(""+level.getLevel(player.getUniqueId()));} // check my level

        if(args[0].equals("set")){
            if (args[1] == null || args[1].equals("")) {
                player.sendMessage("/level ");
            }else if(Integer.parseInt( args[1]) >= 0){ // if the amount is bigger than or equal to 0 then set the level to that amount
                level.setLevel(player.getUniqueId(),Integer.parseInt(args[1]));
            }
        }

        return true;//end of this thing
    }

}
