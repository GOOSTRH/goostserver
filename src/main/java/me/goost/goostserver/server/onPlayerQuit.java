package me.goost.goostserver.server;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class onPlayerQuit implements Listener {

    @EventHandler
    public void onplayerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        String targetWorldName = "worldKill";
        if(p.getWorld().getName().equals(targetWorldName)){
            for(ItemStack i : p.getInventory()){
                if(i != null){
                    p.getWorld().dropItemNaturally(p.getLocation(), i);
                    p.getInventory().remove(i);
                }
            }
        }
    }
}
