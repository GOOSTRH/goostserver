package me.goost.goostserver.server.NPCs.GUI;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;

public class NPCFoodSellGUI {
    public static int applePrice =            20;
    public static int potatoPrice =           15;
    public static int carrotPrice =           10;
    public static int watermelonPrice =      25;
    public static int beetrootPrice =        20;
    public static int netherwartPrice =      30;
    public static int beefPrice =            40;
    public static int porkchopPrice =        40;
    public static int chickenPrice =         35;
    public static int rabbitPrice =          50;
    public static int driedKelpPrice =       15;
    public static int kelpPrice =            10;
    public static int chorusFruitPrice =     50;
    public static int codPrice =             25;
    public static int salmonPrice =          30;
    public static int tropicalFishPrice =    35;
    public static int muttonPrice =          45;
    public static int glowBerriesPrice =     60;
    public static int sweetBerriesPrice =    15;
    public static int melonPrice =           25;
    public static int pumpkinPrice =         30;

    public static int cookiePrice =          10;
    public static int pumpkinPiePrice =      30;
    public static int goldenCarrotPrice =    50;
    public static int cookedPotatoPrice =    20;
    public static int breadPrice =           15;
    public static int steakPrice =           40;
    public static int cookedPorkchopPrice =  35;
    public static int cookedChickenPrice =   30;
    public static int cookedRabbitPrice =    50;
    public static int cookedMuttonPrice =    45;
    public static int cookedCodPrice =       25;
    public static int cookedSalmonPrice =    30;


    static ItemStack Filler = new ItemStack(Material.AIR);

    public static void showFoodSellGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54,  "Food Vendor");

        ItemStack apple =            new ItemStack(Material.APPLE);
        ItemStack potato =           new ItemStack(Material.POTATO);
        ItemStack carrot =           new ItemStack(Material.CARROT);
        ItemStack watermelon =      new ItemStack(Material.MELON_SLICE); // Watermelon slices
        ItemStack beetroot =        new ItemStack(Material.BEETROOT);
        ItemStack netherwart =      new ItemStack(Material.NETHER_WART);
        ItemStack beef =            new ItemStack(Material.BEEF); // Beets and beetroot are combined
        ItemStack porkchop =        new ItemStack(Material.PORKCHOP); // Assuming cooked porkchop
        ItemStack chicken =         new ItemStack(Material.CHICKEN); // Assuming cooked chicken
        ItemStack rabbit =          new ItemStack(Material.RABBIT); // Assuming cooked rabbit
        ItemStack driedKelp =       new ItemStack(Material.DRIED_KELP);
        ItemStack kelp =            new ItemStack(Material.KELP);
        ItemStack chorusFruit =     new ItemStack(Material.CHORUS_FRUIT);
        ItemStack cod =             new ItemStack(Material.COD); // Assuming raw cod
        ItemStack salmon =          new ItemStack(Material.SALMON); // Assuming raw salmon
        ItemStack tropicalFish =   new ItemStack(Material.TROPICAL_FISH);
        ItemStack mutton =          new ItemStack(Material.MUTTON); // Assuming cooked mutton
        ItemStack glowBerries =     new ItemStack(Material.GLOW_BERRIES);
        ItemStack sweetBerries =    new ItemStack(Material.SWEET_BERRIES);
        ItemStack melon =           new ItemStack(Material.MELON); // Melon slices
        ItemStack pumpkin =         new ItemStack(Material.PUMPKIN); // Assuming whole pumpkin

        ItemStack cookie =          new ItemStack(Material.COOKIE);
        ItemStack pumpkinPie =      new ItemStack(Material.PUMPKIN_PIE);
        ItemStack goldenCarrot =    new ItemStack(Material.GOLDEN_CARROT);
        ItemStack cookedPotato =    new ItemStack(Material.BAKED_POTATO);
        ItemStack bread =           new ItemStack(Material.BREAD);
        ItemStack steak =           new ItemStack(Material.COOKED_BEEF);
        ItemStack cookedPorkchop =  new ItemStack(Material.COOKED_PORKCHOP);
        ItemStack cookedChicken =   new ItemStack(Material.COOKED_CHICKEN);
        ItemStack cookedRabbit =    new ItemStack(Material.COOKED_RABBIT);
        ItemStack cookedMutton =    new ItemStack(Material.COOKED_MUTTON);
        ItemStack cookedCod =       new ItemStack(Material.COOKED_COD);
        ItemStack cookedSalmon =    new ItemStack(Material.COOKED_SALMON);


        itemTrade.setItemMeta(apple, "Apple",              applePrice);
        itemTrade.setItemMeta(potato, "Potato",            potatoPrice);
        itemTrade.setItemMeta(carrot, "Carrot",            carrotPrice);
        itemTrade.setItemMeta(watermelon, "Watermelon",    watermelonPrice);
        itemTrade.setItemMeta(beetroot, "Beetroot",        beetrootPrice);
        itemTrade.setItemMeta(netherwart, "Netherwart",    netherwartPrice);
        itemTrade.setItemMeta(beef, "Beet",                beefPrice); // Same as beetroot
        itemTrade.setItemMeta(porkchop, "Porkchop",        porkchopPrice);
        itemTrade.setItemMeta(chicken, "Chicken",          chickenPrice);
        itemTrade.setItemMeta(rabbit, "Rabbit",            rabbitPrice);
        itemTrade.setItemMeta(driedKelp, "Dried Kelp",     driedKelpPrice);
        itemTrade.setItemMeta(kelp, "Kelp",                kelpPrice);
        itemTrade.setItemMeta(chorusFruit, "Chorus Fruit", chorusFruitPrice);
        itemTrade.setItemMeta(cod, "Cod",                  codPrice);
        itemTrade.setItemMeta(salmon, "Salmon",            salmonPrice);
        itemTrade.setItemMeta(tropicalFish, "Tropical Fish", tropicalFishPrice);
        itemTrade.setItemMeta(mutton, "Mutton",            muttonPrice);
        itemTrade.setItemMeta(glowBerries, "Glow Berries", glowBerriesPrice);
        itemTrade.setItemMeta(sweetBerries, "Sweet Berries", sweetBerriesPrice);
        itemTrade.setItemMeta(melon, "Melon",              melonPrice);
        itemTrade.setItemMeta(pumpkin, "Pumpkin",          pumpkinPrice);

        itemTrade.setItemMeta(cookie,          "Cookie",            cookiePrice);
        itemTrade.setItemMeta(pumpkinPie,      "Pumpkin Pie",        pumpkinPiePrice);
        itemTrade.setItemMeta(goldenCarrot,    "Golden Carrot",      goldenCarrotPrice);
        itemTrade.setItemMeta(cookedPotato,    "Cooked Potato",      cookedPotatoPrice);
        itemTrade.setItemMeta(bread,           "Bread",              breadPrice);
        itemTrade.setItemMeta(steak,           "Steak",              steakPrice);
        itemTrade.setItemMeta(cookedPorkchop,  "Cooked Porkchop",    cookedPorkchopPrice);
        itemTrade.setItemMeta(cookedChicken,   "Cooked Chicken",     cookedChickenPrice);
        itemTrade.setItemMeta(cookedRabbit,    "Cooked Rabbit",      cookedRabbitPrice);
        itemTrade.setItemMeta(cookedMutton,    "Cooked Mutton",      cookedMuttonPrice);
        itemTrade.setItemMeta(cookedCod,       "Cooked Cod",         cookedCodPrice);
        itemTrade.setItemMeta(cookedSalmon,    "Cooked Salmon",      cookedSalmonPrice);

        //Put the items in the inventory
        ItemStack[] menu_items = {

                apple,potato,carrot,watermelon,beetroot,Filler,sweetBerries,glowBerries, Filler,
                bread,cookedPotato, goldenCarrot,pumpkinPie, cookie,Filler,chorusFruit,netherwart,Filler,
                beef,porkchop,chicken,rabbit,mutton,Filler,pumpkin,kelp,Filler,
                steak,cookedPorkchop,cookedChicken,cookedRabbit,cookedMutton,Filler, melon,driedKelp,Filler,
                tropicalFish,salmon,cookedSalmon,cod,cookedCod, Filler, Filler, Filler, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler

        };
        gui.setContents(menu_items);
        player.openInventory(gui);
    }
}
