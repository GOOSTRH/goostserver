package me.goost.goostserver.player;

import me.lemonypancakes.bukkit.common.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class show_stat implements Listener {
    public static void showstattick() {
        for (Player player : Bukkit.getOnlinePlayers()) { // loop through all players
            showstat(player); // show stat
        }
    }

    public static void showstat(Player player){

        ActionBarAPI.sendMessage(player,
                ChatColor.RED +
                        String.valueOf(Math.round(health.health.get(player.getUniqueId()))) +
                        "/" +
                        Math.round((health.healthy.get(player.getUniqueId())))+
                        "❤"+"        "+
                        ChatColor.GREEN+
                        def.get_def(player.getUniqueId()) +"🛡          "+
                        ChatColor.BLUE+
                        mana.get_mana(player.getUniqueId())+"/"+mana.get_manam(player.getUniqueId())+" ⁂"

        );
    }
}
