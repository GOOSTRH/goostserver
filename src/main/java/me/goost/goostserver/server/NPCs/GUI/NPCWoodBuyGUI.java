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

public class NPCWoodBuyGUI implements Listener {
    // Define prices for each type of log
    static int oakLogPrice = 40;
    static int spruceLogPrice = 40;
    static int birchLogPrice = 40;
    static int jungleLogPrice = 40;
    static int acaciaLogPrice = 40;
    static int darkOakLogPrice = 40;
    static int mangroveLogPrice = 40;
    static int cherryLogPrice = 40;
    static int crimsonLogPrice = 40;
    static int warpedLogPrice = 40;
    // Define prices for each type of plank
    static int oakPlankPrice = 10;
    static int sprucePlankPrice = 10;
    static int birchPlankPrice = 10;
    static int junglePlankPrice = 10;
    static int acaciaPlankPrice = 10;
    static int darkOakPlankPrice = 10;
    static int mangrovePlankPrice = 10;
    static int cherryPlankPrice = 10;
    static int crimsonPlankPrice = 10;
    static int warpedPlankPrice = 10;

    static ItemStack AIR = new ItemStack(Material.AIR);

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
                case OAK_LOG -> itemTrade.sellItem(player, item, oakLogPrice, e.getClick());
                case SPRUCE_LOG -> itemTrade.sellItem(player, item, spruceLogPrice, e.getClick());
                case BIRCH_LOG -> itemTrade.sellItem(player, item, birchLogPrice, e.getClick());
                case JUNGLE_LOG -> itemTrade.sellItem(player, item, jungleLogPrice, e.getClick());
                case ACACIA_LOG -> itemTrade.sellItem(player, item, acaciaLogPrice, e.getClick());
                case DARK_OAK_LOG -> itemTrade.sellItem(player, item, darkOakLogPrice, e.getClick());
                case MANGROVE_LOG -> itemTrade.sellItem(player, item, mangroveLogPrice, e.getClick());
                case CHERRY_LOG -> itemTrade.sellItem(player, item, cherryLogPrice, e.getClick());
                case CRIMSON_STEM -> itemTrade.sellItem(player, item, crimsonLogPrice, e.getClick());
                case WARPED_STEM -> itemTrade.sellItem(player, item, warpedLogPrice, e.getClick());
                case OAK_PLANKS -> itemTrade.sellItem(player, item, oakPlankPrice, e.getClick());
                case SPRUCE_PLANKS -> itemTrade.sellItem(player, item, sprucePlankPrice, e.getClick());
                case BIRCH_PLANKS -> itemTrade.sellItem(player, item, birchPlankPrice, e.getClick());
                case JUNGLE_PLANKS -> itemTrade.sellItem(player, item, junglePlankPrice, e.getClick());
                case ACACIA_PLANKS -> itemTrade.sellItem(player, item, acaciaPlankPrice, e.getClick());
                case DARK_OAK_PLANKS -> itemTrade.sellItem(player, item, darkOakPlankPrice, e.getClick());
                case MANGROVE_PLANKS -> itemTrade.sellItem(player, item, mangrovePlankPrice, e.getClick());
                case CHERRY_PLANKS -> itemTrade.sellItem(player, item, cherryPlankPrice, e.getClick());
                case CRIMSON_PLANKS -> itemTrade.sellItem(player, item, crimsonPlankPrice, e.getClick());
                case WARPED_PLANKS -> itemTrade.sellItem(player, item, warpedPlankPrice, e.getClick());
                default -> {return;}
            }


            e.setCancelled(true); //So they cant take the items
        }
    }



}
