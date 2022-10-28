package me.goost.goostserver.player;

import java.util.HashMap;
import java.util.UUID;

public class money {
    private static HashMap<UUID, Integer> bank = new HashMap<>();
    private static HashMap<UUID, Integer> cash = new HashMap<>();

    public static Integer get_bank(UUID uuid){return bank.get(uuid);};
    public static Integer get_cash(UUID uuid){return cash.get(uuid);};

    public static void set_bank(UUID uuid, int num){bank.put(uuid,num);};
    public static void set_cash(UUID uuid, int num){cash.put(uuid,num);};

    public static void add_bank(UUID uuid, int num){bank.put(uuid,get_bank(uuid)+num);};
    public static void add_cash(UUID uuid, int num){cash.put(uuid,get_cash(uuid)+num);};

    public static void setmoneyforfirsttimer(UUID uuid){
        // set money for players that joined this server first time to 0 0
        money.set_bank(uuid,0);
        money.set_cash(uuid,0);
    }



}

