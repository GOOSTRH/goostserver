package me.goost.goostserver.player.commands;

import me.goost.goostserver.SQLiteDB.dataBaseListener;
import me.goost.goostserver.player.ChooseJob;
import me.goost.goostserver.player.checkPlayer_;
import me.goost.goostserver.skill.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Job implements CommandExecutor {

    public static HashMap<UUID, String> Job = new HashMap<>();

    private final String magicClass = "caster";
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
                case "sword" -> {
                    // Saber for users
                    setJobForUser(player,"sword");
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
                default -> player.sendMessage("job "+args[0]+" does not exist.");
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
            player.sendMessage(ChatColor.WHITE+"Current Job is:"+ChatColor.BOLD+""+ChatColor.GOLD+Job.get(player.getUniqueId()));
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
            }
            case "assassin" -> {
                //Assassin for users
                setJobForDatabase(player,"assassin");
                player.setWalkSpeed(0.30f);
            }
            case "sword" -> {
                //Sword for users
                setJobForDatabase(player,"sword");
                player.setWalkSpeed(0.25f);
            }
            case "tank" -> {
                //Night for users
                setJobForDatabase(player,"tank");
                player.setWalkSpeed(0.15f);
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
            case "sword" ->{
                player.getInventory().addItem(Items.Short_knife);
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
        } else Job.putIfAbsent(player.getUniqueId(), Class);

        checkPlayer_.checkPlayer_Constantly(player);
        ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
        ChooseJob.choosingJob.put(player.getUniqueId(), Boolean.FALSE);
    }

    // SET JOB for database loading
    private static void setJobForDatabase(Player player , String Class){

        Job.put(player.getUniqueId(), Class);

        checkPlayer_.checkPlayer_Constantly(player);
        ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
        ChooseJob.choosingJob.put(player.getUniqueId(), Boolean.FALSE);
    }

}

