package me.goost.goostserver.Server;

import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class staffCommands implements CommandExecutor {

    private static HashMap<UUID, String> StoryLine = new HashMap<>();
    public static String GetStoryLine(UUID uuid){return StoryLine.get(uuid);};
    public static void SetStoryLine(UUID uuid, String str){StoryLine.put(uuid,str);};
    public static void PrintStroyLine(UUID uuid){
        System.out.println(GetStoryLine(uuid));
    }

    private static HashMap<UUID, Date> lastLogin = new HashMap<>();
    public static Date GetlastLoginDate(UUID uuid){return lastLogin.get(uuid);};
    public static void SetlastLoginDate(UUID uuid, Date date){lastLogin.put(uuid,date);};
    public static void PrintlastLoginDate(UUID uuid){
        System.out.println(GetlastLoginDate(uuid));
    }

    private static HashMap<UUID, Date> lastLogout = new HashMap<>();
    public static Date GetlastLogoutDate(UUID uuid){return lastLogout.get(uuid);};
    public static void SetlastLogoutDate(UUID uuid, Date date){lastLogout.put(uuid,date);};
    public static void PrintlastLogoutDate(UUID uuid){
        System.out.println(GetlastLogoutDate(uuid));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!sender.isOp()){sender.sendMessage(ChatColor.RED+"You don't have permission to access this command!"); return true;}

        if(args.length != 3){
            sender.sendMessage("/staff/stf StoryLine/lastLogin/lastLogout Player");
        }else{
            Player target = Bukkit.getPlayer(args[1]);
            Player player = (Player) sender;
            switch (args[0]) {
                case "StoryLine":
                    player.sendMessage(GetStoryLine(target.getUniqueId()));
                    PrintStroyLine(target.getUniqueId());
                    break;
                case "lastLogin":
                    player.sendMessage((ComponentLike) GetlastLoginDate(target.getUniqueId()));
                    PrintlastLoginDate(target.getUniqueId());
                    break;
                case "lastLogout":
                    player.sendMessage((ComponentLike) GetlastLogoutDate(target.getUniqueId()));
                    PrintlastLogoutDate(target.getUniqueId());
                    break;
            }
        }
        return true;
    }

}
