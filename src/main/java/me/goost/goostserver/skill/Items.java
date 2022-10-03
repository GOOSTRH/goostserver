package me.goost.goostserver.skill;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Items {
    public static ItemStack Dark_Elf_invis_book;

    public static void set_all_items(){
        set_Dark_Elf_items();
    }

    public static void set_Dark_Elf_items(){
        Dark_Elf_invis_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = Dark_Elf_invis_book.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "은신");
        Dark_Elf_invis_book.setItemMeta(meta);
    }

}
