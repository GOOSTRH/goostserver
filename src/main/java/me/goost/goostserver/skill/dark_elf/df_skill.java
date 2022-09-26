package me.goost.goostserver.skill.dark_elf;

import me.goost.goostserver.player.mana;
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


    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack invis_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = invis_book.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "은신");
        invis_book.setItemMeta(meta);

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(invis_book)) return;



    }

    public static void dark_elf_skill_check(Player player,UUID uuid){
        if (player.getItemInHand().getItemMeta().hasDisplayName()) {return;}
        if (Objects.equals(player.getItemInHand().getItemMeta().displayName(), "은신")) {
            player.sendMessage("1");
            invis_cooldown.putIfAbsent(uuid, 0L);

            if(!((invis_cooldown.get (uuid) - System.currentTimeMillis()) * 1000 < invis_time)){//
                //long secondsLeft = ((cooldowns.get(sender.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                //if(secondsLeft>0) {
                invis_cooldown.replace(uuid ,System.currentTimeMillis());
                mana.remove_mana(uuid,30);
                df_skill.invis(player);
            }
        }
    }


    public static void invis(Player player){

        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 1),true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*3, 2),true);
    }
}
