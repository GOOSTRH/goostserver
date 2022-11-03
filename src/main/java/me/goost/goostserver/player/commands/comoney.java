package me.goost.goostserver.player.commands;

import me.goost.goostserver.player.money;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class comoney implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!sender.isOp()){sender.sendMessage(ChatColor.RED+"You don't have permission to access this command!"); return true;}

        if(args.length != 2){
            sender.sendMessage("/com bank/cash amount");
        }else{
            Player player = (Player) sender;
            switch (args[0]) {
                case "bank":
                    money.add_bank(player.getUniqueId(),Integer.parseInt(args[1]));
                    break;
                case "cash":
                    money.add_cash(player.getUniqueId(),Integer.parseInt(args[1]));
                    break;
            }
        }
        return true;//end of this thing
    }
}
