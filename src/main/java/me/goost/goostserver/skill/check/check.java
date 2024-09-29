package me.goost.goostserver.skill.check;

import me.goost.goostserver.player.commands.Job;
import me.goost.goostserver.skill.assassinSkill.assassinSkill;
import me.goost.goostserver.skill.bladeMaster.bladeMasterSkill;
import org.bukkit.Bukkit;
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

            /*if(Objects.equals(Job.Job.get(player.getUniqueId()), "demon")){
                de_skill.demon_skill_check(player,uuid);
            }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "dark_elf")){
                df_skill.dark_elf_skill_check(player,uuid);
            }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "archer")){
                ac_skill.archer_skill_check(player,uuid);
            }*/
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getHand()== EquipmentSlot.HAND){ // if the item is used from main hand
            if(event.getAction()== Action.RIGHT_CLICK_AIR||event.getAction()==Action.RIGHT_CLICK_BLOCK){ // if clicked on air or block
                Player player = event.getPlayer();
                UUID uuid = event.getPlayer().getUniqueId();

                if(Objects.equals(Job.Job.get(player.getUniqueId()), "assassin")){
                    assassinSkill.assasin_skill_check(player,uuid);
                }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "blademaster")){
                    bladeMasterSkill.onPlayerUse(event);
                }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "caster")){
                }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "tank")){
                }
            }
        }
    }

}
