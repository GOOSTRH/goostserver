package me.goost.goostserver.player.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.goost.goostserver.player.ChooseJob;

public class showjob implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (ChooseJob.player_.get(player.getUniqueId()) == null
                    || !ChooseJob.player_.get(player.getUniqueId())
                    || ChooseJob.choosingJob.get(player.getUniqueId()) == Boolean.TRUE){
                ChooseJob.choose_class((Player) sender);
            }else{
                player.sendMessage("You already have a Job!");
            }

        }
        return true;
    }
}
