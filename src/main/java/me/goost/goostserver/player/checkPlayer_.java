package me.goost.goostserver.player;

import me.goost.goostserver.GoostServer;
import me.goost.goostserver.player.commands.Job;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class checkPlayer_ {

    static Double magicHP = 400.0;
    static Integer magicDef = 250;
    static Integer magicMP = 800;

    static Double assassinHP = 330.0;
    static Integer assassinDef = 220;
    static Integer assassinMP = 300;

    static Double blademasterHP = 250.0;
    static Integer blademasterDef = 300;
    static Integer blademasterMP = 150;

    static Double paladinHP = 500.0;
    static Integer paladinDef = 350;
    static Integer paladinMP = 400;



    public static void checkPlayer_(Player player){
        // check Player's class to give the right health
        // right after choosing class


        if(Job.Job.get(player.getUniqueId()) == null ){
            // no job
            setHealthStats(player,1.0,1.0,2.0);
            setDefStats(player,1);
            setManaStats(player,1,1);


        }else if(Job.Job.get(player.getUniqueId()).equals("magic") ){
            // Arcanist
            setHealthStats(player,magicHP,magicHP,20.0);
            setDefStats(player,magicDef);
            setManaStats(player,magicMP,magicMP);

        }else if(Job.Job.get(player.getUniqueId()).equals("assassin") ){
            // Assassin
            setHealthStats(player,assassinHP,assassinHP,20.0);
            setDefStats(player,assassinDef);
            setManaStats(player,assassinMP,assassinMP);

        }else if(Job.Job.get(player.getUniqueId()).equals("blademaster") ){
            // Blademaster
            setHealthStats(player, blademasterHP, blademasterHP,20.0);
            setDefStats(player, blademasterDef);
            setManaStats(player, blademasterMP, blademasterMP);

        }else if(Job.Job.get(player.getUniqueId()).equals("tank") ){
            // Paladin
            setHealthStats(player, paladinHP, paladinHP,20.0);
            setDefStats(player, paladinDef);
            setManaStats(player, paladinMP,  paladinMP);

        }
    }

    public static void checkPlayer_Constantly(Player player){
        if(Job.Job.get(player.getUniqueId()) == null ){
            // no job
            setHealthStatsConstantly(player,1.0,1.0,2.0);
            setDefStatsConstantly(player,1);
            setManaStatsConstantly(player,1,1);


        }else if(Job.Job.get(player.getUniqueId()).equals("magic") ){
            // Arcanist
            setHealthStatsConstantly(player,magicHP,magicHP,20.0);
            setDefStatsConstantly(player,magicDef);
            setManaStatsConstantly(player,magicMP,magicMP);

        }else if(Job.Job.get(player.getUniqueId()).equals("assassin") ){
            // Assassin
            setHealthStatsConstantly(player,assassinHP,assassinHP,20.0);
            setDefStatsConstantly(player,assassinDef);
            setManaStatsConstantly(player,assassinMP,assassinMP);

        }else if(Job.Job.get(player.getUniqueId()).equals("blademaster") ){
            // Blademaster
            setHealthStatsConstantly(player, blademasterHP, blademasterHP,20.0);
            setDefStatsConstantly(player, blademasterDef);
            setManaStatsConstantly(player, blademasterMP, blademasterMP);

        }else if(Job.Job.get(player.getUniqueId()).equals("tank") ){
            // Paladin
            setHealthStatsConstantly(player, paladinHP, paladinHP,20.0);
            setDefStatsConstantly(player, paladinDef);
            setManaStatsConstantly(player, paladinMP,  paladinMP);

        }
    }

    private static void setHealthStats(Player player, double maxHealthValue, double healthValue, double healthScaleValue){
        health.maxHealth.put(player.getUniqueId(),maxHealthValue);
        health.health.put(player.getUniqueId(),healthValue);
        health.healthScale.put(player.getUniqueId(),healthScaleValue);
    }

    private static void setDefStats(Player player, int defValue){
        def.SetDef(player.getUniqueId(),defValue);
    }

    private static void setManaStats(Player player, int manaValue, int maxmanaValue){
        mana.setMana(player.getUniqueId(),manaValue);
        mana.setMaxMana(player.getUniqueId(),maxmanaValue);
    }

    private static void setHealthStatsConstantly(Player player, double maxHealthValue, double healthValue, double healthScaleValue){

        double thisMaxHealthValue = maxHealthValue;

        if(player.getInventory().getChestplate() != null){ // if player have a chest plate
            ItemStack chestplate = player.getInventory().getChestplate();
            assert chestplate != null;
            ItemMeta meta = chestplate.getItemMeta();
            assert meta != null;
            PersistentDataContainer dataContainer = meta.getPersistentDataContainer();

            // if player have armor or weapon with +hp, add that to the manHealth and do it
            if(dataContainer.has(new NamespacedKey(GoostServer.getPlugin(), "hp"), PersistentDataType.DOUBLE)){
                Double additionalHP = dataContainer.get(new NamespacedKey(GoostServer.getPlugin(), "hp"), PersistentDataType.DOUBLE);
                thisMaxHealthValue = maxHealthValue + additionalHP;
            }
        }

        health.maxHealth.put(player.getUniqueId(),thisMaxHealthValue);
    }

    private static void setDefStatsConstantly(Player player, int defValue){

        int thisDefValue = defValue;

        if(player.getInventory().getChestplate() != null){
            ItemStack chestplate = player.getInventory().getChestplate();
            assert chestplate != null;
            ItemMeta meta = chestplate.getItemMeta();
            assert meta != null;
            PersistentDataContainer dataContainer = meta.getPersistentDataContainer();

            // if player have armor with +ar, add that to the def
            if(dataContainer.has(new NamespacedKey(GoostServer.getPlugin(), "ar"), PersistentDataType.INTEGER)){
                Integer additionalAR = dataContainer.get(new NamespacedKey(GoostServer.getPlugin(), "ar"), PersistentDataType.INTEGER);
                thisDefValue = defValue + additionalAR;
            }
        }

        def.SetDef(player.getUniqueId(),thisDefValue);
    }

    private static void setManaStatsConstantly(Player player, int manaValue, int maxmanaValue){

        int thisMaxManaValue = maxmanaValue;

        if(player.getInventory().getHelmet() != null){
            ItemStack helmet = player.getInventory().getHelmet();
            assert helmet != null;
            ItemMeta meta = helmet.getItemMeta();
            assert meta != null;
            PersistentDataContainer dataContainer = meta.getPersistentDataContainer();

            // if player have armor or weapon with +hp, add that to the manHealth and do it
            if(dataContainer.has(new NamespacedKey(GoostServer.getPlugin(), "mp"), PersistentDataType.FLOAT)){
                Float additionalMP = dataContainer.get(new NamespacedKey(GoostServer.getPlugin(), "mp"), PersistentDataType.FLOAT);
                thisMaxManaValue = (int) (thisMaxManaValue + additionalMP);
            }
        }

        mana.setMaxMana(player.getUniqueId(),thisMaxManaValue);
    }

}
