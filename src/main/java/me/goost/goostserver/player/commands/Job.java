package me.goost.goostserver.player.commands;

import me.goost.goostserver.SQLDB.dataBaseListener;
import me.goost.goostserver.player.ChooseJob;
import me.goost.goostserver.player.health;
import me.goost.goostserver.skill.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Job implements CommandExecutor {

    public static HashMap<UUID, String> Job = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player player)){
            return true;
        }

        //궁수/archer		드라군/dragon		다크엘프/dark_elf		검사/sword_man
        if(ChooseJob.choosing_job.get(player.getUniqueId()) == Boolean.TRUE){
            switch (args[0]) {
                case "A" -> {//Archer for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "archer");
                    health.check_player(player);
                    ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, "A");
                    ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                    player.setWalkSpeed(0.20f);
                }
                case "DRA" -> {//DRAGON for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "dragon");
                    health.check_player(player);
                    ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, "DRA");
                    ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                    player.setWalkSpeed(0.17f);
                }
                case "D" -> {//Dark_elf for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "dark_elf");
                    health.check_player(player);
                    ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, "D");
                    ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                    player.setWalkSpeed(0.30f);
                }
                case "S" -> {//Sword_man for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "sword_man");
                    health.check_player(player);
                    ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, "S");
                    ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                    player.setWalkSpeed(0.25f);
                }
                case "DE" -> {//Demon for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "demon");
                    health.check_player(player);
                    ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, "DE");
                    ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                    player.setWalkSpeed(0.30f);
                }
                case "beak_su" -> {// 백수쉑ㅋㅋ
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "beak_su");
                    health.check_player(player);
                    ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                    dataBaseListener.updateDataBasePlayerClassAndPlayer_(player, "beak_su");
                    ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                    player.setWalkSpeed(0.18f);
                }
                default -> player.sendMessage("머라카노");
            }
            // give starting item to the Player matches with the job
            give_item(player,Job.get(player.getUniqueId()));
        }else if(args[0].equals("remove")){
            if (Job.get(player.getUniqueId()) == null) {
                player.sendMessage("직업이미없는데???");
            }else if (Job.get(player.getUniqueId()) != null){
                Job.remove(player.getUniqueId());
                health.check_player(player);
                ChooseJob.player_.remove(player.getUniqueId());
                ChooseJob.choosing_job.put(player.getUniqueId(),Boolean.TRUE);
                player.setWalkSpeed(0.20f);
            }
        }else{
            player.sendMessage(ChatColor.RED + "이미 직업이 적용됐습니다!");
        }



        return true;
        // end of job choosing ( chatBox,command
    }

    public static void setJob(UUID uuid, String str){
        Player player = Bukkit.getPlayer(uuid);
        assert player != null; // asserting if the Player is not null ( for safety
        switch (str) {
            case "A" -> {//Archer for users
                Job.put(player.getUniqueId(), "archer");
                health.check_player(player);
                ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                player.setWalkSpeed(0.20f);
            }
            case "DRA" -> {//DRAGON for users
                Job.put(player.getUniqueId(), "dragon");
                health.check_player(player);
                ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                player.setWalkSpeed(0.17f);
            }
            case "D" -> {//Dark_elf for users
                Job.put(player.getUniqueId(), "dark_elf");
                health.check_player(player);
                ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                player.setWalkSpeed(0.30f);
            }
            case "S" -> {//Swrod_man for users
                Job.put(player.getUniqueId(), "sword_man");
                health.check_player(player);
                ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                player.setWalkSpeed(0.25f);
            }
            case "DE" -> {//Demon for users
                Job.put(player.getUniqueId(), "demon");
                health.check_player(player);
                ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                player.setWalkSpeed(0.30f);
            }
            case "Beak_Su" -> {// 백수쉑ㅋㅋ
                Job.put(player.getUniqueId(), "beak_su");
                health.check_player(player);
                ChooseJob.player_.put(player.getUniqueId(), Boolean.TRUE);
                ChooseJob.choosing_job.put(player.getUniqueId(), Boolean.FALSE);
                player.setWalkSpeed(0.18f);
            }
            default -> {
                player.sendMessage(str);
                player.sendMessage("존재하지않는 명령어");
            }
        }
    }

    public static void repeat(){
        // repeatedly sending title to players who does own a job
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(Job.get(player.getUniqueId()) == null){
                player.sendTitle("직업을 선택해주세요", "", 0, 10, 0);
            }
        }
    }


    public static void give_item(Player player, String job){
        // gives item to the Player according to the job
        switch (job) {
            case "dark_elf" -> {
                player.getInventory().addItem(Items.Dark_Elf_invis_book);
                player.getInventory().addItem(Items.Short_knife);
            }
            case "archer" -> {
                player.getInventory().addItem(Items.Archer_Bow);
                player.getInventory().addItem(Items.Archer_invis_book);
                player.getInventory().addItem(Items.Short_knife);
            }
        }
    }

}
































