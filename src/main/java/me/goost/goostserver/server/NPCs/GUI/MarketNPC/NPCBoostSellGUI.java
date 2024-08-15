package me.goost.goostserver.server.NPCs.GUI.MarketNPC;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class NPCBoostSellGUI {

    public static int gapplePrice =            300;
    public static int egapplePrice =            15000;
    public static int nightVisionPotionPrice = 800;
    public static int jumpBoostPotionPrice = 700;
    public static int speedPotionPrice = 700;
    public static int regenerationPotionPrice = 800;
    public static int strengthPotionPrice = 900;
    public static int slowFallingSplashPotionPrice = 600;


    static ItemStack Filler = new ItemStack(Material.AIR);

    public static void showBoostSellGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54,  "Boost Vendor");

        ItemStack gapple =            new ItemStack(Material.GOLDEN_APPLE);
        ItemStack egapple =           new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);

        ItemStack nightVisionPotion = new ItemStack(Material.POTION);
        PotionMeta nightVisionPotionMeta = (PotionMeta) nightVisionPotion.getItemMeta();
        if (nightVisionPotionMeta != null) {
            nightVisionPotionMeta.setBasePotionData(new PotionData(PotionType.NIGHT_VISION, true, false));
            nightVisionPotion.setItemMeta(nightVisionPotionMeta);
        }

        ItemStack jumpBoostPotion = new ItemStack(Material.POTION);
        PotionMeta jumpBoostPotionMeta = (PotionMeta) jumpBoostPotion.getItemMeta();
        if (jumpBoostPotionMeta != null) {
            jumpBoostPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 3600, 1), true);
            jumpBoostPotionMeta.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE)); // Set to Uncraftable to prevent default effects
            jumpBoostPotion.setItemMeta(jumpBoostPotionMeta);
        }

        ItemStack speedPotion = new ItemStack(Material.POTION);
        PotionMeta speedPotionMeta = (PotionMeta) speedPotion.getItemMeta();
        if (speedPotionMeta != null) {
            speedPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 0), true);
            speedPotionMeta.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
            speedPotion.setItemMeta(speedPotionMeta);
        }

        ItemStack regenPotion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta regenPotionMeta = (PotionMeta) regenPotion.getItemMeta();
        if (regenPotionMeta != null) {
            regenPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 3600, 1), true);
            regenPotionMeta.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
            regenPotion.setItemMeta(regenPotionMeta);
        }

        ItemStack strengthPotion = new ItemStack(Material.POTION);
        PotionMeta strengthPotionMeta = (PotionMeta) strengthPotion.getItemMeta();
        if (strengthPotionMeta != null) {
            strengthPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 1), true);
            strengthPotionMeta.setBasePotionData(new PotionData(PotionType.UNCRAFTABLE));
            strengthPotion.setItemMeta(strengthPotionMeta);
        }


        ItemStack slowFallingSplashPotion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta slowFallingPotionMeta = (PotionMeta) slowFallingSplashPotion.getItemMeta();
        if (slowFallingPotionMeta != null) {
            slowFallingPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 3600, 0), true);
            slowFallingPotionMeta.setBasePotionData(new PotionData(PotionType.SLOW_FALLING));
            slowFallingSplashPotion.setItemMeta(slowFallingPotionMeta);
        }


        itemTrade.setItemMeta(gapple, "Golden Apple", gapplePrice);
        itemTrade.setItemMeta(egapple, "Enchanted Golden Apple", egapplePrice);

        itemTrade.setItemMeta(nightVisionPotion, "Night Vision Potion", nightVisionPotionPrice);
        itemTrade.setItemMeta(jumpBoostPotion, "Jump Boost Potion", jumpBoostPotionPrice);
        itemTrade.setItemMeta(speedPotion, "Speed Potion", speedPotionPrice);
        itemTrade.setItemMeta(regenPotion, "Regeneration Potion", regenerationPotionPrice);
        itemTrade.setItemMeta(strengthPotion, "Strength Potion", strengthPotionPrice);
        itemTrade.setItemMeta(slowFallingSplashPotion, "Slow Falling Splash Potion", slowFallingSplashPotionPrice);


        ItemStack[] menu_items = {

                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, gapple,egapple, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, nightVisionPotion,jumpBoostPotion,speedPotion,regenPotion,strengthPotion,slowFallingSplashPotion, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler


        };
        gui.setContents(menu_items);
        player.openInventory(gui);
    }
}
