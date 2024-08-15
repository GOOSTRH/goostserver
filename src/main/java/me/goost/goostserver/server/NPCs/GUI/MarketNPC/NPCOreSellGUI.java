package me.goost.goostserver.server.NPCs.GUI.MarketNPC;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCOreSellGUI {

    public static int rawIronPrice = 95;
    public static int ironIngotPrice = 130;
    public static int rawCopperPrice = 65;
    public static int copperIngotPrice = 95;
    public static int rawGoldPrice = 190;
    public static int goldIngotPrice = 250;
    public static int lapisPrice = 50;
    public static int emeraldPrice = 315;
    public static int diamondPrice = 630;
    public static int charcoalPrice = 40;
    public static int coalPrice = 50;
    public static int quartzPrice = 95;
    public static int amethystPrice = 130;


    static ItemStack Filler = new ItemStack(Material.AIR);

    public static void showOreSellGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54,  "Ore Vendor");

        ItemStack rawIron = new ItemStack(Material.RAW_IRON);
        ItemStack ironIngot = new ItemStack(Material.IRON_INGOT);
        ItemStack rawCopper = new ItemStack(Material.RAW_COPPER);
        ItemStack copperIngot = new ItemStack(Material.COPPER_INGOT);
        ItemStack rawGold = new ItemStack(Material.RAW_GOLD);
        ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT);
        ItemStack lapis = new ItemStack(Material.LAPIS_LAZULI);
        ItemStack emerald = new ItemStack(Material.EMERALD);
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemStack charcoal = new ItemStack(Material.CHARCOAL);
        ItemStack coal = new ItemStack(Material.COAL);
        ItemStack quartz= new ItemStack(Material.QUARTZ);
        ItemStack amethyst = new ItemStack(Material.AMETHYST_SHARD);


        itemTrade.setItemMeta(rawIron, "Raw Iron",              rawIronPrice);
        itemTrade.setItemMeta(ironIngot, "Iron Ingot",          ironIngotPrice);
        itemTrade.setItemMeta(rawCopper, "Raw Copper",          rawCopperPrice);
        itemTrade.setItemMeta(copperIngot, "Copper Ingot",      copperIngotPrice);
        itemTrade.setItemMeta(rawGold, "Raw Gold",              rawGoldPrice);
        itemTrade.setItemMeta(goldIngot, "Gold Ingot", goldIngotPrice);
        itemTrade.setItemMeta(lapis, "Lapis Lazuli",            lapisPrice);
        itemTrade.setItemMeta(emerald, "Emerald",               emeraldPrice);
        itemTrade.setItemMeta(diamond, "Diamond",               diamondPrice);
        itemTrade.setItemMeta(charcoal, "Charcoal",             charcoalPrice);
        itemTrade.setItemMeta(coal, "Coal",                     coalPrice);
        itemTrade.setItemMeta(quartz, "Quartz",                 quartzPrice);
        itemTrade.setItemMeta(amethyst, "Amethyst Shard",       amethystPrice);

        //Put the items in the inventory
        ItemStack[] menu_items = {

                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, rawIron, rawCopper,rawGold,lapis,diamond,charcoal,quartz,Filler,
                Filler, ironIngot,copperIngot,goldIngot,emerald,Filler,coal,amethyst,Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler

        };
        gui.setContents(menu_items);
        player.openInventory(gui);
    }
}
