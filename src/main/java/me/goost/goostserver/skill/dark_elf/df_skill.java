package me.goost.goostserver.skill.dark_elf;

import me.goost.goostserver.player.mana;
import me.goost.goostserver.skill.Items;
import me.goost.goostserver.skill.Skills_mana_uses;
import me.goost.goostserver.skill.particle;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class df_skill {

    public static HashMap<UUID, Long> invis_cooldown = new HashMap<>();

    public static int invis_cooldowntime = 1;


    public static void onPlayerUse(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack invis_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = invis_book.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "은신");
        invis_book.setItemMeta(meta);

        UUID uuid = player.getUniqueId();

        if(player.getItemInHand().equals(Items.Dark_Elf_invis_book)){// 은신스킬
            if(mana.get_mana(player.getUniqueId()) >= 30){
                if(invis_cooldown.containsKey(uuid)){

                    // when it's *NOT* the first time the player using this skill
                    long secondleft = ((invis_cooldown.get(uuid) / 1000) + invis_cooldowntime) - (System.currentTimeMillis() / 1000);
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

    public static void dark_elf_skill_check(Player player,UUID uuid){

    }

    public static void set_invis_Cooldown(UUID player){
        invis_cooldown.put(player, System.currentTimeMillis());
    }

    public static long get_invis_cooldown(UUID player){
        return ((invis_cooldown.get(player) / 1000) + invis_cooldowntime) - (System.currentTimeMillis() / 1000);
    }

    public static void invis(Player player){
        mana.remove_mana(player.getUniqueId(), Skills_mana_uses.Dark_elf_invis_skill());
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 1),true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 255),true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*3, 2),true);

        particle.spawn_ball_particle(player.getLocation(),player, Color.fromRGB(127, 0, 255), Color.fromRGB(255,102,255), 200,0.5f,0.8f,0.5f,2);
    }
}
