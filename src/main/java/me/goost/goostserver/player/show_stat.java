package me.goost.goostserver.player;

import me.goost.goostserver.player.commands.Job;

import me.lemonypancakes.bukkit.common.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import static me.goost.goostserver.player.health.*;

public class show_stat implements Listener {
    public static void showstattick() {
        for (Player player : Bukkit.getOnlinePlayers()) { // loop through all players
            if(healthy.get(player.getUniqueId()) != null) return; // check if player has a max health

            showstat(player); // show stat

            double number = 20.0;

            for (double i = healthy.get(player.getUniqueId()); i >= 101; i -= 100) {
                number += 2;// number = extra heart ex. if 101-200 = 11 heart
            }
            //showing the health bar

            if (healthy.get(player.getUniqueId()) >= 1000.0) { // if player's health is more that 1000.0

                if (health.get(player.getUniqueId()) <= 0) return;
                // if player dies ↓

                    player.setHealthScale(40);
                    playerrespawn(player);

                if (!(health.get(player.getUniqueId()) <= 0)) return;
                // while player is not dead ↓

                    player.setHealthScale(40);
                    player.setHealth((health.get(player.getUniqueId()) / healthy.get(player.getUniqueId())) * 20);

            } else if (healthy.get(player.getUniqueId()) < 1000) {
                // if player's health is less than 1000.0

                if (health.get(player.getUniqueId()) <= 0) return;
                // if player dies ↓

                for (ItemStack item : player.getInventory().getContents()) {
                    // go through every slot in player's inventory

                    if (item != null) return;
                    // if the slot is not empty ↓

                    if (item.getType() == Material.TOTEM_OF_UNDYING) return;
                    // if player has a TOTEM_OF_UNDYING in the slot ↓

                    healthscale.replace(player.getUniqueId(), number);
                    player.setHealthScale(healthscale.get(player.getUniqueId()));
                    playerrespawn(player);
                    player.sendMessage("DIED ( saved inventory");

                }

                healthscale.replace(player.getUniqueId(), number);
                player.setHealthScale(healthscale.get(player.getUniqueId()));
                playerrespawn(player);

                if (!(health.get(player.getUniqueId()) <= 0)) return;
                // while player is not dead ↓

                healthscale.replace(player.getUniqueId(), number);
                player.setHealthScale(healthscale.get(player.getUniqueId()));
                player.setHealth((health.get(player.getUniqueId()) / healthy.get(player.getUniqueId())) * 20);

            } else if (healthy.get(player.getUniqueId()) < 100) {
                // if player's max hp is lower than 100

                healthscale.replace(player.getUniqueId(), number);
                player.setHealthScale(healthscale.get(player.getUniqueId()));
                player.setHealth((health.get(player.getUniqueId()) / healthy.get(player.getUniqueId())) * 20);
            }

        }
    }

    public static void showstat(Player player){
        ActionBarAPI.sendMessage(player,
                ChatColor.RED +
                        String.valueOf(Math.round(health.get(player.getUniqueId()))) +
                        "/" +
                        (healthy.get(player.getUniqueId()))+
                        "❤"+"       "+
                        ChatColor.GREEN+
                        def.get_def(player.getUniqueId()) +"🛡       "+
                        ChatColor.BLUE+
                        mana.get_mana(player.getUniqueId())+"/"+mana.get_manam(player.getUniqueId())+" ⁂"

        );
    }

    @EventHandler
    public void onplayerjoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();

        check_player(player);
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
            mana.set_mana(player.getUniqueId(),250);
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


}
