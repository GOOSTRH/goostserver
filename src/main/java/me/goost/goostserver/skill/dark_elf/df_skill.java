package me.goost.goostserver.skill.dark_elf;

import me.goost.goostserver.player.mana;
import me.goost.goostserver.skill.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class df_skill {

    public static HashMap<UUID, Long> invis_cooldown = new HashMap<>();

    public static int invis_time = 5;


    public static void onPlayerUse(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack invis_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = invis_book.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "은신");
        invis_book.setItemMeta(meta);
        player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Click success!");

        if(player.getItemInHand().equals(Items.Dark_Elf_invis_book)){// 은신스킬
            player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Launch success!");
            invis(player);
        }//else if(){}
    }

    public static void dark_elf_skill_check(Player player,UUID uuid){

    }


    public static void invis(Player player){
        player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Invis success!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 1),true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*3, 2),true);
    }
}
