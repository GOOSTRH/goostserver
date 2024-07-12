package me.goost.goostserver.server;


import me.goost.goostserver.GoostServer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomRecipe {


    public static void createRecipe(){
        ItemStack bottle = new ItemStack(Material.EXPERIENCE_BOTTLE,1);

        ShapedRecipe expBottle = new ShapedRecipe(bottle);

        expBottle.shape("*%*","%B%","*%*");

        expBottle.setIngredient('*', Material.INK_SAC);
        expBottle.setIngredient('%', Material.SUGAR);
        expBottle.setIngredient('B', Material.GLASS_BOTTLE);

        GoostServer.getPlugin().getServer().addRecipe(expBottle);
    }


}
