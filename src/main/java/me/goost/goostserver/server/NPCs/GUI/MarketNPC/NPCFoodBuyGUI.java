package me.goost.goostserver.server.NPCs.GUI.MarketNPC;

import me.goost.goostserver.server.NPCs.itemTrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Pumpkin;

public class NPCFoodBuyGUI {
    public static int applePrice =            20;
    public static int potatoPrice =           15;
    public static int carrotPrice =           10;
    public static int watermelonPrice =      25;
    public static int beetrootPrice =        20;
    public static int netherwartPrice =      30;
    public static int eggPrice =             10;
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


    static ItemStack Filler = new ItemStack(Material.AIR);

    public static void showFoodBuyGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54,  "Food Purchaser");

        ItemStack apple =            new ItemStack(Material.APPLE);
        ItemStack potato =           new ItemStack(Material.POTATO);
        ItemStack carrot =           new ItemStack(Material.CARROT);
        ItemStack watermelon =      new ItemStack(Material.MELON_SLICE); // Watermelon slices
        ItemStack beetroot =        new ItemStack(Material.BEETROOT);
        ItemStack netherwart =      new ItemStack(Material.NETHER_WART);
        ItemStack egg =             new ItemStack(Material.EGG);
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



        itemTrade.setItemMeta(apple, "Apple",              applePrice);
        itemTrade.setItemMeta(potato, "Potato",            potatoPrice);
        itemTrade.setItemMeta(carrot, "Carrot",            carrotPrice);
        itemTrade.setItemMeta(watermelon, "Watermelon",    watermelonPrice);
        itemTrade.setItemMeta(beetroot, "Beetroot",        beetrootPrice);
        itemTrade.setItemMeta(netherwart, "Netherwart",    netherwartPrice);
        itemTrade.setItemMeta(egg, "Egg",                  eggPrice);
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



        //Put the items in the inventory
        ItemStack[] menu_items = {

                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,
                Filler,apple,potato,carrot,watermelon,beetroot,netherwart,egg,Filler,
                Filler,beef,porkchop,chicken,rabbit,driedKelp,kelp,chorusFruit,Filler,
                Filler,tropicalFish,salmon,cod,mutton,glowBerries,sweetBerries,melon,Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, pumpkin, Filler,
                Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler, Filler,

        };
        gui.setContents(menu_items);
        player.openInventory(gui);
    }
}
