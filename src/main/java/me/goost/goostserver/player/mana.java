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

    public static Float getMana(UUID uuid){return mana.get(uuid);};
    public static Float getManam(UUID uuid){return manam.get(uuid);};

    public static void setMana(UUID uuid, float num){mana.put(uuid,num);};
    public static void setManam(UUID uuid, float num){manam.put(uuid,num);};

    public static void addMana(UUID uuid, float num){mana.replace(uuid,(getMana(uuid)+num));};
    public static void removeMana(UUID uuid, float num){mana.replace(uuid,(getMana(uuid)-num));};

    public static void repeat(){
        Plugin plugin = GoostServer.plugin;
        long delay_time = 10L;
        float amount = 2.5f;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(GoostServer.getPlugin(), new Runnable() {//repeat
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    UUID uuid = player.getUniqueId();
                    if(getMana(uuid) + amount <= getManam(uuid)){
                        addMana(uuid, amount);
                        ShowStats.showstat(player);
                    }else{
                        setMana(uuid, getManam(uuid));
                        ShowStats.showstat(player);
                    }
                }
            }
        }, 0, delay_time);

    }

    public static void manaOnTick(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            if(getMana(uuid) > getManam(uuid)){
                setMana(uuid, getManam(uuid));
            }
        }
    }
}