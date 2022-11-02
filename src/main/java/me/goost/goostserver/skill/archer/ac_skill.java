package me.goost.goostserver.skill.archer;

import me.goost.goostserver.GoostServer;
import me.goost.goostserver.player.mana;
import me.goost.goostserver.skill.Items;
import me.goost.goostserver.skill.Skills_mana_uses;
import me.goost.goostserver.skill.particle;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class ac_skill {

    public static HashMap<UUID, Long> basicinvis_cooldown = new HashMap<>();

    public static int basicinvis_cooldowntime = 3;


    public static void onPlayerUse(PlayerInteractEvent e){
        Player player = e.getPlayer();

        ItemStack invis_book = Items.Archer_invis_book;

        UUID uuid = player.getUniqueId();

        if(player.getItemInHand().equals(Items.Archer_invis_book)){// 은신스킬
            if(mana.get_mana(player.getUniqueId()) >= 30){
                if(basicinvis_cooldown.containsKey(uuid)){

                    // when it's *NOT* the first time the player using this skill
                    long secondleft = ((basicinvis_cooldown.get(uuid) / 1000) + basicinvis_cooldowntime) - (System.currentTimeMillis() / 1000);
                    // cooldown seconds left for skill
                    if(secondleft > 0){
                        // if still in cooldown
                        player.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"스킬쿨다운 "+ get_invis_cooldown(player.getUniqueId())+"초 남음");
                    }else{
                        // if already able to use skill
                        invis(player);
                        set_invis_Cooldown(player.getUniqueId());
                    }
                }else{
                    // when it's the first time the player using this skill
                    invis(player);
                    set_invis_Cooldown(player.getUniqueId());
                }
            }else{
                player.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"너 마나없잖아");
            }
        }//else if(){}
    }

    public static void archer_skill_check(Player player,UUID uuid){

    }

    public static void set_invis_Cooldown(UUID player){
        basicinvis_cooldown.put(player, System.currentTimeMillis());
    }

    public static long get_invis_cooldown(UUID player){
        return ((basicinvis_cooldown.get(player) / 1000) + basicinvis_cooldowntime) - (System.currentTimeMillis() / 1000);
    }

    public static void invis(Player player){
        mana.remove_mana(player.getUniqueId(), Skills_mana_uses.Archer_basic_invis_skill());
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 1),true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 255),true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*3, 2),true);

        particle.spawn_ball_particle(player.getLocation(),player, Color.fromRGB(0, 180, 30), Color.fromRGB(0, 180, 30), 200,0.5f,0.8f,0.5f,1);

    }
}
