package me.goost.goostserver.player;

import me.goost.goostserver.player.commands.Job;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;



public class health implements Listener{

    static HashMap<UUID, Double> healthy = new HashMap<>();
    static HashMap<UUID, Double> health = new HashMap<>();

    //static HashMap<UUID, Double> addition_health_effect = new HashMap<>();
    //static HashMap<UUID, Double> addition_health_Item = new HashMap<>();

    static HashMap<UUID, Double> healthscale = new HashMap<>();



    public static void check_player_health_always(){
        for (Player player : Bukkit.getOnlinePlayers()) { // loop through all players

            if (healthy.get(player.getUniqueId()) == null) return; // check if player has a max health value , if non return

            double extra_health_number = 20.0; // value that will be set as extra health value

            for (double i = healthy.get(player.getUniqueId()); i >= 101; i -= 100) {
                extra_health_number += 2.0; // extra_health_number = extra heart ex. if 101-200 = 11 heart
            }

            if ( healthy.get(player.getUniqueId()) < 100 ){ // if the player's max health is lower than 100
                extra_health_number = (healthy.get(player.getUniqueId())/5);
            }

            when_player_dies(player);
            // if the player dies

            when_player_alive(player, extra_health_number);
            // when player is alive
        }
    }

    private static void when_player_dies(Player player){
        if (health.get(player.getUniqueId()) < 1.0 ){
            player_respawn(player);
        }
    }

    private static void when_player_alive(Player player,double extra_health_number){
        if (health.get(player.getUniqueId()) >= 1){
            healthscale.replace(player.getUniqueId(), extra_health_number);
            player.setHealthScale(extra_health_number);
            player.setHealth((health.get(player.getUniqueId()) / healthy.get(player.getUniqueId())) * 20);
        }
    }


    public static void player_respawn(Player player){

        for (ItemStack item : player.getInventory().getContents()) {
            // go through every slot in player's inventory

            if (item != null) return;
            //if the item is not null

            assert false;
            if (item.getType() == Material.TOTEM_OF_UNDYING) {
                // if player has a TOTEM_OF_UNDYING in the slot ↓

                player.sendMessage("DIED ( saved inventory , used one totem of undying");
            }
        }

        player.sendMessage("너죽음");
        player.teleport(new Location(Bukkit.getWorld("World"), -59,67,8));
        health.replace(player.getUniqueId(),healthy.get(player.getUniqueId()));
    }


    public static void check_player(Player player){
        // check player's class to give the right health
        // right after choosing class
        //궁수/archer		마법사/magician		다크엘프/dark_elf		검사/sword_man
        if(Job.Job.get(player.getUniqueId()) == null ){

            healthy.put(player.getUniqueId(),1.0);
            health.put(player.getUniqueId(),1.0);
            healthscale.put(player.getUniqueId(),2.0);

            def.set_def(player.getUniqueId(),1);
            mana.set_mana(player.getUniqueId(),1);
            mana.set_manam(player.getUniqueId(),1);

        }else if(Job.Job.get(player.getUniqueId()).equals("archer") ){

            healthy.put(player.getUniqueId(),100.0);
            health.put(player.getUniqueId(),100.0);
            healthscale.put(player.getUniqueId(),20.0);

            def.set_def(player.getUniqueId(),100);
            mana.set_mana(player.getUniqueId(),200);
            mana.set_manam(player.getUniqueId(),200);

        }else if(Job.Job.get(player.getUniqueId()).equals("dragon") ){

            healthy.put(player.getUniqueId(),600.0);
            health.put(player.getUniqueId(),600.0);
            healthscale.put(player.getUniqueId(),20.0);

            def.set_def(player.getUniqueId(),400);
            mana.set_mana(player.getUniqueId(),800);
            mana.set_manam(player.getUniqueId(),800);

        }else if(Job.Job.get(player.getUniqueId()).equals("dark_elf") ){

            healthy.put(player.getUniqueId(),200.0);
            health.put(player.getUniqueId(),200.0);
            healthscale.put(player.getUniqueId(),20.0);

            def.set_def(player.getUniqueId(),150);
            mana.set_mana(player.getUniqueId(),800);
            mana.set_manam(player.getUniqueId(),800);

        }else if(Job.Job.get(player.getUniqueId()).equals("sword_man") ){

            healthy.put(player.getUniqueId(),250.0);
            health.put(player.getUniqueId(),250.0);
            healthscale.put(player.getUniqueId(),20.0);

            def.set_def(player.getUniqueId(),150);
            mana.set_mana(player.getUniqueId(),150);
            mana.set_manam(player.getUniqueId(),800);

        }else if(Job.Job.get(player.getUniqueId()).equals("demon") ){

            healthy.put(player.getUniqueId(),500.0);
            health.put(player.getUniqueId(),500.0);
            healthscale.put(player.getUniqueId(),20.0);

            def.set_def(player.getUniqueId(),350);
            mana.set_mana(player.getUniqueId(),500);
            mana.set_manam(player.getUniqueId(),500);

        }
    }


    @EventHandler
    public void playerheal(EntityRegainHealthEvent event){
        Entity e = event.getEntity();
        if(e instanceof Player){
            // if entity is a player
            Player player = (Player)e;
            double heal = event.getAmount();
            if(health.get(player.getUniqueId())+heal > healthy.get(player.getUniqueId())){
                health.replace(player.getUniqueId(),healthy.get(player.getUniqueId()));
            }else{
                health.replace(player.getUniqueId(),health.get(player.getUniqueId())+heal);
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onplayerjoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();
        check_player(player);
    }

}













