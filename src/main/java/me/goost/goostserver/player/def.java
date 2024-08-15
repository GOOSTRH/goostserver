package me.goost.goostserver.player;


import me.goost.goostserver.GoostServer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class def {
    private static HashMap<UUID, Integer> defencePoint = new HashMap<>();

    public static Integer GetDef(UUID uuid){return defencePoint.get(uuid);};

    public static void SetDef(UUID uuid, Integer num){defencePoint.put(uuid,num);};



}
