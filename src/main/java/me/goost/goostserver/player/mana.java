package me.goost.goostserver.player;


import me.goost.goostserver.GoostServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class mana {
    private static HashMap<UUID, Integer> mana = new HashMap<>();
    private static HashMap<UUID, Integer> manam = new HashMap<>();

    public static Integer get_mana(UUID uuid){return mana.get(uuid);};
    public static Integer get_manam(UUID uuid){return manam.get(uuid);};

    public static void set_mana(UUID uuid, int num){mana.put(uuid,num);};
    public static void set_manam(UUID uuid,int num){manam.put(uuid,num);};

    public static void add_mana(UUID uuid,int num){mana.replace(uuid,(get_mana(uuid)+num));};
    public static void remove_mana(UUID uuid,int num){mana.replace(uuid,(get_mana(uuid)-num));};

    public static void repeat(){
        Plugin plugin = GoostServer.plugin;
        long delay_time = 20L;
        new BukkitRunnable() {
            @Override
            public void run() {//run this code every other sec
                for (Player player : Bukkit.getOnlinePlayers()) {
                    UUID uuid = player.getUniqueId();
                    if(get_mana(uuid) + 3 <= get_manam(uuid)){
                        add_mana(uuid,3);
                    }else if(Math.abs( get_manam(uuid) - get_mana(uuid)) <= 3) {
                        set_mana(uuid, get_manam(uuid));
                    }
                }
            }
        }.runTaskTimer(plugin, 0 ,delay_time);
    }
}