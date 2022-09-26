package me.goost.goostserver.player;


import java.util.HashMap;
import java.util.UUID;

public class def {
    private static HashMap<UUID, Integer> armor = new HashMap<>();

    public static Integer get_def(UUID uuid){return armor.get(uuid);};

    public static void set_def(UUID uuid, int num){armor.put(uuid,num);};
}
