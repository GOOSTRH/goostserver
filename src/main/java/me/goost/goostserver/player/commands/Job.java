package me.goost.goostserver.player.commands;

import me.goost.goostserver.SQLiteDB.Database;
import me.goost.goostserver.SQLiteDB.PlayerStats;
import me.goost.goostserver.SQLiteDB.dataBaseListener;
import me.goost.goostserver.player.ChooseJob;
import me.goost.goostserver.player.checkPlayer_;
import me.goost.goostserver.player.health;
import me.goost.goostserver.player.mana;
import me.goost.goostserver.skill.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Job implements CommandExecutor {

    public static HashMap<UUID, String> Job = new HashMap<>();

    private final String magicClass = "magic";
    public String getMagicClass() {return magicClass;}
    private final String assassinClass = "assassin";
    public String getAssassinClass() {return assassinClass;}
    private final String swordClass = "blademaster";
    public String getSwordClass() {return swordClass;}
    private final String tankClass = "tank";
    public String getTankClass() {return tankClass;}


    // COMMAND CODE STARTS HERE
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player player)){
            return true;
        }


        if(args.length == 0){ // if there is no arguments return and tell instructions
            player.sendMessage("Usage: /job check");
            return false;
        }

        if(ChooseJob.choosingJob.get(player.getUniqueId()) == Boolean.TRUE){
            switch (args[0]) {
                case "magic" -> {
                    // Magic for users
                    setJobForUser(player,"magic");
                    // set class
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, magicClass);
                    //update database class
                    player.setWalkSpeed(0.17f);
                    // set walk speed to XXX (normal = 0.20f
                    give_item(player,Job.get(player.getUniqueId()));
                }
                case "assassin" -> {
                    // Assassin for users
                    setJobForUser(player,"assassin");
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, assassinClass);
                    player.setWalkSpeed(0.30f);
                    give_item(player,Job.get(player.getUniqueId()));
                }
                case "blademaster" -> {
                    // Saber for users
                    setJobForUser(player,"blademaster");
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, swordClass);
                    player.setWalkSpeed(0.25f);
                    give_item(player,Job.get(player.getUniqueId()));
                }
                case "tank" -> {
                    // tank for users
                    setJobForUser(player,"tank");
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, tankClass);
                    player.setWalkSpeed(0.15f);
                    give_item(player,Job.get(player.getUniqueId()));
                }
                case "check" -> {
                    // checking player's job
                    player.sendMessage(ChatColor.WHITE+"Current Job is:"+ChatColor.BOLD+"->"+ChatColor.GOLD+Job.get(player.getUniqueId())+"<-");
                }
            }

            // give starting item kit to the Player matches with the job

        }else if(args[0].equals("remove")){ // if removing job

            if(!player.isOp()){
                player.sendMessage("You are not OP!");
                return false;
            }

            if (Job.get(player.getUniqueId()) == null) {
                player.sendMessage("You are jobless!");
            }else if (Job.get(player.getUniqueId()) != null){

                Job.put(player.getUniqueId(),null);
                // remove string from job hashmap

                checkPlayer_.checkPlayer_Constantly(player);
                // check player's health

                ChooseJob.player_.remove(player.getUniqueId());
                // remove player_ tag

                ChooseJob.choosingJob.put(player.getUniqueId(),Boolean.TRUE);
                // set choosing job state to true

                player.setWalkSpeed(0.20f);
                // set to default walk speed

                dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, null);
            }
        }else if(Objects.equals(args[0], "check")){
            // checking player's job
            player.sendMessage(ChatColor.WHITE+"Current Job is:"+ChatColor.BOLD+"->"+ChatColor.GOLD+Job.get(player.getUniqueId())+"<-");
        }
        return true;
        // end of job choosing ( chatBox's command
    }


    // SET JOB METHOD
    public static void setJob(UUID uuid, String str){
        Player player = Bukkit.getPlayer(uuid);
        assert player != null;

        switch (str) {

            case "magic" -> {
                //Magic for users
                setJobForDatabase(player,"magic");
                player.setWalkSpeed(0.17f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 255),true);
                mana.setMana(player.getUniqueId(),mana.getMaxMana(player.getUniqueId()));
            }
            case "assassin" -> {
                //Assassin for users
                setJobForDatabase(player,"assassin");
                player.setWalkSpeed(0.30f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 255),true);
                mana.setMana(player.getUniqueId(),mana.getMaxMana(player.getUniqueId()));
            }
            case "blademaster" -> {
                //Sword for users
                setJobForDatabase(player,"blademaster");
                player.setWalkSpeed(0.25f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 255),true);
                mana.setMana(player.getUniqueId(),mana.getMaxMana(player.getUniqueId()));
            }
            case "tank" -> {
                //Night for users
                setJobForDatabase(player,"tank");
                player.setWalkSpeed(0.15f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 255),true);
                mana.setMana(player.getUniqueId(),mana.getMaxMana(player.getUniqueId()));
            }

            default -> {
                player.sendMessage(str);
                player.sendMessage(ChatColor.BOLD+""+ChatColor.RED+"Command does not exist");
            }
        }
    }


    // REPEAT METHOD
    public static void repeat(){
        // repeatedly sending title to players who does not own a job
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(Job.get(player.getUniqueId()) == null){
                player.sendTitle("Choose a Class!", "Click in chat window or /showjob", 0, 10, 5);
            }
        }
    }


    // GIVE ITEM METHOD
    public static void give_item(Player player, String job){
        // gives item to the Player according to the job
        switch (job) {
            case "assassin" -> {
                player.getInventory().addItem(Items.Assassin_invis_book);
                player.getInventory().addItem(Items.Short_knife);
            }
            case "magic" ->{
                player.getInventory().addItem(Items.Short_knife);
            }
            case "blademaster" ->{
                player.getInventory().addItem(Items.Short_knife);
                player.getInventory().addItem(Items.BladeMaster_knife);
            }
            case "tank" ->{
                player.getInventory().addItem(Items.Short_knife);
            }
        }
    }


    // SET JOB for user using
    private static void setJobForUser(Player player , String Class){
        if (Job.get(player.getUniqueId()) != null) {
            player.sendMessage(ChatColor.RED+"Job is already applied!");
            return;
        } else Job.putIfAbsent(player.getUniqueId(), Class);
        setJob(player.getUniqueId(),Class);
        checkPlayer_.checkPlayer_Constantly(player);
        ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
        ChooseJob.choosingJob.put(player.getUniqueId(), Boolean.FALSE);
    }

    // SET JOB for database loading
    private static void setJobForDatabase(Player player , String Class) {

        Job.put(player.getUniqueId(), Class);
        try {
            PlayerStats stats = Database.findPlayerStatsByUUID(player.getUniqueId().toString());
            if (stats == null) { // if db does not contain the players info
                stats = new PlayerStats(
                        player.getUniqueId().toString(),
                        false, "", 0, 0, 0.0, new Date(), new Date()
                );
                stats.setClass_(Class);
                Database.setPlayerStats(stats);
            } else {
                stats.setClass_(Class);
                Database.updatePlayerStats(stats);
            }
        }
        catch(SQLException e) {
            Bukkit.getLogger().info("Class Job: SQLException "+e);
        }


        checkPlayer_.checkPlayer_Constantly(player);
        ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
        ChooseJob.choosingJob.put(player.getUniqueId(), Boolean.FALSE);

    }

}

