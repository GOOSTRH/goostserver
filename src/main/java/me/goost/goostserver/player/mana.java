package me.goost.goostserver.player;


import me.goost.goostserver.GoostServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class mana {
    private static HashMap<UUID, Float> mana = new HashMap<>();
    private static HashMap<UUID, Float> manam = new HashMap<>();

    public static Float get_mana(UUID uuid){return mana.get(uuid);};
    public static Float get_manam(UUID uuid){return manam.get(uuid);};

    public static void set_mana(UUID uuid, float num){mana.put(uuid,num);};
    public static void set_manam(UUID uuid,float num){manam.put(uuid,num);};

    public static void add_mana(UUID uuid,float num){mana.replace(uuid,(get_mana(uuid)+num));};
    public static void remove_mana(UUID uuid,float num){mana.replace(uuid,(get_mana(uuid)-num));};

    public static void repeat(){
        Plugin plugin = GoostServer.plugin;
        long delay_time = 10L;
        float amount = 2.5f;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(GoostServer.getPlugin(), new Runnable() {//repeat
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    UUID uuid = player.getUniqueId();
                    if(get_mana(uuid) + amount <= get_manam(uuid)){
                        add_mana(uuid, amount);
                        show_stat.showstat(player);
                    }else{
                        set_mana(uuid, get_manam(uuid));
                        show_stat.showstat(player);
                    }
                }
            }
        }, 0, delay_time);

    }

    public static void mana_on_tick(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            if(get_mana(uuid) > get_manam(uuid)){
                set_mana(uuid,get_manam(uuid));
            }
        }
    }
}