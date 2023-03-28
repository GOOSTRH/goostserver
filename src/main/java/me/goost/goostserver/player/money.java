package me.goost.goostserver.player;

import me.goost.goostserver.Server.scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class money implements Listener {
    private static HashMap<UUID, Integer> bank = new HashMap<>();
    private static HashMap<UUID, Integer> cash = new HashMap<>();

    public static Integer GetBank(UUID uuid){return bank.get(uuid);};
    public static Integer GetCash(UUID uuid){return cash.get(uuid);};

    public static void SetBank(UUID uuid, int num){
        bank.put(uuid,num);
        scoreboard.show();
    };
    public static void SetCash(UUID uuid, int num) {
        cash.put(uuid,num);
        scoreboard.show();
    };

    public static void AddBank(UUID uuid, int num){
        bank.put(uuid, GetBank(uuid)+num);
        scoreboard.show();
    };
    public static void AddCash(UUID uuid, int num){
        cash.put(uuid, GetCash(uuid)+num);
        scoreboard.show();
    };

    public static void setmoneyforfirsttimer(UUID uuid){
        // set money for players that joined this server first time to 0 0
        money.SetBank(uuid,0);
        money.SetCash(uuid,0);
    }

    public static void onplayerjoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CheckMoney(player);
    }

    public static void CheckMoney(Player player){
        if(!player.hasPlayedBefore()){
            money.setmoneyforfirsttimer(player.getUniqueId());
        }
        if(money.GetBank(player.getUniqueId()) == null || money.GetCash(player.getUniqueId()) == null){
            money.setmoneyforfirsttimer(player.getUniqueId());
        }
    }



}

