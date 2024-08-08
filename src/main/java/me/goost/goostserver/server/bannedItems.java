package me.goost.goostserver.server;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class bannedItems {

    public static void checkBannedItems(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            checkAndHandlePotion(player); // check if player is holding invis potion in off and main hand
        }
    }


    private static void checkAndHandlePotion(Player player) {
        boolean potionInMainHand = isInvisibilityPotion(player.getInventory().getItemInMainHand());
        boolean potionInOffHand = isInvisibilityPotion(player.getInventory().getItemInOffHand());

        if (potionInMainHand) {
            handleInvisibilityPotion(player, player.getInventory().getItemInMainHand());
        }
        if (potionInOffHand) {
            handleInvisibilityPotion(player, player.getInventory().getItemInOffHand());
        }
    }

    private static boolean isInvisibilityPotion(ItemStack item) {
        if (item == null || !item.getType().equals(Material.POTION)) {
            return false;
        }
        if (item.getItemMeta() instanceof PotionMeta) {
            PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
            for (PotionEffect effect : potionMeta.getCustomEffects()) {
                if (effect.getType() == PotionEffectType.INVISIBILITY) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void handleInvisibilityPotion(Player player, ItemStack potionItem) {
        if (isInvisibilityPotion(potionItem)) {
            // Drop the item
            player.getWorld().dropItemNaturally(player.getLocation(), potionItem);

            // Remove the item from the player's hand
            if (player.getInventory().getItemInMainHand().equals(potionItem)) {
                player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            } else if (player.getInventory().getItemInOffHand().equals(potionItem)) {
                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }

            // Send a message to the player
            player.sendMessage("Holding or using invisibility potions is not allowed!");
        }
    }
}
