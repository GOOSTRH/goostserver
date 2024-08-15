package me.goost.goostserver.server.NPCs.GUI.MarketNPC;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCWoodSellGUI{

    // Define prices for each type of log
    public static int oakLogPrice = 40;
    public static int spruceLogPrice = 40;
    public static int birchLogPrice = 40;
    public static int jungleLogPrice = 40;
    public static int acaciaLogPrice = 40;
    public static int darkOakLogPrice = 40;
    public static int mangroveLogPrice = 40;
    public static int cherryLogPrice = 40;
    public static int crimsonLogPrice = 40;
    public static int warpedLogPrice = 40;
    // Define prices for each type of plank
    public static int oakPlankPrice = 10;
    public static int sprucePlankPrice = 10;
    public static int birchPlankPrice = 10;
    public static int junglePlankPrice = 10;
    public static int acaciaPlankPrice = 10;
    public static int darkOakPlankPrice = 10;
    public static int mangrovePlankPrice = 10;
    public static int cherryPlankPrice = 10;
    public static int crimsonPlankPrice = 10;
    public static int warpedPlankPrice = 10;
    static ItemStack Filler = new ItemStack(Material.AIR);

    public static void showWoodSellGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54,  "Wood Vendor");

        ItemStack oakLog = new ItemStack(Material.OAK_LOG);
        ItemStack spruceLog = new ItemStack(Material.SPRUCE_LOG);
        ItemStack birchLog = new ItemStack(Material.BIRCH_LOG);
        ItemStack jungleLog = new ItemStack(Material.JUNGLE_LOG);
        ItemStack acaciaLog = new ItemStack(Material.ACACIA_LOG);
        ItemStack darkOakLog = new ItemStack(Material.DARK_OAK_LOG);
        ItemStack mangroveLog = new ItemStack(Material.MANGROVE_LOG);
        ItemStack cherryLog = new ItemStack(Material.CHERRY_LOG);
        ItemStack crimsonLog = new ItemStack(Material.CRIMSON_STEM);
        ItemStack warpedLog = new ItemStack(Material.WARPED_STEM);

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

