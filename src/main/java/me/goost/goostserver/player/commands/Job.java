package me.goost.goostserver.player.commands;

import me.goost.goostserver.player.choose_class;
import me.goost.goostserver.player.show_stat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class Job implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;

        //궁수/archer		마법사/magician		다크엘프/dark_elf		검사/sword_man
        if(choose_class.choosing_class.get(player.getUniqueId()) == Boolean.TRUE){
            switch (args[0]) {

                case "A"://Archer for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "archer");
                    show_stat.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.2f);
                    break;

                case "DRA"://DRAGON for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "dragon");
                    show_stat.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.17f);
                    break;

                case "D"://Dark_elf for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "dark_elf");
                    show_stat.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.30f);

                    ItemStack invis_book = new ItemStack(Material.ENCHANTED_BOOK, 1);
                    ItemMeta meta = invis_book.getItemMeta();
                    meta.setDisplayName(ChatColor.YELLOW + "은신");
                    invis_book.setItemMeta(meta);
                    player.getInventory().addItem(invis_book);


                    break;

                case "S"://Swrod_man for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "sword_man");
                    show_stat.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.25f);
                    break;

                case "DE"://Demon for users
                    if (Job.get(player.getUniqueId()) != null) {
                        player.sendMessage("이미 직업이 적용됐습니다");
                    } else Job.putIfAbsent(player.getUniqueId(), "demon");
                    show_stat.check_player(player);
                    choose_class.player_.put(player.getUniqueId(),Boolean.TRUE);
                    choose_class.choosing_class.put(player.getUniqueId(),Boolean.FALSE);
                    player.setWalkSpeed(0.30f);
                    break;
            }
        }else if(choose_class.choosing_class.get(player.getUniqueId()) == null){
            player.sendMessage(ChatColor.RED + "백수앜ㅋㅋㅋㅋㅋㅋㅋㅋ");
        }else {
            player.sendMessage(ChatColor.RED + "You already have Job!");
            player.sendMessage(ChatColor.RED + "이미 직업이 적용됐습니다!");
        }


        return true;
    }


    public static HashMap<UUID, String> Job = new HashMap<>();

    public static void repeat(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(Job.get(player.getUniqueId()) == null){
                player.sendTitle("직업을 선택해주세요", "", 0, 10, 0);

            }else if(Job.get(player.getUniqueId()).equals("demon")){

            }

        }
    }
}































