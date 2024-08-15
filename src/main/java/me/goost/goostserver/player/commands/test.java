package me.goost.goostserver.player.commands;

import me.goost.goostserver.GoostServer;
import me.goost.goostserver.mob.hostile.zombie;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class test implements CommandExecutor {

    public static ItemStack testweapon;
    public static ItemStack testarmor;
    public static ItemStack testhelmet;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        Location location = player.getLocation();
        World world = player.getWorld();


        testhelmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta testHelmetMeta = testhelmet.getItemMeta();
        testHelmetMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Test Helmet");

        List<String> testHelmetlore_List = new ArrayList<String>();
        testHelmetlore_List.add(ChatColor.BOLD + "This is a test helmet");
        testHelmetlore_List.add(ChatColor.BOLD + "+50 MP");


        NamespacedKey testHelmetMpTag = new NamespacedKey(GoostServer.getPlugin(), "mp");
        testHelmetMeta.getPersistentDataContainer().set(testHelmetMpTag, PersistentDataType.FLOAT, 50f);


        testHelmetMeta.setUnbreakable(true);
        testHelmetMeta.setLore(testHelmetlore_List);
        testhelmet.setItemMeta(testHelmetMeta);






        testarmor = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta testArmorMeta = testarmor.getItemMeta();
        testArmorMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Test Armor");

        List<String> testArmorlore_List = new ArrayList<String>();
        testArmorlore_List.add(ChatColor.BOLD + "This is a test armor");
        testArmorlore_List.add(ChatColor.BOLD + "+50 MP");
        testArmorlore_List.add(ChatColor.BOLD + "+50 HP");


        NamespacedKey testArmorHpTag = new NamespacedKey(GoostServer.getPlugin(), "hp");
        testArmorMeta.getPersistentDataContainer().set(testArmorHpTag, PersistentDataType.DOUBLE, 200.0);

        NamespacedKey testArmorArTag = new NamespacedKey(GoostServer.getPlugin(), "ar");
        testArmorMeta.getPersistentDataContainer().set(testArmorArTag, PersistentDataType.INTEGER, 50);

        testArmorMeta.setUnbreakable(true);
        testArmorMeta.setLore(testArmorlore_List);
        testarmor.setItemMeta(testArmorMeta);







        testweapon = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta testWeaponMeta = testweapon.getItemMeta();
        testWeaponMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Test Weapon");

        List<String> testWeaponlore_List = new ArrayList<String>();
        testWeaponlore_List.add(ChatColor.BOLD + "This is a test weapon");
        testWeaponlore_List.add(ChatColor.BOLD + "+50 MP");
        testWeaponlore_List.add(ChatColor.BOLD + "+50 HP");


        NamespacedKey testWeaponHpTag = new NamespacedKey(GoostServer.getPlugin(), "hp");
        testWeaponMeta.getPersistentDataContainer().set(testWeaponHpTag, PersistentDataType.DOUBLE, 200.0);

        NamespacedKey testWeaponMpTag = new NamespacedKey(GoostServer.getPlugin(), "mp");
        testWeaponMeta.getPersistentDataContainer().set(testWeaponMpTag, PersistentDataType.FLOAT, 50.0f);


        testWeaponMeta.setUnbreakable(true);
        testWeaponMeta.setLore(testWeaponlore_List);
        testweapon.setItemMeta(testWeaponMeta);

        player.getInventory().addItem(testweapon);
        player.getInventory().addItem(testarmor);
        player.getInventory().addItem(testhelmet);
        return false;
    }
}













