package me.goost.goostserver.skill;

import me.goost.goostserver.GoostServer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class skill_DB {
    public static ItemStack _head;
    public static ItemStack _chest;
    public static ItemStack _leg;
    public static ItemStack _feet;

    public static ItemStack BLANK;

    public static void invis_sys(Player player,int time){

        BLANK =  new ItemStack(Material.AIR);

        _head = player.getInventory().getHelmet();
        _chest = player.getInventory().getChestplate();
        _leg = player.getInventory().getLeggings();
        _feet = player.getInventory().getBoots();

        player.getInventory().setHelmet(BLANK);
        player.getInventory().setChestplate(BLANK);
        player.getInventory().setLeggings(BLANK);
        player.getInventory().setBoots(BLANK);

        Plugin plugin = GoostServer.plugin;
        double delay_time = (time * 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                player.getInventory().setHelmet(_head);
                player.getInventory().setChestplate(_chest);
                player.getInventory().setLeggings(_leg);
                player.getInventory().setBoots(_feet);
            }
        }.runTaskLater(plugin, (long)delay_time);
    }
}
