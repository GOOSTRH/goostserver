package me.goost.goostserver.player;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class level {
    private static HashMap<UUID, Double> lev = new HashMap<>();

    public static Double GetLevel(UUID uuid){return lev.get(uuid);};

    public static void SetLevel(UUID uuid, double num){lev.put(uuid,num);};

    public static int get_percentage(Player player){
        int ret = 56;

        return ret;
    }












    public static void setlevelforfirsttimer(UUID uuid){
        // set money for players that joined this server first time to 0 0
        level.SetLevel(uuid,0);
    }

    public static void onplayerjoin(PlayerJoinEvent event){
        check_player_level(event.getPlayer());
    }

    public static void check_player_level(Player player){
        if(!player.hasPlayedBefore()){
            level.setlevelforfirsttimer(player.getUniqueId());
        }
        if(level.GetLevel(player.getUniqueId()) == null || level.GetLevel(player.getUniqueId()) == null){
            level.setlevelforfirsttimer(player.getUniqueId());
        }
    }

}

