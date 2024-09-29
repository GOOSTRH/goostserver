package me.goost.goostserver.skill;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class Items {
    // basic items
    public static ItemStack Short_knife;

    // Blade Master items
    public static ItemStack BladeMaster_knife;


    // Assassin items
    public static ItemStack Assassin_invis_book;


    public static void set_all_items(){
        set_Assassin_items();
        set_Short_knife();
        set_BladeMaster_knife();
    }

    public static void set_BladeMaster_knife(){
        BladeMaster_knife = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = BladeMaster_knife.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "BladeMaster Sword");

        List<String> lore_List = new ArrayList<String>();
        lore_List.add(ChatColor.BOLD + "Basic BladeMaster Sword");
        lore_List.add(ChatColor.BOLD + "[Right Click] to spawn a Fire Ball");

        meta.setLore(lore_List);

        BladeMaster_knife.setItemMeta(meta);
    }

    public static void set_Short_knife(){
        Short_knife = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = Short_knife.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "단검");

        List<String> lore_List = new ArrayList<String>();
        lore_List.add(ChatColor.BOLD + "기본 단검");
        lore_List.add(ChatColor.BOLD + "Line 2");
        lore_List.add(ChatColor.BOLD + "Line 3... etc");

        meta.setUnbreakable(true);

        meta.setLore(lore_List);

        Short_knife.setItemMeta(meta);
    }

    public static void set_Assassin_items(){
        Assassin_invis_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = Assassin_invis_book.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "은신");

        List<String> Dark_Elf_invis_book_lore = new ArrayList<String>();
        Dark_Elf_invis_book_lore.add(ChatColor.BOLD + "은신스킬");
        Dark_Elf_invis_book_lore.add(ChatColor.BOLD + "Line 2");
        Dark_Elf_invis_book_lore.add(ChatColor.BOLD + "Line 3... etc");

        meta.setLore(Dark_Elf_invis_book_lore);
        Assassin_invis_book.setItemMeta(meta);

    }

    /*
    public static void set_Archer_items(){
        Archer_Bow = new ItemStack(Material.BOW, 1);
        ItemMeta meta = Archer_Bow.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "기본활");

        List<String> Archer_Bow_lore = new ArrayList<String>();
        Archer_Bow_lore.add(ChatColor.BOLD + "기본 활");
        Archer_Bow_lore.add(ChatColor.BOLD + "Line 2");
        Archer_Bow_lore.add(ChatColor.BOLD + "Line 3... etc");

        meta.setLore(Archer_Bow_lore);
        Archer_Bow.setItemMeta(meta);
        // -------------------------------------------------------------------------
        Archer_invis_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta2 = Archer_invis_book.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "기본:은신");

        List<String> Archer_invis_book_lore = new ArrayList<String>();
        Archer_invis_book_lore.add(ChatColor.BOLD + "궁수:기본 은신스킬");
        Archer_invis_book_lore.add(ChatColor.BOLD + "Line 2");
        Archer_invis_book_lore.add(ChatColor.BOLD + "Line 3... etc");

        meta2.setLore(Archer_invis_book_lore);
        Archer_invis_book.setItemMeta(meta2);
        // -------------------------------------------------------------------------
        Archer_invis2_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta3 = Archer_invis2_book.getItemMeta();
        meta3.setDisplayName(ChatColor.YELLOW + "중급:은신");

        List<String> Archer_invis2_book_lore = new ArrayList<String>();
        Archer_invis2_book_lore.add(ChatColor.BOLD + "궁수:중급 은신스킬");
        Archer_invis2_book_lore.add(ChatColor.BOLD + "Line 2");
        Archer_invis2_book_lore.add(ChatColor.BOLD + "Line 3... etc");

        meta3.setLore(Archer_invis2_book_lore);
        Archer_invis2_book.setItemMeta(meta3);

    }
    */

}
