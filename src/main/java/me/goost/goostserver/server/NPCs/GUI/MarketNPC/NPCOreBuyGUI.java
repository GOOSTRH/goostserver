package me.goost.goostserver.server.NPCs.GUI.MarketNPC;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCOreBuyGUI {


    public static int rawIronPrice = 75;
    public static int ironIngotPrice = 100;
    public static int rawCopperPrice = 50;
    public static int copperIngotPrice = 75;
    public static int rawGoldPrice = 150;
    public static int goldIngotPrice = 200;
    public static int lapisPrice = 40;
    public static int emeraldPrice = 250;
    public static int diamondPrice = 500;
    public static int charcoalPrice = 30;
    public static int coalPrice = 40;
    public static int quartzPrice = 75;
    public static int amethystPrice = 100;

    public static int ancientDebrisPrice = 2000;
    public static int netheriteScrapPrice = 2200;
    public static int netheriteIngotPrice = 3000;



    static ItemStack Filler = new ItemStack(Material.AIR);

    public static void showOreBuyGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54,  "Ore Purchaser");

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

        ItemStack ancientDebris = new ItemStack(Material.ANCIENT_DEBRIS);
        ItemStack netheriteScrap = new ItemStack(Material.NETHERITE_SCRAP);
        ItemStack netheriteIngot = new ItemStack(Material.NETHERITE_INGOT);


        itemTrade.setItemMeta(rawIron, "Raw Iron",              rawIronPrice);
        itemTrade.setItemMeta(ironIngot, "Iron Ingot",          ironIngotPrice);
        itemTrade.setItemMeta(rawCopper, "Raw Copper",          rawCopperPrice);
        itemTrade.setItemMeta(copperIngot, "Copper Ingot",      copperIngotPrice);
        itemTrade.setItemMeta(rawGold, "Raw Gold",              rawGoldPrice);
        itemTrade.setItemMeta(goldIngot, "Gold Ingot",          goldIngotPrice);
        itemTrade.setItemMeta(lapis, "Lapis Lazuli",            lapisPrice);
        itemTrade.setItemMeta(emerald, "Emerald",               emeraldPrice);
        itemTrade.setItemMeta(diamond, "Diamond",               diamondPrice);
        itemTrade.setItemMeta(charcoal, "Charcoal",             charcoalPrice);
        itemTrade.setItemMeta(coal, "Coal",                     coalPrice);
        itemTrade.setItemMeta(quartz, "Quartz",                 quartzPrice);
        itemTrade.setItemMeta(amethyst, "Amethyst Shard",       amethystPrice);

        itemTrade.setItemMeta(ancientDebris, "Ancient Debris ",        ancientDebrisPrice);
        itemTrade.setItemMeta(netheriteScrap, "Netherite Scrap",       netheriteScrapPrice);
        itemTrade.setItemMeta(netheriteIngot, "Netherite Ingot",       netheriteIngotPrice);

        //Put the items in the inventory
        ItemStack[] menu_items = {

                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, rawIron, rawCopper,rawGold,lapis,diamond,charcoal,quartz,Filler,
                Filler, ironIngot,copperIngot,goldIngot,emerald,Filler,coal,amethyst,Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler, ancientDebris,netheriteScrap,netheriteIngot, Filler, Filler, Filler, Filler, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler

        };
        gui.setContents(menu_items);
        player.openInventory(gui);
    }
}
