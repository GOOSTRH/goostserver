package me.goost.goostserver.skill.check;

import me.goost.goostserver.player.commands.Job;
import me.goost.goostserver.skill.dark_elf.df_skill;
import me.goost.goostserver.skill.demon.de_skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;


import java.util.Objects;
import java.util.UUID;


public class check {

    public static void check() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            if(Objects.equals(Job.Job.get(player.getUniqueId()), "sword_man")){

            }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "demon")){
                de_skill.demon_skill_check(player,uuid);
            }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "dark_elf")){
                df_skill.dark_elf_skill_check(player,uuid);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        UUID uuid = e.getPlayer().getUniqueId();
        if(Objects.equals(Job.Job.get(player.getUniqueId()), "sword_man")){

        }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "demon")){
            de_skill.demon_skill_check(player,uuid);
        }else if(Objects.equals(Job.Job.get(player.getUniqueId()), "dark_elf")){
            df_skill.dark_elf_skill_check(player,uuid);
        }
    }

}
