package me.goost.goostserver.player.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class test implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        Location location = player.getLocation();
        World world = player.getWorld();
        /*
        BlockDisplay block =
                (BlockDisplay) Player.getWorld().spawnEntity(Player.getLocation().add(0,1,0), EntityType.BLOCK_DISPLAY);

        block.setBlock(Material.BONE_BLOCK.createBlockData());
         */
        ItemDisplay Item =
                (ItemDisplay) player.getWorld().spawnEntity(player.getLocation().add(0,1,0), EntityType.ITEM_DISPLAY);

        // Item.setItemStack(new ItemStack(Material.GOLDEN_SWORD));
        return false;
    }
}



/*
        switch (args[0]) {
            case "":
        }
*/
/*
ArmorStand indicator =
(ArmorStand) victim.getWorld().spawnEntity(victim.getLocation().add(x,y+1,z),EntityType.ARMOR_STAND);
*/













