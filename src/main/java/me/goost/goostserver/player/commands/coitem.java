package me.goost.goostserver.player.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class coitem implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to access this command!");
                return true;
            }
            Player player = (Player) sender;
            Inventory gui = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Custom GUI");

            //Menu Options(Items)
            ItemStack suicide = new ItemStack(Material.TNT); //Kills the Player
            ItemStack feed = new ItemStack(Material.BREAD); //Fills the hunger bar
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD); //Gives the Player a weapon

            //Edit the items
            ItemMeta suicide_meta = suicide.getItemMeta();
            suicide_meta.setDisplayName(ChatColor.RED + "Suicide");
            ArrayList<String> suicide_lore = new ArrayList<>();
            suicide_lore.add(ChatColor.GOLD + "Kill yourself. ;)");
            suicide_meta.setLore(suicide_lore);
            suicide.setItemMeta(suicide_meta);

            ItemMeta feed_meta = feed.getItemMeta();
            feed_meta.setDisplayName(ChatColor.DARK_GREEN + "Feed");
            ArrayList<String> feed_lore = new ArrayList<>();
            feed_lore.add(ChatColor.GOLD + "Hunger no more.");
            feed_meta.setLore(feed_lore);
            feed.setItemMeta(feed_meta);

            ItemMeta sword_meta = sword.getItemMeta();
            sword_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sword");
            ArrayList<String> sword_lore = new ArrayList<>();
            sword_lore.add(ChatColor.GOLD + "Get a sword.");
            sword_meta.setLore(sword_lore);
            sword.setItemMeta(sword_meta);

            //Put the items in the inventory
            ItemStack[] menu_items = {suicide, feed, sword};
            gui.setContents(menu_items);
            player.openInventory(gui);


        }

        return true;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e){

        //Check to see if its the GUI menu
        if( e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Custom GUI")){
            Player player = (Player) e.getWhoClicked();
            //Determine what they selected and what to do
            switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {
                case TNT -> {
                    player.closeInventory();
                    player.setHealth(0.0);
                    player.sendMessage("You just killed yourself");
                }
                case BREAD -> {
                    player.closeInventory();
                    player.setFoodLevel(20);
                    player.sendMessage("YUM!");
                }
                case DIAMOND_SWORD -> {
                    player.closeInventory();
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                    player.sendMessage("Don't slice yourself");
                }
            }


            e.setCancelled(true); //So they cant take the items
        }

    }
}
