package me.goost.goostserver.server.NPCs;

import me.goost.goostserver.player.money;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class itemTrade {

    public static void setItemMeta(ItemStack item, String name, int price) {
        ItemMeta itemMeta = item.getItemMeta(); // NBT
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.WHITE + name);
        ArrayList<String> itemLore = new ArrayList<>(); // Lore
        itemLore.add(ChatColor.GREEN + "Price: " + price);
        itemLore.add("");
        itemLore.add(ChatColor.WHITE + "LeftClick sell "+ ChatColor.GREEN +"[1]");
        itemLore.add(ChatColor.WHITE + "1:"+ ChatColor.GREEN +"["+price+"]");
        itemLore.add(ChatColor.WHITE + "RightClick sell "+ ChatColor.GREEN +"[64]");
        itemLore.add(ChatColor.WHITE + "64:"+ ChatColor.GREEN +"["+(price*64)+"]");
        itemMeta.setLore(itemLore); // set Lore
        item.setItemMeta(itemMeta); // set NBT
    }

    public static void sellItem(Player player, Material item, int price, ClickType clickType) {
        if (player.getInventory().contains(item)) { // If the player have any of the item.
            int amountToSell = 0;

            if (clickType == ClickType.LEFT) {
                amountToSell = 1; // Sell one item on left-click
            } else if (clickType == ClickType.RIGHT) {
                amountToSell = 64; // Sell a full stack on right-click
            } else if (clickType == ClickType.DOUBLE_CLICK) {
                player.sendMessage(ChatColor.RED + "Slow down! Don't double click please!");
                return;
            }else {
                player.sendMessage(ChatColor.RED + "Unsupported click type.");
                return;
            }

            int totalSold = 0;

            for (ItemStack stack : player.getInventory().getContents()) { // run through all the items in player's inven
                if (stack != null && stack.getType() == item) { // if the item is not null and is the item
                    int stackAmount = stack.getAmount(); // get the amount of the item in that slot
                    int removeAmount = Math.min(stackAmount, amountToSell - totalSold);
                    // get the larger num between amount to sell and the stackAmount

                    if (stackAmount > removeAmount) { // if there are more items than to be sold
                        stack.setAmount(stackAmount - removeAmount); // - amount to be sold
                    } else { // if there are less items than to be sold
                        stack.setAmount(0);// remove all items in that slot
                    }

                    totalSold += removeAmount; // after removed, add the count to totalsold

                    if (totalSold >= amountToSell) {
                        break;
                    }
                }
            }

            player.sendMessage(ChatColor.GREEN + "You sold " + totalSold + " " + item.name().toLowerCase().replace("_", " ") + "(s) for " + (price * totalSold) + " coins.");

            payPlayer(player,(price * totalSold));

        } else { // Send a message to the player if they don't have the item
            player.sendMessage(ChatColor.RED + "You don't have any " + item.name().toLowerCase().replace("_", " ") + " to sell.");
        }
    }

    public static void buyItem(Player player, Material item, int price, ClickType clickType) {

        int amountToBuy = 0;
        int totalPrice = 0;
        if (clickType == ClickType.LEFT) {
            amountToBuy = 1; // Sell one item on left-click
        } else if (clickType == ClickType.RIGHT) {
            amountToBuy = 64; // Sell a full stack on right-click
        } else if (clickType == ClickType.DOUBLE_CLICK) {
            player.sendMessage(ChatColor.RED + "Slow down! Don't double click please!");
            return;
        }else {
            player.sendMessage(ChatColor.RED + "Unsupported click type.");
            return;
        }

        totalPrice = amountToBuy * price;

        if (money.GetBank(player.getUniqueId()) >= totalPrice) { // If the player have enough $$$ to buy the item/s

            int freeSpace = calculateFreeSpace(player, item);

            if (freeSpace >= amountToBuy) { // Check if there's enough space in the inventory
                playerPay(player,totalPrice);
                // Add the items to the player's inventory
                ItemStack itemStack = new ItemStack(item, amountToBuy);
                player.getInventory().addItem(itemStack);


                // Notify the player of the successful purchase
                player.sendMessage(ChatColor.GREEN + "You bought " + amountToBuy + " " + item.name().toLowerCase().replace("_", " ") + "(s) for " + totalPrice + " coins.");
            } else {
                // Notify the player that they don't have enough inventory space
                player.sendMessage(ChatColor.RED + "You don't have enough inventory space to buy " + amountToBuy + " " + item.name().toLowerCase().replace("_", " ") + "(s).");
            }

        } else { // Send a message to the player if they poor
            player.sendMessage(ChatColor.RED + "You don't have enough money to buy " + amountToBuy + " " + item.name().toLowerCase().replace("_", " ") + "(s).");
        }
    }

    private static int calculateFreeSpace(Player player, Material item) {
        int freeSpace = 0;

        for (ItemStack inventoryItem : player.getInventory().getContents()) {
            if (inventoryItem == null || inventoryItem.getType() == Material.AIR) {
                freeSpace += item.getMaxStackSize(); // Count empty slots
            } else if (inventoryItem.getType() == item) {
                freeSpace += item.getMaxStackSize() - inventoryItem.getAmount(); // Count partial stacks
            }
        }

        return freeSpace;
    }

    private static void playerPay(Player player, int amount){
        money.RemoveBank(player.getUniqueId(),amount);
    }

    private static void payPlayer(Player player, int amount){
        money.AddBank(player.getUniqueId(),amount);
    }
}
