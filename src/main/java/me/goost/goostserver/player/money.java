package me.goost.goostserver.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class money implements Listener {
    private static HashMap<UUID, Integer> bank = new HashMap<>();

    public static Integer GetBank(UUID uuid){return bank.get(uuid);};

    public static void SetBank(UUID uuid, int num){
        bank.put(uuid,num);
    };

    public static void AddBank(UUID uuid, int num){
        bank.put(uuid, GetBank(uuid)+num);
    };

    public static void RemoveBank(UUID uuid, int num){
        bank.put(uuid, GetBank(uuid)-num);
    };

    public static void setMoneyForFirstTimer(UUID uuid){
        // set money for players that joined this server first time to 0 0
        money.SetBank(uuid,0);
    }

    public static void onplayerjoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CheckMoney(player);
    }

    public static void CheckMoney(Player player){
        if(!player.hasPlayedBefore()){
            money.setMoneyForFirstTimer(player.getUniqueId());
        }
        if(money.GetBank(player.getUniqueId()) == null){
            money.setMoneyForFirstTimer(player.getUniqueId());
        }
    }



}

