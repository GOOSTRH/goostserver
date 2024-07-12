package me.goost.goostserver.player;


import me.lemonypancakes.bukkit.api.actionbar.ActionBarAPI;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;


public class ShowStats implements Listener {
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
                        " [HP]"+"             "+

                        ChatColor.BLUE+
                        mana.getMana(player.getUniqueId())+"/"+mana.getManam(player.getUniqueId())+" [MP]"

        );



/* Armor
        ChatColor.GREEN+
                def.get_def(Player.getUniqueId()) +"[AR]          "+
  */
    }
}
