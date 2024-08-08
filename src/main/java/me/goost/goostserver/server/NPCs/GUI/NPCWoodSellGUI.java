package me.goost.goostserver.server.NPCs.GUI;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCWoodSellGUI implements Listener {
    // Define prices for each type of log
    static int oakLogPrice = 20;
    static int spruceLogPrice = 20;
    static int birchLogPrice = 20;
    static int jungleLogPrice = 20;
    static int acaciaLogPrice = 20;
    static int darkOakLogPrice = 20;
    static int mangroveLogPrice = 20;
    static int cherryLogPrice = 20;
    static int crimsonLogPrice = 20;
    static int warpedLogPrice = 20;
    // Define prices for each type of plank
    static int oakPlankPrice = 5;
    static int sprucePlankPrice = 5;
    static int birchPlankPrice = 5;
    static int junglePlankPrice = 5;
    static int acaciaPlankPrice = 5;
    static int darkOakPlankPrice = 5;
    static int mangrovePlankPrice = 5;
    static int cherryPlankPrice = 5;
    static int crimsonPlankPrice = 5;
    static int warpedPlankPrice = 5;

    static ItemStack AIR = new ItemStack(Material.AIR);

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

                AIR,AIR,AIR,AIR,AIR,AIR,AIR,AIR,AIR,
                AIR,oakLog,birchLog,darkOakLog,spruceLog,jungleLog,AIR,AIR,AIR,
                AIR,acaciaLog,crimsonLog,warpedLog,mangroveLog,cherryLog,AIR,AIR,AIR,
                AIR,oakPlank,birchPlank,darkOakPlank,sprucePlank,junglePlank,AIR,AIR,AIR,
                AIR,acaciaPlank,crimsonPlank,warpedPlank,mangrovePlank,cherryPlank,AIR,AIR,AIR,
                AIR,AIR,AIR,AIR,AIR,AIR,AIR,AIR,AIR

        };
        gui.setContents(menu_items);
        player.openInventory(gui);
    }



    @EventHandler
    public void clickEvent(InventoryClickEvent e){

        //C heck to see if its the GUI menu
        if( e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Wood Purchaser")){
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            // Determine what they selected and what to do
            // The item player clicked/selected
            ItemStack currentItem = e.getCurrentItem();

            if (currentItem == null || currentItem.getType() == Material.AIR) { // if nothing, return
                return;
            }

            Material item = currentItem.getType();
            switch (item) {
                case OAK_LOG -> itemTrade.buyItem(player, item, oakLogPrice, e.getClick());
                case SPRUCE_LOG -> itemTrade.buyItem(player, item, spruceLogPrice, e.getClick());
                case BIRCH_LOG -> itemTrade.buyItem(player, item, birchLogPrice, e.getClick());
                case JUNGLE_LOG -> itemTrade.buyItem(player, item, jungleLogPrice, e.getClick());
                case ACACIA_LOG -> itemTrade.buyItem(player, item, acaciaLogPrice, e.getClick());
                case DARK_OAK_LOG -> itemTrade.buyItem(player, item, darkOakLogPrice, e.getClick());
                case MANGROVE_LOG -> itemTrade.buyItem(player, item, mangroveLogPrice, e.getClick());
                case CHERRY_LOG -> itemTrade.buyItem(player, item, cherryLogPrice, e.getClick());
                case CRIMSON_STEM -> itemTrade.buyItem(player, item, crimsonLogPrice, e.getClick());
                case WARPED_STEM -> itemTrade.buyItem(player, item, warpedLogPrice, e.getClick());
                case OAK_PLANKS -> itemTrade.buyItem(player, item, oakPlankPrice, e.getClick());
                case SPRUCE_PLANKS -> itemTrade.buyItem(player, item, sprucePlankPrice, e.getClick());
                case BIRCH_PLANKS -> itemTrade.buyItem(player, item, birchPlankPrice, e.getClick());
                case JUNGLE_PLANKS -> itemTrade.buyItem(player, item, junglePlankPrice, e.getClick());
                case ACACIA_PLANKS -> itemTrade.buyItem(player, item, acaciaPlankPrice, e.getClick());
                case DARK_OAK_PLANKS -> itemTrade.buyItem(player, item, darkOakPlankPrice, e.getClick());
                case MANGROVE_PLANKS -> itemTrade.buyItem(player, item, mangrovePlankPrice, e.getClick());
                case CHERRY_PLANKS -> itemTrade.buyItem(player, item, cherryPlankPrice, e.getClick());
                case CRIMSON_PLANKS -> itemTrade.buyItem(player, item, crimsonPlankPrice, e.getClick());
                case WARPED_PLANKS -> itemTrade.buyItem(player, item, warpedPlankPrice, e.getClick());
                default -> {return;}
            }


            e.setCancelled(true); //So they cant take the items
        }
    }



}

