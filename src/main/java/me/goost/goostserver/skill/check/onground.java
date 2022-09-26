package me.goost.goostserver.skill.check;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class onground {
    private static HashMap<UUID, Boolean> ground_check = new HashMap<>();

    public static void ground_check(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(ground_check.containsKey(player.getUniqueId())){
                if(!ground_check.get(player.getUniqueId())){
                    if(player.isOnGround()){
                        ground_check.replace(player.getUniqueId(),player.isOnGround());
                    }
                }
            }else{
                ground_check.put(player.getUniqueId(),player.isOnGround());
            }
        }
    }

    public static boolean groundcheck(UUID uuid){return ground_check.get(uuid);}
    public static void groundreplace(UUID uuid,boolean bool){ground_check.replace(uuid,bool);}

}
