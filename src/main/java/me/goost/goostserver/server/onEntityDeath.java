package me.goost.goostserver.server;

import me.goost.goostserver.player.level;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.sql.SQLException;

public class onEntityDeath implements Listener {
    private final String specificZombieName = "Special Zombie";

    @EventHandler
    public void onEntityDeathHandler(EntityDeathEvent event) throws SQLException {
        // Check if the entity is a zombie
        if (event.getEntityType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) event.getEntity();

            // Check if the zombie has a custom name and if it matches the specific name
            if (zombie.getCustomName() != null && zombie.getCustomName().equals(ChatColor.BLUE +specificZombieName)) {
                // Check if the killer is a player
                if (zombie.getKiller() != null && zombie.getKiller() instanceof Player) {
                    Player player = zombie.getKiller();
                    // Your logic here for what to do when the specific named zombie is killed by a player
                    player.sendMessage( player.getName() + " have killed the special zombie named " + ChatColor.BLUE + specificZombieName + "!");
                    level.addExperience(player.getUniqueId(),250.0);
                }
            }
        }
    }
}
