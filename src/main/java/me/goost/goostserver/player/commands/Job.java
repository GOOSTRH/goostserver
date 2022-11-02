package me.goost.goostserver.player.commands;

import me.goost.goostserver.player.choose_class;
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
        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;

        //궁수/archer		드라군/dragon		다크엘프/dark_elf		검사/sword_man
        if(choose_class.choosing_class.get(player.getUniqueId()) == Boolean.TRUE){
            switch (args[0]) {


                case "A"://Archer for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "archer");
                    health.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.20f);
                    break;

                case "DRA"://DRAGON for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "dragon");
                    health.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.17f);
                    break;

                case "D"://Dark_elf for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "dark_elf");
                    health.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.30f);
                    break;

                case "S"://Swrod_man for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "sword_man");
                    health.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.25f);
                    break;

                case "DE"://Demon for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "demon");
                    health.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.30f);
                    break;
            }
            give_item(player,Job.get(player.getUniqueId()));
        }else{
            player.sendMessage(ChatColor.RED + "이미 직업이 적용됐습니다!");
        }

        if(args[0].equals("remove")){
            if (Job.get(player.getUniqueId()) == null) {
                player.sendMessage("직업이 없음");
            }else if (Job.get(player.getUniqueId()) != null){
                Job.remove(player.getUniqueId());
                health.check_player(player);
                choose_class.player_.remove(player.getUniqueId());
                choose_class.choosing_class.put(player.getUniqueId(),Boolean.TRUE);
                player.setWalkSpeed(0.20f);
            }
        }

        return true;//end of this thing
    }


    public static void repeat(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(Job.get(player.getUniqueId()) == null){
                player.sendTitle("직업을 선택해주세요", "", 0, 10, 0);
            }
        }
    }


    public static void give_item(Player player, String job){
        switch (job){
            case "dark_elf":
                player.getInventory().addItem(Items.Dark_Elf_invis_book);
                player.getInventory().addItem(Items.Short_knife);
                break;
            case "archer":
                player.getInventory().addItem(Items.Archer_Bow);
                player.getInventory().addItem(Items.Archer_invis_book);
                player.getInventory().addItem(Items.Short_knife);
                break;
        }
    }

}
































