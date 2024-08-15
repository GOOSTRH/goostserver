package me.goost.goostserver.server.NPCs.GUI.MarketNPC;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCWoodBuyGUI{
    // Define prices for each type of log
    public static int oakLogPrice = 20;
    public static int spruceLogPrice = 20;
    public static int birchLogPrice = 20;
    public static int jungleLogPrice = 20;
    public static int acaciaLogPrice = 20;
    public static int darkOakLogPrice = 20;
    public static int mangroveLogPrice = 20;
    public static int cherryLogPrice = 20;
    public static int crimsonLogPrice = 20;
    public static int warpedLogPrice = 20;
    // Define prices for each type of plank
    public static int oakPlankPrice = 5;
    public static int sprucePlankPrice = 5;
    public static int birchPlankPrice = 5;
    public static int junglePlankPrice = 5;
    public static int acaciaPlankPrice = 5;
    public static int darkOakPlankPrice = 5;
    public static int mangrovePlankPrice = 5;
    public static int cherryPlankPrice = 5;
    public static int crimsonPlankPrice = 5;
    public static int warpedPlankPrice = 5;

    static ItemStack Filler = new ItemStack(Material.AIR);

    public static void showWoodBuyGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54,  "Wood Purchaser");

        // Menu Options(Items)
        // Create ItemStacks for different types of logs
        ItemStack oakLog = new ItemStack(Material.OAK_LOG); // Sell oak log
        ItemStack spruceLog = new ItemStack(Material.SPRUCE_LOG); // etc..
        ItemStack birchLog = new ItemStack(Material.BIRCH_LOG);
        ItemStack jungleLog = new ItemStack(Material.JUNGLE_LOG);
        ItemStack acaciaLog = new ItemStack(Material.ACACIA_LOG);
        ItemStack darkOakLog = new ItemStack(Material.DARK_OAK_LOG);
        ItemStack mangroveLog = new ItemStack(Material.MANGROVE_LOG);
        ItemStack cherryLog = new ItemStack(Material.CHERRY_LOG);
        ItemStack crimsonLog = new ItemStack(Material.CRIMSON_STEM);
        ItemStack warpedLog = new ItemStack(Material.WARPED_STEM);

        // Create ItemStacks for different types of planks
        ItemStack oakPlank = new ItemStack(Material.OAK_PLANKS);
        ItemStack sprucePlank = new ItemStack(Material.SPRUCE_PLANKS);
        ItemStack birchPlank = new ItemStack(Material.BIRCH_PLANKS);
        ItemStack junglePlank = new ItemStack(Material.JUNGLE_PLANKS);
        ItemStack acaciaPlank = new ItemStack(Material.ACACIA_PLANKS);
        ItemStack darkOakPlank = new ItemStack(Material.DARK_OAK_PLANKS);
        ItemStack mangrovePlank = new ItemStack(Material.MANGROVE_PLANKS);
        ItemStack cherryPlank = new ItemStack(Material.CHERRY_PLANKS);
        ItemStack crimsonPlank = new ItemStack(Material.CRIMSON_PLANKS);
        ItemStack warpedPlank = new ItemStack(Material.WARPED_PLANKS);

        // Edit the items
        itemTrade.setItemMeta(oakLog, "Oak Log", oakLogPrice);
        itemTrade.setItemMeta(spruceLog, "Spruce Log", spruceLogPrice);
        itemTrade.setItemMeta(birchLog, "Birch Log", birchLogPrice);
        itemTrade.setItemMeta(jungleLog, "Jungle Log", jungleLogPrice);
        itemTrade.setItemMeta(acaciaLog, "Acacia Log", acaciaLogPrice);
        itemTrade.setItemMeta(darkOakLog, "Dark Oak Log", darkOakLogPrice);
        itemTrade.setItemMeta(mangroveLog, "Mangrove Log", mangroveLogPrice);
        itemTrade.setItemMeta(cherryLog, "Cherry Log", cherryLogPrice);
        itemTrade.setItemMeta(crimsonLog, "Crimson Log", crimsonLogPrice);
        itemTrade.setItemMeta(warpedLog, "Warped Log", warpedLogPrice);
        // Edit the planks
        itemTrade.setItemMeta(oakPlank, "Oak Plank", oakPlankPrice);
        itemTrade.setItemMeta(sprucePlank, "Spruce Plank", sprucePlankPrice);
        itemTrade.setItemMeta(birchPlank, "Birch Plank", birchPlankPrice);
        itemTrade.setItemMeta(junglePlank, "Jungle Plank", junglePlankPrice);
        itemTrade.setItemMeta(acaciaPlank, "Acacia Plank", acaciaPlankPrice);
        itemTrade.setItemMeta(darkOakPlank, "Dark Oak Plank", darkOakPlankPrice);
        itemTrade.setItemMeta(mangrovePlank, "Mangrove Plank", mangrovePlankPrice);
        itemTrade.setItemMeta(cherryPlank, "Cherry Plank", cherryPlankPrice);
        itemTrade.setItemMeta(crimsonPlank, "Crimson Plank", crimsonPlankPrice);
        itemTrade.setItemMeta(warpedPlank, "Warped Plank", warpedPlankPrice);

        //Put the items in the inventory
        ItemStack[] menu_items = {

                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler,oakLog,birchLog,darkOakLog,spruceLog,jungleLog, Filler, Filler, Filler,
                Filler,acaciaLog,crimsonLog,warpedLog,mangroveLog,cherryLog, Filler, Filler, Filler,
                Filler,oakPlank,birchPlank,darkOakPlank,sprucePlank,junglePlank, Filler, Filler, Filler,
                Filler,acaciaPlank,crimsonPlank,warpedPlank,mangrovePlank,cherryPlank, Filler, Filler, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler

                };
        gui.setContents(menu_items);
        player.openInventory(gui);
    }


}
