package me.goost.goostserver.server.NPCs.GUI;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCDropSellGUI {

    public static int fleshPrice = 15;
    public static int bonePrice = 25;
    public static int stringPrice = 20;
    public static int spiderEyePrice = 30;
    public static int gunPowderPrice = 65;
    public static int slimeBallPrice = 95;

    public static int prismarineShardPrice = 125;
    public static int blazeRodPrice = 190;
    public static int phantomMembranePrice = 250;
    public static int enderPearlPrice = 275;
    public static int shulkerShellPrice = 750;

    public static int rabbitHidePrice = 40;
    public static int rabbitFootPrice = 125;
    public static int leatherPrice = 50;
    public static int scutePrice = 375;
    public static int inkSacPrice = 20;
    public static int glowingInkSacPrice = 50;
    public static int featherPrice = 15;




    static ItemStack Filler = new ItemStack(Material.AIR);

    public static void showDropSellGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54,  "Mob Drop Vendor");

        ItemStack flesh =                   new ItemStack(Material.ROTTEN_FLESH);
        ItemStack bone =                    new ItemStack(Material.BONE);
        ItemStack string =                  new ItemStack(Material.STRING);
        ItemStack spiderEye =               new ItemStack(Material.SPIDER_EYE);
        ItemStack gunPowder =               new ItemStack(Material.GUNPOWDER);
        ItemStack slimeBall =               new ItemStack(Material.SLIME_BALL);

        ItemStack prismarineShard =         new ItemStack(Material.PRISMARINE_SHARD);
        ItemStack blazeRod =                new ItemStack(Material.BLAZE_ROD);
        ItemStack phantomMembrane =         new ItemStack(Material.PHANTOM_MEMBRANE);
        ItemStack enderPearl =              new ItemStack(Material.ENDER_PEARL);
        ItemStack shulkerShell =            new ItemStack(Material.SHULKER_SHELL);

        ItemStack rabbitHide =              new ItemStack(Material.RABBIT_HIDE);
        ItemStack rabbitFoot =              new ItemStack(Material.RABBIT_FOOT);
        ItemStack leather =                 new ItemStack(Material.LEATHER);
        ItemStack scute =                   new ItemStack(Material.SCUTE);
        ItemStack inkSac =                  new ItemStack(Material.INK_SAC);
        ItemStack glowingInkSac =           new ItemStack(Material.GLOW_INK_SAC);
        ItemStack feather =                 new ItemStack(Material.FEATHER);


        itemTrade.setItemMeta(flesh, "Rotten Flesh",              fleshPrice);
        itemTrade.setItemMeta(bone, "Bone",                       bonePrice);
        itemTrade.setItemMeta(string, "String",                   stringPrice);
        itemTrade.setItemMeta(spiderEye, "Spider Eye",            spiderEyePrice);
        itemTrade.setItemMeta(gunPowder, "Gunpowder",             gunPowderPrice);
        itemTrade.setItemMeta(slimeBall, "Slime Ball",            slimeBallPrice);
        itemTrade.setItemMeta(prismarineShard, "Prismarine Shard", prismarineShardPrice);
        itemTrade.setItemMeta(blazeRod, "Blaze Rod",              blazeRodPrice);
        itemTrade.setItemMeta(phantomMembrane, "Phantom Membrane", phantomMembranePrice);
        itemTrade.setItemMeta(enderPearl, "Ender Pearl",          enderPearlPrice);
        itemTrade.setItemMeta(shulkerShell, "Shulker Shell",      shulkerShellPrice);
        itemTrade.setItemMeta(rabbitHide, "Rabbit Hide",          rabbitHidePrice);
        itemTrade.setItemMeta(rabbitFoot, "Rabbit Foot",          rabbitFootPrice);
        itemTrade.setItemMeta(leather, "Leather",                 leatherPrice);
        itemTrade.setItemMeta(scute, "Scute",                     scutePrice);
        itemTrade.setItemMeta(inkSac, "Ink Sac",                  inkSacPrice);
        itemTrade.setItemMeta(glowingInkSac, "Glowing Ink Sac",   glowingInkSacPrice);
        itemTrade.setItemMeta(feather, "Feather",                 featherPrice);


        //Put the items in the inventory
        ItemStack[] menu_items = {

                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, flesh, bone, string, spiderEye, gunPowder, slimeBall, Filler, Filler,
                Filler, prismarineShard, blazeRod, phantomMembrane, enderPearl, shulkerShell, Filler, Filler, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, rabbitHide, rabbitFoot, leather, scute, inkSac, glowingInkSac, feather, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler

        };
        gui.setContents(menu_items);
        player.openInventory(gui);
    }
}
