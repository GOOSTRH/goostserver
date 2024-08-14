package me.goost.goostserver.server.NPCs;

import me.goost.goostserver.GoostServer;
import me.goost.goostserver.server.NPCs.GUI.*;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class NPCInteraction implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager villager) {


            NamespacedKey key = new NamespacedKey(GoostServer.getPlugin(), "JOB");
            PersistentDataContainer data = villager.getPersistentDataContainer();
            String Job = data.getOrDefault(key, PersistentDataType.STRING, "Jobless");

            if (!Job.equals("Jobless")) {
                event.setCancelled(true);
                switch (Job) {
                    case "woodbuy" -> NPCWoodBuyGUI.showWoodBuyGUI(event.getPlayer());
                    case "woodsell" -> NPCWoodSellGUI.showWoodSellGUI(event.getPlayer());
                    case "orebuy" -> NPCOreBuyGUI.showOreBuyGUI(event.getPlayer());
                    case "oresell" -> NPCOreSellGUI.showOreSellGUI(event.getPlayer());
                    case "dropbuy" -> NPCDropBuyGUI.showDropBuyGUI(event.getPlayer());
                    case "dropsell" -> NPCDropSellGUI.showDropSellGUI(event.getPlayer());
                    case "foodbuy" -> NPCFoodBuyGUI.showFoodBuyGUI(event.getPlayer());
                    case "foodsell" -> NPCFoodSellGUI.showFoodSellGUI(event.getPlayer());
                    case "boostsell" -> NPCBoostSellGUI.showBoostSellGUI(event.getPlayer());
                    //case "armorsell" -> NPCArmorSellGUI.showArmorSellGUI(event.getPlayer());
                    //case "toolsell" -> NPCToolSellGUI.showToolSellGUI(event.getPlayer());

                }
            }
        }
    }
}
