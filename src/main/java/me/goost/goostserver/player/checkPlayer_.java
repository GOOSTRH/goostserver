package me.goost.goostserver.player;

import me.goost.goostserver.player.commands.Job;
import org.bukkit.entity.Player;

public class checkPlayer_ {
    public static void checkPlayer_(Player player){
        // check Player's class to give the right health
        // right after choosing class

        // ArcherClass		MagicClass		AssassinClass	    SwordClass		NightClass
        // archerClass		magicClass		assassinClass		swordClass		nightClass
        // 아처/archer        캐스터/Caster    어쌔신/Assassin      세이버/saber      마족/demon

        if(Job.Job.get(player.getUniqueId()) == null ){
            // no job
            setHealthStats(player,1.0,1.0,2.0);
            setDefStats(player,1);
            setManaStats(player,1,1);


        }else if(Job.Job.get(player.getUniqueId()).equals("magic") ){
            // Arcanist
            setHealthStats(player,600.0,600.0,20.0);
            setDefStats(player,250);
            setManaStats(player,800,800);

        }else if(Job.Job.get(player.getUniqueId()).equals("assassin") ){
            // Assassin
            setHealthStats(player,330.0,330.0,20.0);
            setDefStats(player,220);
            setManaStats(player,250,250);

        }else if(Job.Job.get(player.getUniqueId()).equals("sword") ){
            // Blademaster
            setHealthStats(player,250.0,250.0,20.0);
            setDefStats(player,150);
            setManaStats(player,150,150);

        }else if(Job.Job.get(player.getUniqueId()).equals("tank") ){
            // Paladin
            setHealthStats(player,500.0,500.0,20.0);
            setDefStats(player,350);
            setManaStats(player,500,500);

        }
    }

    private static void setHealthStats(Player player, double healthyValue, double healthValue, double healthScaleValue){
        health.healthy.put(player.getUniqueId(),healthyValue);
        health.health.put(player.getUniqueId(),healthValue);
        health.healthScale.put(player.getUniqueId(),healthScaleValue);
    }

    private static void setDefStats(Player player, int defValue){
        def.set_def(player.getUniqueId(),defValue);
    }

    private static void setManaStats(Player player, int manaValue, int manamValue){
        mana.setMana(player.getUniqueId(),manaValue);
        mana.setManam(player.getUniqueId(),manamValue);
    }

}
