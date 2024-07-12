package me.goost.goostserver.player.commands;

import me.goost.goostserver.SQLDB.dataBaseListener;
import me.goost.goostserver.player.ChooseJob;
import me.goost.goostserver.player.checkPlayer;
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

    private String archerClass = "archer";
    public String getArcherClass() {return archerClass;}
    private String magicClass = "caster";
    public String getMagicClass() {return magicClass;}
    private String assassinClass = "assassin";
    public String getAssassinClass() {return assassinClass;}
    private String swordClass = "saber";
    public String getSwordClass() {return swordClass;}
    private String nightClass = "demon";
    public String getNightClass() {return nightClass;}
    private String beakSuClass = "beaksu";
    public String getBeakSuClass(){return beakSuClass;}


    // COMMAND CODE STARTS HERE
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player player)){
            return true;
        }

        if(!player.isOp()){return false;} // if the player isn't OP ret false

        if(args.length == 0){ // if there is no arguments return and tell instructions
            player.sendMessage("Usage: /job check Archer Magic Assassin Sword night remove");
            return false;
        }

        if(ChooseJob.choosingJob.get(player.getUniqueId()) == Boolean.TRUE){
            switch (args[0]) {
                case "archer" -> {
                    // Archer for users
                    setJobForUser(player,"archer");
                    // set class

                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, archerClass);
                    //update database class

                    player.setWalkSpeed(0.20f);
                    // set walk speed to  normal walk speed

                }
                case "magic" -> {
                    // Magic for users
                    setJobForUser(player,"magic");
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, magicClass);
                    player.setWalkSpeed(0.17f);
                }
                case "assassin" -> {
                    // Assassin for users
                    setJobForUser(player,"assassin");
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, assassinClass);
                    player.setWalkSpeed(0.30f);
                }
                case "sword" -> {
                    // Saber for users
                    setJobForUser(player,"sword");
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, swordClass);
                    player.setWalkSpeed(0.25f);
                }
                case "night" -> {
                    // Night for users
                    setJobForUser(player,"night");
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, nightClass);
                    player.setWalkSpeed(0.30f);
                }
                case "beakSu" -> {
                    // BeakSu for non users or easter egg
                    setJobForUser(player,"beakSu");
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, swordClass);
                    player.setWalkSpeed(0.20f);
                }
                default -> player.sendMessage("존재하지않는 명령어");
            }

            give_item(player,Job.get(player.getUniqueId()));
            // give starting item kit to the Player matches with the job

        }else if(args[0].equals("remove")){ // if removing job


            if (Job.get(player.getUniqueId()) == null) {
                player.sendMessage("현제 직업이 없습니다");
            }else if (Job.get(player.getUniqueId()) != null){

                Job.remove(player.getUniqueId());
                // remove string from job hashmap

                checkPlayer.checkPlayer(player);
                // check player's health

                ChooseJob.player_.remove(player.getUniqueId());
                // remove player_ tag

                ChooseJob.choosingJob.put(player.getUniqueId(),Boolean.TRUE);
                // set choosing job state to true

                player.setWalkSpeed(0.20f);
                // set to default walk speed

            }
        }else if(Objects.equals(args[0], "check")){
            // checking player's job
            player.sendMessage(ChatColor.WHITE+"현제 직업은:"+ChatColor.BOLD+""+ChatColor.GOLD+Job.get(player.getUniqueId()));
        }
        return true;
        // end of job choosing ( chatBox's command
    }


    // SET JOB METHOD
    public static void setJob(UUID uuid, String str){
        Player player = Bukkit.getPlayer(uuid);
        assert player != null;

        switch (str) {

            case "archer" -> {
                //Archer for users
                setJobForDatabase(player,"archer");
                player.setWalkSpeed(0.20f);
            }
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
            case "night" -> {
                //Night for users
                setJobForDatabase(player,"night");
                player.setWalkSpeed(0.30f);
            }
            case "beaksu" -> {
                // BeakSu for non users or easter egg
                setJobForDatabase(player,"beaksu");
                player.setWalkSpeed(0.18f);
            }
            default -> {
                player.sendMessage(str);
                player.sendMessage(ChatColor.BOLD+""+ChatColor.RED+"존재하지않는 명령어");
            }
        }
    }


    // REPEAT METHOD
    public static void repeat(){
        // repeatedly sending title to players who does own a job
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(Job.get(player.getUniqueId()) == null){
                player.sendTitle("직업을 선택해주세요", "채팅창에서 클릭하세요", 0, 10, 5);
            }
        }
    }


    // GIVE ITEM METHOD
    public static void give_item(Player player, String job){
        // gives item to the Player according to the job
        switch (job) {
            case "archer" -> {
                player.getInventory().addItem(Items.Archer_Bow);
                player.getInventory().addItem(Items.Archer_invis_book);
                player.getInventory().addItem(Items.Short_knife);
            }
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
            case "night" ->{
                player.getInventory().addItem(Items.Short_knife);
            }
        }
    }


    // SET JOB for user using
    private static void setJobForUser(Player player , String Class){
        if (Job.get(player.getUniqueId()) != null) {
            player.sendMessage(ChatColor.RED+"이미 직업이 적용됐습니다");
        } else Job.putIfAbsent(player.getUniqueId(), Class);

        checkPlayer.checkPlayer(player);
        ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
        ChooseJob.choosingJob.put(player.getUniqueId(), Boolean.FALSE);
    }

    // SET JOB for database loading
    private static void setJobForDatabase(Player player , String Class){

        Job.put(player.getUniqueId(), Class);

        checkPlayer.checkPlayer(player);
        ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
        ChooseJob.choosingJob.put(player.getUniqueId(), Boolean.FALSE);
    }

}

