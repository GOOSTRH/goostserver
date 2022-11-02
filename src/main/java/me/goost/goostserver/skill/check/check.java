package me.goost.goostserver.skill.check;

import me.goost.goostserver.player.commands.Job;
import me.goost.goostserver.skill.archer.ac_skill;
import me.goost.goostserver.skill.dark_elf.df_skill;
import me.goost.goostserver.skill.demon.de_skill;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;


import java.util.Objects;
import java.util.UUID;


public class check implements Listener {

    public static void check() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();

            if(Objects.equals(Job.Job.get(player.getUniqueId()), "demon")){
                de_skill.demon_skill_check(player,uuid);
            }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "dark_elf")){
                df_skill.dark_elf_skill_check(player,uuid);
            }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "archer")){
                ac_skill.archer_skill_check(player,uuid);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getHand()== EquipmentSlot.HAND){
            if(e.getAction()== Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK){
                Player player = e.getPlayer();
                UUID uuid = e.getPlayer().getUniqueId();

                if(Objects.equals(Job.Job.get(player.getUniqueId()), "demon")){
                    //de_skill.demon_skill_check(player,uuid);
                }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "dark_elf")){
                    df_skill.onPlayerUse(e);
                }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "archer")){
                    ac_skill.onPlayerUse(e);
                }
            }
        }
    }

}
