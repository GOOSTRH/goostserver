package me.goost.goostserver.mob.hostile;

import me.goost.goostserver.player.level;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.loot.LootTables;


public class zombie {
    private static final int health = 20;
    private static final String name = ChatColor.BLUE + "" + ChatColor.BOLD + "테스트용 좀비\nTEST";
    private static final double damage = 10;
    private static final double exp = 30;

    public static void spawnTestZombie(Player player){ // testing method
        //TextDisplay Indicator =
        //        (TextDisplay) player.getWorld().spawn(player.getLocation().add(0,1,0), TextDisplay.class, it -> {
        //            it.setText("GYASdASD");
        //        });

        Zombie TestZombie =
                 player.getWorld().spawn(player.getLocation().add(0,1,0), Zombie.class, it -> {
                    it.setCustomNameVisible(true);
                    it.setCustomName(name);
                    it.setCanPickupItems(false);
                    it.setLootTable(LootTables.valueOf("SHEEP_YELLOW").getLootTable());
                    it.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage);
                    it.setHealth(health);
                });
    }

    @EventHandler
    public void ZombieDeathEvent(EntityDeathEvent event){
        Entity victim = event.getEntity();
        Entity killer = event.getEntity().getKiller();

        if(killer instanceof Player){ // if player is the killer
            level.addExperience(killer.getUniqueId(), exp);
        }
    }

}
