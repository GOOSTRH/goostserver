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
    public static ItemStack Archer_Bow;


    // basic skill items
    public static ItemStack Archer_invis_book;


    // skill items
    public static ItemStack Dark_Elf_invis_book;

    public static void set_all_items(){
        set_Dark_Elf_items();
        set_Archer_items();
        set_Short_knife();
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

    public static void set_Dark_Elf_items(){
        Dark_Elf_invis_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = Dark_Elf_invis_book.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "은신");

        List<String> Dark_Elf_invis_book_lore = new ArrayList<String>();
        Dark_Elf_invis_book_lore.add(ChatColor.BOLD + "은신스킬");
        Dark_Elf_invis_book_lore.add(ChatColor.BOLD + "Line 2");
        Dark_Elf_invis_book_lore.add(ChatColor.BOLD + "Line 3... etc");

        meta.setLore(Dark_Elf_invis_book_lore);
        Dark_Elf_invis_book.setItemMeta(meta);

    }

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



    }

}
