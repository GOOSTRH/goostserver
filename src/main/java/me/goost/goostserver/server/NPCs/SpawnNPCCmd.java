package me.goost.goostserver.server.NPCs;

import me.goost.goostserver.GoostServer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.jetbrains.annotations.NotNull;

public class SpawnNPCCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player player)){
            return false;
        }

        if(!player.getUniqueId().toString().equals("24b6ea58-7ef9-49f0-8458-2b0b2b4356df")){
            player.sendMessage("You can't use this command!");
            return false;
        }

        if(!player.isOp()){return false;} // if the player isn't OP return false

        if(args.length == 0){ // if there is no arguments return and tell instructions
            player.sendMessage("Usage: /npc");
            return false;
        }

        Location location = player.getLocation();
        String customName = null;

        switch (args[0]) {
            case "woodbuy" -> spawnVillager("woodbuy","Wood Purchaser",player);
            case "woodsell" -> spawnVillager("woodsell","Wood Vendor",player);
            case "orebuy" -> spawnVillager("orebuy","Ore Purchaser",player);
            case "oresell" -> spawnVillager("oresell","Ore Vendor",player);

            default -> player.sendMessage("this command does not exist.");
        }

        return false;
    }


    private static void spawnVillager(String NBTtag,  String name, Player player){

        Location location = player.getLocation();
        String customName = ChatColor.GREEN + name;

        if (location == null || location.getWorld() == null) {
            player.sendMessage("Invalid location or world.");
            return;
        }

        player.sendMessage("Spawned in " + name);
        Marker marker = (Marker) location.getWorld().spawnEntity(location, EntityType.MARKER);
        // Spawn NPC
        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);

        // Set the custom name and make it visible
        marker.addPassenger(villager);
        initializeVillager(villager, customName, player, NBTtag);

    }


    private static void initializeVillager(Villager villager, String customName, Player player, String nbtTag){
        villager.setCustomName(customName);
        villager.getLocation().setDirection(player.getLocation().getDirection());
        villager.setCollidable(false);
        villager.setAI(false);
        villager.setInvulnerable(true);
        villager.setSilent(true);
        villager.setCustomNameVisible(true);
        villager.setProfession(Villager.Profession.FLETCHER);
        villager.setAdult();
        villager.setGravity(false);
        MetadataValue metadataValue = new FixedMetadataValue(GoostServer.getPlugin(), nbtTag);
        villager.setMetadata("JOB", metadataValue);

    }



}

