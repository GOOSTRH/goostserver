package me.goost.goostserver.player;

import me.goost.goostserver.scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class money implements Listener {
    private static HashMap<UUID, Integer> bank = new HashMap<>();
    private static HashMap<UUID, Integer> cash = new HashMap<>();

    public static Integer get_bank(UUID uuid){return bank.get(uuid);};
    public static Integer get_cash(UUID uuid){return cash.get(uuid);};

    public static void set_bank(UUID uuid, int num){
        bank.put(uuid,num);
        scoreboard.show();
    };
    public static void set_cash(UUID uuid, int num) {
        cash.put(uuid,num);
        scoreboard.show();
    };

    public static void add_bank(UUID uuid, int num){
        bank.put(uuid,get_bank(uuid)+num);
        scoreboard.show();
    };
    public static void add_cash(UUID uuid, int num){
        cash.put(uuid,get_cash(uuid)+num);
        scoreboard.show();
    };

    public static void setmoneyforfirsttimer(UUID uuid){
        // set money for players that joined this server first time to 0 0
        money.set_bank(uuid,0);
        money.set_cash(uuid,0);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(!player.hasPlayedBefore()){
            money.setmoneyforfirsttimer(player.getUniqueId());
        }
        if(money.get_bank(player.getUniqueId()) == null || money.get_cash(player.getUniqueId()) == null){
            money.setmoneyforfirsttimer(player.getUniqueId());
        }
    }



}

