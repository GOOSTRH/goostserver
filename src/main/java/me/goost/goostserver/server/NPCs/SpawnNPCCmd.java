package me.goost.goostserver.server.NPCs;

import me.goost.goostserver.GoostServer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
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


        switch (args[0]) {
            case "woodbuy" -> spawnVillager("woodbuy","Wood Purchaser",player, Villager.Profession.FLETCHER);
            case "woodsell" -> spawnVillager("woodsell","Wood Vendor",player, Villager.Profession.FLETCHER);
            case "orebuy" -> spawnVillager("orebuy","Ore Purchaser",player,Villager.Profession.SHEPHERD);
            case "oresell" -> spawnVillager("oresell","Ore Vendor",player, Villager.Profession.SHEPHERD);
            case "dropbuy" -> spawnVillager("dropbuy","Mob Drop Purchaser",player, Villager.Profession.BUTCHER);
            case "dropsell" -> spawnVillager("dropsell","Mob Drop Vendor",player, Villager.Profession.BUTCHER);
            case "foodbuy" -> spawnVillager("foodbuy","Food Purchaser",player, Villager.Profession.FARMER);
            case "foodsell" -> spawnVillager("foodsell","Food Vendor",player, Villager.Profession.FARMER);
            case "boostsell" -> spawnVillager("boostsell","Boost Vendor",player, Villager.Profession.CLERIC);
            case "armorsell" -> spawnVillager("armorsell","Armor Vendor",player, Villager.Profession.TOOLSMITH);
            case "toolsell" -> spawnVillager("toolsell","Tool Vendor",player, Villager.Profession.WEAPONSMITH);

            default -> player.sendMessage("this command does not exist.");
        }

        return false;
    }


    private static void spawnVillager(String NBTtag,  String name, Player player,Villager.Profession type){

        Location location = player.getLocation();
        String customName = ChatColor.GREEN + name;

        if (location.getWorld() == null) {
            player.sendMessage("Invalid location or world.");
            return;
        }

        player.sendMessage("Spawned in " + name);
        Marker marker = (Marker) location.getWorld().spawnEntity(location, EntityType.MARKER);
        // Spawn NPC
        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);

        // Set the custom name and make it visible
        marker.addPassenger(villager);

        initializeVillager(villager, customName, player, NBTtag, type);

    }


    private static void initializeVillager(Villager villager, String customName, Player player, String nbtTag,Villager.Profession type){
        villager.setCustomName(customName);
        villager.getLocation().setDirection(player.getLocation().getDirection());
        villager.setCollidable(false);
        villager.setAI(false);
        villager.setInvulnerable(true);
        villager.setSilent(true);
        villager.setCustomNameVisible(true);
        villager.setProfession(type);
        villager.setAdult();
        villager.setGravity(false);
        NamespacedKey key = new NamespacedKey(GoostServer.getPlugin(), "JOB");
        PersistentDataContainer data = villager.getPersistentDataContainer();
        data.set(key, PersistentDataType.STRING, nbtTag);




    }



}

