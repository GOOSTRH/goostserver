package me.goost.goostserver.server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Set;

public class spawnProtection implements Listener  {
    private static final String TARGET_WORLD = "world"; //104 319 -119 -105 116 118
    private static final Location REGION_START = new Location(Bukkit.getWorld(TARGET_WORLD), -105, -64, -119);
    private static final Location REGION_END = new Location(Bukkit.getWorld(TARGET_WORLD), 104, 319, 118);

    // List of hostile mobs in the Overworld
    private static final Set<EntityType> HOSTILE_MOBS = Set.of(
            EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER,
            EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.ENDERMAN,
            EntityType.SLIME, EntityType.WITCH, EntityType.HUSK,
            EntityType.STRAY, EntityType.DROWNED, EntityType.PHANTOM,
            EntityType.PILLAGER, EntityType.VINDICATOR, EntityType.EVOKER,
            EntityType.ILLUSIONER, EntityType.RAVAGER, EntityType.VEX,
            EntityType.SILVERFISH, EntityType.ENDERMITE, EntityType.GUARDIAN,
            EntityType.ELDER_GUARDIAN, EntityType.BLAZE, EntityType.MAGMA_CUBE,
            EntityType.WITHER_SKELETON, EntityType.GHAST, EntityType.ZOMBIFIED_PIGLIN,
            EntityType.HOGLIN, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE,
            EntityType.ZOGLIN, EntityType.SHULKER, EntityType.ZOMBIE_VILLAGER
    );

    // List of animals including bats
    private static final Set<EntityType> ANIMALS = Set.of(
            EntityType.COW, EntityType.SHEEP, EntityType.PIG,
            EntityType.CHICKEN, EntityType.BAT, EntityType.HORSE,
            EntityType.DONKEY, EntityType.MULE, EntityType.LLAMA,
            EntityType.RABBIT, EntityType.FOX, EntityType.PANDA,
            EntityType.PARROT, EntityType.CAT, EntityType.WOLF,
            EntityType.OCELOT, EntityType.TURTLE, EntityType.DOLPHIN,
            EntityType.COD, EntityType.SALMON, EntityType.TROPICAL_FISH,
            EntityType.PUFFERFISH, EntityType.SQUID, EntityType.GLOW_SQUID,
            EntityType.BEE, EntityType.MUSHROOM_COW, EntityType.POLAR_BEAR,
            EntityType.STRIDER, EntityType.AXOLOTL, EntityType.GOAT,
            EntityType.FROG, EntityType.ALLAY, EntityType.SNIFFER
    );


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.getPlayer().isOp()){return;}
        if (isInRegion(event.getPlayer().getLocation())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.getPlayer().isOp()){return;}
        if (isInRegion(event.getPlayer().getLocation())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager().isOp()){return;}
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            if (isInRegion(event.getDamager().getLocation())) {
                event.setCancelled(true);
            }
        }
        if(event.getEntity() instanceof Villager && event.getDamager() instanceof Player) {
            if (isInRegion(event.getDamager().getLocation())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        EntityType entityType = event.getEntity().getType();
        if (isInRegion(event.getLocation())
                && (HOSTILE_MOBS.contains(entityType)
                || ANIMALS.contains(entityType))) {
            event.setCancelled(true);
        }
    }

    private boolean isInRegion(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        return x >= REGION_START.getX() && x <= REGION_END.getX() &&
                y >= REGION_START.getY() && y <= REGION_END.getY() &&
                z >= REGION_START.getZ() && z <= REGION_END.getZ();
    }
}
