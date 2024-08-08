package me.goost.goostserver.server.NPCs;

import me.goost.goostserver.server.NPCs.GUI.NPCWoodBuyGUI;
import me.goost.goostserver.server.NPCs.GUI.NPCWoodSellGUI;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class NPCInteraction implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager villager) {

            List<MetadataValue> metadataList = villager.getMetadata("JOB");

            if (!metadataList.isEmpty()) {
                MetadataValue job = metadataList.get(0);
                String jobName = job.asString();
                event.setCancelled(true);

                switch (jobName) {
                    case "woodbuy" -> NPCWoodBuyGUI.showWoodBuyGUI(event.getPlayer());
                    case "woodsell" -> NPCWoodSellGUI.showWoodSellGUI(event.getPlayer());

                }

            }
        }
    }
}
