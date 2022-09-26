package me.goost.goostserver;

import me.goost.goostserver.player.money;
import me.goost.goostserver.worlds.time;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;


import java.util.Objects;
import java.util.UUID;

public class scoreboard implements Listener {


    static String show_time = "xx:xx";
    public static void show(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = manager.getNewScoreboard();

            Objective objective = scoreboard.registerNewObjective("Sidebar_Info", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName( ChatColor.BLUE + "" + ChatColor.BOLD + "미정이서버");

            Score s0 = objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "은행: ₩" + ChatColor.GREEN + money.get_bank(uuid));
            Score s1 = objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "소지금액: ₩" + ChatColor.GREEN + money.get_cash(uuid));
            Score s2 = objective.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + "플래이어수:");
            Score s3 = objective.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + "현재시간: "+ChatColor.YELLOW+show_time);
            Score s4 = objective.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + "레벨: ["+ChatColor.BLUE+"■■■■■"+ChatColor.GRAY+"■■■■■"+"]");
            Score s5 = objective.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + "레벨: ["+ChatColor.BLUE+"|||||||||||||||"+ChatColor.GRAY+"|||||||||||||||"+"]");

            s0.setScore(6);
            s1.setScore(5);
            s2.setScore(4);
            s3.setScore(3);
            s4.setScore(2);
            s5.setScore(1);


            player.setScoreboard(scoreboard);
        }
    }

    public static void always_check(){

        int tim = (int) Objects.requireNonNull(Bukkit.getWorld("world")).getTime();
        //get cur time

        if(time.time.get(tim) != null){//if cur time is in time list
            show_time = time.time.get(tim);//get cur time in xx:xx format and import it in show_time
            show();//show scoreboard
        }else if(time.time.get(tim+1) != null){
            show_time = time.time.get(tim+1);
            show();
        }
        // check time and set time



    }


}

